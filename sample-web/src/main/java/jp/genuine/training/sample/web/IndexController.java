package jp.genuine.training.sample.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.genuine.training.sample.model.sample.SampleRepository;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	private SampleRepository repository;
	
	@RequestMapping(value="", method=RequestMethod.GET)
    
	public String show(Model model, HttpServletRequest request){
		repository.find();
		return "/index/index";
	}

}
