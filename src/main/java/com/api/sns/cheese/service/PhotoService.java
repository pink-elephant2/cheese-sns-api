package com.api.sns.cheese.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.sns.cheese.form.PhotoForm;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.PhotoResource;

/**
 * 写真サービス
 */
public interface PhotoService {

	/**
	 * 写真を取得する
	 *
	 * @param cd
	 *            コード
	 * @return 写真情報
	 */
	public PhotoResource find(String cd);

	/**
	 * 写真一覧を取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @param 写真一覧
	 */
	public Page<PhotoResource> findList(String loginId, Pageable pageable);

	/**
	 * 写真を登録する
	 *
	 * @param form
	 *            写真フォーム
	 * @return 写真情報
	 */
	public PhotoResource create(PhotoForm form);

	/**
	 * 写真にいいねをする/解除する
	 *
	 * @param cd
	 *            コード
	 * @param isLike
	 */
	public boolean like(String cd, boolean isLike);

	/**
	 * コメントする
	 *
	 * @param cd
	 *            コード
	 * @param comment
	 *            コメント
	 * @return コメント情報
	 */
	public CommentResource comment(String cd, String comment);

	/**
	 * コメントにいいねをする/解除する
	 *
	 * @param cd
	 *            コード
	 * @param commentCd
	 *            コメントコード
	 */
	public boolean likeComment(String cd, String commentCd, boolean isLike);
}
