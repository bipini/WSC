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
	
	List<Products> findAll();
	
	//List<Products> findByCategoryId(int categoryId);
	
}
