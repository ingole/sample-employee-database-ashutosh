package com.ekart.springbootjetty.sample.apis.service.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.ekart.springbootjetty.sample.apis.service.dtos.Employee;

import java.util.Collection;

/**
 * Created by ingole.as on 05/01/17.
 */
@Component
@Mapper(componentModel = "spring")
public abstract class ServiceDalDtoMapper {
    public abstract com.ekart.springbootjetty.sample.apis.model.Employee serviceToDalCreateEmployee(Employee employee);

    public abstract Employee dalToServiceEmployee(com.ekart.springbootjetty.sample.apis.model.Employee employee);

    public abstract Collection<Employee>
    dalToServiceCollectionEmployee(Collection<com.ekart.springbootjetty.sample.apis.model.Employee> employee);
}
