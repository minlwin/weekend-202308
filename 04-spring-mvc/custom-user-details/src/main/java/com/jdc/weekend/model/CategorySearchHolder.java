package com.jdc.weekend.model;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.jdc.weekend.model.input.CategorySearch;

@Service
@SessionScope
public class CategorySearchHolder {

	private CategorySearch search;
	
	public void setSearch(CategorySearch search) {
		this.search = search;
	}
	
	public CategorySearch getSearch() {
		if(null == search) {
			return new CategorySearch(null, null);
		}
		return search;
	}
}
