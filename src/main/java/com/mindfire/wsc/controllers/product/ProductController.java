/**
 * 
 */
package com.mindfire.wsc.controllers.product;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mindfire.wsc.model.ProductCategoryDTO;
import com.mindfire.wsc.model.ProductsDTO;
import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.service.ProductServices;
import com.mindfire.wsc.service.UserService;

/**
 * @author bipins
 * It will be used to add Categories of product and show its details
 *
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private UserService userService;	
	
	/**
	 * Simply selects the home view to render by returning its name. Its now redirect to login page
	 * Used for Redirect
	 * @From Browser
	 */
	@RequestMapping(value = "/addcategory", method = RequestMethod.GET)
	public String addProductCategory(HttpSession session) {
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			logger.info("Admin Session Not Valid");
			return ("redirect:/login");
		}		
	
		return "addcategory";
	}
	
	/**
	 * Simply selects the home view to render by returning its name. Its now redirect to login page
	 * Used for Redirect
	 * @From Browser
	 */
	@RequestMapping(value = "/addproduct/{categoryName}", method = RequestMethod.GET)
	@ResponseBody
	public String addProductCategoryToDB(HttpServletResponse response,
			@PathVariable("categoryName") String categoryName, HttpSession session) {
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			logger.info("Admin Session Not Valid");
			return ("redirect:/login");
		}
				
		//logger.info("Welcome home! The client locale is {}.", locale);
	
		boolean flag = false;
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");    
	    
		if (categoryName != null) {
			flag = productServices.checkCategoryname(categoryName);			
		}	
		if(flag == true) {
			return "Category Already Exist, <br>Please try another";
		} else {
			return "Congratulations: "+categoryName+" is Available";
		}
	}
	
	/**
	 * This method is used to save the Product Category to DB
	 * After done Redirect to Admin Home page
	 * @param ProductCategory
	 */
	@RequestMapping(value = "/savecategoryname", method = RequestMethod.POST)
	public String saveProductCategory(ProductCategoryDTO categoryDTO,HttpSession session) {			
				
		//Check Session for Admin
		UserDTO user = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(user.getRole())) {
			logger.info("Admin Session Not Valid");
			return ("redirect:/login");
		}	
		
		if(categoryDTO != null) {
			
			//Check if user save successfully
			try {
				productServices.saveCategory(categoryDTO,user.getUserName());	
			} catch(Exception e) {
				e.printStackTrace();
				return "admin";	
			}
		}

		return ("redirect:/admin");		
	}
	
	/*
	 * This method is used check the user from DB
	 * Parameter used as user id.
	 */
	@RequestMapping(value = "/getcategorydetail/{categoryId}", headers="Accept=*/*", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductsDTO> getProductsUsingCategoryId(HttpServletResponse response, ModelMap model, @PathVariable("categoryId") int  categoryId,HttpSession session) {
		List<ProductsDTO> products = null;
		
		//Check Session for Admin
		UserDTO user = (UserDTO)session.getAttribute("userSession");
		if(("admin").equals(user.getRole())) {
			logger.info("Admin Session Not Valid");
			//return new ModelAndView("redirect:/login");
			
		//Set Content type Headers
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    //Check if Category name is not null
		if(categoryId >= 0) {
			products = productServices.getProductDetails(categoryId);
		}
		
		//model.addAttribute("productsDetails", products);	
		return products;
		}
		
		return products;
	}
}
