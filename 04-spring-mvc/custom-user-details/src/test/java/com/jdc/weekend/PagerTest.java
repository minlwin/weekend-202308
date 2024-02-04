package com.jdc.weekend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.jdc.weekend.model.output.Pager;

public class PagerTest {

	@ParameterizedTest
	@CsvSource({
		"23,10,3,1,3",
		"51,10,6,3,5",
		"51,10,6,1,5",
		"71,10,8,7,5",
	})
	void test_building_page_list(long totalCount, int pageSize, int pageCount, int current, int result) {
		var pager = new Pager(current, pageSize, pageCount, totalCount);
		var pages = pager.getPages();
		System.out.println(pages);
		assertEquals(result, pages.size());
	}
	
}
