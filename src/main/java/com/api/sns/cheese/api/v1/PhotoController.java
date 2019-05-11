package com.api.sns.cheese.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.form.PhotoReportForm;
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
	public Page<PhotoResource> findList(String loginId, @SortDefault.SortDefaults({
			@SortDefault(sort = "photo_id", direction = Direction.DESC) }) Pageable pageable) {
		// 写真一覧を取得する
		return photoService.findList(loginId, pageable);
	}

	/**
	 * 写真通報
	 *
	 * @param cd
	 *            コード
	 */
	@PostMapping("/{cd}/report")
	@ResponseStatus(HttpStatus.OK)
	public boolean report(@PathVariable("cd") String cd, @RequestBody @Validated PhotoReportForm form) {
		// 写真を通報する
		return photoService.report(cd, form.getReason());
	}
}
