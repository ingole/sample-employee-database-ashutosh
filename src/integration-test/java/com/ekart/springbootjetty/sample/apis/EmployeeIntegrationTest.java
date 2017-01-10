package com.ekart.springbootjetty.sample.apis;

import com.ekart.springbootjetty.sample.apis.controller.dtos.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * Created by ingole.as on 09/01/17.
 */
public class EmployeeIntegrationTest extends BaseIntegrationTest {
    @Test
    public void createEmployeeAndReturn() throws Exception {
        Employee employee = new Employee();
        employee.setName("Ayush");
        employee.setContact(9412472304L);

        HttpEntity<Employee> request = new HttpEntity<>(employee, newHeader());
        ResponseEntity<Employee> response =
                CLIENT.exchange(url("/api/employees"), HttpMethod.POST, request, Employee.class);
        Assert.assertEquals("Expected is 201", HttpStatus.CREATED, response.getStatusCode());
        ResponseEntity<Collection> response1 =
                CLIENT.exchange(url("/api/employees"), HttpMethod.GET, request, Collection.class);
        Assert.assertEquals("Expected is 200", HttpStatus.OK, response1.getStatusCode());
    }
}
