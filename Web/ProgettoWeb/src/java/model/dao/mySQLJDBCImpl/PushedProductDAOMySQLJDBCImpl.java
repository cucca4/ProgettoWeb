

package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.PushedProductDAO;
import model.mo.PushedProduct;
import model.session.mo.LoggedUser;


public class PushedProductDAOMySQLJDBCImpl implements PushedProductDAO{
    
    Connection conn;
    
    public PushedProductDAOMySQLJDBCImpl(Connection conn) {
        this.conn=conn;
    }
    
    @Override
    public List<PushedProduct> getPushedProduct() {
        PreparedStatement ps;
      ArrayList<PushedProduct> pushedProduct = new ArrayList<PushedProduct>();

      try {
          String sq1
            = "SELECT * "
            + "FROM pushedproduct;";
            
          ps = conn.prepareStatement(sq1);

          ResultSet resultSet = ps.executeQuery();

          while(resultSet.next()){

              pushedProduct.add(read(resultSet));
          }

          resultSet.close();
          ps.close();


      }catch(SQLException e){
          throw new RuntimeException(e);
      }
      return pushedProduct;
    } 
    
    
    PushedProduct read(ResultSet rs){
        PushedProduct pushedProduct = new PushedProduct();
        try {
            pushedProduct.setProd_Id(rs.getLong("prod_Id"));
        } catch (SQLException sqle) {
        }
        try {
            pushedProduct.setBrand(rs.getString("brand"));
        } catch (SQLException sqle) {
        }
        try {
            pushedProduct.setModel(rs.getString("model"));
        } catch (SQLException sqle) {
        }
        try {
            pushedProduct.setDescription(rs.getString("description"));
        } catch (SQLException sqle) {
        }
        
       try {
            pushedProduct.setCategory(rs.getString("category"));
        } catch (SQLException sqle) {
        }
        try {
            pushedProduct.setPrice(rs.getFloat("price"));
        } catch (SQLException sqle) {
        }
        try {
            pushedProduct.setQty(rs.getLong("qty"));
        } catch (SQLException sqle) {
        }
        return pushedProduct;
        
        
    }
   
}
