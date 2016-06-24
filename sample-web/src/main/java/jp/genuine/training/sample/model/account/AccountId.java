package jp.genuine.training.sample.model.account;

public class AccountId {

	private Integer value;
	public AccountId()
	{
	}
	public AccountId(String value)
	{
		this.value = Integer.parseInt(value);
	}
	public AccountId(Integer value)
	{
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return String.format("AccountId [value=%s]", value);
	}
	
}
