/**
 *  This class is used for Products Class
 */
package com.mindfire.wsc.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindfire.wsc.domain.Products;


/**
 * @author bipins
 *
 */
@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
	
	//Get All Products Info
	List<Products> findAll();
	
	// Get the Products Info using productNumber
	public Products findByProductNumber(String productNumber);
	
	//Get the Product Detail Using Product Name
	public Products findByProductname(String productName);
		
}
