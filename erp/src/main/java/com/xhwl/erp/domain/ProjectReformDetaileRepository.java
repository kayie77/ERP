package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xhwl.erp.entity.ProjectReform;
import com.xhwl.erp.entity.ProjectReformDetaile;

public interface ProjectReformDetaileRepository extends PagingAndSortingRepository<ProjectReformDetaile, Long> {
	
	List<ProjectReformDetaile> findByProjectReform(ProjectReform projectReform);
	
}