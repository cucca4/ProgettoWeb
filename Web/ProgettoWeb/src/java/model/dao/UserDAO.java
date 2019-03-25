package model.dao;

import java.util.List;
import model.dao.exception.DuplicatedObjectException;
import model.mo.User;

public interface UserDAO {

  public User insert(
          //Long userId,
          String username,
          String password,
          String firstname,
          String surname,
          String email,
          String address,
          String city,
          String cap)throws DuplicatedObjectException;

  public void update(User user);
  public void updatePassword(User user);
  public void delete(User user);
  public List<User> Allview();
  public User findByUserId(Long userId);
  
  public User findByUsername(String username);

}
