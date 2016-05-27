/**
 * 
 */
package com.mindfire.wsc.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author bipins
 *
 */

@Entity
@Table(name = "products")
public class Products {
	
	@Id
    @GeneratedValue
	@Column(name="ProductNumber", unique=true, nullable=false)	
	private String productNumber;
		
	//private int categoryId;
	
	@Column
	private String productname;
	
	@Column
	private float quantity;
	
	@Column
	private float costprice;
	
	@Column
	private float sellingprice;
	
	@Column
	private Date timestamp;
	
	@ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name="categoryId")
    private ProductCategory productCategory;
		
	public Products() {}    
	
	public Products(String productNumber, String productname, float quantity , float costprice, float sellingprice) {
		this.productNumber = productNumber;
		this.productname = productname;
		this.quantity = quantity;
		this.costprice = costprice;
		this.sellingprice = sellingprice;
	}   
		
	
	/**
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the productCategory
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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
