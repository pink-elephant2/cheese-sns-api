package com.api.sns.cheese.api.v1.photo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<PhotoResource> findList(String loginId, Pageable pageable) {
		List<PhotoResource> photoList = new ArrayList<>();
		photoList.add(photo);

		return photoList;
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
