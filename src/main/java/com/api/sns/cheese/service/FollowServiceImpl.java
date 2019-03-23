package com.api.sns.cheese.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.resources.AccountResource;

/**
 * フォローサービス
 */
@Service
@Transactional
public class FollowServiceImpl implements FollowService {

	/** アカウントテストデータ */
	private List<AccountResource> accountList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new AccountResource(Long.valueOf(1), "my_melody", "マイメロディ", "おはよう♪　あさごはん　ちゃんとたべた〜？　いっしゅうかん　がんばろうね♪",
					"assets/images/my_melody.png", null, null, "Melody_Mariland", null),
			// テストデータ2
			new AccountResource(Long.valueOf(2), "ki_ri_mi", "KIRIMIちゃん", "ラブ！サーモン！>°))))◁",
					"assets/images/ki_ri_mi.png", null, null, "kirimi_sanrio", null),
			// テストデータ3
			new AccountResource(Long.valueOf(1), "gudetama", "ぐでたま", "だるい", "assets/images/gudetama.png", null, null,
					"gudetama_sanrio", null)));

	/** フォローテストデータ */
	private Map<String, List<AccountResource>> followMap = new HashMap<String, List<AccountResource>>() {
		{
			put("my_melody", Arrays.asList(accountList.get(1), accountList.get(2)));
		}
		{
			put("ki_ri_mi", Arrays.asList(accountList.get(0)));
		}
	};

	/** フォローワーテストデータ */
	private Map<String, List<AccountResource>> followerMap = new HashMap<String, List<AccountResource>>() {
		{
			put("my_melody", Arrays.asList(accountList.get(1)));
		}
		{
			put("ki_ri_mi", Arrays.asList(accountList.get(0)));
		}
		{
			put("gudetama", Arrays.asList(accountList.get(0)));
		}
	};

	/**
	 * フォローを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	@Override
	public Page<AccountResource> findFollow(String loginId) {
		List<AccountResource> followList = followMap.containsKey(loginId) ? followMap.get(loginId) : Arrays.asList();

		return new PageImpl<>(followList);
	}

	/**
	 * フォローワーを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	@Override
	public Page<AccountResource> findFollowers(String loginId) {
		List<AccountResource> followList = followerMap.containsKey(loginId) ? followerMap.get(loginId)
				: Arrays.asList();

		return new PageImpl<>(followList);
	}
}
