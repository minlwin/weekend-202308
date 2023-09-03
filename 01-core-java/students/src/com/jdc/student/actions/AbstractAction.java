package com.jdc.student.actions;

import com.jdc.student.model.StudentRegistry;

public abstract class AbstractAction implements Action {

	private int id;
	private String name;
	protected StudentRegistry registry;
	protected InputUtils inputs;
	
	public AbstractAction(int id, String name, StudentRegistry registry, InputUtils inputs) {
		super();
		this.id = id;
		this.name = name;
		this.registry = registry;
		this.inputs = inputs;
	}

	@Override
	public void showMenu() {
		System.out.println("%d. %s".formatted(id, name));
	}
	
	@Override
	public int getId() {
		return id;
	}
	
}