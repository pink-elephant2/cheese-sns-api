package com.api.sns.cheese.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.aop.SessionInfoContextHolder;
import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.domain.TActivity;
import com.api.sns.cheese.domain.TActivityExample;
import com.api.sns.cheese.domain.TBanReport;
import com.api.sns.cheese.domain.TBookmark;
import com.api.sns.cheese.domain.TBookmarkExample;
import com.api.sns.cheese.domain.TFollow;
import com.api.sns.cheese.domain.TFollowExample;
import com.api.sns.cheese.domain.TPhoto;
import com.api.sns.cheese.domain.TPhotoComment;
import com.api.sns.cheese.domain.TPhotoCommentExample;
import com.api.sns.cheese.domain.TPhotoCommentLike;
import com.api.sns.cheese.domain.TPhotoCommentLikeExample;
import com.api.sns.cheese.domain.TPhotoExample;
import com.api.sns.cheese.domain.TPhotoExample.Criteria;
import com.api.sns.cheese.domain.TPhotoLike;
import com.api.sns.cheese.domain.TPhotoLikeExample;
import com.api.sns.cheese.domain.TTag;
import com.api.sns.cheese.domain.TTagExample;
import com.api.sns.cheese.domain.TTagPhoto;
import com.api.sns.cheese.enums.ActivityTypeEnum;
import com.api.sns.cheese.enums.DocumentTypeEnum;
import com.api.sns.cheese.enums.ReportReasonEnum;
import com.api.sns.cheese.enums.ReportTargetEnum;
import com.api.sns.cheese.exception.NotFoundException;
import com.api.sns.cheese.form.PhotoForm;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.repository.TActivityRepository;
import com.api.sns.cheese.repository.TBanReportRepository;
import com.api.sns.cheese.repository.TBookmarkRepository;
import com.api.sns.cheese.repository.TFollowRepository;
import com.api.sns.cheese.repository.TPhotoCommentLikeRepository;
import com.api.sns.cheese.repository.TPhotoCommentRepository;
import com.api.sns.cheese.repository.TPhotoLikeRepository;
import com.api.sns.cheese.repository.TPhotoRepository;
import com.api.sns.cheese.repository.TTagPhotoRepository;
import com.api.sns.cheese.repository.TTagRepository;
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
	private TTagRepository tTagRepository;

	@Autowired
	private TTagPhotoRepository tTagPhotoRepository;

	@Autowired
	private TAccountRepository tAccountRepository;

	@Autowired
	private TFollowRepository tFollowRepository;

	@Autowired
	private TActivityRepository tActivityRepository;

	@Autowired
	private TBanReportRepository tBanReportRepository;

	@Autowired
	private TBookmarkRepository tBookmarkRepository;

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
			throw new NotFoundException("写真が存在しません");
		}

		PhotoResource resource = mapper.map(photo, PhotoResource.class);

		// TODO 投稿ユーザー View または キャッシュ
		resource.setAccount(mapper.map(tAccountRepository.findOneById(photo.getAccountId()), AccountResource.class));

		// ログインユーザー
		Integer accountId = SessionInfoContextHolder.isAuthenticated()
				? SessionInfoContextHolder.getSessionInfo().getAccountId()
				: null;

		// 自分がいいねしているか
		TPhotoLikeExample likeExample = new TPhotoLikeExample();
		if (SessionInfoContextHolder.isAuthenticated()) {
			likeExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId()).andAccountIdEqualTo(accountId);
			TPhotoLike photoLike = tPhotoLikeRepository.findOneBy(likeExample);
			resource.setLike(photoLike != null && CommonConst.DeletedFlag.OFF.equals(photoLike.getDeleted()));
		}

		// いいね件数 TODO 性能改善
		likeExample.clear();
		likeExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		resource.setLikeCount(tPhotoLikeRepository.countBy(likeExample));

		// コメント
		TPhotoCommentExample commentExample = new TPhotoCommentExample();
		commentExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TPhotoComment> commentList = tPhotoCommentRepository.findAllBy(commentExample);
		if (!commentList.isEmpty()) {
			resource.setComments(commentList.stream().map(tPhotoComment -> {
				CommentResource commentResource = mapper.map(tPhotoComment, CommentResource.class);

				// TODO 投稿ユーザー View または キャッシュ
				commentResource.setAccount(mapper.map(tAccountRepository.findOneById(tPhotoComment.getAccountId()),
						AccountResource.class));

				// 自分がコメントにいいねをしているか TODO 性能改善
				if (SessionInfoContextHolder.isAuthenticated()) {
					TPhotoCommentLikeExample commentLikeExample = new TPhotoCommentLikeExample();
					commentLikeExample.createCriteria().andAccountIdEqualTo(accountId)
							.andPhotoIdEqualTo(photo.getPhotoId()).andCommentIdEqualTo(tPhotoComment.getCommentId());
					TPhotoCommentLike photoCommentLike = tPhotoCommentLikeRepository.findOneBy(commentLikeExample);
					commentResource.setLike(photoCommentLike != null
							&& CommonConst.DeletedFlag.OFF.equals(photoCommentLike.getDeleted()));
				}

				return commentResource;
			}).collect(Collectors.toList()));
		}

		// 自分がブックマークしているか
		if (SessionInfoContextHolder.isAuthenticated()) {
			TBookmarkExample bookmarkExample = new TBookmarkExample();
			bookmarkExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId()).andAccountIdEqualTo(accountId)
					.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
			TBookmark tBookmark = tBookmarkRepository.findOneBy(bookmarkExample);
			resource.setBookmark(tBookmark != null);
		}
		return resource;
	}

	/**
	 * 写真一覧を取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param keyword
	 *            検索ワード
	 * @param tag
	 *            検索タグ
	 * @param pageable
	 *            ページ情報
	 * @param 写真一覧
	 */
	@Override
	public Page<PhotoResource> findList(String loginId, String keyword, String tag, Pageable pageable) {
		TPhotoExample example = new TPhotoExample();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(loginId)) {
			// 指定されたユーザーの写真一覧
			Integer accountId = tAccountRepository.findOneByLoginId(loginId).getAccountId();
			criteria.andAccountIdEqualTo(accountId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		} else {
			criteria.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		}

		// 検索
		if (!StringUtils.isEmpty(keyword)) {
			criteria.andCaptionLike("%" + keyword + "%"); // TODO 性能改善
		}

		// タグ検索
		if (!StringUtils.isEmpty(tag)) {
			List<Long> photoIdList = findPhotoIdByTag(tag);
			if (!photoIdList.isEmpty()) {
				criteria.andPhotoIdIn(photoIdList);
			} else {
				return Page.empty(pageable);
			}
		}

		example.or(criteria);
		return tPhotoRepository.findPageBy(example, pageable).map(tPhoto -> {
			PhotoResource resource = mapper.map(tPhoto, PhotoResource.class);

			// TODO 投稿ユーザー View または キャッシュ
			resource.setAccount(
					mapper.map(tAccountRepository.findOneById(tPhoto.getAccountId()), AccountResource.class));

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
			String fileName = cd + form.getUpfile().getOriginalFilename()
					.substring(form.getUpfile().getOriginalFilename().lastIndexOf("."));
			String filePath = s3Service.upload(DocumentTypeEnum.PHOTO, fileName, form.getUpfile());

			// レコード追加
			TPhoto photo = mapper.map(form, TPhoto.class);
			photo.setPhotoCd(cd);
			photo.setImgUrl(filePath);
			photo.setAccountId(SessionInfoContextHolder.getSessionInfo().getAccountId());
			tPhotoRepository.createReturnId(photo); // photoIdがセットされる

			// TODO コードが重複した場合、ランダム文字列を再生成してリトライする

			// タグを登録
			saveTag(photo.getPhotoId(), form.getTags());

			// フォローワーにアクティビティ登録
			createActivity(photo.getPhotoId());

			// 戻り値
			PhotoResource resource = mapper.map(photo, PhotoResource.class);
			return resource;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * タグを登録する
	 *
	 * @param photoId
	 * @param tagList
	 */
	private void saveTag(Long photoId, List<String> tagList) {
		// タグを取得
		List<TTag> tTagList;
		if (tagList == null || tagList.isEmpty()) {
			tagList = new ArrayList<>();
			tTagList = new ArrayList<>();
		} else {
			TTagExample tTagExample = new TTagExample();
			tTagExample.createCriteria().andTagNameIn(tagList).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
			tTagList = tTagRepository.findAllBy(tTagExample);
		}

		// なければ登録
		tagList.stream().forEach(tag -> {
			if (tTagList.stream().filter(t -> t.getTagName().equals(tag)).count() == 0) {
				TTag tTag = new TTag();
				tTag.setTagName(tag);
				tTag.setDeleted(CommonConst.DeletedFlag.OFF);
				tTagRepository.createReturnId(tTag); // tagIdがセットされる

				tTagList.add(tTag);
			}
		});

		// タグを取得
		List<TTagPhoto> photoTagList = tTagPhotoRepository.findAllByPhotoId(photoId);
		if (photoTagList.size() < tagList.size()) {
			// 新規登録分が多い場合
			int n = tagList.size() - photoTagList.size();
			for (int i = 0; i < n; i++) {
				TTagPhoto tagPhoto = new TTagPhoto();
				tagPhoto.setPhotoId(photoId);
				photoTagList.add(tagPhoto);
			}
		} else if (photoTagList.size() > tagList.size()) {
			// 既存分が多い場合
			for (int i = photoTagList.size(); i >= tagList.size(); i--) {
				photoTagList.get(i - 1).setDeleted(CommonConst.DeletedFlag.ON);
				tagList.add(null);
			}
		}

		// 登録/更新
		for (int i = 0; i < photoTagList.size(); i++) {
			TTagPhoto tagPhoto = photoTagList.get(i);
			tagPhoto.setTagId(tTagList.get(i).getTagId());

			if (tagPhoto.getTagPhotoId() == null) {
				tTagPhotoRepository.create(tagPhoto);
			} else {
				tTagPhotoRepository.updatePartially(tagPhoto);
			}
		}
	}

	/**
	 * タグから画像IDを検索する
	 * @param tag
	 * @return photoId
	 */
	private List<Long> findPhotoIdByTag(String tag) {
		// TODO 性能改善

		// タグ取得
		TTagExample example = new TTagExample();
		example.createCriteria().andTagNameEqualTo(tag).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<Long> tagIdList = tTagRepository.findAllBy(example).stream().map(TTag::getTagId)
				.collect(Collectors.toList());

		List<Long> photoIdList = new ArrayList<>();
		if (!tagIdList.isEmpty()) {
			// 画像ID取得
			List<TTagPhoto> tagPhotoList = tTagPhotoRepository.findAllByTagId(tagIdList);
			photoIdList = tagPhotoList.stream().map(TTagPhoto::getPhotoId).collect(Collectors.toList());
		}
		return photoIdList;
	}

	/**
	 * フォローワーにアクティビティ登録する
	 * TODO バッチで行う
	 *
	 * @param photoId
	 */
	private void createActivity(Long photoId) {
		TFollowExample followExample = new TFollowExample();
		followExample.createCriteria()
				.andFollowAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TFollow> followList = tFollowRepository.findAllBy(followExample);
		if (!followList.isEmpty()) {
			TAccountExample accountExample = new TAccountExample();
			accountExample.createCriteria()
					.andAccountIdIn(followList.stream().map(TFollow::getAccountId).collect(Collectors.toList()));
			tAccountRepository.findAllBy(accountExample).stream().forEach(tAccount -> {
				TActivity tActivity = new TActivity();
				tActivity.setAccountId(tAccount.getAccountId());
				tActivity.setActivityType(ActivityTypeEnum.NEW_POST);
				tActivity.setPhotoId(photoId);

				// レコード登録
				tActivityRepository.create(tActivity);
			});
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

		// ログインユーザ
		Integer accountId = SessionInfoContextHolder.getSessionInfo().getAccountId();

		// いいねを取得
		TPhotoLikeExample likeExample = new TPhotoLikeExample();
		likeExample.createCriteria().andPhotoIdEqualTo(photo.getPhotoId()).andAccountIdEqualTo(accountId);
		TPhotoLike photoLike = tPhotoLikeRepository.findOneBy(likeExample);

		boolean ret;
		if (photoLike == null) {
			// レコード登録
			TPhotoLike entity = new TPhotoLike();
			entity.setPhotoId(photo.getPhotoId());
			entity.setAccountId(accountId);
			entity.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			ret = tPhotoLikeRepository.create(entity);
		} else {
			// レコード更新
			photoLike.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			ret = tPhotoLikeRepository.updatePartially(photoLike);
		}

		if (ret && !photo.getAccountId().equals(accountId)) {
			// アクティビティを登録する
			TActivityExample example = new TActivityExample();
			example.createCriteria().andAccountIdEqualTo(photo.getAccountId())
					.andActivityTypeEqualTo(ActivityTypeEnum.LIKE)
					.andPhotoIdEqualTo(photo.getPhotoId()).andFollowAccountIdEqualTo(accountId)
					.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
			TActivity tActivity = tActivityRepository.findOneBy(example);
			if (tActivity == null) {
				TActivity activity = new TActivity();
				activity.setAccountId(photo.getAccountId());
				activity.setActivityType(ActivityTypeEnum.LIKE);
				activity.setPhotoId(photo.getPhotoId());
				activity.setFollowAccountId(accountId);

				// レコード登録
				ret = tActivityRepository.create(activity);
			}
		}
		return ret;
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

		// ログインユーザ
		Integer accountId = SessionInfoContextHolder.getSessionInfo().getAccountId();

		// レコード登録
		TPhotoComment entity = new TPhotoComment();
		entity.setCommentCd(RandomStringUtils.randomAlphanumeric(10));
		entity.setAccountId(accountId);
		entity.setPhotoId(photo.getPhotoId());
		entity.setContent(comment);
		boolean ret = tPhotoCommentRepository.create(entity);

		if (ret && !photo.getAccountId().equals(accountId)) {
			// アクティビティを登録する
			TActivityExample example = new TActivityExample();
			example.createCriteria().andAccountIdEqualTo(photo.getAccountId())
					.andActivityTypeEqualTo(ActivityTypeEnum.COMMENT)
					.andPhotoIdEqualTo(photo.getPhotoId()).andFollowAccountIdEqualTo(accountId)
					.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
			TActivity tActivity = tActivityRepository.findOneBy(example);
			if (tActivity == null) {
				TActivity activity = new TActivity();
				activity.setAccountId(photo.getAccountId());
				activity.setActivityType(ActivityTypeEnum.COMMENT);
				activity.setPhotoId(photo.getPhotoId());
				activity.setFollowAccountId(accountId);

				// レコード登録
				ret = tActivityRepository.create(activity);
			}
		}

		// 戻り値
		CommentResource resource = mapper.map(entity, CommentResource.class);

		// TODO 投稿ユーザー View または キャッシュ
		resource.setAccount(mapper.map(tAccountRepository.findOneById(accountId), AccountResource.class));

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

		// ログインユーザ
		Integer accountId = SessionInfoContextHolder.getSessionInfo().getAccountId();

		// コメントを取得
		TPhotoCommentExample commentExample = new TPhotoCommentExample();
		commentExample.createCriteria().andCommentCdEqualTo(commentCd).andPhotoIdEqualTo(photo.getPhotoId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TPhotoComment photoComment = tPhotoCommentRepository.findOneBy(commentExample);

		// コメントいいねを取得
		TPhotoCommentLikeExample commentLikeExample = new TPhotoCommentLikeExample();
		commentLikeExample.createCriteria().andAccountIdEqualTo(accountId).andPhotoIdEqualTo(photo.getPhotoId())
				.andCommentIdEqualTo(photoComment.getCommentId());
		TPhotoCommentLike photoCommentLike = tPhotoCommentLikeRepository.findOneBy(commentLikeExample);

		boolean ret;
		if (photoCommentLike == null) {
			// レコード登録
			TPhotoCommentLike entity = new TPhotoCommentLike();
			entity.setAccountId(accountId);
			entity.setPhotoId(photo.getPhotoId());
			entity.setCommentId(photoComment.getCommentId());
			entity.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			ret = tPhotoCommentLikeRepository.create(entity);
		} else {
			// レコード更新
			photoCommentLike.setDeleted(isLike ? CommonConst.DeletedFlag.OFF : CommonConst.DeletedFlag.ON);
			ret = tPhotoCommentLikeRepository.updatePartially(photoCommentLike);
		}

		if (ret && isLike && !photo.getAccountId().equals(accountId)) {
			// アクティビティを登録する
			TActivityExample example = new TActivityExample();
			example.createCriteria().andAccountIdEqualTo(photo.getAccountId())
					.andActivityTypeEqualTo(ActivityTypeEnum.COMMENT_LIKE)
					.andPhotoIdEqualTo(photo.getPhotoId()).andFollowAccountIdEqualTo(accountId)
					.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
			TActivity tActivity = tActivityRepository.findOneBy(example);
			if (tActivity == null) {
				TActivity activity = new TActivity();
				activity.setAccountId(photo.getAccountId());
				activity.setActivityType(ActivityTypeEnum.COMMENT_LIKE);
				activity.setPhotoId(photo.getPhotoId());
				activity.setFollowAccountId(accountId);

				// レコード登録
				ret = tActivityRepository.create(activity);
			}
		}
		return ret;
	}

	/**
	 * 写真を通報する
	 *
	 * @param cd
	 *            コード
	 * @param reason
	 *            理由
	 */
	@Override
	public boolean report(String cd, ReportReasonEnum reason) {
		// 写真を取得
		TPhoto photo = tPhotoRepository.findOneByCd(cd);

		// レコード登録
		TBanReport entity = new TBanReport();
		entity.setReportTarget(ReportTargetEnum.PHOTO);
		entity.setReason(reason);
		entity.setPhotoId(photo.getPhotoId());
		entity.setReadFlag(false);
		entity.setDoneFlag(false);
		return tBanReportRepository.create(entity);
	}

	/**
	 * 写真を削除する
	 *
	 * @param cd
	 *            コード
	 */
	@Override
	public boolean remove(String cd) {
		// 写真を取得
		TPhoto photo = tPhotoRepository.findOneByCd(cd);

		// 論理削除
		TPhoto entity = new TPhoto();
		entity.setDeleted(CommonConst.DeletedFlag.ON);

		TPhotoExample example = new TPhotoExample();
		example.createCriteria().andPhotoIdEqualTo(photo.getPhotoId())
				.andAccountIdEqualTo(SessionInfoContextHolder.getSessionInfo().getAccountId())
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return BooleanUtils.toBoolean(tPhotoRepository.updatePartiallyBy(entity, example));
	}

	/**
	 * サジェスト用キーワードを取得する
	 *
	 * @param keyword
	 *            検索文字列
	 */
	@Override
	public List<String> findSuggest(@NotNull String keyword) {
		// TODO 性能改善
		TTagExample example = new TTagExample();
		example.createCriteria().andTagNameLike("%" + keyword + "%").andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return tTagRepository.findAllBy(example).stream().map(tag -> {
			return "#" + tag.getTagName();
		}).collect(Collectors.toList());
	}
}
