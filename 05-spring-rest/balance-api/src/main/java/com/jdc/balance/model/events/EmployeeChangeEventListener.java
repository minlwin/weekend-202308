package com.jdc.balance.model.events;

import static com.jdc.balance.model.Commons.getOne;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.entity.EmployeeHistory;
import com.jdc.balance.model.entity.EmployeeHistoryPk;
import com.jdc.balance.model.repo.EmployeeHistoryRepo;
import com.jdc.balance.model.repo.EmployeeRepo;
import com.jdc.balance.model.service.LoginUserService;

@Component
public class EmployeeChangeEventListener {
	
	private static final String EMPLOYEE_DOMAIN = "Employee";

	private static final String ACCOUNT_DOMAIN = "Account";
	
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private EmployeeHistoryRepo historyRepo;
	@Autowired
	private LoginUserService loginUserService;
	
	@Transactional
	@EventListener
	public void handle(EmployeeChangeEvent event) {
		
		var employee = getOne(employeeRepo.findById(event.employeeId()), EMPLOYEE_DOMAIN, event.employeeId());
		var loginUser = getOne(loginUserService.getLoginUser(), ACCOUNT_DOMAIN, loginUserService.getLoginId().orElse("No User"));
		
		var history = new EmployeeHistory();
		
		history.setEmployee(employee);
		history.setChanges(event.changes());
		history.setChangeAt(LocalDateTime.now());
		history.setChangeBy(loginUser.name());
		
		var historyId = new EmployeeHistoryPk();
		historyId.setEmployeeId(event.employeeId());
		historyId.setSeqNumber(employee.getHistories().size() + 1);
		
		history.setId(historyId);
		
		history.setName(employee.getAccount().getName());
		history.setRole(employee.getAccount().getRole());
		history.setStatus(employee.getStatus());
		history.setStatusChangeAt(employee.getStatusChangeAt());
		history.setEmail(employee.getEmail());
		history.setPhone(employee.getPhone());
		
		historyRepo.save(history);
	}

}
