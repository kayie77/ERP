package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.Organization;
import com.xhwl.erp.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	 List<User> findByName(String name);
	 
	 Page<User> findByOrganization(Organization organization,Pageable pageable);
	 
	 User findByAccount(Account account);
	 
	 @Query("select u.id ,u.code ,u.name ,u.phone from User u where u.name like %:name%")
	 List<Object[]> getNameArrary(@Param("name") String name);

}