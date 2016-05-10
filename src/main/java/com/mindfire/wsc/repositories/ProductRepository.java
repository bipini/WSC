/**
 * Product Repository
 * This is used to get all product Informations from DB
 */
package com.mindfire.wsc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindfire.wsc.domain.ProductCategory;

/**
 * @author bipins
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductCategory, Long> {

	public ProductCategory findByCategoryName(String categoryName);

}
