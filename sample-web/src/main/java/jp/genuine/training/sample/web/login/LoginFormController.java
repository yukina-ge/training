package jp.genuine.training.sample.web.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginFormController {
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String form( @RequestParam( value="error", required=false) String error, Model model, HttpServletRequest request )
	{
		String username = request.getParameter("username");
		model.addAttribute("username", username);
		if(error == null)
			return "/login/form";
		model.addAttribute("error", true);
		return "/login/form";
	}
}
