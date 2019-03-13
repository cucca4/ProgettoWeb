
package model.dao.mySQLJDBCImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.mo.Product;
import model.dao.ProductDAO;
import model.dao.exception.DuplicatedObjectException;


public class ProductDAOMySQLJDBCImpl implements ProductDAO{
    Connection conn;
    
    public ProductDAOMySQLJDBCImpl(Connection conn){
        this.conn=conn;
    }
    
    Product read(ResultSet rs){
            Product product = new Product();
       
        try {
            product.setProd_Id(rs.getLong("prod_Id"));
        } catch (SQLException sqle) {
        }
        try {
            product.setBrand(rs.getString("brand"));
        } catch (SQLException sqle) {
        }
        try {
            product.setModel(rs.getString("model"));
        } catch (SQLException sqle) {
        }
        try {
            product.setDescription(rs.getString("description"));
        } catch (SQLException sqle) {
        }
        
       try {
            product.setCategory(rs.getString("category"));
        } catch (SQLException sqle) {
        }
        try {
            product.setPrice(rs.getFloat("price"));
        } catch (SQLException sqle) {
        }
        try {
            product.setQty(rs.getLong("qty"));
        } catch (SQLException sqle) {
        }
        return product;
    }
  
  @Override
  public Product insert(
     Long Prod_Id,
     String brand,
     String model,
     String description,
     String category,
     Float price,
     Long qty)throws DuplicatedObjectException {
      
  
     PreparedStatement ps;
     ResultSet resultSet;
     Product product=new Product();
     product.setProd_Id(Prod_Id);
     product.setBrand(brand);
     product.setModel(model);
     product.setDescription(description);
     product.setCategory(category);
     product.setPrice(price);
     product.setQty(qty);
     try{
                  String sql;
                  sql = "SELECT * "
                      + "FROM product "
                      + "WHERE brand = ? AND "
                      + "model = ? AND "
                      + "deleted_Pr = '0';";

                  ps = conn.prepareStatement(sql);
                  ps.setString(1, product.getBrand());
                  ps.setString(2, product.getModel());

                  resultSet = ps.executeQuery();

                  if(resultSet.next())
                      throw new DuplicatedObjectException("ProductDAOJDBCImpl.create: Tentativo di inserimento di un prodotto già esistente.");
                  
                  try {
                      sql
                      = " INSERT INTO product "
                      + "   ( Prod_id,"
                      + "     brand,"
                      + "     model,"
                      + "     description,"
                      + "     category,"
                      + "     price,"
                      + "     qty,"
                      + "     deleted_Pr "
                      + "   ) "
                      + " VALUES (?,?,?,?,?,?,?,'0');";

                      ps = conn.prepareStatement(sql);

                      ps.setLong(1, product.getProd_Id());
                      ps.setString(2,product.getBrand());
                      ps.setString(3,product.getModel() );
                      ps.setString(4, product.getDescription());
                      ps.setString(5,product.getCategory());
                      ps.setFloat (6,product.getPrice());
                      ps.setLong (7, product.getQty());

                      ps.executeUpdate();
                  }
                  catch(SQLIntegrityConstraintViolationException e){
                      throw new DuplicatedObjectException("ProductDAOJDBCImpl.create: Tentativo di inserimento di un prodotto già esistente.");
                  }
              }
              catch(SQLException e)
              {
                  throw new RuntimeException(e);
              }

              return product;
        }
    
 

    @Override
    public void update(Product product) throws DuplicatedObjectException{
        PreparedStatement ps;
    
        try {

            String sql;
            sql
            ="UPDATE product"
            +"SET "
            + "     brand = ?, "
            + "     model = ?, "
            + "     description = ?, "
            + "     category = ?, "
            + "     price = ?, "
            + "     qty = ?, "
            + "WHERE "
            +"Prod_Id = ?;";

            ps = conn.prepareStatement(sql);

            ps.setString(2,product.getBrand());
            ps.setString(3,product.getModel() );
            ps.setString(4, product.getDescription());
            ps.setString(5,product.getCategory());
            ps.setFloat (6,product.getPrice());
            ps.setLong (7, product.getQty());

            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Product product){
        PreparedStatement ps;

        try {

            String sql
                    = "DELETE "
                    + "FROM product "
                    + "WHERE "
                    + "Prod_Id = ?;";

            ps = conn.prepareStatement(sql);

            ps.setLong(1, product.getProd_Id());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

  @Override
  public Product findByProdId(Long prod_Id){
         PreparedStatement ps;
         Product product = new Product();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM product "
                      + " WHERE Prod_Id = ?;";

              ps = conn.prepareStatement(sq1);
              ps.setLong(1, prod_Id);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  product = read(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return product;
     }
  
  @Override
  public Product findByBrand(String brand){

    PreparedStatement ps;
         Product product = new Product();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM product "
                      + " WHERE brand = ?;";

              ps = conn.prepareStatement(sq1);
              ps.setString(1, brand);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  product = read(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return product;

  }
  
  @Override
  public Product findByCategory(String category){

    PreparedStatement ps;
         Product product = new Product();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM product "
                      + " WHERE category = ?;";

              ps = conn.prepareStatement(sq1);
              ps.setString(1, category);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  product = read(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return product;

}
  
  @Override
  public Product findByModel(String model){

    PreparedStatement ps;
         Product product = new Product();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM product "
                      + " WHERE model = ?;";

              ps = conn.prepareStatement(sq1);
              ps.setString(1, model);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  product = read(resultSet);

              resultSet.close();
              ps.close();
              
              return product;
              
         }catch(SQLException e){
              throw new RuntimeException(e);
         }
    }
  
  @Override
  public Product findByQty(Long qty){

    PreparedStatement ps;
         Product product = new Product();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM product "
                      + " WHERE qty = ?;";

              ps = conn.prepareStatement(sq1);
              ps.setLong(1, qty);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  product = read(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return product;

}
  
  @Override
  public Product findByPrice(Float price){

    PreparedStatement ps;
         Product product = new Product();
         

         try {
              String sq1
                      = "SELECT * "
                      + " FROM product "
                      + " WHERE price = ?;";

              ps = conn.prepareStatement(sq1);
              ps.setFloat (1, price);

              ResultSet resultSet = ps.executeQuery();

              if(resultSet.next())
                  product = read(resultSet);

              resultSet.close();
              ps.close();

         }catch(SQLException e){
              throw new RuntimeException(e);
         }

         return product;

    }
    
    
}
