package com.mindfire.wsc.controllers;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.service.UserService;

/**
 * This class provides functionalities 
 * for the admin to control users 
 * @author sudhansub
 *
 */
@Controller
@RequestMapping(value = "admin/user")
public class AdminUserController {

	@Autowired
	private UserService userService;	
	
	/*
	 * This method is used to redirect the user to AdminUser.jsp
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUser(ModelMap model , HttpSession session) {
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(("admin").equals(udto.getRole())) {
			return new ModelAndView("AdminUser");
		}
		return new ModelAndView("redirect:/login");
	}
	
	/*
	 * This method is used to Edit/Modify the user using userid
	 * here user id is taken as @PathVaribale, as parameter
	 */
	@RequestMapping(value = "/edit/{eno}", method = RequestMethod.GET)
	public ModelAndView editUser(ModelMap model, @PathVariable("eno") int eno, HttpSession session) {
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			return new ModelAndView("redirect:/login");
		}
				
		if (eno >= 0) {
			model.addAttribute("userdetail", userService.modifyUser(eno));
		}

		return new ModelAndView("AdminUser");
	}
	
	/*
	 * This method is used Save the new User to database
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@Valid UserDTO user, BindingResult result) throws UnknownHostException {
		
		//Validation the User Detail
		if (result.hasErrors()) 		
			return "AdminUser";	
		
		if(user != null) {
			
			//Check if user save successfully
			try {
				userService.save(user);	
			} catch(Exception e) {
				e.printStackTrace();
				return "admin";	
			}
		}

		return ("redirect:/admin");
	}
	
	/*
	 * This method is used delete the user from DB
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/delete/{eno}", method = RequestMethod.GET)
	public ModelAndView deleteUser(ModelMap model, @PathVariable("eno") int eno, HttpSession session) {
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			return new ModelAndView("redirect:/login");
		}
				
		if (eno >= 0) {
			userService.deleteUser(eno);
			model.addAttribute("msg", "User Deleted Successfully");
		}

		return new ModelAndView("redirect:/admin");
	}
	
	/*
	 * This method is used Check the user from DB
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/checkUsername/{user}", method = RequestMethod.GET)
	@ResponseBody
	public String checkUsername(HttpServletResponse response,@PathVariable("user") String user) {
		boolean flag = false;
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");    
	    
		if (user != null) {
			flag = userService.checkUsername(user);			
		}	
		if(flag == true) {
			return "User Already Exist";
		} else {
			return "Congratulations: "+user+" is Available";
		}
	}	
	
	/*
	 * This method is used check the user from DB
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/changepassword/{ename}", method = RequestMethod.GET)
	public ModelAndView changeUserPassword(ModelMap model, @PathVariable("ename") String ename,HttpSession session) {
		UserDTO sessionUser = (UserDTO) session.getAttribute("userSession");
		UserDTO dtoUser = userService.getUserByUserName(ename);
		
		if((sessionUser.getUserName() != null)) {
			if(sessionUser.getUserName().equals(dtoUser.getUserName())) {		
				//return "ChangePwd";
				return new ModelAndView("ChangePwd");		
			}
			else {
				return new ModelAndView("redirect:/login");				
			}	
		}
		else {
			return new ModelAndView("redirect:/login");			
		}		
	}
	
	/*
	 * This method is used Change the user Password from DB
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/UpdateUserPassword", method = RequestMethod.POST)
	public String UpdateUserPassword(Model model,UserDTO user,HttpSession session, RedirectAttributes redirectAttributes) {		
		UserDTO sessionUser = (UserDTO) session.getAttribute("userSession");
		
		if(user.getExistingpwd().equals(sessionUser.getPassword()) && user.getPassword().equals(user.getConfpassword())) {
			// Check the existing password and update the existing password
			sessionUser.setPassword(user.getPassword());
			userService.update(sessionUser);
		} 
		
		else if(!user.getPassword().equals(user.getConfpassword())){
			// check if new password and confirmation password is same or not
			model.addAttribute("msg", "New Password and Confirmation do not match , try again");			
			return "ChangePwd";
		} 
		
		else {
			// check if Existing password does not match
			model.addAttribute("msg", "Existing password does not match , try again");			
			return "ChangePwd";
		}

		return ("redirect:/login");
	}	
	
	/*
	 * This method is used check the user from DB
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/editprofile/{ename}", method = RequestMethod.GET)
	public ModelAndView updateprofile(ModelMap model, @PathVariable("ename") String ename,HttpSession session) {
		UserDTO sessionUser = (UserDTO) session.getAttribute("userSession");
		UserDTO dtoUser = userService.getUserByUserName(ename);
		
		if((sessionUser.getUserName() != null)) {
			if(sessionUser.getUserName().equals(dtoUser.getUserName())) {	
				return new ModelAndView("updateprofile");		
			}
			else {
				return new ModelAndView("redirect:/wsc/login");				
			}	
		}
		else {
			return new ModelAndView("redirect:/wsc/login");			
		}		
	}
}
