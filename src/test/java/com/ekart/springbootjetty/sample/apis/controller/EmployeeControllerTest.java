package com.ekart.springbootjetty.sample.apis.controller;

import com.ekart.springbootjetty.sample.apis.controller.dtos.Employee;
import com.ekart.springbootjetty.sample.apis.controller.mappers.ApiServiceDtoMapper;
import com.ekart.springbootjetty.sample.apis.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * Created by ingole.as on 10/01/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private ApiServiceDtoMapper mapper;

    private EmployeeController employeeController;

    @Before
    public void setUp() throws Exception {
        employeeController = new EmployeeController(employeeService, mapper);

    }

    @Test
    public void whenFindOneReturnsNonNullObject() {
        Employee employee = new Employee();
        employee.setName("testName");
        employee.setContact(8806168178L);

        com.ekart.springbootjetty.sample.apis.service.dtos.Employee employee1 =
                new com.ekart.springbootjetty.sample.apis.service.dtos.Employee();
        when(employeeService.findOne(1L)).thenReturn(employee1);
        when(mapper.serviceToApiCreateEmployee(employee1)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployee(1L);

        assertReflectionEquals(new ResponseEntity<>(employee, org.springframework.http.HttpStatus.OK), response);
        verify(employeeService).findOne(1L);
    }
}
