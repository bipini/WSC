/**
 * 
 */
package com.mindfire.wsc.model;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Service;

/**
 * @author bipins
 *
 */

@Service
public class ProductCategoryDTO {	
	
	private int categoryId;
	
	@Size(min = 5, max = 100)
	private String categoryName;
	private String addBy;	
	
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the addBy
	 */
	public String getAddBy() {
		return addBy;
	}
	/**
	 * @param addBy the addBy to set
	 */
	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}	
	
}
