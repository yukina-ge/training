package jp.genuine.training.sample.service.account;

import java.util.List;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.LoginAccount;

public interface ListingAccountService {
	public List<Account> list( LoginAccount account );
}
