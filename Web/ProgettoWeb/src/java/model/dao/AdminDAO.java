package model.dao;

import java.util.List;

import model.mo.Admin;

import model.dao.exception.DuplicatedObjectException;

public interface AdminDAO {

  public Admin insert(
           Long admin_Id,
           String username_Ad,
           String password_Ad);

  public void update(Admin Admin) throws DuplicatedObjectException;

  public void delete(Admin Admin);

  public Admin findAdminByUsername(String username);
  
  public int countOrdersByBuyer (String buyer);
}
