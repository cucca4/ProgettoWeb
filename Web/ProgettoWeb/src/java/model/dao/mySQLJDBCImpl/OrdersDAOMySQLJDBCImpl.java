/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mySQLJDBCImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.mo.Orders;
import model.dao.OrdersDAO;
import model.dao.exception.DuplicatedObjectException;


public class OrdersDAOMySQLJDBCImpl implements OrdersDAO{
    
     Connection conn;
    
    public OrdersDAOMySQLJDBCImpl(Connection conn){
        this.conn=conn;
    }
    
    Orders read(ResultSet rs){
            Orders order = new Orders();
       
        try {
            order.setOrder_Id(rs.getLong("order_Id"));
        } catch (SQLException sqle) {
        }
        try {
            order.setBuyer(rs.getString("buyer"));
        } catch (SQLException sqle) {
        }
        try {
            order.setTotprice(rs.getFloat("totprice"));
        } catch (SQLException sqle) {
        }
        try {
           order.setStatus(rs.getString("status"));
        } catch (SQLException sqle) {
        }
        
        return order;
    }
    
    
    @Override
    public Orders insert(
           Long order_Id,
           String buyer,
           Float totprice,
           String status)throws DuplicatedObjectException{
      
  
     PreparedStatement ps;
     ResultSet resultSet;
     Orders order=new Orders();
     order.setBuyer(buyer);
     order.setTotprice(totprice);
     order.setStatus(status);
     
     try{
                  String sql;
                  sql = "SELECT * "
                      + "FROM orders "
                      + "WHERE buyer = ? AND "
                      + "totprice = ? AND "
                      + "status = ? AND "
                      + "deleted_Or = '0' ";

                  ps = conn.prepareStatement(sql);
                  ps.setString(1, order.getBuyer());
                  ps.setFloat(2, order.getTotprice());
                  ps.setString(3, order.getStatus());

                  resultSet = ps.executeQuery();

                  if(resultSet.next())
                      throw new DuplicatedObjectException("OrdersDAOJDBCImpl.create: Tentativo di inserimento di un ordine già esistente.");
                  
                  try {
                      sql
                      = " INSERT INTO orders "
                      + "   ( Order_id,"
                      + "     buyer,"
                      + "     totprice,"
                      + "     status,"
                      + "     deleted_Or "
                      + "   ) "
                      + " VALUES (?,?,?,?,'0')";

                      ps = conn.prepareStatement(sql);

                      ps.setString(1, order.getBuyer());
                      ps.setFloat(2, order.getTotprice());
                      ps.setString(3, order.getStatus());

                      ps.executeUpdate();
                  }
                  catch(SQLIntegrityConstraintViolationException e){
                      throw new DuplicatedObjectException("OrdersDAOJDBCImpl.create: Tentativo di inserimento di un ordine già esistente.");
                  }
              }
              catch(SQLException e)
              {
                  throw new RuntimeException(e);
              }

              return order;
    }
    
    @Override
    public void update(Orders order) throws DuplicatedObjectException{
        PreparedStatement ps;
    
        try {

            String sql;
            sql
            ="UPDATE orders"
            +"SET "
            + "     buyer = ?, "
            + "     totprice = ?, "
            + "     status = ?, "
            + "WHERE "
            +"order_Id = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, order.getBuyer());
            ps.setFloat(2, order.getTotprice());
            ps.setString(3, order.getStatus());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
               
    }

    public void delete(Orders order){
        PreparedStatement ps;

        try {

            String sql
                    = "DELETE "
                    + "FROM orders "
                    + "WHERE "
                    + "order_Id = ? ";

            ps = conn.prepareStatement(sql);

            ps.setLong(1, order.getOrder_Id());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Orders findOrdersByOrder_Id(Long order_Id){
        
        PreparedStatement ps;
         Orders order = new Orders();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM orders "
                      + " WHERE order_Id = ?";

              ps = conn.prepareStatement(sq1);
              ps.setLong(1, order_Id);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  order = read(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return order;
        
    }


}
