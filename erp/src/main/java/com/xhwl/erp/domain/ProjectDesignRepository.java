package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xhwl.erp.entity.Project;
import com.xhwl.erp.entity.ProjectDesign;

public interface ProjectDesignRepository extends PagingAndSortingRepository<ProjectDesign, Long> {
	
	List<ProjectDesign> findByProject(Project project);
	
}