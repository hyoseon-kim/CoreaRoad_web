package kr.corearoad.controller;

import kr.corearoad.bo.LoginUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@Autowired
	LoginUserBO loginUserBO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(HttpServletRequest req, ModelMap model) {
		return "main";
	}

	@RequestMapping(value = "/test",  method = RequestMethod.GET)
	public String ajaxTest(HttpServletRequest req, ModelMap model) {
		model.addAttribute("message", loginUserBO.getTest());
		return "hello";
	}
}