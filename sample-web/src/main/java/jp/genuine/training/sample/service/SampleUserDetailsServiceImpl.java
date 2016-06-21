package jp.genuine.training.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountName;
import jp.genuine.training.sample.model.account.AccountRepository;
import jp.genuine.training.sample.model.account.LoginAccount;

public class SampleUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findBy(new AccountName(username));
		return new LoginAccount(account);
	}
	
}
