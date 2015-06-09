package com.haivu.spring.jpa.model;

public class Pagination {

	private Integer page;

	private Integer size;

	private String sort;

	public Pagination() {
		super();
	}

	public Pagination(Integer page, Integer size, String sort) {
		super();
		this.page = page;
		this.size = size;
		this.sort = sort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
