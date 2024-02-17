package com.mongo.springapi.repository;

import com.mongo.springapi.collection.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

    @Query("{employeeId:?0}")
    Employee findByCustomId(Integer id);
}
