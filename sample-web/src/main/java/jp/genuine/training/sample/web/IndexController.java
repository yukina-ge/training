package jp.genuine.training.sample.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.genuine.training.sample.model.account.Account;
import jp.genuine.training.sample.model.account.LoginAccount;
import jp.genuine.training.sample.service.account.ListingAccountService;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private ListingAccountService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String show(
			@AuthenticationPrincipal LoginAccount loginAccount, Model model, HttpServletRequest request){
		List<Account> accounts = service.list(loginAccount);
		model.addAttribute("accounts", accounts );
		return "/index/index";
	}

}
