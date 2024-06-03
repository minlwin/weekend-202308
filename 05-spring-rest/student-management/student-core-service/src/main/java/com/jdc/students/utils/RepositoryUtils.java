package com.jdc.students.utils;

import java.util.Optional;

import com.jdc.students.exceptions.AppBusinessException;

public class RepositoryUtils {

	public static <T> T getOne(Optional<T> optional, String message) {
		return optional.orElseThrow(() -> new AppBusinessException(message));
	}
}
