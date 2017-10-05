package com.rest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.model.Product;
@Component
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
	
	
	// custom query example and return a stream
    @Query("select p from Product p where lower(p.product_name) like :name")
    List<Product> findByMatchingProductList(@Param("name") String name);
  
    @Query("select p from Product p where lower(p.product_name) like :name")
    public Page<Product> findByMatchingProductPagedList(Pageable pageable,@Param("name") String name);
  
    
    
}
