package model.dao;

import java.util.List;

import model.mo.User;
import model.mo.Contact;

import model.dao.exception.DuplicatedObjectException;

public interface ContactDAO {

  public Contact insert(
          User user,
          String firstname,
          String surname,
          String email,
          String address,
          String city,
          String phone,
          String sex) throws DuplicatedObjectException;

  public void update(Contact contact) throws DuplicatedObjectException;

  public void delete(Contact contact);

  public Contact findByContactId(Long contactId);

  public List<String> findInitialsByUser(User user);

  public List<Contact> findByInitialAndSearchString(User user, String initial, String searchString);

}
