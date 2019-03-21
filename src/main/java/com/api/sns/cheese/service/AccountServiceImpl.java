package com.api.sns.cheese.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.form.AccountForm;
import com.api.sns.cheese.resources.AccountResource;

/**
 * アカウントサービス
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private Mapper mapper;

	/** アカウントテストデータ */
	private List<AccountResource> accountList = new ArrayList<>(Arrays.asList(
			// テストデータ1
			new AccountResource(Long.valueOf(1), "my_melody", "マイメロディ", "おはよう♪　あさごはん　ちゃんとたべた〜？　いっしゅうかん　がんばろうね♪", null,
					null, "Melody_Mariland", null),
			// テストデータ2
			new AccountResource(Long.valueOf(2), "ki_ri_mi", "KIRIMIちゃん", "ラブ！サーモン！>°))))◁", null, null,
					"kirimi_sanrio", null),
			// テストデータ3
			new AccountResource(Long.valueOf(1), "gudetama", "ぐでたま", "だるい", null, null, "gudetama_sanrio", null)));

	/**
	 * アカウントを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	@Override
	public AccountResource find(String loginId) {
		return accountList.stream().filter(account -> account.getLoginId().equals(loginId)).findFirst().get();
	}

	/**
	 * プロフィールを更新する
	 *
	 * @param form
	 *            プロフィールフォーム
	 */
	@Override
	public boolean saveProfile(AccountForm form) {
		String loginId = "my_melody"; // TODO セッション情報から取得

		// プロフィールを更新する
		mapper.map(form, find(loginId));

		return true;
	}
}
