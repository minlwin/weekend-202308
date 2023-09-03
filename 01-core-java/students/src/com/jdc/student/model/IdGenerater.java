package com.jdc.student.model;

public class IdGenerater {

	private int current;

	public int next() {
		return ++ current;
	}
}