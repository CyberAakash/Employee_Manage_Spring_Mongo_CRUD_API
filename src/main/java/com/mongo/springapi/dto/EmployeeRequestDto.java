package com.mongo.springapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private Integer employeeId; // Include ID in the request DTO
    private String empName;
    private String empMail;
}
