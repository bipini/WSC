/**
 * 
 */
package com.mindfire.wsc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.service.UserService;

/**
 * @author sudhansub
 *
 */

@Controller
@RequestMapping(value="admin")
public class AdminController {

	@Autowired
	private UserService userService;
		
	@RequestMapping(method=RequestMethod.GET)
	public String getAdminHome(ModelMap mmap) {	
		mmap.addAttribute("users", userService.getAllUsers());		
		return "AdminHome";
	}	
	
	/**
	 * Redirect to admin-user.jsp
	 * @return
	 */
	@RequestMapping(value="/user", method = RequestMethod.GET) 
	public String regUser(UserDTO providedUser, ModelMap model) {	
				
		return "AdminUser";
	}	
}
