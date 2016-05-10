/**
 * 
 */
package com.mindfire.wsc.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bipins
 * Product Category Domain object. 
 * This is used to map the data between Database and productcategory object
 */

@Entity
@Table(name="productcategory")
public class ProductCategory {
	
	@Id
	@Column
	private int categoryId;
	
	@Column
	private String categoryName;
	
	@Column
	private String addby;
	
	@Column
	private Date timestamp;

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
	 * @return the addby
	 */
	public String getAddby() {
		return addby;
	}

	/**
	 * @param addby the addby to set
	 */
	public void setAddby(String addby) {
		this.addby = addby;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
