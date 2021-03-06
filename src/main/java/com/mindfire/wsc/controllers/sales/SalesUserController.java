/**
 * 
 */
package com.mindfire.wsc.controllers.sales;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mindfire.wsc.domain.Fileupload;
import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.service.FileUploadServices;
import com.mindfire.wsc.service.UserService;

/**
 * This provides user to control the functionality
 * of theirs. Like updating user details
 * @author Bipin
 *
 */

@Controller
@RequestMapping(value = "sales")
public class SalesUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileUploadServices uploadServices;
	/*
	 * This method used to check the user first name and last name
	 * if it is blank, it redirect to user detail form
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getAdminHome(UserDTO providedUser,HttpSession session) {			
		
		providedUser = (UserDTO)session.getAttribute("userSession");
		System.out.println("First Name"+providedUser.getFirstName());
		if((providedUser.getFirstName()) == null && (providedUser.getLastName()) == null) {
			return new ModelAndView("redirect:/sales/userdetail");
		} else {
		return new ModelAndView("redirect:/sales/user");
		}		
	}
	
	/*
	 * This method will redirect to User detail page
	 */
	@RequestMapping(value="/userdetail",method=RequestMethod.GET)
	public String getUserHome() {			
		return "UserDetail";
	}
	
	/*
	 * This method will redirect to User page
	 */
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String getSalesHome() {			
		return "SalesHome";
	}
	
	/*
	 * This method used Update the users data to user's table
	 * Also do the validation of the fields
	 */
	@RequestMapping(value="/user/updateUser",method=RequestMethod.POST)
	public String updateUserdata(UserDTO user, HttpSession session) {	
			
		if (user != null) {					
			userService.saveUser(user);		
			
			//Override the existing session with latest update
			user = userService.getUserByUserName(user.getUserName());
			session.setAttribute("userSession", user);
		}
			return ("redirect:/login");			
	}
	
	
	/*
	 * This method is used Change the user Password from DB
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/admin/user/changepassword/{ename}", method = RequestMethod.GET)
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
	 * This method is used Update the User profile 
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/admin/user/editprofile/{ename}", method = RequestMethod.GET)
	public ModelAndView updateprofile(ModelMap model, @PathVariable("ename") String ename,HttpSession session) {
		UserDTO sessionUser = (UserDTO) session.getAttribute("userSession");
		UserDTO dtoUser = userService.getUserByUserName(ename);
		if((sessionUser.getUserName() != null)) {
			if(sessionUser.getUserName().equals(dtoUser.getUserName())) {		
				return new ModelAndView("updateprofile");
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
	 * This method is used Update the User profile Photo
	 * Parameter used FileUpload , @RequestParam and Session.
	 */
	@Autowired
	private ResourceLoader resourceLoader;
	@RequestMapping(value = "/user/doUpload", method = RequestMethod.POST)
	public ModelAndView save(
            @ModelAttribute("fileupload") Fileupload fileupload,
            @RequestParam("filecontent") MultipartFile file, HttpSession session) {
         
		UserDTO sessionUser = (UserDTO) session.getAttribute("userSession");
		
		if (!file.isEmpty()) {
            try {                       
                String orgName = file.getOriginalFilename();                
                File dest = new File(orgName);
                
                // Get the Absolute path
                File resource = resourceLoader.getResource("resources/images/"+orgName).getFile();
                
                System.out.println("Filepath:" + resource.getAbsolutePath());     
                System.out.println("ContentType:" + file.getContentType());
                
                // Check if the file is an image or not in Server
                String mimetype= new MimetypesFileTypeMap().getContentType(dest);
                String type = mimetype.split("/")[0];
                if(type.equals("image"))  {                   
                	//Save the file to Folder                	
                	file.transferTo(resource);                	
                }
                else 
                	return new ModelAndView("redirect:/login");         
               
                //Set the parameter to Domain object
                fileupload.setFilename(file.getOriginalFilename());
                
                //Set the path to FileUpload
                fileupload.setFilepath(resource.getAbsolutePath());
                
                //Set the Users data to FileUpload
                fileupload.setUserdata(userService.convetUserDTOUserDomain(sessionUser));
                
                //Save the Photo to DB
                uploadServices.savephoto(fileupload, sessionUser);
                
                //set the image path to Session
                session.setAttribute("userPhoto", resource.getAbsolutePath());
            } 
            catch(Exception e) {
            	e.printStackTrace();
            }
		}
         
        return new ModelAndView("redirect:/login");	
    } 
}
