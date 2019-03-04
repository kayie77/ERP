package com.xhwl.erp.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhwl.erp.domain.OutboundCheckRepository;
import com.xhwl.erp.domain.OutboundListRepository;
import com.xhwl.erp.entity.Account;
import com.xhwl.erp.entity.OutboundCheck;
import com.xhwl.erp.entity.OutboundList;
import com.xhwl.erp.service.OutboundCheckService;
import com.xhwl.erp.util.exception.AppWebException;
import com.xhwl.erp.util.exception.ErrorConstant;
import com.xhwl.erp.util.result.ResultJson;
import com.xhwl.erp.util.util.CheckState;
import com.xhwl.erp.util.util.Constants;

@Service
public class OutboundCheckServiceImpl implements OutboundCheckService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OutboundCheckServiceImpl.class);
	
	@Autowired
	private OutboundCheckRepository  outboundCheckRepository;
	
	@Autowired
	private OutboundListRepository  outboundListRepository;
	
	@Override
	public ResultJson findByOutboundList(Long ol_id) {
		ResultJson resultJson = new ResultJson();
		
		OutboundList outboundList = outboundListRepository.findOne(ol_id);//获得出库清单
		if(outboundList == null) throw new AppWebException(ErrorConstant.DATA_NOTEXIT.getCode(), ErrorConstant.DATA_NOTEXIT.getMsg());
		
		List<OutboundCheck> outboundCheckList = outboundCheckRepository.findByOutboundList(outboundList);
		
		resultJson.setSuccess(true);
		resultJson.setData(outboundCheckList);
		LOGGER.info("根据采购清单id获得数据");
		return resultJson;
	}
	
	@Override
	public ResultJson submit(OutboundCheck outboundCheck) {
		ResultJson resultJson = new ResultJson();
		String step = outboundCheck.getStep();
		/**
		 * 设置当前操作人
		 */
		Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		if(account!=null) {
			outboundCheck.setStepPerson(account.getName());
		}
		/**
		 * 设置操作时间
		 */
		outboundCheck.setTime(new Date());
		/**
		 * 设置出库单外键
		 */
		if(outboundCheck.getOutboundList()!=null) {
			OutboundList outboundList = outboundListRepository.findOne(outboundCheck.getOutboundList().getId());
			if(outboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			outboundCheck.setOutboundList(outboundList);
			//设置相应的审批步骤
			switch (step) {
				case CheckState.SUBMIT:
					outboundCheck.setNextStep(CheckState.OFFICE_CHECK);
					outboundCheck.setNextStepPerson("办事处负责人");
					resultJson.setSuccess(true);	
					resultJson.setData(outboundCheckRepository.save(outboundCheck));
					
					outboundList.setState(CheckState.ING_AUDIT);
					outboundListRepository.save(outboundList);
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
	public ResultJson officeCheck(OutboundCheck outboundCheck) {
		ResultJson resultJson = new ResultJson();
		String step = outboundCheck.getStep();
		/**
		 * 设置当前操作人
		 */
		Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		if(account!=null) {
			outboundCheck.setStepPerson(account.getName());
		}
		/**
		 * 设置操作时间
		 */
		outboundCheck.setTime(new Date());
		/**
		 * 设置出库单外键
		 */
		if(outboundCheck.getOutboundList()!=null) {
			OutboundList outboundList = outboundListRepository.findOne(outboundCheck.getOutboundList().getId());
			if(outboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			outboundCheck.setOutboundList(outboundList);
			//设置相应的审批步骤
			switch (step) {
				case CheckState.PASS:
					outboundCheck.setNextStep(CheckState.COST_CHECK);
					outboundCheck.setNextStepPerson("成本部负责人");
					resultJson.setSuccess(true);	
					resultJson.setData(outboundCheckRepository.save(outboundCheck));
					
					outboundList.setState(CheckState.AD_AUDIT);
					outboundListRepository.save(outboundList);
					break;
				case CheckState.RETURN_FILL:
					outboundCheck.setNextStep(CheckState.TABULATE);
					outboundCheck.setNextStepPerson("制表人");
					resultJson.setSuccess(true);	
					resultJson.setData(outboundCheckRepository.save(outboundCheck));
					
					outboundList.setState(CheckState.UN_AUDIT);
					outboundListRepository.save(outboundList);
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
	public ResultJson costCheck(OutboundCheck outboundCheck) {
		ResultJson resultJson = new ResultJson();
		String step = outboundCheck.getStep();
		/**
		 * 设置当前操作人
		 */
		Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Constants.SESSION_USER_INFO);
		if(account!=null) {
			outboundCheck.setStepPerson(account.getName());
		}
		/**
		 * 设置操作时间
		 */
		outboundCheck.setTime(new Date());
		/**
		 * 设置出库单外键
		 */
		if(outboundCheck.getOutboundList()!=null) {
			OutboundList outboundList = outboundListRepository.findOne(outboundCheck.getOutboundList().getId());
			if(outboundList == null) throw new AppWebException(ErrorConstant.FOREIGN_KEY_NOTEXIT.getCode(), ErrorConstant.FOREIGN_KEY_NOTEXIT.getMsg());
			outboundCheck.setOutboundList(outboundList);
			//设置相应的审批步骤
			switch (step) {
				case CheckState.COST_ACCOUNT:
					resultJson.setSuccess(true);	
					resultJson.setData(outboundCheckRepository.save(outboundCheck));
					
					outboundList.setState(CheckState.COST_ACCOUNT);
					outboundListRepository.save(outboundList);
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
