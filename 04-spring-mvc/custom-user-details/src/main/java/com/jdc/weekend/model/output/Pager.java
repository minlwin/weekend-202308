package com.jdc.weekend.model.output;

import java.util.ArrayList;
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
	
	public Pager(int current, int pageSize, int pageCount, long totalCount) {
		super();
		this.current = current;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		
		buildPages();
	}	
	
	public Pager(Page<?> page) {
		super();
		this.totalCount = page.getTotalElements();
		this.current = page.getNumber() + 1;
		this.pageSize = page.getSize();
		this.pageCount = page.getTotalPages();

		buildPages();
	}

	private void buildPages() {

		this.pages = new ArrayList<>();
		
		pages.add(current);
		
		while(pages.size() < 3 && pages.get(0) > 1) {
			pages.addFirst(pages.get(0) - 1);
		}
		
		while(pages.size() < 5 && pages.get(pages.size() - 1) < pageCount) {
			pages.add(pages.get(pages.size() - 1) + 1);
		}
		
		while(pages.size() < 5 && pages.get(0) > 1) {
			pages.addFirst(pages.get(0) - 1);
		}
	}
	
}
