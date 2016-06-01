/**
 * 
 */
package com.mindfire.wsc.controllers.product;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mindfire.wsc.model.ProductCategoryDTO;
import com.mindfire.wsc.model.ProductsDTO;
import com.mindfire.wsc.model.UserDTO;
import com.mindfire.wsc.service.ProductServices;
import com.mindfire.wsc.service.UserService;

/**
 * @author bipins
 * It will be used to add Product Categories and Products to categories, and show its details
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
	 * Used Rirect the Url to Addcategory Jsp page
	 * @admin Session Check
	 */
	@RequestMapping(value = "/addproductcategory", method = RequestMethod.GET)
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
	 * This method is used to check the category name
	 * @param Catgoryname
	 * @return to jsp page
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
	
	/**
	 * This method is used check the user from DB
	 * Parameter used as user id.
	 * @return products details to Jsp in JSON
	 */
	@RequestMapping(value = "/getcategorydetail/{categoryId}", headers="Accept=*/*", method = RequestMethod.GET)
	@ResponseBody
	public Set<ProductsDTO> getProductsUsingCategoryId(HttpServletResponse response, ModelMap model, @PathVariable("categoryId") int  categoryId,HttpSession session) {
		Set<ProductsDTO> products = null;
		
		//Check Session for Admin
		UserDTO user = (UserDTO)session.getAttribute("userSession");
		if(("admin").equals(user.getRole())) {
			logger.info("Admin Session Valid");
			//return new ModelAndView("redirect:/login");
			
		//Set Content type Headers
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
		    //Check if Category name is not null
			if(categoryId >= 0) {
				products = productServices.getProductDetails(categoryId);
			}
		
		} // End First If 
		
		return products;
	}
	
	/**
	 * This method is used to Edit/Modify the Product using productnumber
	 * here productNumber is taken as @PathVaribale, as parameter
	 * @return to EditProducts Jsp page
	 */
	@RequestMapping(value = "/editproducts/{productNumber}", method = RequestMethod.GET)
	public ModelAndView editProducts(ModelMap model, @PathVariable("productNumber") String productNumber, 
			@RequestParam("categoryId") int categoryId, HttpSession session) {
		
		//Check Session for Admin , if not redirect to Login page
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			return new ModelAndView("redirect:/login");
		}
		
		//Null Check
		if (!("").equals(productNumber)) {
			model.addAttribute("productdetails", productServices.modifyProductNumber(productNumber,categoryId));
		}

		return new ModelAndView("EditProducts");
	}
	
	/**
	 * Used to check the ProductName
	 * @param productname
	 * @return ajax type value to jsp
	 */
	@RequestMapping(value = "/checkProductname/{productname}", method = RequestMethod.GET)
	@ResponseBody
	public String validateProductname(HttpServletResponse response,
			@PathVariable("productname") String productName, HttpSession session) {
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			logger.info("Admin Session Not Valid");
			return ("redirect:/login");
		}				
		
		boolean flag = false;
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");    
	    
		if (productName != null) {
			flag = productServices.validateProductname(productName);			
		}	
		if(flag == true) {
			return "Product Name Already Exist, <br>Please try another";
		} else {
			return "Congratulations: "+productName+" is Available";
		}
	}
	
	/**
	 * This method is used Update the Products Info to database
	 * @param productsdto object
	 * @return to AdminHome page
	 */
	@RequestMapping(value = "/updateProducts", method = RequestMethod.POST)
	public String saveUser(@Valid ProductsDTO pdto, BindingResult result) {
		
		logger.info("Product Number: "+pdto.getProductNumber());
		
		//Check it is not null
		if(pdto != null) {			
			//Check if user save successfully
			productServices.updateProducts(pdto);
		}

		return ("redirect:/admin");
	}
	
	/**
	 * This method is used to delete indivisual Product using productnumber
	 * here productNumber is taken as @PathVaribale, as parameter
	 * @return to AdminHome page
	 */
	@RequestMapping(value = "/deleteproducts/{productNumber}", method = RequestMethod.GET)
	public String deleteProducts(ModelMap model, @PathVariable("productNumber") String productNumber, 
			HttpSession session) {
		
		//Check Session for Admin , if not redirect to Login page
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			return ("redirect:/login");
		}
		
		logger.info(productNumber +" Found, to be deleted");
		//Null Check
		if (!("").equals(productNumber)) {
			productServices.deleteProductNumber(productNumber);
		}

		return ("redirect:/admin");
	}
	
	/**
	 * Used to Delete Products Category along with related products
	 * @Session Check for Admin
	 * @return to Popup AdminHome page
	 */
	@RequestMapping(value = "/editdelproductcategory", method = RequestMethod.GET)
	public String editProductCategory(ModelMap mmap, HttpSession session) {
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			logger.info("Admin Session Not Valid");
			return ("redirect:/login");
		}		
		
		//Return All Product category to Admin Page
		mmap.addAttribute("productCategory", productServices.getAllProductCategory());	
		return "DeleteProductCategory";
	}
	
	/**
	 * Used to Delete Products Category along with related products
	 * @Session Check for Admin
	 * @return to AdminHome page
	 */
	@RequestMapping(value = "/editdeleteproductcategory", method = RequestMethod.POST, params="DeleteCategory")
	public String deleteProductCategory(ProductCategoryDTO categoryDTO,ModelMap mmap, HttpSession session) {
		
		logger.info("Category Id "+ categoryDTO.getCategoryId() +" Found");
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			logger.info("Admin Session Not Valid");
			return ("redirect:/login");
		}	
		
		mmap.addAttribute("msg", "Category Deleted Successfully");
		
		if(categoryDTO.getCategoryId() > 0) { 
			//Delete Product category
			productServices.deleteSelectedCategory(categoryDTO.getCategoryId());
		}
		
		//Return
		return "DeleteProductCategory";
	}
	
	/**
	 * Used to Delete Products Category along with related products
	 * @Session Check for Admin
	 * @return to AdminHome page
	 */
	@RequestMapping(value = "/editdeleteproductcategory", method = RequestMethod.POST, params="EditCategory")
	public String editProductCategory(ProductCategoryDTO categoryDTO,ModelMap mmap, HttpSession session) {
		ProductCategoryDTO pdto = null;
		logger.info("Category Id "+ categoryDTO.getCategoryId() +" Found");
		
		//Check Session for Admin
		UserDTO udto = (UserDTO)session.getAttribute("userSession");
		if(!("admin").equals(udto.getRole())) {
			logger.info("Admin Session Not Valid");
			return ("redirect:/login");
		}	
				
		if(categoryDTO.getCategoryId() > 0) { 
			//Delete Product category
			pdto = productServices.editSelectedCategory(categoryDTO.getCategoryId());
		}
		
		mmap.addAttribute("msg", pdto);
		
		//Return
		return "addcategory";
	}
	
}
