package jp.genuine.training.sample.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.genuine.training.sample.exception.ResourceNotFoundException;
import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountId;
import jp.genuine.training.sample.model.account.AccountRepository;

@Service
public class FindAccountServiceImpl implements FindAccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Override
	public Account find(AccountId accountId) {
		
		return accountRepository.findBy(accountId).orElseThrow( ResourceNotFoundException:: new );
	}

}
