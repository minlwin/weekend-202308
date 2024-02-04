package com.jdc.weekend.model.output;

import java.time.LocalDateTime;

public record CategoryInfoDetails(
		int id,
		String name,
		String description,
		long posts,
		boolean deleted,
		LocalDateTime createAt,
		String createBy
		) {

}
