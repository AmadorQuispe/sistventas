package com.amsoft.services;

import java.util.List;

import com.amsoft.dao.DaoFactory;
import com.amsoft.models.Product;

public class ProductService {
	private DaoFactory daoFactory;

	public ProductService(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Product> listAll() {
		return daoFactory.getProductDao().findAll();
	}

	public void save(Product obj) {
		daoFactory.getProductDao().create(obj);
	}

	public Product findBySlug(String slug) {
		return daoFactory.getProductDao().findBySlug(slug);
	}
}
