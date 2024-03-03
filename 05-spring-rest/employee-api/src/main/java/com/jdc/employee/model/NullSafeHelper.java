package com.jdc.employee.model;

import java.util.Optional;

public class NullSafeHelper {

	public static<T, ID> T safeCalll(Optional<T> optional, String domain, ID id) {
		return optional.orElseThrow(() -> new BusinessException("There is no %s with %s.".formatted(domain, id)));
	}
}
