package jp.genuine.training.sample.model.account;

import org.springframework.stereotype.Component;

@Component
public class AccountFactory {
	public Account create(){
		return new Account();
	}
}
