package com.ekart.springbootjetty.sample.apis.controller;

import com.ekart.springbootjetty.sample.apis.controller.dtos.Employee;
import com.ekart.springbootjetty.sample.apis.controller.mappers.ApiServiceDtoMapper;
import com.ekart.springbootjetty.sample.apis.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//import org.springframework.http.HttpStatus;

/**
 * Created by ingole.as on 03/01/17.
 */
@Api(protocols = "http")
@RestController
public class EmployeeController extends BaseController {

    private EmployeeService employeeService;
    private ApiServiceDtoMapper mapper;


    public EmployeeController(EmployeeService employeeService, ApiServiceDtoMapper mapper) {
        this.employeeService = employeeService;
        this.mapper = mapper;
    }

    @ApiOperation(nickname = "get-all-employees", value = "Get All Employees")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.NO_CONTENT_204, message = "There are no employees in Database",
                    response = Employee.class),
            @ApiResponse(code = HttpStatus.OK_200,
                    message = "Employees retrieved successfully", response = Employee.class)

    })

    @RequestMapping(
            value = "/api/employees",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Collection<Employee>> getEmployees() {

        Collection<Employee> employees = mapper.serviceToApiCollectionEmployee(employeeService.findAll());

        if (employees.isEmpty()) {
            return new ResponseEntity<Collection<Employee>>(employees, org.springframework.http.HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Collection<Employee>>(employees, org.springframework.http.HttpStatus.OK);

    }

    @ApiOperation(nickname = "get-an-employee", value = "Get the employee with specified id")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.NOT_FOUND_404, message = "There is no employee with this id"),
            @ApiResponse(code = HttpStatus.OK_200,
                    message = "Employee retrieved successfully", response = Employee.class)

    })

    @RequestMapping(
            value = "/api/employees/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {

        Employee employee = mapper.serviceToApiCreateEmployee(employeeService.findOne(id));
        if (employee == null) {
            return new ResponseEntity<Employee>(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, org.springframework.http.HttpStatus.OK);

    }

    @ApiOperation(nickname = "create-employee", value = "Create a new employee")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.CREATED_201, message = "Employee created successfully")
    })


    @RequestMapping(
            value = "/api/employees",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        Employee savedEmployee = mapper.
                serviceToApiCreateEmployee(employeeService.create(mapper.apiToServiceCreateEmployee(employee)));
        return new ResponseEntity<Employee>(savedEmployee, org.springframework.http.HttpStatus.CREATED);

    }

    @ApiOperation(nickname = "update-an-employee", value = "Update the employee")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.INTERNAL_SERVER_ERROR_500,
                    message = "There is no employee with this id"),
            @ApiResponse(code = HttpStatus.OK_200,
                    message = "Employee info updated successfully", response = Employee.class)

    })

    @RequestMapping(
            value = "/api/employees/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {

        Employee updatedEmployee = mapper.
                serviceToApiCreateEmployee(employeeService.update(mapper.apiToServiceCreateEmployee(employee)));
        if (updatedEmployee == null) {
            return new ResponseEntity<Employee>(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<Employee>(updatedEmployee, org.springframework.http.HttpStatus.OK);
    }

    @ApiOperation(nickname = "delete-an-employee", value = "delete the employee with given id")
    @ApiResponses({
            @ApiResponse(code = HttpStatus.NO_CONTENT_204, message = "There is no employee with this id")

    })

    @RequestMapping(
            value = "/api/employees/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id) {

        employeeService.delete(id);

        return new ResponseEntity<Employee>(org.springframework.http.HttpStatus.NO_CONTENT);
    }
}
