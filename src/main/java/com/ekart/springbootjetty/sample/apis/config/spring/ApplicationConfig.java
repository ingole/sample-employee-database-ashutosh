package com.ekart.springbootjetty.sample.apis.config.spring;

import com.ekart.springbootjetty.sample.apis.controller.EmployeeController;
import com.ekart.springbootjetty.sample.apis.controller.mappers.ApiServiceDtoMapper;
import com.ekart.springbootjetty.sample.apis.controller.mappers.ApiServiceDtoMapperImpl;
import com.ekart.springbootjetty.sample.apis.repository.EmployeeRepository;
import com.ekart.springbootjetty.sample.apis.service.EmployeeService;
import com.ekart.springbootjetty.sample.apis.service.EmployeeServiceImpl;
import com.ekart.springbootjetty.sample.apis.service.mappers.ServiceDalDtoMapper;
import com.ekart.springbootjetty.sample.apis.service.mappers.ServiceDalDtoMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;


@Configuration
@ComponentScan({"com.ekart.springbootjetty.sample.apis"})
@Import({EnvironmentConfig.class})
public class ApplicationConfig {

    // Reference:
    // http://docs.spring.io/spring-javaconfig/docs/1.0.0.M4/reference/html/ch04s02.html
    //Add your configs here


    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private ServiceDalDtoMapper serviceDalDtoMapper;

    @Bean
    public ApiServiceDtoMapper apiServiceDtoMapper() {
        return new ApiServiceDtoMapperImpl();
    }

    @Bean
    public ServiceDalDtoMapper serviceDalDtoMapper() {
        return new ServiceDalDtoMapperImpl();
    }

    @Bean
    public EmployeeController employeeController() {
        return new EmployeeController(employeeService(), apiServiceDtoMapper());

    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl(employeeRepository, serviceDalDtoMapper);

    }

}


