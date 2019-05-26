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

import com.api.sns.cheese.form.PhotoCommentForm;
import com.api.sns.cheese.form.PhotoForm;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.PhotoResource;
import com.api.sns.cheese.service.PhotoService;

/**
 * (認証必須)写真API TODO インターセプタで自分のログインIDかチェックする
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/user/{loginId}/photo")
public class UserPhotoController {

	@Autowired
	private PhotoService photoService;

	/**
	 * 写真登録
	 *
	 * @param form
	 *            写真フォーム
	 * @return 写真情報
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PhotoResource create(@Validated PhotoForm form) {
		// 写真を登録し、登録内容を返却する
		return photoService.create(form);
	}

	/**
	 * 写真にいいねをする
	 *
	 * @param cd
	 *            コード
	 */
	@PostMapping("/{cd}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean like(@PathVariable("cd") String cd) {
		// いいねをする
		return photoService.like(cd, true);
	}

	/**
	 * 写真のいいねを解除する
	 *
	 * @param cd
	 *            コード
	 */
	@PostMapping("/{cd}/dislike")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean dislike(@PathVariable("cd") String cd) {
		// いいねを解除する
		return photoService.like(cd, false);
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
	public CommentResource comment(@PathVariable("cd") String cd, @RequestBody @Validated PhotoCommentForm form) {
		// コメントを登録し、登録内容を返却する
		return photoService.comment(cd, form.getComment());
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
		return photoService.likeComment(cd, commentCd, true);
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
		return photoService.likeComment(cd, commentCd, false);
	}

	/**
	 * 写真を削除する
	 *
	 * @param cd
	 *            コード
	 */
	@PostMapping("/{cd}/remove")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean remove(@PathVariable("cd") String cd) {
		// 写真を削除する
		return photoService.remove(cd);
	}
}
