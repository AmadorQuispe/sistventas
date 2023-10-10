package com.amsoft.dao.mysql;

import com.amsoft.dao.CustomerDao;
import com.amsoft.dao.DaoFactory;
import com.amsoft.dao.OrderDao;
import com.amsoft.dao.ProductDao;

public class DaoFactoryMysql extends DaoFactory {
	private CustomerDao customerDao;
	private ProductDao productDao;
	private OrderDao orderDao;

	@Override
	public CustomerDao getCustomerDao() {
		if (customerDao == null) {
			customerDao = new CustomerDaoMysql();
		}
		return customerDao;
	}

	@Override
	public ProductDao getProductDao() {
		if (productDao == null) {
			productDao = new ProductDaoMysql();
		}
		return productDao;
	}

	@Override
	public OrderDao getOrderDao() {
		if (orderDao == null) {
			orderDao = new OrderDaoMysql();
		}
		return orderDao;
	}



}
