package com.xhwl.erp.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xhwl.erp.entity.Project;
import com.xhwl.erp.entity.ProjectReform;

public interface ProjectReformRepository extends PagingAndSortingRepository<ProjectReform, Long> {
	
	List<ProjectReform> findByProject(Project project);
	
	List<ProjectReform> findByYear(String year);
	
}