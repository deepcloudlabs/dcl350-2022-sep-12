package com.example.hr.application.business.event;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends HrEventBase {
	
	public EmployeeHiredEvent(TcKimlikNo identity) {
		super(identity,HrEventType.EMPLOYEE_HIRED);
	}

}
