package kr.corearoad.controller;

import kr.corearoad.bean.User;
import kr.corearoad.bo.LoginUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	LoginUserBO loginUserBO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(HttpServletRequest req, ModelMap model) {
		return "main";
	}

	@RequestMapping(value = "/test.do",  method = RequestMethod.GET)
	public String ajaxTest(HttpServletRequest req, ModelMap model) {
		model.addAttribute("message", loginUserBO.getUser("hyos810@naver.com"));
		return "hello";
	}

	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		User user = loginUserBO.getUser(req.getParameter("email"));
		if(user.getPassword().equals(req.getParameter("password"))){
			HttpSession session = req.getSession();
			if(session != null) {
				session.invalidate();
			}

			session = req.getSession(true);
			session.setAttribute("loginUser", user);
			model.addAttribute("json", "success");
			return "main";
		} else {
			model.addAttribute("json", "fail");
			return "main";
		}
	}
}