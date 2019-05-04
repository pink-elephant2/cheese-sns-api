package com.api.sns.cheese.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.sns.cheese.consts.CommonConst;
import com.api.sns.cheese.domain.TAccount;
import com.api.sns.cheese.domain.TAccountExample;
import com.api.sns.cheese.repository.TAccountRepository;
import com.api.sns.cheese.resources.SessionInfoResource;

/**
 * ユーザー認証サービス
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private TAccountRepository tAccountRepository;

	/**
	 * 認証
	 */
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		// DB検索
		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TAccount account = tAccountRepository.findOneBy(example);

		if (account != null) {
			return new User(loginId, account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		}
		throw new UsernameNotFoundException("not found : " + loginId);
	}

	/**
	 * 認証済みかチェックする
	 */
	public SessionInfoResource authenticated() {
		// ログインセッションが取得できない場合はエラー
		if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null
				|| SecurityContextHolder.getContext().getAuthentication().getName() == null) {
			return null;
		}

		// DB検索
		String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
		TAccountExample example = new TAccountExample();
		example.createCriteria().andLoginIdEqualTo(loginId).andDeletedEqualTo(CommonConst.DeletedFlag.OFF);
		TAccount account = tAccountRepository.findOneBy(example);

		// ユーザがいない場合はエラー
		if (account == null) {
			return null;
		}

		// セッション情報(HttpSession)
		SessionInfoResource sessionInfo = new SessionInfoResource();
		sessionInfo.setLoginId(loginId);
		sessionInfo.setUserName(account.getName());

		return sessionInfo;
	}
}
