package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import model.mo.User;
import model.mo.Contact;
import model.dao.ContactDAO;
import model.dao.exception.DuplicatedObjectException;


public class ContactDAOMySQLJDBCImpl implements ContactDAO {

  private final String COUNTER_ID = "contactId";
  Connection conn;

  public ContactDAOMySQLJDBCImpl(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Contact insert(
          User user,
          String firstname,
          String surname,
          String email,
          String address,
          String city,
          String phone,
          String sex) throws DuplicatedObjectException {

    PreparedStatement ps;
    Contact contact = new Contact();
    contact.setUser(user);
    contact.setFirstname(firstname);
    contact.setSurname(surname);
    contact.setEmail(email);
    contact.setAddress(address);
    contact.setCity(city);
    contact.setPhone(phone);
    contact.setSex(sex);

    try {

      String sql
              = " SELECT contactId "
              + " FROM contact "
              + " WHERE "
              + " deleted ='N' AND "
              + " firstname = ? AND"
              + " surname = ? AND"
              + " email = ? AND"
              + " userId = ? ";

      ps = conn.prepareStatement(sql);
      int i = 1;
      ps.setString(i++, contact.getFirstname());
      ps.setString(i++, contact.getSurname());
      ps.setString(i++, contact.getEmail());
      ps.setLong(i++, contact.getUser().getUserId());

      ResultSet resultSet = ps.executeQuery();

      boolean exist;
      exist = resultSet.next();
      resultSet.close();

      if (exist) {
        throw new DuplicatedObjectException("ContactDAOJDBCImpl.insert: Tentativo di inserimento di un contatto già esistente.");
      }

      sql = "update counter set counterValue=counterValue+1 where counterId='" + COUNTER_ID + "'";

      ps = conn.prepareStatement(sql);
      ps.executeUpdate();

      sql = "SELECT counterValue FROM counter where counterId='" + COUNTER_ID + "'";

      ps = conn.prepareStatement(sql);
      resultSet = ps.executeQuery();
      resultSet.next();

      contact.setContactId(resultSet.getLong("counterValue"));

      resultSet.close();

      sql
              = " INSERT INTO contact "
              + "   ( contactId,"
              + "     userId,"
              + "     firstname,"
              + "     surname,"
              + "     email,"
              + "     address,"
              + "     city,"
              + "     phone,"
              + "     sex,"
              + "     deleted "
              + "   ) "
              + " VALUES (?,?,?,?,?,?,?,?,?,'N')";

      ps = conn.prepareStatement(sql);
      i = 1;
      ps.setLong(i++, contact.getContactId());
      ps.setLong(i++, contact.getUser().getUserId());
      ps.setString(i++, contact.getFirstname());
      ps.setString(i++, contact.getSurname());
      ps.setString(i++, contact.getEmail());
      ps.setString(i++, contact.getAddress());
      ps.setString(i++, contact.getCity());
      ps.setString(i++, contact.getPhone());
      ps.setString(i++, contact.getSex());

      ps.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return contact;

  }

  @Override
  public void update(Contact contact) throws DuplicatedObjectException {
    PreparedStatement ps;

    try {

      String sql
              = " SELECT contactId "
              + " FROM contact "
              + " WHERE "
              + " deleted ='N' AND "
              + " firstname = ? AND"
              + " surname = ? AND"
              + " email = ? AND"
              + " userId = ? AND "
              + " contactId <> ?";

      ps = conn.prepareStatement(sql);
      int i = 1;
      ps.setString(i++, contact.getFirstname());
      ps.setString(i++, contact.getSurname());
      ps.setString(i++, contact.getEmail());
      ps.setLong(i++, contact.getUser().getUserId());
      ps.setLong(i++, contact.getContactId());

      ResultSet resultSet = ps.executeQuery();

      boolean exist;
      exist = resultSet.next();
      resultSet.close();

      if (exist) {
        throw new DuplicatedObjectException("ContactDAOJDBCImpl.create: Tentativo di aggiornamento in un contatto già esistente.");
      }

      sql 
              = " UPDATE contact "
              + " SET "
              + "   firstname = ?, "
              + "   surname = ?, "
              + "   email = ?, "              
              + "   address = ?, "
              + "   city = ?, "
              + "   phone = ?, "
              + "   sex= ? "
              + " WHERE "
              + "   contactId = ? ";

      ps = conn.prepareStatement(sql);
      i = 1;
      ps.setString(i++, contact.getFirstname());
      ps.setString(i++, contact.getSurname());
      ps.setString(i++, contact.getEmail());      
      ps.setString(i++, contact.getAddress());
      ps.setString(i++, contact.getCity());
      ps.setString(i++, contact.getPhone());
      ps.setString(i++, contact.getSex());
      ps.setLong(i++, contact.getContactId());
      ps.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void delete(Contact contact) {

    PreparedStatement ps;

    try {

      String sql 
              = " UPDATE contact "
              + " SET deleted='Y' "
              + " WHERE "
              + " contactId=?";

      ps = conn.prepareStatement(sql);
      ps.setLong(1, contact.getContactId());
      ps.executeUpdate();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public Contact findByContactId(Long contactId) {

    PreparedStatement ps;
    Contact contact = null;

    try {

      String sql
              = " SELECT *"
              + " FROM contact "
              + " WHERE "
              + "   contactId = ? AND "
              + "   deleted  = 'N' ";

      ps = conn.prepareStatement(sql);
      ps.setLong(1, contactId);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        contact = read(resultSet);
      }
      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return contact;

  }

  @Override
  public List<String> findInitialsByUser(User user) {

    PreparedStatement ps;
    String initial;
    ArrayList<String> initials = new ArrayList<String>();

    try {

      String sql
              = " SELECT DISTINCT UCase(Left(surname,1)) AS initial "
              + " FROM contact "
              + " WHERE "
              + "   userId = ? "
              + "   AND deleted = 'N' "
              + " ORDER BY UCase(Left(surname,1))";

      ps = conn.prepareStatement(sql);
      ps.setLong(1, user.getUserId());

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        initial = resultSet.getString("initial");
        initials.add(initial);
      }

      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return initials;
  }

  @Override
  public List<Contact> findByInitialAndSearchString(User user, String initial, String searchString) {

    PreparedStatement ps;
    Contact contact;
    ArrayList<Contact> contacts = new ArrayList<Contact>();

    try {

      String sql
              = " SELECT * FROM contact "
              + " WHERE "
              + "   userId = ? "
              + "   AND deleted='N' ";
      if (initial != null) {
        sql += " AND UCASE(LEFT(surname,1)) = ? ";
      }
      if (searchString != null) {
        sql += " AND ( INSTR(surname,?)>0 "; //dice la posizione inn cui la sottostringa è presente nella stringa (in questo caso è per capire solo se ce)
        sql += " OR INSTR(firstname,?)>0 ";
        sql += " OR INSTR(address,?)>0 ";
        sql += " OR INSTR(city,?)>0 ";
        sql += " OR INSTR(phone,?)>0 ";
        sql += " OR INSTR(email,?)>0 )";
      }
      sql += "ORDER BY surname, firstname, email";

      ps = conn.prepareStatement(sql);
      int i = 1;
      ps.setLong(i++, user.getUserId());
      if (initial != null) {
        ps.setString(i++, initial);
      }
      if (searchString != null) {
        ps.setString(i++, searchString);
        ps.setString(i++, searchString);
        ps.setString(i++, searchString);
        ps.setString(i++, searchString);
        ps.setString(i++, searchString);
        ps.setString(i++, searchString);
      }

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        contact = read(resultSet);
        contacts.add(contact);
      }

      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return contacts;
  }

  Contact read(ResultSet rs) {
    Contact contact = new Contact();
    User user = new User();
    contact.setUser(user);
    try {
      contact.setContactId(rs.getLong("contactId"));
    } catch (SQLException sqle) {
    }
    try {
      contact.getUser().setUserId(rs.getLong("userId"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setFirstname(rs.getString("firstname"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setSurname(rs.getString("surname"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setEmail(rs.getString("email"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setAddress(rs.getString("address"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setCity(rs.getString("city"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setPhone(rs.getString("phone"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setSex(rs.getString("sex"));
    } catch (SQLException sqle) {
    }
    try {
      contact.setDeleted(rs.getString("deleted").equals("Y"));
    } catch (SQLException sqle) {
    }
    return contact;
  }
}
