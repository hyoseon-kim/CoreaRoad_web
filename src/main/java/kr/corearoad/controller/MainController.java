package kr.corearoad.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import kr.corearoad.bean.User;
import kr.corearoad.bo.ActionBO;
import kr.corearoad.bo.LoginUserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainController {

	@Autowired
	LoginUserBO loginUserBO;

	@Autowired
	ActionBO actionBO;

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

	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signUp(HttpServletRequest req, HttpServletResponse res, ModelMap map) {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String nationality = req.getParameter("nationality");

		String date = req.getParameter("date");
		String month = req.getParameter("month");
		String year = req.getParameter("year");


		String capableLang1 = req.getParameter("capable_lang1");
		String capableLang1Lavel = req.getParameter("capable_lang1_level");
		String capableLang2 = req.getParameter("capable_lang2");
		String capableLang2Lavel = req.getParameter("capable_lang2_level");
		String capableLang3 = req.getParameter("capable_lang3");
		String capableLang3Lavel = req.getParameter("capable_lang3_level");


		String initinerary = req.getParameter("initinerary");
		String introduction = req.getParameter("introduction");


		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setGender(gender);
		user.setNationality(nationality);
		user.setBirthDate(year+month+date);


		user.setCapableLang1(user.getCapableLangJsonString(capableLang1, capableLang1Lavel));
		user.setCapableLang2(user.getCapableLangJsonString(capableLang2, capableLang2Lavel));
		user.setCapableLang3(user.getCapableLangJsonString(capableLang3, capableLang3Lavel));
		user.setTourInitinerary(initinerary);
		user.setSelfIntroduction(introduction);

		user.setIsKorean(true);
		user.setChatStatus(true);

		loginUserBO.join(user);
		return "redirect:/";
	}

	@RequestMapping("/checkId.do")
	public String checkId(HttpServletRequest req, HttpServletResponse res, ModelMap map) {
		User user = null;
		String email = req.getParameter("email");
		if(!email.isEmpty()) {
			user = loginUserBO.getUser(email);
		}

		if(user != null) {
			map.put("message", "exist");
		} else {
			map.put("message", "not exist");
		}
		return "hello";
	}

	@RequestMapping("/getMainActionPictureList.do")
	public String getMainActionPictureList(HttpServletRequest req, HttpServletResponse res, ModelMap map) {
		Gson gson = new GsonBuilder().create();
		map.put("message", gson.toJson(actionBO.getMainPictureList()));
		return "hello";
	}

	@RequestMapping("/getAction.do")
	public String getAction(HttpServletRequest req, HttpServletResponse res, ModelMap map) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().create();
		map.put("message", gson.toJson(actionBO.getAction(req.getParameter("actionNo"))));
		return "hello";
	}
}