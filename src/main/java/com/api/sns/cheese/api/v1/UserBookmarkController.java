package com.api.sns.cheese.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.form.BookmarkForm;
import com.api.sns.cheese.resources.PhotoResource;
import com.api.sns.cheese.service.BookmarkService;

/**
 * (認証必須)ブックマークAPI TODO インターセプタで自分のログインIDかチェックする
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/user/{loginId}/bookmark")
public class UserBookmarkController {

	@Autowired
	private BookmarkService bookmarkService;

	/**
	 * ブックマーク一覧取得
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<PhotoResource> findList(String loginId, @SortDefault.SortDefaults({
			@SortDefault(sort = "bookmark_id", direction = Direction.DESC) }) Pageable pageable) {
		// ブックマーク一覧を取得する
		return bookmarkService.findList(pageable);
	}

	/**
	 * ブックマーク登録
	 *
	 * @param form
	 *            ブックマークフォーム
	 * @return ブックマーク情報
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PhotoResource create(@Validated BookmarkForm form) {
		// ブックマークを登録し、登録内容を返却する
		return bookmarkService.create(form.getPhotoCd());
	}

	/**
	 * ブックマークを削除する
	 *
	 * @param id
	 *            ブックマークID
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public boolean remove(@PathVariable("id") Long id) {
		// ブックマークを削除する
		return bookmarkService.remove(id);
	}
}
