package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.OrganizationRepository;
import com.xhwl.erp.entity.Organization;
import com.xhwl.erp.service.OrganizationService;
import com.xhwl.erp.util.tree.ETreeNode;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	@Autowired
	OrganizationRepository  organizationRepository;
	
	@Override
	public List<ETreeNode> tree() {
		
		List<Organization> organizationList = (List<Organization>) organizationRepository.findAll();
		List<Organization> firstLeafs = new ArrayList<Organization>();
		List<Organization> secondLeafs = new ArrayList<Organization>();
		List<ETreeNode> firstNodes = new ArrayList<ETreeNode>();
		List<ETreeNode> resultlist = new LinkedList<ETreeNode>();
		
		/**
		 * 循环筛选1级和2级树节点
		 */
		if(!organizationList.isEmpty()){
			for (Organization object : organizationList)
	        {
				if(object.getOrganization()!=null && (0 == object.getOrganization().getId()) ) {
					firstLeafs.add(object);//一级子节点
				}else if(object.getOrganization()!=null && (0 != object.getOrganization().getId()) ){
					secondLeafs.add(object);//二级子节点
				}
	        }
		}
		
		/**
		 * 将1，2级树节点组合成树装json结构
		 */
		ETreeNode parent = new ETreeNode();
		long v = 0;
		parent.setValue(v);
		parent.setLabel("中海物业");
		
		for (Iterator<Organization> firlIterator = firstLeafs.iterator(); firlIterator.hasNext();) {
			List<ETreeNode> secondNodes = new ArrayList<ETreeNode>();
			Organization firstLeaf = (Organization)firlIterator.next();
			ETreeNode children1 = new ETreeNode();
			children1.setValue(firstLeaf.getId());
			children1.setLabel(firstLeaf.getName());
			firstNodes.add(children1);
			for (Iterator<Organization> seclIterator = secondLeafs.iterator(); seclIterator.hasNext();){
				Organization secondLeaf = (Organization)seclIterator.next();
				if (secondLeaf.getOrganization()!=null && firstLeaf.getId().equals(secondLeaf.getOrganization().getId())) {
					ETreeNode children2 = new ETreeNode();
					children2.setValue(secondLeaf.getId());
					children2.setLabel(secondLeaf.getName());
					secondNodes.add(children2);
				}
			}
			children1.setChildren(secondNodes);
		}
		parent.setChildren(firstNodes);
		resultlist.add(parent);
		
		LOGGER.info("返回组织架构列表-树形结构");
		return resultlist;
	}
	
}
