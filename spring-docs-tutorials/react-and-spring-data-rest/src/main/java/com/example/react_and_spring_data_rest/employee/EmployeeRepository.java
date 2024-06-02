package com.example.react_and_spring_data_rest.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, CrudRepository<Employee, Long> {
}
