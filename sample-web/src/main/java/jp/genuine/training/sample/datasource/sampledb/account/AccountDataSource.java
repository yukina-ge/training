package jp.genuine.training.sample.datasource.sampledb.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountId;
import jp.genuine.training.sample.model.account.AccountName;
import jp.genuine.training.sample.model.account.AccountRepository;
import jp.genuine.training.sample.model.account.Password;
import jp.genuine.training.sample.model.account.UserName;

@Repository
public class AccountDataSource implements AccountRepository {

	@Override
	public Optional<Account> findBy(AccountName accountName) {
		//TODO データベースから取得した結果に置き換える
		return Optional.ofNullable(new Account( new AccountId(1), accountName, new UserName("Default User"), new Password("pass#1user")));
	}

	@Override
	public Optional<Account> findBy(AccountId accountId) {
		if( new Integer(1).equals(accountId.getValue()) )
			return Optional.ofNullable(new Account( new AccountId(1), new AccountName( "cranston6548" ), new UserName("Bryan Cranston"), new Password("pass#1user")));
		if( new Integer(2).equals(accountId.getValue()) )
			return Optional.ofNullable(new Account( new AccountId(2), new AccountName( "paul_99" ), new UserName("Aaron Paul"), new Password("pass#2user")));
		if( new Integer(3).equals(accountId.getValue()) )
			return Optional.ofNullable(new Account( new AccountId(3), new AccountName( "miyukin" ), new UserName("Miyuki Inoue"), new Password("pass#3user")));
		return Optional.ofNullable(null);
	}

	@Override
	public List<Account> listOf(Account account) {

		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account( new AccountId(1), new AccountName( "cranston6548" ), new UserName("Bryan Cranston"), new Password("pass#1user")));
		accounts.add(new Account( new AccountId(2), new AccountName( "paul_99" ), new UserName("Aaron Paul"), new Password("pass#2user")));
		accounts.add(new Account( new AccountId(3), new AccountName( "miyukin" ), new UserName("Miyuki Inoue"), new Password("pass#3user")));
		return accounts;
		
	}
}
