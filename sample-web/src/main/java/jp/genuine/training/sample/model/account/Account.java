package jp.genuine.training.sample.model.account;

import javax.validation.Valid;

public class Account {

	private AccountId accountId;
	@Valid
	private AccountName accountName;
	@Valid
	private UserName userName;
	@Valid
	private Password password;
	public Account() {
		accountId = new AccountId();
		accountName = new AccountName();
		userName = new UserName();
		password = new Password();
	}
	public Account(AccountId accountId, AccountName accountName, UserName userName, Password password) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.userName = userName;
		this.password = password;
	}

	public AccountId getAccountId() {
		return accountId;
	}
	public AccountName getAccountName() {
		return accountName;
	}
	public UserName getUserName() {
		return userName;
	}
	public Password getPassword() {
		return password;
	}
	public void setAccountId(AccountId accountId) {
		this.accountId = accountId;
	}
	public void setAccountName(AccountName accountName) {
		this.accountName = accountName;
	}
	public void setUserName(UserName userName) {
		this.userName = userName;
	}
	public void setPassword(Password password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return String.format("Account [accountName=%s, userName=%s, password=%s]", accountName, userName, password);
	}	
}
