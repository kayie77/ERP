package com.xhwl.erp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.alibaba.fastjson.JSONObject;
import com.xhwl.erp.entity.PurchaseList;
import com.xhwl.erp.util.result.ResultJson;

/**
 * 采购清单业务接口
 * @author jiayiwu
 * @date 2018年1月4日
 */
public interface PurchaseListService {
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	ResultJson findAllByPage(Pageable pageable);
	
	/**
	 * 根据付款合同获取全部数据
	 * @author jiayiwu
	 * @date 2018年2月7日
	 * @param pc_id
	 * @return
	 */
	ResultJson findAllByPaymentContract(Long pc_id);
	
	/**
	 * 获取更新时所需数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	ResultJson findUpdateData(Long id);
	
	/**
	 * 导入数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param purchaseList
	 * @return
	 */
	ResultJson importData(List<PurchaseList> purchaseListArrary,Long pc_id);
	
	/**
	 * 保存数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param purchaseList
	 * @return
	 */
	PurchaseList save(JSONObject jsonObject);
	
	/**
	 * 删除数据
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	ResultJson delete(Long[] ids);
	
}
