package com.myapp.service.impl;

import com.myapp.domain.Employee;
import com.myapp.repository.EmployeeRepository;
import com.myapp.service.EmployeeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Mono<Employee> save(Employee employee) {
        log.debug("Request to save Employee : {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Employee> partialUpdate(Employee employee) {
        log.debug("Request to partially update Employee : {}", employee);

        return employeeRepository
            .findById(employee.getId())
            .map(existingEmployee -> {
                if (employee.getFirstName() != null) {
                    existingEmployee.setFirstName(employee.getFirstName());
                }
                if (employee.getLastName() != null) {
                    existingEmployee.setLastName(employee.getLastName());
                }
                if (employee.getEmail() != null) {
                    existingEmployee.setEmail(employee.getEmail());
                }
                if (employee.getPhoneNumber() != null) {
                    existingEmployee.setPhoneNumber(employee.getPhoneNumber());
                }
                if (employee.getHireDate() != null) {
                    existingEmployee.setHireDate(employee.getHireDate());
                }
                if (employee.getSalary() != null) {
                    existingEmployee.setSalary(employee.getSalary());
                }
                if (employee.getCommissionPct() != null) {
                    existingEmployee.setCommissionPct(employee.getCommissionPct());
                }

                return existingEmployee;
            })
            .flatMap(employeeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Employee> findAll() {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll();
    }

    public Mono<Long> countAll() {
        return employeeRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Employee> findOne(Long id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Employee : {}", id);
        return employeeRepository.deleteById(id);
    }
}
