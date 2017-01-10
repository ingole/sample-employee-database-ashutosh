package com.ekart.springbootjetty.sample.apis.repository;

import com.ekart.springbootjetty.sample.apis.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ingole.as on 03/01/17.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
