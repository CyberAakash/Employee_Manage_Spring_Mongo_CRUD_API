package com.mongo.springapi.service;

import com.mongo.springapi.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getEmployees();
    EmployeeDto getEmployeeById(Integer id);
    void deleteEmployee(Integer id);
    EmployeeDto updateMail(Integer id, String empMail);

}
