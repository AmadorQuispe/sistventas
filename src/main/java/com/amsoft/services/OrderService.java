package com.amsoft.services;



import com.amsoft.dao.DaoFactory;
import com.amsoft.models.Order;

public class OrderService {
    private DaoFactory daoFactory;

    public OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void save(Order obj) {        
        daoFactory.getOrderDao().create(obj);
    }

}
