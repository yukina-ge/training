package jp.genuine.training.sample.web.account.register;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountFactory;
import jp.genuine.training.sample.model.account.LoginAccount;
import jp.genuine.training.sample.service.account.AccountRegisterService;

@Controller
@RequestMapping( "/account/register" )
public class AccountRegisterController {

	@Autowired
	private AccountFactory accountFactory;
	
	@Autowired
	private AccountRegisterService accountRegisterService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String form(
			@AuthenticationPrincipal LoginAccount loginAccount, 
			Model model, 
			HttpServletRequest request){
		Account account = accountFactory.create();
		model.addAttribute( "account", account );
		
		return "/account/register/form";
	}
	@RequestMapping(value="", method=RequestMethod.POST)
	public String execute(
			@Valid @ModelAttribute("account") Account account, 
			BindingResult bindingResult,
			@AuthenticationPrincipal LoginAccount loginAccount,
			Model model, 
			HttpServletRequest request){
		
		model.addAttribute( "account", account );
		
		if(bindingResult.hasErrors())
			return "/account/register/form";
		
		accountRegisterService.register(account);
		
		return "/account/register/completed";
	}
}
