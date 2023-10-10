package com.amsoft.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amsoft.dao.CustomerDao;
import com.amsoft.models.Customer;
import com.amsoft.util.Conexion;

public class CustomerDaoMysql implements CustomerDao {

	@Override
	public void create(Customer entity) {
		String query = "INSERT INTO customers(doi,first_name,last_name,address,email,password) VALUES(?,?,?,?,?,?);";
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, entity.getDoi());
			ps.setString(2, entity.getFirstName());
			ps.setString(3, entity.getLastName());
			ps.setString(4, entity.getAddress());
			ps.setString(5, entity.getEmail());
			ps.setString(6, entity.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Customer findById(Long id) {
		String query = "SELECT id_customer,doi,first_name,last_name,address,email FROM customers WHERE id_customer = ?;";
		Customer customer = new Customer();
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer.setIdCustomer(rs.getLong("id_customer"));
				customer.setDoi(rs.getString("doi"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public void update(Customer entity) {
		String query = "UPDATE customers SET doi=?,first_name=?,last_name=?,address=?,email=? WHERE id_customer = ?";
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, entity.getDoi());
			ps.setString(2, entity.getFirstName());
			ps.setString(3, entity.getLastName());
			ps.setString(4, entity.getAddress());
			ps.setString(5, entity.getEmail());
			ps.setLong(6, entity.getIdCustomer());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(Long id) {
		String query = "DELETE FROM customers WHERE id_customer = ?";
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Customer> findAll() {
		String query = "SELECT c.id_customer,c.doi,c.first_name,c.last_name,c.address,c.email FROM customers c;";
		ArrayList<Customer> customers = new ArrayList<>();
		try (Connection conn = Conexion.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setIdCustomer(rs.getLong("id_customer"));
				customer.setDoi(rs.getString("doi"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public Customer findByEmail(String email) {
		String query = "SELECT id_customer,doi,first_name,last_name,address,email,password FROM customers WHERE email = ?;";
		Customer customer = null;
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customer = new Customer();
				customer.setIdCustomer(rs.getLong("id_customer"));
				customer.setDoi(rs.getString("doi"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));				
				customer.setPassword(rs.getString("password"));

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

}
