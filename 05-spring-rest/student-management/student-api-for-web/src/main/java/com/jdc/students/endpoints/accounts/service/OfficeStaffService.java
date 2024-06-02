package com.jdc.students.endpoints.accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.students.domain.account.repo.OfficeStaffRepo;
import com.jdc.students.endpoints.accounts.input.OfficeStaffForm;
import com.jdc.students.endpoints.accounts.input.OfficeStaffSearch;
import com.jdc.students.endpoints.accounts.output.OfficeStaffInfo;

@Service
public class OfficeStaffService {
	
	@Autowired
	private OfficeStaffRepo repo;

	public List<OfficeStaffInfo> search(OfficeStaffSearch form) {
		// TODO Auto-generated method stub
		return null;
	}

	public OfficeStaffInfo create(OfficeStaffForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public OfficeStaffInfo findById(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public OfficeStaffInfo update(String code, OfficeStaffForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
