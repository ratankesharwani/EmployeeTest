package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepo employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    void createEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee result = employeeService.createEmployee(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void getEmployees() {
        List<Employee> employeeList = new ArrayList<>(2);
        employeeList.add(new Employee(1L, "John","Doe","john@example.com","COMP","USA","Admin1234"));
        employeeList.add(new Employee(2L, "Sam","Winter","john@example.com","COMP","USA","Admin1234"));
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> result = employeeService.getEmployees();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getEmployeeById() {
        Employee employee=new Employee(1L, "John","Doe","john@example.com","COMP","USA","Admin1234");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Optional<Employee> foundEmployee = Optional.ofNullable(employeeService.getEmployeeById(1L));
        assertTrue(foundEmployee.isPresent());
        assertEquals("John", foundEmployee.get().getFirstName());
        assertEquals("john@example.com", foundEmployee.get().getEmailId());
    }

    @Test
    void updateEmployee() {
        Employee existingEmployee = new Employee(1L, "John","Doe","john@example.com","COMP","USA","Admin1234");
        Employee updatedEmployee = new Employee(1L, "Sam","Winter","john@example.com","COMP","USA","Admin1234");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        assertNotNull(result);
        assertEquals("Sam", result.getFirstName());
        assertEquals("john@example.com", result.getEmailId());
    }

    @Test
    void deleteEmployee() {
        Employee deleteEmployee = new Employee(1L, "John","Doe","john@example.com","COMP","USA","Admin1234");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(deleteEmployee));

        boolean result = employeeService.deleteEmployee(1L);
        assertTrue(result);
    }
}