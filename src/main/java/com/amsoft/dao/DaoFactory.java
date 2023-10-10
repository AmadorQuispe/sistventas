package com.amsoft.dao;

public abstract class DaoFactory {
	/*
	 * public static final int TIPO_MYSQL =1;
	 * public static final int TIPO_SQL_SERVER =2;
	 */
	public static DaoFactory factory = null;

	public static DaoFactory getFactory() {
		assert factory != null;
		return factory;
	}

	public static void setFactory(DaoFactory factory) {
		DaoFactory.factory = factory;
	}

	public abstract CustomerDao getCustomerDao();

	public abstract ProductDao getProductDao();

	public abstract OrderDao getOrderDao();

}
