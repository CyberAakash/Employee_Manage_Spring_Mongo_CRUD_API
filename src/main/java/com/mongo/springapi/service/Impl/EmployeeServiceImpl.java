package com.mongo.springapi.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongo.springapi.collection.Employee;
import com.mongo.springapi.dto.EmployeeDto;
import com.mongo.springapi.dto.EmployeeRequestDto;
import com.mongo.springapi.dto.EmployeeResponseDto;
import com.mongo.springapi.mapper.EmployeeMapper;
import com.mongo.springapi.repository.EmployeeRepository;
import com.mongo.springapi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private MongoTemplate mongoTemplate;

    //    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeResponseDto(savedEmployee);
    }

    @Override
    public List<EmployeeResponseDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeResponseDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository
                .findByCustomId(id);
//                .orElseThrow(() -> new RuntimeException("Employee does not exist!"));
        return EmployeeMapper.mapToEmployeeResponseDto(employee);

    }

    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository
                .findByCustomId(id);
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponseDto updateMail(Integer id, String empMail) {
        Employee employee = employeeRepository.findByCustomId(id);
        employee.setEmpMail(empMail);
        Employee savedEmployee = employeeRepository.save(employee);
        return  EmployeeMapper.mapToEmployeeResponseDto(savedEmployee);
    }

    @Override
    public long getTotalDocumentCount() {
        return mongoTemplate.count(new Query(), Employee.class); // Replace 'Employee' with your actual entity class name
    }

    @Autowired
    private ObjectMapper objectMapper;

    public String convertObjectToJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    public <T> T convertJsonToObject(String json, Class<T> valueType) throws Exception {
        return objectMapper.readValue(json, valueType);
    }
}
