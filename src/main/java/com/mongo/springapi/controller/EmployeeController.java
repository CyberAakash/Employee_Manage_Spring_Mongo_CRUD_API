package com.mongo.springapi.controller;

import com.mongo.springapi.collection.Employee;
import com.mongo.springapi.dto.EmployeeRequestDto;
import com.mongo.springapi.dto.EmployeeResponseDto;
import com.mongo.springapi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    //    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmp")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeDto) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByID(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteEmployeeByID(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }

    @PutMapping("/{id}/updateMail")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeMailByID(@PathVariable Integer id, @RequestBody Map<String, String> request) {
        String empMail = request.get("empMail");
        return new ResponseEntity<>(employeeService.updateMail(id, empMail), HttpStatus.OK);
    }

    //    Total Document Count
    @GetMapping("/tdc")
    public ResponseEntity<Long> getTotalDocumentCount() {
        long count = employeeService.getTotalDocumentCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/map-object/{id}")
    public ResponseEntity<String> mapObjectById(@PathVariable Integer id) {
        try {
            // Fetch employee from database
            EmployeeResponseDto employee = employeeService.getEmployeeById(id); // Replace employeeId with actual ID
            if (employee != null) {
                String json = employeeService.convertObjectToJson(employee);
                return new ResponseEntity<>(json, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle any exception that might occur during JSON conversion or fetching from database
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/map-object")
    public ResponseEntity<String> mapObject(EmployeeRequestDto employeeRequestDto) {
        try {
            // Fetch employee from database
//            EmployeeResponseDto employee = employeeService.getEmployeeById(id); // Replace employeeId with actual ID
            if (employeeRequestDto != null) {
                String json = employeeService.convertObjectToJson(employeeRequestDto);
                return new ResponseEntity<>(json, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle any exception that might occur during JSON conversion or fetching from database
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/map-json")
    public ResponseEntity<Employee> mapJson(@RequestBody EmployeeRequestDto employeeRequestDto) throws Exception {
        Employee employee = employeeService.convertJsonToObject(employeeService.convertObjectToJson(employeeRequestDto), Employee.class);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
