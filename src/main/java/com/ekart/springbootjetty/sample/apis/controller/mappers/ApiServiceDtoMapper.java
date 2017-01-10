package com.ekart.springbootjetty.sample.apis.controller.mappers;

import com.ekart.springbootjetty.sample.apis.controller.dtos.Employee;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by ingole.as on 05/01/17.
 */
@Component
@Mapper(componentModel = "spring")
public abstract class ApiServiceDtoMapper {
    public abstract com.ekart.springbootjetty.sample.apis.service.dtos.Employee
    apiToServiceCreateEmployee(Employee employee);

    public abstract Employee
    serviceToApiCreateEmployee(com.ekart.springbootjetty.sample.apis.service.dtos.Employee employee);

    public abstract Collection<com.ekart.springbootjetty.sample.apis.controller.dtos.Employee>
    serviceToApiCollectionEmployee(Collection<com.ekart.springbootjetty.sample.apis.service.dtos.Employee> employee);
}
