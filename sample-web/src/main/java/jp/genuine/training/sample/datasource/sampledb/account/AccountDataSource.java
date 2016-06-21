package jp.genuine.training.sample.datasource.sampledb.account;

import org.springframework.stereotype.Repository;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountName;
import jp.genuine.training.sample.model.account.AccountRepository;
import jp.genuine.training.sample.model.account.Password;
import jp.genuine.training.sample.model.account.UserName;

@Repository
public class AccountDataSource implements AccountRepository {

	@Override
	public Account findBy(AccountName accountName) {
		//TODO データベースから取得した結果に置き換える
		return new Account(accountName, new UserName("Default User"), new Password("pass#1user"));
	}
}
