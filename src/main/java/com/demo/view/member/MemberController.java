package com.demo.view.member;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.biz.common.LoginDTO;
import com.demo.biz.member.MemberService;
import com.demo.biz.member.MemberVO;
import com.demo.view.common.SessionListener;

@Controller
@RequestMapping("/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService service;
	
	// Facebook OAuth
	@Autowired
	private FacebookConnectionFactory connectionFactory;
	
	@Autowired
	private OAuth2Parameters oAuth2Parameters;
	
	@Autowired
	public MemberController(MemberService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session, Model model) {
		
		logger.info("login page");
		
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		String facebook_url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oAuth2Parameters);

		model.addAttribute("facebook_url", facebook_url);
		
		if(session.getAttribute("member") == null)
			return "member/login";
		else
			return "/home";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(LoginDTO dto, Model model) {
		
		logger.info(dto.toString());
		
		MemberVO vo = service.loginMember(dto);
		
		SessionListener listener = SessionListener.getInstance();
		
		if(vo == null) {
			model.addAttribute("msg", "FAIL");
			return;
		}
		
		if(listener.isUsing(dto.getId())) {
			model.addAttribute("msg", "DUPLICATE");
			return;
		}

		model.addAttribute("member", vo);
	}
	
	@RequestMapping(value = "/facebookSignInCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String facebookSignInCallback(@RequestParam String code, Model model) throws Exception {

		SessionListener listener = SessionListener.getInstance();
		
		try {
			
			String redirectUri = oAuth2Parameters.getRedirectUri();
			System.out.println("Redirect URI : " + redirectUri);
			System.out.println("Code : " + code);

			OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
			AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, redirectUri, null);
			String accessToken = accessGrant.getAccessToken();
			System.out.println("AccessToken: " + accessToken);
			Long expireTime = accessGrant.getExpireTime();


			if (expireTime != null && expireTime < System.currentTimeMillis()) {
				accessToken = accessGrant.getRefreshToken();
				logger.info("accessToken is expired. refresh token = {}", accessToken);
			};


			Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);
			Facebook facebook = connection == null ? new FacebookTemplate(accessToken) : connection.getApi();
			//UserOperations userOperations = facebook.userOperations();

			try

			{            
				String [] fields = { "id", "email",  "name"};
				User userProfile = facebook.fetchObject("me", User.class, fields);
				
				MemberVO vo = service.loginMember(userProfile.getEmail());
				
				if(vo == null) {
					model.addAttribute("msg", "FAIL");
					return "login";
				}
				
				if(listener.isUsing(vo.getMbId())) {
					model.addAttribute("msg", "DUPLICATE");
					return "login";
				}
				
				model.addAttribute("member", vo);

			} catch (MissingAuthorizationException e) {
				e.printStackTrace();
			}


		} catch (Exception e) {

			e.printStackTrace();

		}

		return "home";

	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logoutMember(HttpSession session) {
		
		MemberVO vo = (MemberVO) session.getAttribute("member");
		
		logger.info(vo.getMbNick() + " Logout");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void signup() {
	
		logger.info("register");
	
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String signup(MemberVO vo, HttpSession session, RedirectAttributes rttr) {
		
		logger.info(vo.toString());
		
		try {
			service.insertMember(vo);
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msg", "FAIL");
			return "redirect:register";
		}
		
		session.setAttribute("temp", vo.getMbId());
		
		return "redirect:authkey";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkId/{mbId}", method = RequestMethod.GET)
	public ResponseEntity<String> checkId(@PathVariable("mbId") String mbId) {
		
		ResponseEntity<String> entity = null;
		
		try {
			
			if(service.checkId(mbId) > 0)
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			else
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch (Exception e) {
			
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	@RequestMapping(value = "/authkey", method = RequestMethod.GET)
	public void authkey(String id, HttpSession session, Model model) {
		
		logger.info("authkey page");

		model.addAttribute("temp", id);
	}
	
	@RequestMapping(value = "/authkey", method = RequestMethod.POST)
	public String authkey(@RequestParam("mbAuth") String mbAuth, @RequestParam("mbId") String mbId, RedirectAttributes rttr, HttpSession session) {
		
		logger.info(mbAuth);

		MemberVO vo = service.getMember(mbId);
		
		if(mbAuth.equals(vo.getMbAuthkey())) {
			rttr.addFlashAttribute("msg", "SUCCESS");
			service.updateAuth(mbId);
			return "redirect:login";
		} else {
			rttr.addFlashAttribute("msg", "FAIL");
			return "redirect:authkey";
		}
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyMember() {
		
		logger.info("modify page");
		
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyMember(HttpSession session, MemberVO vo) {
		
		logger.info(vo.toString());
		
		service.updateMember(vo);
		session.setAttribute("member", vo);
		
		return "/home";
	}
}
