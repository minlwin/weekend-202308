package com.jdc.student.actions;

import com.jdc.student.model.StudentRegistry;

public class AddNewStudentAction extends AbstractAction {

	public AddNewStudentAction(StudentRegistry registry, InputUtils inputs) {
		super(2, "Add New Student", registry, inputs);
	}

	@Override
	public void doAction() {

		// Show Menu
		
		// Get Student Name Input
		var name = inputs.readString("Student Name  : ");
		
		// Get Phone Number Input
		
		// Get Email Address Input
		
		// Create Student Object
		
		// Add To Registry
		
		// Show Result
	}
}