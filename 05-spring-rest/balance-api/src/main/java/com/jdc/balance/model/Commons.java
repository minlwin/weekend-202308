package com.jdc.balance.model;

import java.util.Optional;

public class Commons {

	public static <T, ID> T getOne(Optional<T> optional, String domain, ID id) {
		return optional.orElseThrow(() -> new ApiBusinessException("There is no %s with id %s.".formatted(domain, id)));
	}
}
