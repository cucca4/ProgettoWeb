
package model.dao;

import java.util.List;

import model.dao.exception.DuplicatedObjectException;
import model.mo.Product;

public interface ProductDAO {
     public Product insert(
     Long Prod_Id,
     String name,
     String brand,
     String model,
     String description,
     String category,
     Float price,
     Long qty)throws DuplicatedObjectException;
    
  public void update(Product Product) throws DuplicatedObjectException;

  public void delete(Product Product);

  public Product findByProdId(Long prod_Id);
  public Product findByName(String name);
  public Product findByCategory(String category);
  public Product findByModel(String model);
  public Product findByQty(Long qty);
  public Product findByPrice(Float Price);
}