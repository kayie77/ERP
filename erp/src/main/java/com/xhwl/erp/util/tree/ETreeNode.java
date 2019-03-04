package com.xhwl.erp.util.tree;

import java.util.List;

public class ETreeNode {

	private Long value;
	private String label;
	private List<ETreeNode> children;
	
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<ETreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<ETreeNode> children) {
		this.children = children;
	}
	
}
