package com.amsoft.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amsoft.dao.OrderDao;
import com.amsoft.models.Order;
import com.amsoft.models.OrderDetail;
import com.amsoft.util.Conexion;

public class OrderDaoMysql implements OrderDao {
    
	 

    @Override
    public void create(Order entity) {
        String query = "INSERT INTO orders(customer_id,payment_id,required_date,is_paid,status)" +
                "VALUES(?,?,?,?,?);";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Conexion.getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setLong(1, entity.getCustomerId());
            ps.setLong(2, entity.getPaymentId());
            ps.setDate(3, Date.valueOf(entity.getRequiredDate()));
            ps.setBoolean(4, entity.getIsPaid());
            ps.setString(5, entity.getStatus());
            conn.setAutoCommit(false);
            ps.executeUpdate();
            Long orderIdGenerated;
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    orderIdGenerated = rs.getLong(1);
                }else{
                    throw new RuntimeException("Id no generado");
                }
            }
            
            // id_order_detail,order_id,product_id,price,quantity,discount,total
            for (OrderDetail orderDetail : entity.getOrderDetails()) {
                query = "INSERT INTO order_details(order_id,product_id,size,price,quantity,total)VALUES(?,?,?,?,?,?);";
                ps = conn.prepareStatement(query);
                ps.setLong(1, orderIdGenerated);
                ps.setLong(2, orderDetail.getProduct().getIdProduct());
                ps.setString(3, orderDetail.getSize());
                ps.setDouble(4, orderDetail.getPrice());
                ps.setLong(5, orderDetail.getQuantity());                              
                ps.setDouble(6, orderDetail.getTotal());
                ps.executeUpdate();

            }

            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        
    }

    @Override
    public Order findById(Long id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
