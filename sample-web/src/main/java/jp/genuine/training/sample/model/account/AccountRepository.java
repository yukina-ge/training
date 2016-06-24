package jp.genuine.training.sample.model.account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

	public Optional<Account> findBy( AccountName accountName );
	public Optional<Account> findBy( AccountId accountId );
	public List<Account> listOf( Account account );

}
