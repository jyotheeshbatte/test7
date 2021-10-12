package com.myapp.repository;

import com.myapp.domain.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data SQL reactive repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends R2dbcRepository<Employee, Long>, EmployeeRepositoryInternal {
    // just to avoid having unambigous methods
    @Override
    Flux<Employee> findAll();

    @Override
    Mono<Employee> findById(Long id);

    @Override
    <S extends Employee> Mono<S> save(S entity);
}

interface EmployeeRepositoryInternal {
    <S extends Employee> Mono<S> insert(S entity);
    <S extends Employee> Mono<S> save(S entity);
    Mono<Integer> update(Employee entity);

    Flux<Employee> findAll();
    Mono<Employee> findById(Long id);
    Flux<Employee> findAllBy(Pageable pageable);
    Flux<Employee> findAllBy(Pageable pageable, Criteria criteria);
}
