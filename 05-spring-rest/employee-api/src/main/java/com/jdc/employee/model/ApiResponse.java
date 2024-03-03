package com.jdc.employee.model;

import java.time.LocalDateTime;

public record ApiResponse<T>(
		boolean success,
		LocalDateTime responseAt,
		T payload){

	public static<T> ApiResponse<T> success(T payload) {
		return new ApiResponse<>(true, LocalDateTime.now(), payload);
	}
	
	public static<T> ApiResponse<T> fails(T payload) {
		return new ApiResponse<>(false, LocalDateTime.now(), payload);
	}
}
