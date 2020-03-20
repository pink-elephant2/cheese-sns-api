package com.api.sns.cheese.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.sns.cheese.enums.ReportReasonEnum;
import com.api.sns.cheese.form.VideoForm;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.VideoResource;

/**
 * 動画サービス
 */
public interface VideoService {

	/**
	 * 動画を取得する
	 *
	 * @param cd
	 *            コード
	 * @return 動画情報
	 */
	public VideoResource find(String cd);

	/**
	 * 動画一覧を取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @param 動画一覧
	 */
	public Page<VideoResource> findList(String loginId, Pageable pageable);

	/**
	 * 動画を登録する
	 *
	 * @param form
	 *            動画フォーム
	 * @return 動画情報
	 */
	public VideoResource create(VideoForm form);

	/**
	 * 動画にいいねをする/解除する
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

	/**
	 * 動画を通報する
	 *
	 * @param cd
	 *            コード
	 * @param reason
	 *            理由
	 */
	public boolean report(String cd, ReportReasonEnum reason);

	/**
	 * 動画を削除する
	 *
	 * @param cd
	 *            コード
	 */
	public boolean remove(String cd);
}
