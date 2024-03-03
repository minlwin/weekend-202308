package com.jdc.employee.model;

public record DataModificationResult<T>(
		T id,
		String message) {

	public static<T> DataModificationResult<T> createResult(T id, String domainName, String fieldName) {
		return new DataModificationResult<>(id, "%s has been created with %s %s.".formatted(domainName, fieldName, id));
	}

	public static<T> DataModificationResult<T> updateResult(T id, String domainName, String fieldName) {
		return new DataModificationResult<>(id, "%s has been updated with %s %s.".formatted(domainName, fieldName, id));
	}
}
