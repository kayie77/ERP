package com.xhwl.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.ProjectDesignRepository;
import com.xhwl.erp.service.ProjectDesignService;

@Service
public class ProjectDesignServiceImpl implements ProjectDesignService{
	
	
	@Autowired
	ProjectDesignRepository projectDesignRepository;

}
