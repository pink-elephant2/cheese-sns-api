package com.api.sns.cheese.repository;

import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.domain.TAccountKey;
import com.api.sns.common.business.repository.BaseRepository;

public interface TAccountRepository extends BaseRepository<TAccountKey, TAccount, TAccountExample> {

	/**
	 * アカウントIDで検索
	 *
	 * @param accountId アカウントID
	 */
	public TAccount findOneById(Integer accountId);

	/**
	 * ログインIDで検索
	 *
	 * @param loginId
	 *            ログインId
	 */
	public TAccount findOneByLoginId(String loginId);
}