/**
 * 
 */
package com.mindfire.wsc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.UnknownHostException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.service.FileUploadServices;
import com.mindfire.wsc.service.UserService;
import com.twilio.sdk.TwilioRestException;


/**
 * It is used to Validate the username and password.
 * @author bipinis
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileUploadServices uploadServices;
		
	@RequestMapping(value="/login", method = RequestMethod.GET) 
	public String goHome() {
		return "login";
	}	
	
	/*
	 * Redirect the User to change the password on first login
	 * @Requestparam UserName
	 * @Session
	 */
	@RequestMapping(value="/authorize/login", method = RequestMethod.GET) 
	public ModelAndView authorizeEmailUser(@RequestParam("uname") String uname, HttpSession session) throws UnsupportedEncodingException {
		
		System.out.print(uname);
		
		//Decode the UserName
		String name = URLDecoder.decode(uname, "UTF-8");
		System.out.print(name);
		
		UserDTO fetchedUser = userService.getUserByUserName(name);
		if(("1234").equals(fetchedUser.getPassword())) {
			session.setAttribute("userSession", fetchedUser);
			return new ModelAndView("redirect:/admin/user/changepassword/"+uname);			
		}
		return new ModelAndView("redirect:/login");		
	}
	
	@RequestMapping(value="/user/forgotpassword", method = RequestMethod.GET) 
	public String forgotUser() {
		return "forgotpassword";
	}	
	
	/*
	 * Redirect the User to change the password on first login
	 * @Requestparam UserName
	 * @Session
	 */
	@RequestMapping(value="/user/sendpassword", method = RequestMethod.POST) 
	public String forgotPassword(UserDTO providedUser, ModelMap model) throws UnknownHostException, TwilioRestException {
			
		
		UserDTO fetchedUser = userService.getUserByUserName(providedUser.getUserName());
		
		//Check if the user exist or not
		if(fetchedUser == null) {
			model.addAttribute("msg", "Username does not match");
			return "forgotpassword";
		} 
		else {
		//Check the Email with user Email id
		if(providedUser.getEmail() != null) {
			if(providedUser.getEmail().equals(fetchedUser.getEmail())) {
		
				//Send Password in Email
				userService.sendPasswordByEmail(providedUser.getEmail(),fetchedUser.getPassword());
				//return "login";
			} else {
				model.addAttribute("msg", "Email does not match");
				return "forgotpassword";
			}
		}
		// Check the phone number with the user phone number
		if(providedUser.getPhone() != null) {
			if(providedUser.getPhone().equals(fetchedUser.getPhone())) {
				
				//Set the country code if it is not there
				String myphone = providedUser.getPhone();
				if(!myphone.startsWith("+91")) {
					myphone = "+91"+myphone;
				}
				//Send Password to Phone
				try {
					userService.sendMessageToUser(myphone,fetchedUser.getPassword());
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {				
				model.addAttribute("msg", "Phone Number does not match");
				return "forgotpassword";
			}
		}		
		}
		return "login";		
	}
	
	/** 
	 * This method is used to check the login authorization
	 * @param userservice
	 * @param model
	 * @return
	 */		
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView goLogin(@Valid UserDTO providedUser, BindingResult result, HttpSession session) {
		
		if (result.hasErrors()) {			
			return new ModelAndView("redirect:/login");	
		}

		UserDTO fetchedUser = userService.getUserByUserName(providedUser.getUserName());
		String path = uploadServices.getUserPhoto(fetchedUser.getUserId());
				
		if(fetchedUser.getUserName() == null
				|| !fetchedUser.getPassword().equals(providedUser.getPassword())) {			
			return new ModelAndView("redirect:/login");			
		}
		
		if(fetchedUser.getRole().equalsIgnoreCase("ADMIN")){//Admin user login
			if(path != null) {
				session.setAttribute("userPhoto", path);
			}
			session.setAttribute("userSession", fetchedUser);
			return new ModelAndView("redirect:/admin");
		} else{//Normal user login
			if(path != null) {
				session.setAttribute("userPhoto", path);				
				System.out.println(path);
			}
			session.setAttribute("userSession", fetchedUser);
			return new ModelAndView("redirect:/sales");
		}
		
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(HttpSession session) {		
		if(session!=null) {
			session.removeAttribute("userSession");
			session.invalidate();
		}
		return new ModelAndView("redirect:/login");		
	}
	
}
