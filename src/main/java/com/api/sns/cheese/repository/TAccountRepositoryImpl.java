package com.api.sns.cheese.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;

@Primary
@Repository
public class TAccountRepositoryImpl implements TAccountRepository {
	private TAccountMapper tAccountMapper;

	public TAccountRepositoryImpl(TAccountMapper tAccountMapper) {
		this.tAccountMapper = tAccountMapper;
	}

	@Override
	public TAccountMapper getMapper() {
		return this.tAccountMapper;
	}

	/**
	 * アカウントIDで検索
	 *
	 * @param accountId アカウントID
	 */
	@Override
	public TAccount findOneById(Integer accountId) {
		TAccountExample example = new TAccountExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return findOneBy(example);
	}

	/**
	 * ログインIDで検索
	 *
	 * @param loginId
	 *            ログインId
	 */
	@Override
	public TAccount findOneByLoginId(String loginId) {
		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		return findOneBy(example);
	}
}