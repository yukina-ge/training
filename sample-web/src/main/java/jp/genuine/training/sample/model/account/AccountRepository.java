package jp.genuine.training.sample.model.account;

public interface AccountRepository {
	public Account findBy( AccountName accountName );
}
