package com.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.model.S_User;
@Component
public interface S_UserJpaRepository extends JpaRepository<S_User, Long> {
	
	List<S_User> findByFirstName(String firstName);
	List<S_User> findByLastName(String lastName);
	
	
	// custom query example and return a stream
    @Query("select s from S_User s where s.sdxFN = :sdxCd or s.sdxLN = :sdxCd")
    List<S_User> findByMatchingUserList(@Param("sdxCd") String sdxCd);
    
    
    @Query("select s from S_User s where lower(s.firstName) like :name or lower(s.lastName) like :name")
    List<S_User> findBySoundexUserList(@Param("name") String name);

}
