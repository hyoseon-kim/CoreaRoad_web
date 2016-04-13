package kr.corearoad.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kr.corearoad.bean.User;
import kr.corearoad.bo.LoginUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
		HttpSession session = req.getSession();
		User user = null;
		if(StringUtils.isEmpty(req.getParameter("email")) || StringUtils.isEmpty(req.getParameter("password"))) {
			return "redirect:/";
		}

		//email에 해당하는 User가 있는지 검사.
		if(loginUserBO.getUser(req.getParameter("email")) != null){
			user = loginUserBO.getUser(req.getParameter("email"));
		} else {
			//아이디가 잘못됨
			User fakeUser = new User(true);
			session.setAttribute("loginUser", fakeUser);
			return "redirect:/";
		}

		//해당하는 email이 있을 경우 password가 일치하는지 검사
		if(user.getPassword().equals(req.getParameter("password"))){
			if(session != null) {
				session.invalidate();
			}

			session = req.getSession(true);
			session.setAttribute("loginUser", user);
			return "redirect:/";
		} else {
			//email이 있지만 password가 일치하지 않는 경우
			User fakeUser = new User(true);
			session.setAttribute("loginUser", fakeUser);
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/getSession.do", method = RequestMethod.GET)
	public String getSeesion(HttpServletRequest req, HttpServletResponse res, ModelMap map) {
		HttpSession session = req.getSession();
		User user = null;
		if(session != null && session.getAttribute("loginUser") != null) {
			user = (User)session.getAttribute("loginUser");
			if(user.isForFakeUser()) {
				map.addAttribute("message", "login fail");
				session.removeAttribute("loginUser");
			} else {
				map.addAttribute("message", new Gson().toJson(user));
			}
		} else {
			map.addAttribute("message", "not a member");
		}

		return "hello";
	}
}