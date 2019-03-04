package com.xhwl.erp.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.InboundCheckRepository;
import com.xhwl.erp.domain.InboundListRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.InboundCheck;
import com.xhwl.erp.entity.InboundList;
import com.xhwl.erp.service.InboundCheckService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.CheckState;
import com.xhwl.erp.util.util.Constants;

@Service
public class InboundCheckServiceImpl implements InboundCheckService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InboundCheckServiceImpl.class);
	
	@Autowired
	private InboundCheckRepository  inboundCheckRepository;
	
	@Autowired
	private InboundListRepository  inboundListRepository;
	
	@Override
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = new ResultJson();
		resultJson.setData(inboundCheckRepository.findAll(pageable));
		resultJson.setSuccess(true);
		
		LOGGER.info(" \n 分页查询数据：" + " PageNumber = " + pageable.getPageNumber()+ " PageSize = " + pageable.getPageSize());
		return resultJson;
	}
	
	@Override
	public ResultJson findByInboundList(Long il_id) {
		ResultJson resultJson = new ResultJson();
		
		InboundList inboundList = inboundListRepository.findOne(il_id);//获得入库单
		if(inboundList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		List<InboundCheck> inboundCheckList = inboundCheckRepository.findByInboundList(inboundList);
		
		resultJson.setSuccess(true);
		resultJson.setData(inboundCheckList);
		LOGGER.info("根据付款合同id获得数据");
		return resultJson;
	}
	
	@Override
	public ResultJson submit(InboundCheck inboundCheck) {
		ResultJson resultJson = new ResultJson();
		String step = inboundCheck.getStep();
		/**
		 * 设置当前操作人
		 */
		Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		if(account!=null) {
			inboundCheck.setStepPerson(account.getName());
		}
		/**
		 * 设置操作时间
		 */
		inboundCheck.setTime(new Date());
		/**
		 * 设置入库单外键
		 */
		if(inboundCheck.getInboundList()!=null) {
			InboundList inboundList = inboundListRepository.findOne(inboundCheck.getInboundList().getId());
			if(inboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			inboundCheck.setInboundList(inboundList);
			//设置相应的审批步骤
			switch (step) {
				case CheckState.SUBMIT:
					inboundCheck.setNextStep(CheckState.OFFICE_CHECK);
					inboundCheck.setNextStepPerson("办事处负责人");
					resultJson.setSuccess(true);	
					resultJson.setData(inboundCheckRepository.save(inboundCheck));
					
					inboundList.setState(CheckState.ING_AUDIT);
					inboundListRepository.save(inboundList);
					break;
			default:
				resultJson.setSuccess(false);
				resultJson.setErrMsg("数据未正确填写");
				break;
			}
		}
		return resultJson;
	}
	
	@Override
	public ResultJson officeCheck(InboundCheck inboundCheck) {
		ResultJson resultJson = new ResultJson();
		String step = inboundCheck.getStep();
		/**
		 * 设置当前操作人
		 */
		Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		if(account!=null) {
			inboundCheck.setStepPerson(account.getName());
		}
		/**
		 * 设置操作时间
		 */
		inboundCheck.setTime(new Date());
		/**
		 * 设置入库单外键
		 */
		if(inboundCheck.getInboundList()!=null) {
			InboundList inboundList = inboundListRepository.findOne(inboundCheck.getInboundList().getId());
			if(inboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			inboundCheck.setInboundList(inboundList);
			//设置相应的审批步骤
			switch (step) {
				case CheckState.PASS:
					inboundCheck.setNextStep(CheckState.COST_CHECK);
					inboundCheck.setNextStepPerson("成本部负责人");
					resultJson.setSuccess(true);	
					resultJson.setData(inboundCheckRepository.save(inboundCheck));
					
					inboundList.setState(CheckState.AD_AUDIT);
					inboundListRepository.save(inboundList);
					break;
				case CheckState.RETURN_FILL:
					inboundCheck.setNextStep(CheckState.TABULATE);
					inboundCheck.setNextStepPerson("制表人");
					resultJson.setSuccess(true);	
					resultJson.setData(inboundCheckRepository.save(inboundCheck));
					
					inboundList.setState(CheckState.UN_AUDIT);
					inboundListRepository.save(inboundList);
					break;
			default:
				resultJson.setSuccess(false);
				resultJson.setErrMsg("数据未正确填写");
				break;
			}
		}
		return resultJson;
	}
	
	@Override
	public ResultJson costCheck(InboundCheck inboundCheck) {
		ResultJson resultJson = new ResultJson();
		String step = inboundCheck.getStep();
		/**
		 * 设置当前操作人
		 */
		Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		if(account!=null) {
			inboundCheck.setStepPerson(account.getName());
		}
		/**
		 * 设置操作时间
		 */
		inboundCheck.setTime(new Date());
		/**
		 * 设置入库单外键
		 */
		if(inboundCheck.getInboundList()!=null) {
			InboundList inboundList = inboundListRepository.findOne(inboundCheck.getInboundList().getId());
			if(inboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			inboundCheck.setInboundList(inboundList);
			//设置相应的审批步骤
			switch (step) {
				case CheckState.COST_ACCOUNT:
					resultJson.setSuccess(true);	
					resultJson.setData(inboundCheckRepository.save(inboundCheck));
					
					inboundList.setState(CheckState.COST_ACCOUNT);
					inboundListRepository.save(inboundList);
					break;
			default:
				resultJson.setSuccess(false);
				resultJson.setErrMsg("数据未正确填写");
				break;
			}
		}
		return resultJson;
	}
}
