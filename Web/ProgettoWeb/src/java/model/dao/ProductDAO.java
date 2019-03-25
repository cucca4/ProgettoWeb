
package model.dao;

import java.util.List;

import model.dao.exception.DuplicatedObjectException;
import model.mo.Product;

public interface ProductDAO {
     public Product insert(
     Long Prod_Id,
     String brand,
     String model,
     String description,
     String category,
     Float price,
     Long qty)throws DuplicatedObjectException;
    
  public void update(Product product) throws DuplicatedObjectException;

  public void delete(Product product);
  public List<Product> getProduct();
  public Product findByProdId(Long prod_Id);
  public Product findByBrand(String brand);
  public Product findByCategory(String category);
  public List<Product> findByFilter(String category,String brand);
  public Product findByModel(String model);
  public Product findByQty(Long qty);
  public Product findByPrice(Float price);
}