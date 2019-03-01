
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
            product.setName(rs.getString("name"));
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
     String name,
     String brand,
     String model,
     String description,
     String category,
     Float price,
     Long qty)throws DuplicatedObjectException {
      
  
     PreparedStatement ps;
     ResultSet resultSet;
     Product product=new Product();
     product.setName(name);
     product.setBrand(brand);
     product.setModel(model);
     product.setDescription(description);
     product.setCategory(category);
     product.setPrice(price);
     product.setQty(qty);
     try{
                  String sql;
                  sql = "SELECT * "
                      + "FROM product ";
                      
                      

                  ps = conn.prepareStatement(sql);
                  resultSet = ps.executeQuery();
    }
    catch(SQLIntegrityConstraintViolationException e){
                      throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un volo già esistente.");        
    }
    
  @Override
  public Product findByProdId(Long prod_Id){}
  
  @Override
  public Product findByName(String name){}
  
  @Override
  public Product findByCategory(String category){}
  
  @Override
  public Product findByModel(String model){}
  
  @Override
  public Product findByQty(Long qty){}
  
  @Override
  public Product findByPrice(Float Price){}
    
    
}
