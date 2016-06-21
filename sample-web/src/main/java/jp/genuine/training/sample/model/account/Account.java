package jp.genuine.training.sample.model.account;

public class Account {
	private AccountName accountName;
	private UserName userName;
	private Password password;
	public Account() {
		accountName = new AccountName();
		userName = new UserName();
		password = new Password();
	}
	public Account(AccountName accountName, UserName userName, Password password) {
		this.accountName = accountName;
		this.userName = userName;
		this.password = password;
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
