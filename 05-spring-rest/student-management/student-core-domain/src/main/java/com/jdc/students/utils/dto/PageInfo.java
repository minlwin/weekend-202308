package com.jdc.students.utils.dto;

import java.util.List;

public record PageInfo<T>(
		List<T> contents,
		int page,
		int size,
		long total) {

	public long getTotalPage() {
		var pages = total / size;
		return total % size > 0 ? pages + 1 : pages;
	}
}
