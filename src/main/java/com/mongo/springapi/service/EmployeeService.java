package com.mongo.springapi.service;

import com.mongo.springapi.dto.EmployeeDto;
import com.mongo.springapi.dto.EmployeeRequestDto;
import com.mongo.springapi.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeDto);
    List<EmployeeResponseDto> getEmployees();
    EmployeeResponseDto getEmployeeById(Integer id);
    void deleteEmployee(Integer id);
    EmployeeResponseDto updateMail(Integer id, String empMail);
    long getTotalDocumentCount();
    String convertObjectToJson(Object object) throws Exception;
    <T> T convertJsonToObject(String json, Class<T> valueType) throws Exception;


}
