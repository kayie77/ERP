package com.xhwl.erp.util.util;

/**
 * 分页信息值对象类.
 */
public class PageInfo {
	/**
	 * 单页条目数.
	 */
	private Integer pageSize;

	/**
	 * 当前页码.
	 */
	private Integer pageNumber;

	/**
	 * 总条目数.
	 */
	private Integer totalCount;
	
	private Integer total;
	
	private Object rows;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	
}
