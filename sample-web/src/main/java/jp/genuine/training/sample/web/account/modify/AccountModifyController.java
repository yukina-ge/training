package jp.genuine.training.sample.web.account.modify;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.AccountId;
import jp.genuine.training.sample.model.account.LoginAccount;
import jp.genuine.training.sample.service.account.FindAccountService;
import jp.genuine.training.sample.service.account.ModifyAccountService;

@Controller
@RequestMapping( "/account/{accountId:\\d+}/modify" )
public class AccountModifyController {

	@Autowired
	private FindAccountService findAccountService;
	
	@Autowired
	private ModifyAccountService modifyAccountService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String form(
			@PathVariable("accountId") AccountId accountId,
			@AuthenticationPrincipal LoginAccount loginAccount, 
			Model model, 
			HttpServletRequest request){
		Account account = findAccountService.find( accountId );
		model.addAttribute( "account", account );
		
		return "/account/modify/form";
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
			return "/account/modify/form";
		
		modifyAccountService.modify(account);
		
		return "/account/modify/completed";
	}
}
