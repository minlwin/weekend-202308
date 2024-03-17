package com.jdc.employee.model;

public record DataModificationResult<T>(
		T id,
		String message) {
}
