package com.xhwl.erp.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.CityRepository;
import com.xhwl.erp.entity.City;
import com.xhwl.erp.service.CityService;
import com.xhwl.erp.util.tree.ETreeNode;

@Service
public class CityServiceImpl implements CityService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Autowired
	CityRepository cityRepository;
	
	@Override
	public List<ETreeNode> tree() {
		
		List<City> cityList = (List<City>) cityRepository.findAll();
		List<City> firstLeafs = new ArrayList<City>();
		List<City> secondLeafs = new ArrayList<City>();
		List<ETreeNode> firstNodes = new ArrayList<ETreeNode>();
		List<ETreeNode> resultlist = new LinkedList<ETreeNode>();
		
		/**
		 * 循环筛选1级和2级树节点
		 */
		if(!cityList.isEmpty()){
			for (City object : cityList)
	        {
				if(object.getCity()!=null && (100000 == object.getCity().getId()) ) {
					firstLeafs.add(object);//一级子节点
				}else if(object.getCity()!=null && (100000 != object.getCity().getId()) ){
					secondLeafs.add(object);//二级子节点
				}
	        }
		}
		
		/**
		 * 将1，2级树节点组合成树装json结构
		 */
		ETreeNode parent = new ETreeNode();
		long v = 100000;
		parent.setValue(v);
		parent.setLabel("中国");
		
		for (Iterator<City> firlIterator = firstLeafs.iterator(); firlIterator.hasNext();) {
			List<ETreeNode> secondNodes = new ArrayList<ETreeNode>();
			City firstLeaf = (City)firlIterator.next();
			ETreeNode children1 = new ETreeNode();
			children1.setValue(firstLeaf.getId());
			children1.setLabel(firstLeaf.getName());
			firstNodes.add(children1);
			for (Iterator<City> seclIterator = secondLeafs.iterator(); seclIterator.hasNext();){
				City secondLeaf = (City)seclIterator.next();
				if (secondLeaf.getCity()!=null && firstLeaf.getId().equals(secondLeaf.getCity().getId())) {
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
		
		LOGGER.info("返回城市列表-树形结构");
		return resultlist;
	}
	
}
