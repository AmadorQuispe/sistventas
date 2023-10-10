package com.amsoft.dao;

import java.util.List;

import com.amsoft.models.Product;

public interface ProductDao {
    List<Product> findAll();

    void create(Product entity);

    void update(Product entity);

    Product findById(Long id);

    Product findBySlug(String slug);

    void deleteById(Long id);
}
