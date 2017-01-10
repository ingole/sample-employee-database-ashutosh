package com.ekart.springbootjetty.sample.apis.service;

import com.ekart.springbootjetty.sample.apis.repository.EmployeeRepository;
import com.ekart.springbootjetty.sample.apis.service.dtos.Employee;
import com.ekart.springbootjetty.sample.apis.service.mappers.ServiceDalDtoMapper;

import java.util.Collection;

/**
 * Created by ingole.as on 03/01/17.
 */

public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    private ServiceDalDtoMapper serviceDalDtoMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ServiceDalDtoMapper serviceDalDtoMapper) {
        this.employeeRepository = employeeRepository;
        this.serviceDalDtoMapper = serviceDalDtoMapper;
    }

    @Override
    public Collection<Employee> findAll() {
        Collection<Employee> employee = serviceDalDtoMapper.
                dalToServiceCollectionEmployee(employeeRepository.findAll());
        return employee;
    }

    @Override
    public Employee findOne(Long id) {
        Employee employee = serviceDalDtoMapper.dalToServiceEmployee(employeeRepository.findOne(id));
        return employee;
    }

    @Override
    public Employee create(Employee employee) {

        if (employee.getId() != null) {
            // Cannot create greeting with specified ID value
            return null;
        }

        Employee savedEmployee = serviceDalDtoMapper.
                dalToServiceEmployee(employeeRepository.save(serviceDalDtoMapper.serviceToDalCreateEmployee(employee)));
        return savedEmployee;
    }

    @Override
    public Employee update(Employee employee) {

        Employee employeePersisted = findOne(employee.getId());
        if (employeePersisted == null) {
            // Cannot update Greeting that hasn't been persisted
            return null;
        }

        Employee updatedEmployee = serviceDalDtoMapper.
                dalToServiceEmployee(employeeRepository.save(serviceDalDtoMapper.serviceToDalCreateEmployee(employee)));
        return updatedEmployee;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(id);
    }
}
