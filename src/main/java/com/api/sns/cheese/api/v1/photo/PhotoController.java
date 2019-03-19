package com.api.sns.cheese.api.v1.photo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.PhotoResource;

/**
 * 写真API
 */
@RestController
@RequestMapping("/api/v1/photo")
public class PhotoController {

	/**
	 * 写真取得
	 *
	 * @param cd コード
	 * @return
	 */
	@GetMapping("/{cd}")
	@ResponseStatus(HttpStatus.OK)
	public PhotoResource find(@PathVariable("cd") String cd) {
		List<CommentResource> comments = new ArrayList<>();
		comments.add(new CommentResource(Long.valueOf(1), "aaa", "おいしそう😍", new Date(), account, true));
		photo.setComments(comments );
		return photo;
	}

	/**
	 * 写真一覧取得
	 *
	 * @param loginId ログインID
	 * @param pageable ページ情報
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<PhotoResource> findList(String loginId, Pageable pageable) {
		List<PhotoResource> photoList = new ArrayList<>();
		photoList.add(photo);

		// TODO Pageを返却
		return photoList;
	}

	/**
	 * 写真登録
	 *
	 * @param 写真フォーム
	 * @return 写真情報
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PhotoResource create(@Validated PhotoForm form) {

		// 登録した写真情報を返却
		return photo;
	}

	/**
	 * 写真にいいねをする
	 *
	 * @param cd コード
	 */
	@PostMapping("/{cd}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean like(@PathVariable("cd") String cd) {
		return true;
	}

	/**
	 * 写真のいいねを解除する
	 *
	 * @param cd コード
	 */
	@PostMapping("/{cd}/dislike")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean dislike(@PathVariable("cd") String cd) {
		return true;
	}

	/**
	 * コメントする
	 *
	 * @param cd コード
	 * @param comment コメント
	 * @return コメント情報
	 */
	@PostMapping("/{cd}/comment")
	@ResponseStatus(HttpStatus.CREATED)
	public CommentResource comment(@PathVariable("cd") String cd, @RequestBody @Validated PhotoCommentForm form) {

		// 登録したコメント情報を返却
		return new CommentResource(Long.valueOf(1), "aaa", form.getComment(), new Date(), account, true);
	}

	/**
	 * コメントにいいねをする
	 *
	 * @param cd コード
	 * @param commentCd コメントコード
	 */
	@PostMapping("/{cd}/comment/{commentCd}/like")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean likeComment(@PathVariable("cd") String cd, @PathVariable("commentCd") String commentCd) {
		return true;
	}

	/**
	 * コメントのいいねを解除する
	 *
	 * @param cd コード
	 * @param commentCd コメントコード
	 */
	@PostMapping("/{cd}/comment/{commentCd}/dislike")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean dislikeComment(@PathVariable("cd") String cd, @PathVariable("commentCd") String commentCd) {
		return true;
	}

	/** アカウントテストデータ */
	private AccountResource account = new AccountResource(
			Long.valueOf(1),
			"my_melody",
			"マイメロディ",
			"おはよう♪　あさごはん　ちゃんとたべた〜？　いっしゅうかん　がんばろうね♪",
			null,
			null,
			"Melody_Mariland",
			null);

	/** 写真テストデータ */ // TODO 検索
	private PhotoResource photo = new PhotoResource(
			Long.valueOf(1),
			"test1",
			"とろけるチ~ズ",
			"assets/images/sample-1.jpg",
			new Date(),
			account,
			0,
			false,
			new ArrayList<>());

}
