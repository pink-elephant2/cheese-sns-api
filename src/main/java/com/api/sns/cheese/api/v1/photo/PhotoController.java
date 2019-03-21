package com.api.sns.cheese.api.v1.photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
 * 写真API
 */
@RestController
@RequestMapping("/api/v1/photo")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	/**
	 * 写真取得
	 *
	 * @param cd
	 *            コード
	 */
	@GetMapping("/{cd}")
	@ResponseStatus(HttpStatus.OK)
	public PhotoResource find(@PathVariable("cd") String cd) {
		// 写真を取得する
		return photoService.find(cd);
	}

	/**
	 * 写真一覧取得
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PhotoResource> findList(String loginId, Pageable pageable) {
		// 写真一覧を取得する TODO Pageを返却
		return photoService.findList(loginId, pageable);
	}

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

}
