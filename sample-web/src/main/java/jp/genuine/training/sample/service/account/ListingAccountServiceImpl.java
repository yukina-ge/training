package jp.genuine.training.sample.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountRepository;
import jp.genuine.training.sample.model.account.LoginAccount;

@Service
public class ListingAccountServiceImpl implements ListingAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> list(LoginAccount account) {
		return accountRepository.listOf(account.getAccount());
	}

}
