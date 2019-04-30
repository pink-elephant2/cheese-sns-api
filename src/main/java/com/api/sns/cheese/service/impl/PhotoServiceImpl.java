package com.api.sns.cheese.service.impl;

import static java.util.Comparator.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.form.PhotoForm;
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

	/** 写真テストデータ **/
	private List<PhotoResource> photoList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new PhotoResource(Long.valueOf(1), "test1", "【フォンデュ＆ラクレット】 とろ～り、びよーん♪のおいしいチーズ料理",
					"assets/images/sample-1.jpg", new Date(), accountList.get(1), 1, true, commentList),
			// テストデータ2
			new PhotoResource(Long.valueOf(2), "test2", "おうちで簡単！SNSで話題のもちもちとろ～りチーズレシピ♡", "assets/images/sample-2.jpg",
					new Date(), accountList.get(0), 1000, true, Arrays.asList()),
			// テストデータ3
			new PhotoResource(Long.valueOf(3), "test3", "", "assets/images/sample-3.jpg", new Date(),
					accountList.get(1), 1000, false, Arrays.asList()),
			// テストデータ4
			new PhotoResource(Long.valueOf(4), "test4", "", "assets/images/sample-4.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList()),
			// テストデータ5
			new PhotoResource(Long.valueOf(5), "test5", "", "assets/images/sample-5.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList()),
			// テストデータ6
			new PhotoResource(Long.valueOf(6), "test6", "", "assets/images/sample-6.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList()),
			// テストデータ7
			new PhotoResource(Long.valueOf(7), "test7", "", "assets/images/sample-7.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList())));

	/**
	 * 写真を取得する
	 *
	 * @param cd
	 *            コード
	 * @return 写真情報
	 */
	@Override
	public PhotoResource find(String cd) {
		return photoList.stream().filter(photo -> ObjectUtils.nullSafeEquals(cd, photo.getCode())).findFirst().get();
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
		List<PhotoResource> filterdPhotoList = photoList;

		// ログインIDで絞る
		if (loginId != null) {
			filterdPhotoList = photoList.stream().filter(photo -> photo.getAccount().getLoginId().equals(loginId))
					.collect(Collectors.toList());
		}

		// ID降順ソート
		filterdPhotoList = filterdPhotoList.stream().sorted(comparing(PhotoResource::getId).reversed())
				.collect(Collectors.toList());

		// ページで絞る
		int fromIndex = pageable.getPageNumber() * pageable.getPageSize();
		int toIndex = Math.min(fromIndex + pageable.getPageSize(), filterdPhotoList.size());
		List<PhotoResource> subList = filterdPhotoList.subList(fromIndex, toIndex);
		return new PageImpl<>(subList, pageable, filterdPhotoList.size());
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
		// 新規写真
		Long id = Long.valueOf(photoList.size() + 1);	// TODO AUTO_INCREMENT
		String cd = "test" + id; // TODO ランダム文字列生成

		try {
			// S3に保存、URLを設定する
			String filePath = s3Service.upload(DocumentTypeEnum.PHOTO, form.getUpfile());

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
