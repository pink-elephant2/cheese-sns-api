package com.api.sns.cheese.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccountKey;
import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoComment;
import com.api.sns.cheese.domain.TPhotoCommentExample;
import com.api.sns.cheese.domain.TPhotoCommentLike;
import com.api.sns.cheese.domain.TPhotoCommentLikeExample;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.domain.TPhotoLike;
import com.api.sns.cheese.domain.TPhotoLikeExample;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.form.PhotoForm;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TPhotoCommentLikeRepository;
import com.api.sns.cheese.repository.TPhotoCommentRepository;
import com.api.sns.cheese.repository.TPhotoLikeRepository;
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
	private TPhotoLikeRepository tPhotoLikeRepository;

	@Autowired
	private TPhotoCommentRepository tPhotoCommentRepository;

	@Autowired
	private TPhotoCommentLikeRepository tPhotoCommentLikeRepository;

	@Autowired
	private TAccountRepository tAccountRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private Mapper mapper;

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

		// 自分がいいねしているか
		TPhotoLikeExample likeExample = new TPhotoLikeExample();
		Integer accountId = 1; // TODO ログインユーザ
		likeExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId()).andAccountIdEqualTo(accountId);
		TPhotoLike photoLike = tPhotoLikeRepository.findOneBy(likeExample);
		resource.setLike(photoLike != null && CommonConst.DeletedFlag.OFF.equals(photoLike.getDeleted()));

		// いいね件数 TODO 性能改善
		likeExample.clear();
		likeExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		// TODO テーブル定義 型変更
		resource.setLikeCount((int) tPhotoLikeRepository.countBy(likeExample));

		// コメント
		TPhotoCommentExample commentExample = new TPhotoCommentExample();
		commentExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TPhotoComment> commentList = tPhotoCommentRepository.findAllBy(commentExample);
		if (!commentList.isEmpty()) {
			resource.setComments(commentList.stream().map(tPhotoComment -> {
				CommentResource commentResource = mapper.map(tPhotoComment, CommentResource.class);

				// TODO 投稿ユーザー View または キャッシュ
				TAccountKey accountKey = new TAccountKey();
				accountKey.setAccountId(tPhotoComment.getAccountId());
				commentResource.setAccount(mapper.map(tAccountRepository.findOneBy(accountKey), AccountResource.class));

				// 自分がコメントにいいねをしているか
				TPhotoCommentLikeExample commentLikeExample = new TPhotoCommentLikeExample();
				commentLikeExample.createCriteria().andAccountIdEqualTo(accountId).andPhotoIdEqualTo(photo.getPhotoId())
						.andCommentIdEqualTo(tPhotoComment.getCommentId());
				TPhotoCommentLike photoCommentLike = tPhotoCommentLikeRepository.findOneBy(commentLikeExample);
				commentResource.setLike(
						photoCommentLike != null && CommonConst.DeletedFlag.OFF.equals(photoCommentLike.getDeleted()));

				return commentResource;
			}).collect(Collectors.toList()));
		}

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
		if (!StringUtils.isEmpty(loginId)) {
			Integer accountId = 1; // TODO View作成
			example.createCriteria().andAccountIdEqualTo(accountId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		}
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
		// 写真を取得
		TPhoto photo = tPhotoRepository.findOneByCd(cd);

		// いいねを取得
		TPhotoLikeExample likeExample = new TPhotoLikeExample();
		Integer accountId = 1; // TODO ログインユーザ
		likeExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId()).andAccountIdEqualTo(accountId);
		TPhotoLike photoLike = tPhotoLikeRepository.findOneBy(likeExample);

		if (photoLike == null) {
			// レコード登録
			TPhotoLike entity = new TPhotoLike();
			entity.setPhotoId(photo.getPhotoId());
			entity.setAccountId(accountId);
			entity.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			// TODO 共通項目は親クラスで設定する
			entity.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
			entity.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);
			return tPhotoLikeRepository.create(entity);
		} else {
			// レコード更新
			photoLike.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			return tPhotoLikeRepository.updatePartially(photoLike);
		}
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
		// 写真を取得
		TPhoto photo = tPhotoRepository.findOneByCd(cd);

		// レコード登録
		TPhotoComment entity = new TPhotoComment();
		entity.setCommentCd(RandomStringUtils.randomAlphanumeric(10));
		entity.setAccountId(1); // TODO ログインユーザ
		entity.setPhotoId(photo.getPhotoId());
		entity.setContent(comment);
		// TODO 共通項目は親クラスで設定する
		entity.setDeleted(CommonConst.DeletedFlag.OFF);
		entity.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
		entity.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);
		tPhotoCommentRepository.create(entity);

		// 戻り値
		CommentResource resource = mapper.map(entity, CommentResource.class);

		// TODO 投稿ユーザー View または キャッシュ
		TAccountKey key = new TAccountKey();
		key.setAccountId(1); // TODO ログインユーザ
		resource.setAccount(mapper.map(tAccountRepository.findOneBy(key), AccountResource.class));

		return resource;
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
		// 写真を取得
		TPhoto photo = tPhotoRepository.findOneByCd(cd);

		Integer accountId = 1; // TODO ログインユーザ

		// コメントを取得
		TPhotoCommentExample commentExample = new TPhotoCommentExample();
		commentExample.createCriteria().andCommentCdEqualTo(commentCd).andAccountIdEqualTo(accountId)
				.andPhotoIdEqualTo(photo.getPhotoId());
		TPhotoComment photoComment = tPhotoCommentRepository.findOneBy(commentExample);

		// コメントいいねを取得
		TPhotoCommentLikeExample commentLikeExample = new TPhotoCommentLikeExample();
		commentLikeExample.createCriteria().andAccountIdEqualTo(accountId).andPhotoIdEqualTo(photo.getPhotoId())
				.andCommentIdEqualTo(photoComment.getCommentId());
		TPhotoCommentLike photoCommentLike = tPhotoCommentLikeRepository.findOneBy(commentLikeExample);

		if (photoCommentLike == null) {
			// レコード登録
			TPhotoCommentLike entity = new TPhotoCommentLike();
			entity.setAccountId(accountId);
			entity.setPhotoId(photo.getPhotoId());
			entity.setCommentId(photoComment.getCommentId());
			entity.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			// TODO 共通項目は親クラスで設定する
			entity.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
			entity.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);
			return tPhotoCommentLikeRepository.create(entity);
		} else {
			// レコード更新
			photoCommentLike.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			return tPhotoCommentLikeRepository.updatePartially(photoCommentLike);
		}
	}
}
