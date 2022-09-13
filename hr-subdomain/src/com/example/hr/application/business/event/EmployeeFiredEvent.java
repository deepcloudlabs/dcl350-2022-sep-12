package com.example.hr.application.business.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeFiredEvent extends HrEventBase {
	public EmployeeFiredEvent(TcKimlikNo identity) {
		super(identity, HrEventType.EMPLOYEE_FIRED);
	}
}
