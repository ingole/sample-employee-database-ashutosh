package com.ekart.springbootjetty.sample.apis.service;

import com.ekart.springbootjetty.sample.apis.service.dtos.Employee;

import java.util.Collection;

/**
 * Created by ingole.as on 03/01/17.
 */
public interface EmployeeService {

    Collection<Employee> findAll();

    Employee findOne(Long id);

    Employee create(Employee employee);

    Employee update(Employee employee);

    void delete(Long id);
}
