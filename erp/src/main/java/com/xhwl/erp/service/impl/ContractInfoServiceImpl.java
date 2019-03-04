package com.xhwl.erp.service.impl;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.BusinessRepository;
import com.xhwl.erp.domain.ContractAnalysisRepository;
import com.xhwl.erp.domain.ContractBasisRepository;
import com.xhwl.erp.domain.ContractInfoRepository;
import com.xhwl.erp.domain.ContractScheduleRepository;
import com.xhwl.erp.entity.Business;
import com.xhwl.erp.entity.ContractAnalysis;
import com.xhwl.erp.entity.ContractBasis;
import com.xhwl.erp.entity.ContractInfo;
import com.xhwl.erp.entity.ContractSchedule;
import com.xhwl.erp.service.ContractInfoService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;

@Service
public class ContractInfoServiceImpl implements ContractInfoService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractInfoServiceImpl.class);
	
	@Autowired
	private ContractInfoRepository contractInfoRepository;
	
	@Autowired
	private ContractScheduleRepository contractScheduleRepository;
	
	@Autowired
	private ContractAnalysisRepository contractAnalysisRepository;
	
	@Autowired
	private ContractBasisRepository contractBasisRepository;
	
	@Autowired 
	private BusinessRepository businessRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		Page<ContractInfo> contractInfoList = null;
		contractInfoList = contractInfoRepository.findAll(pageable);
		
		resultJson.setData(contractInfoList);
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findUpdateData(Long id) {
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		ContractInfo contractInfo = contractInfoRepository.findOne(id);
		if(contractInfo == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		String oldCity = contractInfo.getOldCity();
		
		resultMap.put("oldCity", oldCity);
		resultMap.put("contractInfo", contractInfo);
		resultJson.setData(resultMap);
		resultJson.setSuccess(true);
		LOGGER.info("获取更新实体时所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson findAllByBussiness(Long b_id) {
		
		ResultJson resultJson = new ResultJson();
		Business business = businessRepository.findOne(b_id);
		if(business == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		resultJson.setData(contractInfoRepository.findByBusiness(business));
		resultJson.setSuccess(true);
		LOGGER.info("根据商机id获取所需数据");
		return resultJson;
	}
	
	@Override
	public ResultJson save(ContractInfo contractInfo) {
		ResultJson resultJson = new ResultJson();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(contractInfo.getBusiness()!=null) {
			Business business = businessRepository.findOne(contractInfo.getBusiness().getId());
			if(business == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			contractInfo.setBusiness(business);//设置商机
			contractInfo.setCode(business.getCode());//设置合同编码为商机编码
		}
		
		/**
		 * 同时新增项目进度实体、项目分析实体、新增合同交底实体
		 */
		
		if(contractInfo.getId()==null) {
			/**
			 *  当合同基础信息是新增数据时，需要新增合同进度跟踪实体，如果是编辑，则不需要新增
			 */
			ContractSchedule contractSchedule = new ContractSchedule();
			contractSchedule.setTime(new Date());
			contractSchedule.setContractInfo(contractInfo);
			contractScheduleRepository.save(contractSchedule);
			resultMap.put("contractSchedule", contractSchedule);
			/**
			 *  当合同基础信息是新增数据时，需要新增项目分析进度实体，如果是编辑，则不需要新增
			 */
			ContractAnalysis contractAnalysis = new ContractAnalysis();
			contractAnalysis.setContractInfo(contractInfo);
			contractAnalysisRepository.save(contractAnalysis);
			resultMap.put("contractAnalysis", contractAnalysis);
			
			/**
			 *  当合同基础信息是新增数据时，需要新增合同交底实体，如果是编辑，则不需要新增
			 */
			ContractBasis contractBasis = new ContractBasis();
			contractBasis.setContractInfo(contractInfo);
			contractBasisRepository.save(contractBasis);
			resultMap.put("contractBasis", contractBasis);
		}
		/**
		 * 保存进度分析中的合同总额
		 */
		ContractAnalysis contractAnalysis = contractAnalysisRepository.findByContractInfo(contractInfo);
		if(contractAnalysis == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		contractAnalysis.setContractTotalAmount(contractInfo.getContractTotalAmount());
		contractAnalysisRepository.save(contractAnalysis);
		
		/**
		 * 保存合同信息实体
		 */
		contractInfoRepository.save(contractInfo);
		resultMap.put("contractInfo", contractInfo);

		resultJson.setSuccess(true);		
		resultJson.setData(resultMap);
		LOGGER.info("保存实体");
		return resultJson;
	}
	
	@Override
	public ResultJson delete(Long[] ids) {
		
		ResultJson resultJson = new ResultJson();
		//循环删除
		for( int i = 0 ; i < ids.length; i++) {
			ContractInfo contractInfo = contractInfoRepository.findOne(ids[i]);
			if(contractInfo == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
			contractInfoRepository.delete(contractInfo);
		}
		
		resultJson.setSuccess(true);
		LOGGER.info("删除实体");
		return resultJson;
		
	}
	
	@Override
	public ResultJson search(String name,String code,Date term,Date signDate,Long region_id,String businessCtg_name, Pageable pageable) {
		ResultJson resultJson = new ResultJson();
		
		Page<ContractInfo> contractInfoList = contractInfoRepository.findAll(new Specification<ContractInfo>(){
			@Override
			public Predicate toPredicate(Root<ContractInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<>(); 
				if(null != name){
        			predicates.add(cb.like(root.get("name"), "%"+name+"%"));
	            }
				if(null != code){
	        			predicates.add(cb.like(root.get("code"), "%"+code+"%"));
	            }
                if(null != term){
                    predicates.add(cb.equal(root.get("term"), term));
                }
                if(null != signDate){
                		predicates.add(cb.equal(root.get("signDate"), signDate));
                }
                if(null != businessCtg_name){
	            		predicates.add(cb.like(root.get("business").get("businessCategory").get("name").as(String.class), "%"+businessCtg_name+"%"));
	            }
                if(null != region_id){
	            		predicates.add(cb.equal(root.get("business").get("region").get("id").as(Long.class),region_id));
	            }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		 
		resultJson.setSuccess(true);
		resultJson.setData(contractInfoList);
		LOGGER.info("数据查询");
		return resultJson;
	}
}
