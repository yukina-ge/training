package jp.genuine.training.sample.datasource.sampledb.account;

import org.apache.ibatis.annotations.Param;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountName;

public interface AccountMapper {
	public Account findBy( @Param("accountName") AccountName accountName );
}
