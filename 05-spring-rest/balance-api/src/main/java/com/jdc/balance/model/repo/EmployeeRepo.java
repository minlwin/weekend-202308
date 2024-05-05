package com.jdc.balance.model.repo;

import java.util.Optional;

import com.jdc.balance.model.BaseRepo;
import com.jdc.balance.model.entity.Employee;

public interface EmployeeRepo extends BaseRepo<Employee, Integer>{

	Optional<Employee> findOneByAccountLoginId(String loginId);
}
