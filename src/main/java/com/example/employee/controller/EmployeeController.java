package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees(){
        List<Employee> employee = employeeService.getEmployees();
        if(!employee.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping("/employee/{empId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("empId") Long empId){
//        HashMap<String,String> result = new HashMap<>();
//        Employee employee = employeeService.getEmployeeById(empId);
//        if(employee!=null){
//            return ResponseEntity.status(HttpStatus.OK).body(employee);
//        }else {
//            result.put("error","Invalid ID");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(empId));
    }
}
