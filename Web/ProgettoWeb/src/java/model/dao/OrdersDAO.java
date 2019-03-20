
package model.dao;

import java.util.List;

import model.mo.Orders;

import model.dao.exception.DuplicatedObjectException;


public interface OrdersDAO {
    public Orders insert(
           Long order_Id,
           String buyer,
           Float totprice,
           String status)throws DuplicatedObjectException;

  public void update(Orders order);

  public void delete(Orders order);
  
  public List<Orders> ALLview();
  
  public List<Orders> findByBuyer(String buyer);

  public Orders findOrdersByOrder_Id(Long order_Id);
}
