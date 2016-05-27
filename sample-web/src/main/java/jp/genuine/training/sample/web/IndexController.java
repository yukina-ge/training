package jp.genuine.training.sample.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(value="", method=RequestMethod.GET)
    public String show(Model model, HttpServletRequest request){
		return "/index/index";
	}

}
