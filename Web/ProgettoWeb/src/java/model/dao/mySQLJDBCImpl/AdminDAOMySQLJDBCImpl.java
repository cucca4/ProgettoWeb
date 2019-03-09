package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import model.mo.User;
import model.mo.Orders;
import model.mo.Admin;
import model.dao.exception.DuplicatedObjectException;
import model.dao.AdminDAO;


public class AdminDAOMySQLJDBCImpl implements AdminDAO {

  Connection conn;
    
    public AdminDAOMySQLJDBCImpl(Connection conn){
        this.conn = conn;
    }
  Admin read(ResultSet rs){
        Admin admin = new Admin();
       
        try {
            admin.setAdmin_Id(rs.getLong("admin_Id"));
        } catch (SQLException sqle) {
        }
        try {
            admin.setUsername_Ad(rs.getString("username_Ad"));
        } catch (SQLException sqle) {
        }
        
        try {
            admin.setPassword_Ad(rs.getString("password_Ad"));
        } catch (SQLException sqle) {
        }
        try {
            admin.setDeleted_Ad(rs.getBoolean("deleted_Ad"));
        } catch (SQLException sqle) {
        }
        return admin;
    }
  
    int readCount(ResultSet rs) {
        try {
            return rs.getInt("count");
        }catch(SQLException sqle){
            return -1;
        }
    }
  
      @Override
    public Admin insert(Long admin_Id,
           String username_Ad,
           String password_Ad){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
  
    @Override
    public void update(Admin admin) throws DuplicatedObjectException{
        PreparedStatement ps;
    
        try {

            String sql;
            sql
            ="UPDATE admin"
            +"SET "
            + "     username_Ad = ?, "
            + "     psw_ad = ?, "
            + "WHERE "
            +"admin_Id = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(2,admin.getUsername_Ad());
            ps.setString(3,admin.getPassword_Ad() );
            
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void delete(Admin admin){
        PreparedStatement ps;

        try {

            String sql
                    = "DELETE "
                    + "FROM admin "
                    + "WHERE "
                    + "admin_Id = ? ";

            ps = conn.prepareStatement(sql);

            ps.setLong(1, admin.getAdmin_Id());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
  @Override
        public Admin findAdminByUsername(String username){
        PreparedStatement ps;
        Admin admin = null;

        try {

            String sql
                    = "SELECT * "
                    + "FROM admin "
                    + "WHERE "
                    + "username_Ad = ? AND "
                    + "deleted_Ad = '0' ";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                admin = read(resultSet);
            }
            resultSet.close();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    @Override
    public int countOrdersByBuyer(String buyer) {
        PreparedStatement ps;
        int numOrders=-1;
         

         try {
              String sq1
                      = "SELECT COUNT * AS count "
                      + " FROM orders "
                      + " WHERE buyer = ?";

              ps = conn.prepareStatement(sq1);
              ps.setString(1, buyer);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  numOrders = readCount(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return numOrders;
    }
}
