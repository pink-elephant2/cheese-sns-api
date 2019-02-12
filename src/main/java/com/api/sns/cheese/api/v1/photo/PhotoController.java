package com.api.sns.cheese.api.v1.photo;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.sns.cheese.resources.PhotoResource;

@RestController
@RequestMapping("/api/v1/photo")
public class PhotoController {

	@GetMapping("/{id}")
	@ResponseBody
	public PhotoResource find(@PathVariable("id") Long id) {
		// TODO 検索
		PhotoResource photo = new PhotoResource(
				id,
				"test1",
				"とろけるチ~ズ",
				"/aaa/bbb/ccc.png",
				new Date()
		);

		return photo;
	}
}
