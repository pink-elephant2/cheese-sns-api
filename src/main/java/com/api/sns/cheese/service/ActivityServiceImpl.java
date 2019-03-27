package com.api.sns.cheese.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.enums.ActivityTypeEnum;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.resources.ActivityResource;
import com.api.sns.cheese.resources.CommentResource;
import com.api.sns.cheese.resources.PhotoResource;

/**
 * アクティビティサービス
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	/** アカウントテストデータ */
	private List<AccountResource> accountList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new AccountResource(Long.valueOf(1), "my_melody", "マイメロディ", "おはよう♪　あさごはん　ちゃんとたべた〜？　いっしゅうかん　がんばろうね♪",
					"assets/images/my_melody.png", null, null, "Melody_Mariland", null, false),
			// テストデータ2
			new AccountResource(Long.valueOf(2), "ki_ri_mi", "KIRIMIちゃん", "ラブ！サーモン！>°))))◁",
					"assets/images/ki_ri_mi.png", null, null, "kirimi_sanrio", null, true),
			// テストデータ3
			new AccountResource(Long.valueOf(1), "gudetama", "ぐでたま", "だるい", "assets/images/gudetama.png", null, null,
					"gudetama_sanrio", null, false)));

	/** コメントテストデータ */
	private List<CommentResource> commentList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new CommentResource(Long.valueOf(1), "comment1", "おいしそう😍", new Date(), accountList.get(0), true),
			// テストデータ2
			new CommentResource(Long.valueOf(2), "comment2", "作るのだるい", new Date(), accountList.get(2), false)));

	/** 写真テストデータ **/
	private List<PhotoResource> photoList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new PhotoResource(Long.valueOf(1), "test1", "【フォンデュ＆ラクレット】 とろ～り、びよーん♪のおいしいチーズ料理",
					"assets/images/sample-1.jpg", new Date(), accountList.get(1), 1, true, commentList),
			// テストデータ2
			new PhotoResource(Long.valueOf(2), "test2", "おうちで簡単！SNSで話題のもちもちとろ～りチーズレシピ♡", "assets/images/sample-2.jpg",
					new Date(), accountList.get(0), 1000, true, Arrays.asList()),
			// テストデータ3
			new PhotoResource(Long.valueOf(3), "test3", "", "assets/images/sample-3.jpg", new Date(),
					accountList.get(1), 1000, false, Arrays.asList()),
			// テストデータ4
			new PhotoResource(Long.valueOf(4), "test4", "", "assets/images/sample-4.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList()),
			// テストデータ5
			new PhotoResource(Long.valueOf(5), "test5", "", "assets/images/sample-5.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList()),
			// テストデータ6
			new PhotoResource(Long.valueOf(6), "test6", "", "assets/images/sample-6.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList()),
			// テストデータ7
			new PhotoResource(Long.valueOf(7), "test7", "", "assets/images/sample-7.jpg", new Date(),
					accountList.get(1), 0, false, Arrays.asList())));

	/** アクティビティテストデータ */
	private List<ActivityResource> activityList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new ActivityResource(ActivityTypeEnum.COMMENT, photoList.get(0), new Date(), photoList.get(0).account),
			// テストデータ2
			new ActivityResource(ActivityTypeEnum.FOLLOW, null, new Date(), accountList.get(1)),
			// テストデータ3
			new ActivityResource(ActivityTypeEnum.LIKE, photoList.get(1), new Date(), photoList.get(2).account),
			// テストデータ4
			new ActivityResource(ActivityTypeEnum.NEW_POST, photoList.get(2), new Date(), photoList.get(2).account)));

	/**
	 * フォロー中のアクティビティを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @return アクティビティ情報
	 */
	@Override
	public Page<ActivityResource> findFollowing(String loginId, Pageable pageable) {
		// ページで絞る
		int fromIndex = pageable.getPageNumber() * pageable.getPageSize();
		int toIndex = Math.min(fromIndex + pageable.getPageSize(), activityList.size());
		List<ActivityResource> subList = activityList.subList(fromIndex, toIndex);
		return new PageImpl<>(subList, pageable, activityList.size());
	}

	/**
	 * 自分に対するアクティビティを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @param pageable
	 *            ページ情報
	 * @return アクティビティ情報
	 */
	@Override
	public Page<ActivityResource> findMe(String loginId, Pageable pageable) {
		// ページで絞る
		int fromIndex = pageable.getPageNumber() * pageable.getPageSize();
		int toIndex = Math.min(fromIndex + pageable.getPageSize(), activityList.size());
		List<ActivityResource> subList = activityList.subList(fromIndex, toIndex);
		return new PageImpl<>(subList, pageable, activityList.size());
	}
}
