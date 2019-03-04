package com.xhwl.erp.domain;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xhwl.erp.entity.Project;
import com.xhwl.erp.entity.Region;

public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {
	
	Page<Project> findByRegion(Region region,Pageable pageable);
	
	List<Project> findByRegion(Region region);
	
	@Query("select p.id from Project p")
	List<Long> getIdArrary();
	
	@Query(value = "select p.id from mk_project p ORDER BY ?#{#pageable}", nativeQuery = true)
	Page<BigInteger> getIdArrary(Pageable pageable);
	
	@Query("select p.id,p.name from Project p where p.name like %:name%")
	List<Object[]> getNameArrary(@Param("name") String name);
	
	@Query("select p.id,p.name from Project p where p.name like %:name% and p.region.code = :region_code")
	List<Object[]> getNameArrary(@Param("name") String name,@Param("region_code") String region_code);
}