/**
 * 
 */
package com.mindfire.wsc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.wsc.domain.ProductCategory;
import com.mindfire.wsc.model.ProductCategoryDTO;
import com.mindfire.wsc.repositories.ProductRepository;

/**
 * @author bipins
 * This is class is used to Manipulate the product data
 */
@Service
public class ProductServices {
	
	@Autowired
	private ProductRepository prepo;
	
	/*
	 * Get the Category Name as parameter
	 * @parameter categoryName
	 * @Response true if exist, else false
	 */
	public boolean checkCategoryname(String categoryName) {		
		boolean flag = false;
		ProductCategory category = prepo.findByCategoryName(categoryName);
				
		if(category != null)
			flag = true;
		return flag;		
	}
	
	/*
	 * This method is user to push the category name to DB
	 * Get the Username from Session and add it as "addBy"
	 */
	public void saveCategory(ProductCategoryDTO categoryDTO, String username) {	
		
		
		ProductCategory pCategory = convertProductDtoToProductDomain(categoryDTO,username);
		ProductCategory existingCategory = prepo.findByCategoryName(categoryDTO.getCategoryName());
		
		//This is for Existing user to rename the CategoryName to a new name		
		if(existingCategory != null) {
			existingCategory.setAddby(username);
			existingCategory.setCategoryName(pCategory.getCategoryName());
			prepo.saveAndFlush(existingCategory);
		} else {
			// This part is used to add new categories
			prepo.saveAndFlush(pCategory);
		}
		
	}

	private ProductCategory convertProductDtoToProductDomain(
			ProductCategoryDTO categoryDTO , String username) {
		
		ProductCategory productCategory = null;
		if(categoryDTO != null){
			productCategory = new ProductCategory();
			productCategory.setAddby(username);
			productCategory.setCategoryName(categoryDTO.getCategoryName());
			productCategory.setCategoryId(categoryDTO.getCategoryId());			
		}
		return productCategory;		
	}
	
}
