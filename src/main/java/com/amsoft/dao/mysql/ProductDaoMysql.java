package com.amsoft.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amsoft.dao.ProductDao;
import com.amsoft.models.Product;
import com.amsoft.util.Conexion;

public class ProductDaoMysql implements ProductDao {

	@Override
	public List<Product> findAll() {
		String query = "SELECT id_product,slug,name,description,url_image,price,stock FROM products;";
		ArrayList<Product> products = new ArrayList<Product>();

		try (Connection conn = Conexion.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {

			while (rs.next()) {
				Product p = new Product();
				p.setIdProduct(rs.getLong("id_product"));
				p.setSlug(rs.getString("slug"));
				p.setName(rs.getString("name"));
				p.setDescription(rs.getString("description"));
				p.setUrlImage(rs.getString("url_image"));
				p.setPrice(rs.getDouble("price"));
				p.setStock(rs.getInt("stock"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void create(Product entity) {
		String query = "INSERT INTO products(slug,name,description,url_image,price,stock) VALUES(?,?,?,?,?,?)";
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, entity.getSlug());
			ps.setString(2, entity.getName());
			ps.setString(3, entity.getDescription());
			ps.setString(4, entity.getUrlImage());
			ps.setDouble(5, entity.getPrice());
			ps.setInt(6, entity.getStock());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Product entity) {
		String query = "UPDATE products SET slug=?,name=?,description=?,url_image=?,price=?,stock=? WHERE id_product = ?";
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, entity.getSlug());
			ps.setString(2, entity.getName());
			ps.setString(3, entity.getDescription());
			ps.setString(4, entity.getUrlImage());
			ps.setDouble(5, entity.getPrice());
			ps.setInt(6, entity.getStock());
			ps.setLong(7, entity.getIdProduct());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product findById(Long id) {
		String query = "SELECT id_product,slug,name,description,url_image,price,stock FROM products WHERE id_product = ? ";
		Product product = new Product();
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product.setIdProduct(rs.getLong("id_product"));
				product.setSlug(rs.getString("slug"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setUrlImage(rs.getString("url_image"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
	}

	@Override
	public Product findBySlug(String slug) {
		String query = "SELECT id_product,slug,name,description,url_image,price,stock FROM products WHERE slug = ? ";
		Product product = null;
		try (Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, slug);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				product.setIdProduct(rs.getLong("id_product"));
				product.setSlug(rs.getString("slug"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setUrlImage(rs.getString("url_image"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

}
