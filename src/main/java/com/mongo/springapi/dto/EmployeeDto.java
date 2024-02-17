package com.mongo.springapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
    private Integer employeeId;
    private String empName;
    private String empMail;
}
