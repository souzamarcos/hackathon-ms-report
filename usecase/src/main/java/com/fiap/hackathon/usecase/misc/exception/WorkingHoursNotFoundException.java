package com.fiap.hackathon.usecase.misc.exception;

public class WorkingHoursNotFoundException extends NotFoundException {
    public WorkingHoursNotFoundException(String employeeId) {
        super("Working Hours for employeeId '" + employeeId + "' not found");
    }
}
