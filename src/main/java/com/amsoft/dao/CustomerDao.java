package com.amsoft.dao;

import java.util.List;

import com.amsoft.models.Customer;

public interface CustomerDao {
    void create(Customer entity);

    Customer findById(Long id);

    void update(Customer entity);

    void deleteById(Long id);

    List<Customer> findAll();

    Customer findByEmail(String email);
}
