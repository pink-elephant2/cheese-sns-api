package com.api.sns.cheese.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.form.VideoCommentForm;
import com.api.sns.cheese.form.VideoForm;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.VideoResource;
import com.api.sns.cheese.service.VideoService;

/**
 * (認証必須)動画API TODO インターセプタで自分のログインIDかチェックする
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/user/{loginId}/video")
public class UserVideoController {

	@Autowired
	private VideoService videoService;

	/**
	 * 動画登録
	 *
	 * @param form
	 *            動画フォーム
	 * @return 動画情報
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VideoResource create(@Validated VideoForm form) {
		// 動画を登録し、登録内容を返却する
		return videoService.create(form);
	}

	/**
	 * 動画にいいねをする
	 *
	 * @param cd
	 *            コード
	 */
	@PostMapping("/{cd}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean like(@PathVariable("cd") String cd) {
		// いいねをする
		return videoService.like(cd, true);
	}

	/**
	 * 動画のいいねを解除する
	 *
	 * @param cd
	 *            コード
	 */
	@PostMapping("/{cd}/dislike")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean dislike(@PathVariable("cd") String cd) {
		// いいねを解除する
		return videoService.like(cd, false);
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
	@PostMapping("/{cd}/comment")
	@ResponseStatus(HttpStatus.CREATED)
	public CommentResource comment(@PathVariable("cd") String cd, @RequestBody @Validated VideoCommentForm form) {
		// コメントを登録し、登録内容を返却する
		return videoService.comment(cd, form.getComment());
	}

	/**
	 * コメントにいいねをする
	 *
	 * @param cd
	 *            コード
	 * @param commentCd
	 *            コメントコード
	 */
	@PostMapping("/{cd}/comment/{commentCd}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean likeComment(@PathVariable("cd") String cd, @PathVariable("commentCd") String commentCd) {
		// いいねをする
		return videoService.likeComment(cd, commentCd, true);
	}

	/**
	 * コメントのいいねを解除する
	 *
	 * @param cd
	 *            コード
	 * @param commentCd
	 *            コメントコード
	 */
	@PostMapping("/{cd}/comment/{commentCd}/dislike")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean dislikeComment(@PathVariable("cd") String cd, @PathVariable("commentCd") String commentCd) {
		// いいねを解除する
		return videoService.likeComment(cd, commentCd, false);
	}

	/**
	 * 動画を削除する
	 *
	 * @param cd
	 *            コード
	 */
	@PostMapping("/{cd}/remove")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean remove(@PathVariable("cd") String cd) {
		// 動画を削除する
		return videoService.remove(cd);
	}
}
