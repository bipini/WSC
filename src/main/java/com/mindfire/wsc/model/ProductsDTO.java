/**
 * 
 */
package com.mindfire.wsc.model;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.springframework.stereotype.Service;

/**
 * @author bipins
 * This class is used to declare all product fileds with Getter and Setter Method
 */

@Service
public class ProductsDTO {
	
	private int categoryId;
	private String productNumber;
	
	@DecimalMax(value = "999999.99", message = "The decimal value can not be more than 999999.99 ")
    @DecimalMin(value = "1.00", message = "The decimal value can not be less than 1.00 digit ")
	private String productname;
	
	@DecimalMax(value = "999999.99", message = "The decimal value can not be more than 999999.99 ")
    @DecimalMin(value = "1.00", message = "The decimal value can not be less than 1.00 digit ")
	private float quantity;
	
	@DecimalMax(value = "9999.99", message = "The decimal value can not be more than 9999.99 ")
    @DecimalMin(value = "1.00", message = "The decimal value can not be less than 1.00 digit ")
	private float costprice;
	
	@DecimalMax(value = "9999.99", message = "The decimal value can not be more than 9999.99 ")
    @DecimalMin(value = "1.00", message = "The decimal value can not be less than 1.00 digit ")
	private float sellingprice;
	
	private Date timestamp;
	/**
	 * @return the productID
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the productNumber
	 */
	public String getProductNumber() {
		return productNumber;
	}
	/**
	 * @param productNumber the productNumber to set
	 */
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	/**
	 * @return the productname
	 */
	public String getProductname() {
		return productname;
	}
	/**
	 * @param productname the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}
	/**
	 * @return the qunatity
	 */
	public float getQuantity() {
		return quantity;
	}
	/**
	 * @param qunatity the qunatity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the costprice
	 */
	public float getCostprice() {
		return costprice;
	}
	/**
	 * @param costprice the costprice to set
	 */
	public void setCostprice(float costprice) {
		this.costprice = costprice;
	}
	/**
	 * @return the sellingprice
	 */
	public float getSellingprice() {
		return sellingprice;
	}
	/**
	 * @param sellingprice the sellingprice to set
	 */
	public void setSellingprice(float sellingprice) {
		this.sellingprice = sellingprice;
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
