package com.api.sns.cheese.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.ibatis.javassist.NotFoundException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.domain.TFollow;
import com.api.sns.cheese.domain.TFollowExample;
import com.api.sns.cheese.repository.TAccountMapper;
import com.api.sns.cheese.repository.TFollowMapper;
import com.api.sns.cheese.resources.AccountResource;
import com.api.sns.cheese.service.FollowService;

/**
 * フォローサービス
 */
@Service
@Transactional
public class FollowServiceImpl implements FollowService {

	@Autowired
	private TAccountMapper tAccountMapper;

	@Autowired
	private TFollowMapper tFollowMapper;

	@Autowired
	private Mapper mapper;

	/**
	 * フォローを取得する
	 *
	 * @param loginId
	 *            ログインID
	 * @return アカウント情報
	 */
	@Override
	public Page<AccountResource> findFollow(String loginId) {
		Integer accountId = 4; // TODO ログインユーザー

		// フォローリストを取得
		TFollowExample followExample = new TFollowExample();
		followExample.createCriteria().andAccountIdEqualTo(accountId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TFollow> followList = tFollowMapper.selectByExample(followExample);

		List<TAccount> accountList = new ArrayList<>();
		if (!followList.isEmpty()) {
			// アカウントを取得
			TAccountExample accountExample = new TAccountExample();
			accountExample.createCriteria()
					.andAccountIdIn(followList.stream().map(TFollow::getAccountId).collect(Collectors.toList()));
			accountList = tAccountMapper.selectByExample(accountExample);
		}

		// TODO ページで絞る
		return new PageImpl<>(accountList.stream().map(account -> {
			AccountResource resource = mapper.map(account, AccountResource.class);
			return resource;
		}).collect(Collectors.toList()));
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
		Integer accountId = 4; // TODO ログインユーザー

		// フォローワーリストを取得
		TFollowExample followExample = new TFollowExample();
		followExample.createCriteria().andFollowAccountIdEqualTo(accountId)
				.andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TFollow> followList = tFollowMapper.selectByExample(followExample);

		List<TAccount> accountList = new ArrayList<>();
		if (!followList.isEmpty()) {
			// アカウントを取得
			TAccountExample accountExample = new TAccountExample();
			accountExample.createCriteria()
					.andAccountIdIn(followList.stream().map(TFollow::getAccountId).collect(Collectors.toList()));
			accountList = tAccountMapper.selectByExample(accountExample);
		}

		// TODO ページで絞る
		return new PageImpl<>(accountList.stream().map(account -> {
			AccountResource resource = mapper.map(account, AccountResource.class);
			return resource;
		}).collect(Collectors.toList()));
	}

	/**
	 * フォローする
	 *
	 * @param loginId
	 *            ログインID(フォロー対象)
	 */
	@Override
	public boolean follow(String loginId) throws Exception {
		// 対象ユーザの取得
		TAccountExample accountExample = new TAccountExample();
		accountExample.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TAccount> followAccountList = tAccountMapper.selectByExample(accountExample);
		if (followAccountList.isEmpty()) {
			throw new NotFoundException("アカウントが存在しません");
		}

		Integer accountId = 4; // TODO ログインユーザー

		// フォロー済みか
		TFollowExample followExample = new TFollowExample();
		followExample.createCriteria().andAccountIdEqualTo(accountId)
				.andFollowAccountIdEqualTo(followAccountList.get(0).getAccountId());
		List<TFollow> followList = tFollowMapper.selectByExample(followExample);

		// 登録内容の設定
		TFollow follow = new TFollow();
		follow.setAccountId(accountId);
		follow.setFollowAccountId(followAccountList.get(0).getAccountId());
		// TODO 共通項目は親クラスで設定する
		follow.setDeleted(CommonConst.DeletedFlag.OFF);
		follow.setCreatedAt(new Date());
		follow.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
		follow.setUpdatedAt(new Date());
		follow.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);

		int ret;
		if (followList.isEmpty()) {
			// レコード登録
			ret = tFollowMapper.insert(follow);
		} else if (CommonConst.DeletedFlag.OFF.equals(followList.get(0).getDeleted())) {
			// TODO 例外
			throw new Exception("すでにフォロー済みです");
		} else {
			// レコード更新
			followList.get(0).setDeleted(CommonConst.DeletedFlag.OFF);
			ret = tFollowMapper.updateByPrimaryKeySelective(followList.get(0));
		}
		return BooleanUtils.toBoolean(ret);
	}

	/**
	 * フォローを解除する
	 *
	 * @param loginId
	 *            ログインID(フォロー対象)
	 */
	@Override
	public boolean unfollow(String loginId) throws Exception {
		// 対象ユーザの取得
		TAccountExample accountExample = new TAccountExample();
		accountExample.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		List<TAccount> followAccountList = tAccountMapper.selectByExample(accountExample);
		if (followAccountList.isEmpty()) {
			throw new NotFoundException("アカウントが存在しません");
		}

		Integer accountId = 4; // TODO ログインユーザー

		// フォロー済みか
		TFollowExample followExample = new TFollowExample();
		followExample.createCriteria().andAccountIdEqualTo(accountId)
				.andFollowAccountIdEqualTo(followAccountList.get(0).getAccountId());
		List<TFollow> followList = tFollowMapper.selectByExample(followExample);

		// 登録内容の設定
		TFollow follow = new TFollow();
		follow.setAccountId(accountId);
		follow.setFollowAccountId(followAccountList.get(0).getAccountId());
		// TODO 共通項目は親クラスで設定する
		follow.setDeleted(CommonConst.DeletedFlag.OFF);
		follow.setCreatedAt(new Date());
		follow.setCreatedBy(CommonConst.SystemAccount.ADMIN_ID);
		follow.setUpdatedAt(new Date());
		follow.setUpdatedBy(CommonConst.SystemAccount.ADMIN_ID);

		int ret;
		if (followList.isEmpty()) {
			// レコード登録
			ret = tFollowMapper.insert(follow);
		} else if (CommonConst.DeletedFlag.ON.equals(followList.get(0).getDeleted())) {
			// TODO 例外
			throw new Exception("すでにフォロー解除済みです");
		} else {
			// レコード更新
			followList.get(0).setDeleted(CommonConst.DeletedFlag.ON);
			ret = tFollowMapper.updateByPrimaryKeySelective(followList.get(0));
		}
		return BooleanUtils.toBoolean(ret);
	}
}
