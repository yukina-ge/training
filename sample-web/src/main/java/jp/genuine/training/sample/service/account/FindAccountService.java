package jp.genuine.training.sample.service.account;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountId;

public interface FindAccountService {
	public Account find( AccountId accountId );
}
