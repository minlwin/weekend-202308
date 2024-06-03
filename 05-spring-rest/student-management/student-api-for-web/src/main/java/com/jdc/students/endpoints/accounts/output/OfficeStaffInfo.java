package com.jdc.students.endpoints.accounts.output;

import java.time.LocalDate;

import com.jdc.students.domain.account.entity.Account_;
import com.jdc.students.domain.account.entity.OfficeStaff;
import com.jdc.students.domain.account.entity.OfficeStaff_;
import com.jdc.students.utils.consts.EmployeeStatus;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record OfficeStaffInfo(
		String code,
		String name,
		EmployeeStatus status,
		boolean activated,
		String phone,
		String email,
		LocalDate assignAt,
		LocalDate provationAt,
		LocalDate resignAt) {
	
	public static OfficeStaffInfo from(OfficeStaff entity) {
		return builder()
				.code(entity.getCode())
				.name(entity.getAccount().getFullName())
				.status(entity.getStatus())
				.activated(entity.getAccount().isActivated())
				.phone(entity.getPhone())
				.email(entity.getEmail())
				.assignAt(entity.getAssignAt())
				.provationAt(entity.getProvationAt())
				.resignAt(entity.getResignAt())
				.build();
	}

	public static void select(CriteriaQuery<OfficeStaffInfo> cq, Root<OfficeStaff> root) {
		cq.multiselect(
			root.get(OfficeStaff_.code),
			root.get(OfficeStaff_.account).get(Account_.fullName),
			root.get(OfficeStaff_.status),
			root.get(OfficeStaff_.account).get(Account_.activated),
			root.get(OfficeStaff_.phone),
			root.get(OfficeStaff_.email),
			root.get(OfficeStaff_.assignAt),
			root.get(OfficeStaff_.provationAt),
			root.get(OfficeStaff_.resignAt)
		);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
	
		private String code;
		private String name;
		private EmployeeStatus status;
		private boolean activated;
		private String phone;
		private String email;
		private LocalDate assignAt;
		private LocalDate provationAt;
		private LocalDate resignAt;
		
		public OfficeStaffInfo build() {
			return new OfficeStaffInfo(code, name, status, activated, phone, email, assignAt, provationAt, resignAt);
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder status(EmployeeStatus status) {
			this.status = status;
			return this;
		}

		public Builder activated(boolean activated) {
			this.activated = activated;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder assignAt(LocalDate assignAt) {
			this.assignAt = assignAt;
			return this;
		}

		public Builder provationAt(LocalDate provationAt) {
			this.provationAt = provationAt;
			return this;
		}

		public Builder resignAt(LocalDate resignAt) {
			this.resignAt = resignAt;
			return this;
		}
		
	}
}
