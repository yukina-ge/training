package jp.genuine.training.sample.model.account;

public class UserName {
	private String value;
	public UserName()
	{
		value="";
	}
	public UserName(String value)
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
		return String.format("UserName [value=%s]", value);
	}

}
