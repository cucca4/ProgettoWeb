
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
           String buyer,
           Float totprice)throws DuplicatedObjectException{
  
        PreparedStatement ps;
        ResultSet resultSet;
        Orders orders = new Orders();
        orders.setBuyer(buyer);
        orders.setTotprice(totprice);
        orders.setStatus("elaborazione");
        
        try {
                String sql
                = " INSERT INTO orders "
                + "   ( buyer,"
                + "     totprice,"
                + "     status "
                + "   ) "
                + " VALUES (?,?,?);";

                ps = conn.prepareStatement(sql);

                ps.setString(1, orders.getBuyer());
                ps.setFloat(2, orders.getTotprice());
                ps.setString(3, orders.getStatus());

                ps.executeUpdate();
            }catch(SQLException e)
        {
            throw new RuntimeException(e);
        }
        return orders;
    }
    
    @Override
    public void update(Orders order){
        
        PreparedStatement ps;

        try {
            String sql
            = " UPDATE orders"
            + " SET "
            + "     buyer = ?, "
            + "     totprice = ?, "
            + "     status = ? "
            + "WHERE order_Id = ?;";
           
            ps = conn.prepareStatement(sql);
           
            ps.setString(1, order.getBuyer());
            ps.setFloat(2, order.getTotprice());
            ps.setString(3, order.getStatus());
            ps.setLong(4, order.getOrder_Id());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     @Override
    public void delete(Orders order){
        PreparedStatement ps;

        try {

            String sql
                    = "DELETE "
                    + "FROM orders "
                    + "WHERE "
                    + "order_Id = ?;";

            ps = conn.prepareStatement(sql);

            ps.setLong(1, order.getOrder_Id());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    @Override
     public List<Orders> ALLview(){
        PreparedStatement ps;
        ArrayList<Orders> Listorders = new ArrayList<Orders>();
        try{
            String sql
                    = " SELECT *"
                    + "FROM orders;";
        
       
            ps = conn.prepareStatement(sql);
            
            

            ResultSet resultSet = ps.executeQuery();
            
            
            while(resultSet.next()){
              Listorders.add(read(resultSet));
            }
            
            resultSet.close();
            ps.close();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Listorders;
     }
    
     @Override
     public List<Orders> findByBuyer(String buyer){
        PreparedStatement ps;
        ArrayList<Orders> Listorders = new ArrayList<Orders>();
        try{
            String sql
                    = " SELECT *"
                    + " FROM orders"
                    + " WHERE buyer = ?;";
        
            ps = conn.prepareStatement(sql);
            
            ps.setString(1,buyer);
            
            ResultSet resultSet = ps.executeQuery();
            
            
            while(resultSet.next()){
              Listorders.add(read(resultSet));
            }
            
            resultSet.close();
            ps.close();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return Listorders;
     }
    
     @Override
    public Orders findOrdersByOrder_Id(Long order_Id){
        
        PreparedStatement ps;
         Orders order = new Orders();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM orders "
                      + " WHERE order_Id = ?;";

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
