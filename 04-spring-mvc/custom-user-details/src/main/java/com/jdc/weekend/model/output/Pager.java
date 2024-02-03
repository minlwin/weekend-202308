package com.jdc.weekend.model.output;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class Pager {

	private int current;
	private int pageSize;
	private int pageCount;
	private long totalCount;
	private List<Integer> pages;
	
	public Pager(Page<?> page) {
		super();
		this.totalCount = page.getTotalElements();
		this.current = page.getNumber();
		this.pageSize = page.getSize();
		this.pageCount = page.getTotalPages();
	}
	
	
	
}
