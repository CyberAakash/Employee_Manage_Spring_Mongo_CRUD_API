package com.mongo.springapi.mapper;

import com.mongo.springapi.collection.Employee;
import com.mongo.springapi.dto.EmployeeDto;
import com.mongo.springapi.dto.EmployeeRequestDto;
import com.mongo.springapi.dto.EmployeeResponseDto;

import java.util.Optional;

public class EmployeeMapper {
//    public static Employee mapToEmployee(EmployeeDto employeeDto) {
//        Employee Employee = new Employee(
//                employeeDto.getEmployeeId(),
//                employeeDto.getEmpName(),
//                employeeDto.getEmpMail()
//        );
//
//        return Employee;
//    }
//
//    public static EmployeeDto mapToEmployeeDto(Employee employee) {
//        EmployeeDto EmployeeDto = new EmployeeDto(
//                employee.getEmployeeId(),
//                employee.getEmpName(),
//                employee.getEmpMail()
//        );
//
//        return EmployeeDto;
//    }

    public static Employee mapToEmployee(EmployeeRequestDto employeeRequestDto) {
        return new Employee(
                employeeRequestDto.getEmployeeId(), // Include ID if needed
                employeeRequestDto.getEmpName(),
                employeeRequestDto.getEmpMail()
        );
    }

    public static EmployeeResponseDto mapToEmployeeResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getEmpName(),
                employee.getEmpMail()
        );
    }
}
