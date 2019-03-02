
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

  public void update(Orders order) throws DuplicatedObjectException;

  public void delete(Orders order);

  public Orders findOrdersByOrder_Id(Long order_Id);
}
