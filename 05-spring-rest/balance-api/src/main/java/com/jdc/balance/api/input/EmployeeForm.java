package com.jdc.balance.api.input;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.entity.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeForm(
		@NotBlank(message = "Please enter name.")
		String name,
		@NotNull(message = "Please select role.")
		Role role,
		@NotBlank(message = "Please enter phone.")
		String phone,
		@NotBlank(message = "Please enter email.")
		String email) {

	private static final CharSequence DEFAULT_PASS = "123456";

	public static EmployeeForm from(Employee entity) {
		return new EmployeeForm(
				entity.getAccount().getName(), 
				entity.getAccount().getRole(), 
				entity.getPhone(), 
				entity.getEmail());
	}

	public Employee entity(PasswordEncoder passwordEncoder) {
		var account = new Account();
		account.setLoginId(email);
		account.setName(name);
		account.setRole(role);
		account.setPassword(passwordEncoder.encode(DEFAULT_PASS));
		
		var employee = new Employee();
		employee.setAccount(account);
		employee.setPhone(phone);
		employee.setEmail(email);
		employee.setStatus(Status.Applied);
		employee.setStatusChangeAt(LocalDateTime.now());
		
		return employee;
	}
}
