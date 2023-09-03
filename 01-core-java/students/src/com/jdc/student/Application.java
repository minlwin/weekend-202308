package com.jdc.student;

import java.util.ArrayList;

import com.jdc.student.actions.Action;
import com.jdc.student.actions.AddNewStudentAction;
import com.jdc.student.actions.InputUtils;
import com.jdc.student.actions.SearchStudentAction;
import com.jdc.student.actions.ShowStudentAction;
import com.jdc.student.model.StudentRegistry;

public class Application {

	public static void main(String [] args) {
		var inputs = new InputUtils();
		var studentRegistry = new StudentRegistry();
		
		var actions = new ArrayList<Action>();
		actions.add(new SearchStudentAction(studentRegistry, inputs));
		actions.add(new AddNewStudentAction(studentRegistry, inputs));
		actions.add(new ShowStudentAction(studentRegistry, inputs));
		
		new AppController(inputs, actions).bootstrap();
	}

}