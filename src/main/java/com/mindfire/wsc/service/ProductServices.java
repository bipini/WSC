/**
 * 
 */
package com.mindfire.wsc.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindfire.wsc.domain.ProductCategory;
import com.mindfire.wsc.domain.Products;
import com.mindfire.wsc.model.ProductCategoryDTO;
import com.mindfire.wsc.model.ProductsDTO;
import com.mindfire.wsc.repositories.ProductRepository;
import com.mindfire.wsc.repositories.ProductsRepository;

/**
 * @author bipins
 * This is class is used to Manipulate the product data
 */
@Service
public class ProductServices {
	
	private static final Logger log = Logger.getLogger(ProductServices.class);
	
	@Autowired
	private ProductRepository prepo;
	
	@Autowired
	private ProductsRepository productsrepo;
	
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
		
		
		ProductCategory pCategory = convertProductCategoryDtoToProductCategoryDomain(categoryDTO,username);
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
	
	/*
	 * Convert Productdto object to ProductDomain object
	 */
	private ProductCategory convertProductCategoryDtoToProductCategoryDomain(
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
	
	/*
	 * Get all product Category
	 */
	public List<ProductCategoryDTO> getAllProductCategory() {

		List<ProductCategoryDTO> productDtos = new ArrayList<ProductCategoryDTO>();
		List<ProductCategory> categories = prepo.findAll();
		
		for(ProductCategory categorie : categories ){			
			productDtos.add(convertProductCategoryDomainToProductCategoryDto(categorie));			
		}
		
		return productDtos;		
	}
	
	/*
	 * Convert ProductCategoryDomain object to Dto object
	 */
	private ProductCategoryDTO convertProductCategoryDomainToProductCategoryDto(
			ProductCategory categorie) {
		ProductCategoryDTO pcategorydto = null;		
		if(categorie != null) {
			pcategorydto = new ProductCategoryDTO();
			pcategorydto.setCategoryId(categorie.getCategoryId());
			pcategorydto.setAddBy(categorie.getAddby());
			pcategorydto.setCategoryName(categorie.getCategoryName());
		}
		return pcategorydto;
	}
	
	/*
	 * Get List of Products based on Category ID
	 */
	public Set<ProductsDTO> getProductDetails(int categoryId) {
		
		//Get the Product Category object
		ProductCategory pCategory = prepo.findByCategoryId(categoryId);
				
		Set<ProductsDTO> productsDtos = new HashSet<ProductsDTO>();
		
		//Get all products using Category Id
		Set<Products> allproducts = pCategory.getProducts();
		
		for(Products products : allproducts ){			
			productsDtos.add(convertProductsDomainToProductsDto(products));			
		}
		
		return productsDtos;	
	}
		
	
	/*
	 * Convert Products Domain to Products DTO
	 */
	private ProductsDTO convertProductsDomainToProductsDto(Products products) {
		
		ProductsDTO productDto = null;
		if(products != null) {
			productDto = new ProductsDTO();
			productDto.setProductNumber(products.getProductNumber());
			productDto.setCostprice(products.getCostprice());
			productDto.setProductname(products.getProductname());
			productDto.setSellingprice(products.getSellingprice());
			productDto.setQuantity(products.getQuantity());				
		}
		return productDto;
	}
	
	//Get the Products detail using ProductNumber
	public ProductsDTO modifyProductNumber(String productNumber,int categoryId) {
		
		// Call to ProductsRepository to get Products Detail
		ProductsDTO productsDto = convertProductsDomainToProductsDto(productsrepo.findByProductNumber(productNumber));
		
		//Add the Category Id to Products DTO object
		productsDto.setCategoryId(categoryId);
		
		return productsDto;
	}
	
	/*
	 * Check the Product Name if it is available or not
	 */
	public boolean validateProductname(String productName) {
		
		boolean flag = false;
		Products product = productsrepo.findByProductname(productName);
				
		if(product != null)
			flag = true;
		return flag;		
	}
	
	/*
	 * save/update the Products To Products Table
	 */
	public void updateProducts(ProductsDTO pdto) {
		Products products = convertProductsDtoToProductsDomain(pdto);
		Products existingproduct = productsrepo.findByProductNumber(pdto.getProductNumber());
		
		log.info("Service Product Number: "+existingproduct.getProductNumber());
		
		log.info("Service Category Id: "+pdto.getCategoryId());
		
		// Check if it is an existing product, then update it
		if(existingproduct != null) {
			products.setProductNumber(existingproduct.getProductNumber());
			products.setProductCategory(prepo.findByCategoryId(pdto.getCategoryId()));			
		}
		
		// Save the Domain object to Db
		productsrepo.saveAndFlush(products);
	}
	
	//Convert Products DTO to Products Domain
	private Products convertProductsDtoToProductsDomain(ProductsDTO pdto) {
		Products product = null;
		if(pdto != null) {
			product = new Products();
			product.setProductNumber(pdto.getProductNumber());
			product.setProductname(pdto.getProductname());
			product.setQuantity(pdto.getQuantity());
			product.setCostprice(pdto.getCostprice());
			product.setSellingprice(pdto.getSellingprice());			
		}
		return product;
	}
	
	/*
	 * Delete the Prodcuts using ProductNumber
	 */
	public void deleteProductNumber(String productNumber) {
		
		//Get Product Infor using ProductNumber
		Products product = productsrepo.findByProductNumber(productNumber);
		
		//Delete the product
		if(product != null) {
			productsrepo.delete(product);
		}
		log.info(productNumber+ "Deleted");
	}

	public void deleteSelectedCategory(int categoryId) {
		
		//Get the Product Category object
		ProductCategory pCategory = prepo.findByCategoryId(categoryId);	
		
		//Get all products using Category Id
		Set<Products> allproducts = pCategory.getProducts();		
	
		//Check the Null and Delete the Category
		if(pCategory != null) {
			if(allproducts != null) {				
				productsrepo.delete(allproducts);
			}
			prepo.delete(pCategory);
		}
	}
	
}
