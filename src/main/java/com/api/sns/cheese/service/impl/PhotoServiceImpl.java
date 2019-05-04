package com.api.sns.cheese.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccountKey;
import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.form.PhotoForm;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TPhotoRepository;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.PhotoResource;
import com.api.sns.cheese.service.PhotoService;
import com.api.sns.cheese.service.S3Service;

/**
 * 写真サービス
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private TPhotoRepository tPhotoRepository;

	@Autowired
	private TAccountRepository tAccountRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private Mapper mapper;

	/** アカウントテストデータ */
	private List<AccountResource> accountList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new AccountResource(Long.valueOf(1), "my_melody", "マイメロディ", "おはよう♪　あさごはん　ちゃんとたべた〜？　いっしゅうかん　がんばろうね♪",
					"assets/images/my_melody.png", null, null, "Melody_Mariland", null, false),
			// テストデータ2
			new AccountResource(Long.valueOf(2), "ki_ri_mi", "KIRIMIちゃん", "ラブ！サーモン！>°))))◁",
					"assets/images/ki_ri_mi.png", null, null, "kirimi_sanrio", null, true),
			// テストデータ3
			new AccountResource(Long.valueOf(1), "gudetama", "ぐでたま", "だるい", "assets/images/gudetama.png", null, null,
					"gudetama_sanrio", null, false)));

	/** コメントテストデータ */
	private List<CommentResource> commentList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new CommentResource(Long.valueOf(1), "comment1", "おいしそう😍", new Date(), accountList.get(0), true),
			// テストデータ2
			new CommentResource(Long.valueOf(2), "comment2", "作るのだるい", new Date(), accountList.get(2), false)));

	/**
	 * 写真を取得する
	 *
	 * @param cd
	 *            コード
	 * @return 写真情報
	 */
	@Override
	public PhotoResource find(String cd) {
		TPhotoExample example = new TPhotoExample();
		example.createCriteria().andPhotoCdEqualTo(cd).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TPhoto photo = tPhotoRepository.findOneBy(example);
		if (photo == null) {
			// TODO 404を返す
			// throw new NotFoundException("写真が存在しません");
		}

		PhotoResource resource = new PhotoResource(photo.getPhotoId(), photo.getPhotoCd());
		mapper.map(photo, resource);

		// TODO 投稿ユーザー View または キャッシュ
		TAccountKey key = new TAccountKey();
		key.setAccountId(photo.getAccountId());
		resource.setAccount(mapper.map(tAccountRepository.findOneBy(key), AccountResource.class));

		return resource;
	}

	/**
	 * 写真一覧を取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @param 写真一覧
	 */
	@Override
	public Page<PhotoResource> findList(String loginId, Pageable pageable) {
		TPhotoExample example = new TPhotoExample();
		Integer accountId = 1; // TODO View作成
		example.createCriteria().andAccountIdEqualTo(accountId);
		return tPhotoRepository.findPageBy(example, pageable).map(tPhoto -> {
			PhotoResource resource = new PhotoResource(tPhoto.getPhotoId(), tPhoto.getPhotoCd());
			mapper.map(tPhoto, resource);

			// TODO 投稿ユーザー View または キャッシュ
			TAccountKey key = new TAccountKey();
			key.setAccountId(tPhoto.getAccountId());
			resource.setAccount(mapper.map(tAccountRepository.findOneBy(key), AccountResource.class));

			return resource;
		});
	}

	/**
	 * 写真を登録する
	 *
	 * @param form
	 *            写真フォーム
	 * @return 写真情報
	 */
	@Override
	public PhotoResource create(PhotoForm form) {
		try {
			// 新規写真
			String cd = RandomStringUtils.randomAlphanumeric(10);

			// S3に保存、URLを設定する
			String fileName = cd + ".png"; // TODO ファイル拡張子
			String filePath = s3Service.upload(DocumentTypeEnum.PHOTO, fileName, form.getUpfile());

			// レコード追加
			TPhoto photo = mapper.map(form, TPhoto.class);
			photo.setPhotoCd(cd);
			photo.setImgUrl(filePath);
			photo.setAccountId(1); // TODO ログインユーザ
			// TODO 共通項目は親クラスで設定する
			photo.setDeleted(CommonConst.DeletedFlag.OFF);
			photo.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
			photo.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);
			tPhotoRepository.create(photo);

			// TODO コードが重複した場合、ランダム文字列を再生成してリトライする

			Long id = tPhotoRepository.lastInsertId();

			// 戻り値
			PhotoResource resource = new PhotoResource(id, cd);
			mapper.map(photo, resource);
			return resource;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 写真にいいねをする/解除する
	 *
	 * @param cd
	 *            コード
	 * @param isLike
	 */
	@Override
	public boolean like(String cd, boolean isLike) {
		find(cd).setLike(isLike);
		find(cd).setLikeCount(find(cd).getLikeCount() + (isLike ? 1 : -1));
		return true;
	}

	/**
	 * コメントする
	 *
	 * @param cd
	 *            コード
	 * @param comment
	 *            コメント
	 * @return コメント情報
	 */
	@Override
	public CommentResource comment(String cd, String comment) {
		// コメント情報生成
		Long id = Long.valueOf(commentList.size() + 1);
		String commentCd = "comment" + id;
		CommentResource commentResource = new CommentResource(id, commentCd, comment, new Date(), accountList.get(0),
				false);

		// レコード追加
		find(cd).getComments().add(commentResource);
		return commentResource;
	}

	/**
	 * コメントにいいねをする/解除する
	 *
	 * @param cd
	 *            コード
	 * @param commentCd
	 *            コメントコード
	 */
	@Override
	public boolean likeComment(String cd, String commentCd, boolean isLike) {
		find(cd).getComments().stream().filter(comment -> comment.getCd().equals(commentCd)).findFirst().get()
				.setLike(isLike);
		return true;
	}
}
