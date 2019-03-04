package com.xhwl.erp.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xhwl.erp.entity.ProjectReform;
import com.xhwl.erp.service.ProjectReformService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController  
@RequestMapping(path="/projectReform")
@Api(description="智慧社区数据库(市场) - 项目改造信息")
public class ProjectReformController {
	
	@Autowired 
	private ProjectReformService projectReformService;
	
	/**
	 * 查看明细
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/findUpdateData/{id}")
	@RequiresPermissions("projectReform:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = projectReformService.findUpdateData(id);
		return resultJson;
	}
	
	/**
	 * 获取单个年份多项目的改造信息
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param year
	 * @return
	 */
	@GetMapping(path = "/findDataByYear/{year}")
	@RequiresPermissions("projectReform:findDataByYear")
	@ApiOperation(value="获取单个年份多项目的改造信息", notes="获取单个年份多项目的改造信息")
	public ResultJson findDataByYear(@PathVariable String year,@PageableDefault Pageable pageable) {
		
		ResultJson resultJson = projectReformService.findDataByYear(year,pageable);
		return resultJson;
	}
	
	/**
	 *编辑 
	 * @author jiayiwu
	 * @date 2018年4月20日
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/update")
	@RequiresPermissions("projectReform:update")
	@ApiOperation(value="编辑 ", notes="编辑 ")
	public ResultJson update() {
		ResultJson resultJson = new ResultJson();
		resultJson.setData("允许编辑");
		resultJson.setSuccess(true);
	    return resultJson;
	}
	
	/**
	 * 保存对象
	 * @author jiayiwu
	 * @date 2018年1月3日
	 * @param business
	 * @return
	 */
	@PostMapping(path = "/save/{p_id}")
	@RequiresPermissions("projectReform:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@PathVariable Long p_id,@RequestBody ProjectReform projectReform) {
		
		ResultJson resultJson = projectReformService.save(p_id,projectReform);
		return resultJson;
    }

	/**
	 * 删除对象
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param ids
	 * @return
	 */
	@PostMapping(path = "/delete")
	@RequiresPermissions("projectReform:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = projectReformService.delete(id.getId());
		return resultJson;
    }

}
