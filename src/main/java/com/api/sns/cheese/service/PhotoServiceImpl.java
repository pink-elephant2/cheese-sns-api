package com.api.sns.cheese.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.form.PhotoForm;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.PhotoResource;

/**
 * 写真サービス
 */
@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

	/** アカウントテストデータ */
	private AccountResource account = new AccountResource(Long.valueOf(1), "my_melody", "マイメロディ",
			"おはよう♪　あさごはん　ちゃんとたべた〜？　いっしゅうかん　がんばろうね♪", null, null, "Melody_Mariland", null);

	/** 写真テストデータ */ // TODO 検索
	private PhotoResource photo = new PhotoResource(Long.valueOf(1), "test1", "とろけるチ~ズ", "assets/images/sample-1.jpg",
			new Date(), account, 0, false, new ArrayList<>());

	/**
	 * 写真を取得する
	 *
	 * @param cd
	 *            コード
	 * @return 写真情報
	 */
	public PhotoResource find(String cd) {
		List<CommentResource> comments = new ArrayList<>();
		comments.add(new CommentResource(Long.valueOf(1), "aaa", "おいしそう😍", new Date(), account, true));
		photo.setComments(comments);
		return photo;
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
	public Page<PhotoResource> findList(String loginId, Pageable pageable) {
		List<PhotoResource> photoList = new ArrayList<>();
		photoList.add(photo);
		return new PageImpl<>(photoList);
	}

	/**
	 * 写真を登録する
	 *
	 * @param form
	 *            写真フォーム
	 * @return 写真情報
	 */
	public PhotoResource create(PhotoForm form) {
		return photo;
	}

	/**
	 * 写真にいいねをする/解除する
	 *
	 * @param cd
	 *            コード
	 * @param isLike
	 */
	public boolean like(String cd, boolean isLike) {
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
	public CommentResource comment(String cd, String comment) {
		return new CommentResource(Long.valueOf(1), "aaa", comment, new Date(), account, true);
	}

	/**
	 * コメントにいいねをする/解除する
	 *
	 * @param cd
	 *            コード
	 * @param commentCd
	 *            コメントコード
	 */
	public boolean likeComment(String cd, String commentCd, boolean isLike) {
		return true;
	}
}
