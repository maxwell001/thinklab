package com.thinklab.platform.web.homesite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thinklab.platform.util.HttpUtil;
import com.thinklab.platform.util.SecurityUtil;
import com.thinklab.platform.web.homesite.model.User;
import com.thinklab.platform.web.homesite.service.IUserService;

@Controller
public class UserActioin {
	
	@Autowired
	private IUserService userService;
	
	private final Logger log = LoggerFactory.getLogger(UserActioin.class);

	@RequestMapping("/userAction/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		log.info("[start]login...");
		Map<String,Object> model = new HashMap<String,Object>();
		try{
			String loginName = HttpUtil.getUrlParameter(request, "loginName");
			String passWord = HttpUtil.getUrlParameter(request, "passWord");
			String captcha = HttpUtil.getUrlParameter(request, "captcha"); 
			log.info("login name is:",loginName);
			//µÇÂ½Ãû³Æ
			if(loginName==null || "".equals(loginName)){
				model.put("message", "please input your login name!");
				return new ModelAndView("",model);
			}
			//ÃÜÂë
			if(passWord==null || "".equals(passWord)){
				model.put("message", "please input your password!");
				return new ModelAndView("",model);
			}
			//ÑéÖ¤Âë
			if(captcha!=null && !"".equals(captcha)){
				String captchaInSession = HttpUtil.getUrlParameter(request, captcha);
				if(!captcha.equals(captchaInSession)){
					log.info("captcha verify failed");
					model.put("message", "capthca verify fail!");
					return new ModelAndView("",model); 
				}
			}else{
				model.put("message", "fail");
				return new ModelAndView("",model);
			}
			
			User loginUser = userService.getUserInfoByLoginName(loginName);
			if(loginUser!=null){
				String userPd = loginUser.getPassWard();
				if(userPd.equals(SecurityUtil.stringToMd5(passWord))){
					log.info("login sucessful.");
					//loginUser set into session
					HttpUtil.setSessionAttribute(request, "loginUser", loginUser);
					model.put("loginUser", loginUser);
				}else{
					model.put("message", "fail");
					return new ModelAndView("",model);
				}
			}
		}catch(Exception e){
			log.error("µÇÂ½±¨´í£º", e);
			e.printStackTrace();
		}
		return new ModelAndView("", model);
	}
	
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}
