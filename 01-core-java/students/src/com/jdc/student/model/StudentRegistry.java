package com.jdc.student.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRegistry {

	private Map<Integer, Student> students;

	private IdGenerater idGen;

	public StudentRegistry() {
		idGen = new IdGenerater();
		students = new HashMap<Integer, Student>();
	}

	public List<Student> search(String name) {
		// TODO implement here
		return null;
	}

	public int addNew(Student student) {
		var id = idGen.next();
		student.setId(id);
		students.put(id, student);
		return id;
	}

	public Student findById(int id) {
		return students.get(id);
	}

}