package com.example.react_and_spring_data_rest;

import com.example.react_and_spring_data_rest.employee.Employee;
import com.example.react_and_spring_data_rest.employee.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public DatabaseLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.employeeRepository.save(new Employee("Zi Qing", "Chew", "my desc hahaha"));
        this.employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer"));
        this.employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
        this.employeeRepository.save(new Employee("Gandalf", "the Grey", "wizard"));
        this.employeeRepository.save(new Employee("Samwise", "Gamgee", "gardener"));
        this.employeeRepository.save(new Employee("Meriadoc", "Brandybuck", "pony rider"));
        this.employeeRepository.save(new Employee("Peregrin", "Took", "pipe smoker"));
    }
}
