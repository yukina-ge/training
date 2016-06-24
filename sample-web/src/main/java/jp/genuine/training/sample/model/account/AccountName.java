package jp.genuine.training.sample.model.account;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountName {
	@NotEmpty
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
