package com.api.sns.cheese.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.sns.cheese.enums.ReportReasonEnum;
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
	 * @param keyword
	 *            検索ワード
	 * @param tag
	 *            検索タグ
	 * @param pageable
	 *            ページ情報
	 * @param 写真一覧
	 */
	public Page<PhotoResource> findList(String loginId, String keyword, String tag, Pageable pageable);

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

	/**
	 * 写真を通報する
	 *
	 * @param cd
	 *            コード
	 * @param reason
	 *            理由
	 */
	public boolean report(String cd, ReportReasonEnum reason);

	/**
	 * 写真を削除する
	 *
	 * @param cd
	 *            コード
	 */
	public boolean remove(String cd);

	/**
	 * サジェスト用キーワードを取得する
	 *
	 * @param keyword
	 *            検索文字列
	 */
	public List<String> findSuggest(@NotNull String keyword);
}
