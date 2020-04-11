package com.api.sns.cheese.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.sns.cheese.resources.PhotoResource;

/**
 * ブックマークサービス
 */
public interface BookmarkService {

	/**
	 * ブックマーク一覧を取得する
	 *
	 * @param pageable
	 *            ページ情報
	 * @param ブックマーク一覧
	 */
	public Page<PhotoResource> findList(Pageable pageable);

	/**
	 * ブックマークを登録する
	 *
	 * @param photoCd
	 *            写真コード
	 * @return ブックマーク情報
	 */
	public PhotoResource create(String photoCd);

	/**
	 * ブックマークを削除する
	 *
	 * @param id
	 *            ブックマークID
	 */
	public boolean remove(Long id);
}
