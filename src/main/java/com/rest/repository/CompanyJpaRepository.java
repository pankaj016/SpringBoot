package com.rest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.model.Company;
@Component
public interface CompanyJpaRepository extends JpaRepository<Company, Long> {
	
	// custom query example and return a stream
    @Query("select c from Company c where lower(c.company_name) like :name")
    List<Company> findByMatchingCompanyList(@Param("name") String name);
    
    @Query("select c from Company c where lower(c.company_name) like :name")
    public Page<Company> findMyCompanyTop10List(Pageable pageable,@Param("name") String name);
  
}
