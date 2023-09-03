package com.jdc.student.actions;

import java.util.Scanner;

public class InputUtils {

	public InputUtils() {
		scanner = new Scanner(System.in);
	}

	private Scanner scanner;

	public String readString(String message) {
		System.out.print(message);
		return scanner.nextLine();
	}

	public int readInt(String message) {
		var string = readString(message);
		return Integer.parseInt(string);
	}

}