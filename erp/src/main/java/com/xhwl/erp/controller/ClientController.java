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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xhwl.erp.entity.Client;
import com.xhwl.erp.service.ClientService;
import com.xhwl.erp.util.bean.ID;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/client")
@Api(description="客户管理(基础)")
public class ClientController {
	
	@Autowired 
	private ClientService clientService;
	
	/**
	 * 获取分页数据列表
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@RequiresPermissions("client:findAllByPage")
	@ApiOperation(value="获取分页数据列表", notes="获取分页数据列表")
	public ResultJson findAllByPage(Pageable pageable) {
		
		ResultJson resultJson = clientService.findAllByPage(pageable);
		return resultJson;
	}
	
	/**
	 * 查看明细
	 * @author jiayiwu
	 * @date 2018年1月5日
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/findUpdateData/{id}")
	@RequiresPermissions("client:findUpdateData")
	@ApiOperation(value="查看明细", notes="查看明细")
	public ResultJson findUpdateData(@PathVariable Long id) {
		
		ResultJson resultJson = clientService.findUpdateData(id);
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
	@RequiresPermissions("client:update")
	@ApiOperation(value="编辑 ", notes="编辑 ")
	public ResultJson update(@PathVariable Long id) {
		ResultJson resultJson = new ResultJson();
		resultJson.setData("允许编辑");
		resultJson.setSuccess(true);
	    return resultJson;
	}

	/**
	 * 保存对象
	 * @author jiayiwu
	 * @date 2018年1月8日
	 * @param client
	 * @return
	 */
	@PostMapping(path = "/save")
	@RequiresPermissions("client:save")
	@ApiOperation(value="保存对象", notes="保存对象")
    public ResultJson save(@RequestBody Client client) {
		
		ResultJson resultJson = clientService.save(client);
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
	@RequiresPermissions("client:delete")
	@ApiOperation(value="删除对象", notes="删除对象")
    public ResultJson deleteObject(@RequestBody ID id) {
		
		ResultJson resultJson = clientService.delete(id.getId());
		return resultJson;
    }
	
	/**
	 * 数据查询
	 * @author jiayiwu
	 * @date 2018年1月12日
	 * @return
	 */
	@PostMapping(path = "/search")
	@RequiresPermissions("client:search")
	@ApiOperation(value="数据查询", notes="数据查询")
    public ResultJson search(@ApiParam(value = "客户名称") @RequestParam(required=false) String name,@ApiParam(value = "客户类别") @RequestParam(required=false) String category,
    		@ApiParam(value = "业态") @RequestParam(required=false) String type,@ApiParam(value = "联系人") @RequestParam(required=false) String person,@PageableDefault(value = 15) Pageable pageable) {
		
		ResultJson resultJson = clientService.search(name,category,type,person,pageable);
		return resultJson;
    }
	
    /**
     * 数据导出
     * @author jiayiwu
     * @date 2018年3月22日
     * @return
     */
	@PostMapping(path = "/export")
    @RequiresPermissions("client:export")
	@ApiOperation(value="数据导出", notes="数据导出")
    public @ResponseBody ResultJson export() {
    		ResultJson resultJson = new ResultJson();
    		resultJson.setData("允许数据导出");
    		resultJson.setSuccess(true);
        return resultJson;
    }
}
