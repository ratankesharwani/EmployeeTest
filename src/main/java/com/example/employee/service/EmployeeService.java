package com.example.employee.service;

import com.example.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id,Employee employee);
    boolean deleteEmployee(Long id);
}
