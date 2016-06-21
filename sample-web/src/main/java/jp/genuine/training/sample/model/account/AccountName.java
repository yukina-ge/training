package jp.genuine.training.sample.model.account;

public class AccountName {
	private String value;
	public AccountName()
	{
		value="";
	}
	public AccountName(String value)
	{
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return String.format("AccountName [value=%s]", value);
	}
}
