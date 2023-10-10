package com.amsoft.dao;



import com.amsoft.models.Order;

public interface OrderDao {
    void create(Order entity);

    Order findById(Long id);

}
