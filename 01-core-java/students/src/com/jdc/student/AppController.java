package com.jdc.student;

import java.util.List;

import com.jdc.student.actions.Action;
import com.jdc.student.actions.InputUtils;

public class AppController {

	private List<Action> actions;
	private InputUtils inputs;

	public AppController(InputUtils inputs, List<Action> actions) {
		this.inputs = inputs;
		this.actions = actions;
	}

	public void bootstrap() {
		
		showMessage("""
				============================
				Student Management System
				============================				
				""");
		
		while(true) {
			
			showMenus();
			int id = getMenuId();
			var action = getAction(id);
			
			if(null == action) {
				break;
			}
			
			System.out.println();
			action.showMenu();
			action.doAction();
			
			System.out.println();
		}
		
		showMessage("""
				============================
				Thank you
				============================				
				""");
		
	}
	
	private Action getAction(int id) {
		
		for(var action : actions) {
			if(action.getId() == id) {
				return action;
			}
		}
		
		return null;
	}

	private void showMessage(String message) {
		System.out.println(message);
	}

	private void showMenus() {
		System.out.println("Menu");
		for(var action : actions) {
			action.showMenu();
		}
		System.out.println();
	}

	private int getMenuId() {
		return inputs.readInt("Menu ID : ");
	}

}