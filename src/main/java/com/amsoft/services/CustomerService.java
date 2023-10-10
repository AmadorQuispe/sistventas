package com.amsoft.services;

import java.util.List;

import com.amsoft.dao.DaoFactory;
import com.amsoft.models.Customer;
import com.amsoft.models.UserDetail;
import com.amsoft.util.PasswordEncryption;

public class CustomerService {
	private DaoFactory daoFactory;

	public CustomerService(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<Customer> listAll() {
		return daoFactory.getCustomerDao().findAll();
	}

	public void save(Customer obj) {
		obj.setPassword(PasswordEncryption.encryptPassword(obj.getPassword()));
		daoFactory.getCustomerDao().create(obj);
	}

	public UserDetail login(String email,String password) {
		Customer customer = daoFactory.getCustomerDao().findByEmail(email);		
		if(customer!=null){
			boolean pass = PasswordEncryption.validatePassword(password, customer.getPassword());
			if (pass) {
				String completeName = customer.getFirstName() + ", " + customer.getLastName();
				UserDetail userDetail = new UserDetail(email,completeName,customer.getIdCustomer());
				return userDetail;
			}else{
				return null;
			}
		}
		return null;
	}
}
