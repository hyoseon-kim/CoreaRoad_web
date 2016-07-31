package kr.corearoad.controller;

import com.google.common.collect.Maps;
import com.google.common.net.InetAddresses;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kr.corearoad.bean.ChatMessanger;
import kr.corearoad.bean.User;
import kr.corearoad.bo.ActionBO;
import kr.corearoad.bo.ChatBO;
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
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

	public static final String MESSAGE = "message";
	public static final String FAIL = "fail";
	@Autowired
	LoginUserBO loginUserBO;

	@Autowired
	ActionBO actionBO;

	@Autowired
	ChatBO chatBO;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(HttpServletRequest req, ModelMap model) {
		return "main";
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
			return "redirect:/";
		}
	}

	@RequestMapping("/logout.do")
	public String logOut(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		HttpSession session = req.getSession();
		if(session.getAttribute("loginUser") != null) {
			session.removeAttribute("loginUser");
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/getSession.do", method = RequestMethod.GET)
	public String getSeesion(HttpServletRequest req, HttpServletResponse res, ModelMap map) {
		HttpSession session = req.getSession();
		User user = null;
		if(session != null && session.getAttribute("loginUser") != null) {
			user = (User)session.getAttribute("loginUser");
			if(user.isForFakeUser()) {
				map.addAttribute(MESSAGE, "login fail");
				session.removeAttribute("loginUser");
			} else {
				map.addAttribute(MESSAGE, new Gson().toJson(user));
			}
		} else {
			map.addAttribute(MESSAGE, "not a member");
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
			map.put(MESSAGE, "exist");
		} else {
			map.put(MESSAGE, "not exist");
		}
		return "hello";
	}

	@RequestMapping("/getMainActionPictureList.do")
	public String getMainActionPictureList(HttpServletRequest req, HttpServletResponse res, ModelMap map) {
		Gson gson = new GsonBuilder().create();
		map.put(MESSAGE, gson.toJson(actionBO.getMainPictureList()));
		return "hello";
	}

	@RequestMapping("/getAction.do")
	public String getAction(HttpServletRequest req, HttpServletResponse res, ModelMap map) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().create();
		map.put(MESSAGE, gson.toJson(actionBO.getAction(req.getParameter("actionNo"))));
		return "hello";
	}

	@RequestMapping("/getAllChatRoomByUserId.do")
	public String getAllChatRoomByUserId(HttpServletRequest req, HttpServletResponse res, ModelMap map) throws UnsupportedEncodingException  {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		if(session.getAttribute("loginUser") == null) {
			map.put(MESSAGE, FAIL);
			return "hello";
		}

		String email = ((User)session.getAttribute("loginUser")).getEmail();
		Gson gson = new GsonBuilder().create();

		Map<String, Object> returnData = Maps.newHashMap();
		List<Map<String, Object>> allChatRoomByUserId = chatBO.getAllCharRoomByUserId(email);

		returnData.put("data", allChatRoomByUserId);
		returnData.put("loginUserId", email);

		map.put(MESSAGE, gson.toJson(returnData));

		return "hello";
	}

	@RequestMapping("/getAllMessengersByRoomId.do")
	public String getAllMessengersByRoomId(HttpServletRequest req, HttpServletResponse res, ModelMap map) throws UnsupportedEncodingException  {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String id = req.getParameter("roomId");
		Gson gson = new GsonBuilder().create();
		map.put(MESSAGE, gson.toJson(chatBO.getAllChatMessengerByRoomId(id)));
		return "hello";
	}

	@RequestMapping("/insertChatMessage.do")
	public String insertChatMessage(HttpServletRequest req, HttpServletResponse res, ModelMap map) {

		String content = req.getParameter("content");
		String roomId = req.getParameter("roomId");

		ChatMessanger chatMessanger = new ChatMessanger();
		chatMessanger.setMessengerContent(content);
		chatMessanger.setMessengerRoomId(roomId);
		chatMessanger.setMessengerSendUserId(getUser(req).getEmail());

		chatBO.insertChatMessage(chatMessanger);
		return "hello";
	}

	public User getUser(HttpServletRequest req) {
		HttpSession httpSession = req.getSession();
		User user = null;
		if(httpSession.getAttribute("loginUser") == null) {
			return user;
		} else {
			return ((User)httpSession.getAttribute("loginUser"));
		}
	}


}