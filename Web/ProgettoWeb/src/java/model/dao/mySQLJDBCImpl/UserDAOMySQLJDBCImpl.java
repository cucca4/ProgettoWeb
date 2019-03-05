package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import model.dao.exception.DuplicatedObjectException;

import model.mo.User;
import model.dao.UserDAO;


public class UserDAOMySQLJDBCImpl implements UserDAO {

  private final String COUNTER_ID = "userId";  
  Connection conn;

  public UserDAOMySQLJDBCImpl(Connection conn) {
    this.conn = conn;
  }
      
    User read(ResultSet rs){
        
        User user = new User();
        try {
            user.setUserId(rs.getLong("userId"));
        } catch (SQLException sqle) {
        }
        try {
            user.setUsername(rs.getString("username"));
        } catch (SQLException sqle) {
        }
        try {
            user.setPassword(rs.getString("password"));
        } catch (SQLException sqle) {
        }
        try {
            user.setFirstname(rs.getString("firstname"));
        } catch (SQLException sqle) {
        }
        try {
            user.setSurname(rs.getString("surname"));
        } catch (SQLException sqle) {
        }
        try {
            user.setEmail(rs.getString("email"));
        } catch (SQLException sqle) {
        }
        try {
            user.setAddress(rs.getString("address"));
        } catch (SQLException sqle) {
        }
        try {
            user.setCity(rs.getString("city"));
        } catch (SQLException sqle) {
        }
        try {
            user.setCap(rs.getString("cap"));
        } catch (SQLException sqle) {
        }
        try {
            user.setDeleted(rs.getBoolean("deleted"));
        } catch (SQLException sqle) {
        }
        return user;
    }
  @Override
  public User insert(String username,
          String password,
          String firstname,
          String surname,
          String email,
          String address,
          String city,
          String cap) throws DuplicatedObjectException{
    
          PreparedStatement ps;
     ResultSet resultSet;
     User user=new User();
     user.setUsername(username);
     user.setPassword(password);
     user.setFirstname(firstname);
     user.setSurname(surname);
     user.setEmail(email);
     user.setAddress(address);
     user.setCity(city);
     user.setCap(cap);
     try{
                  String sql;
                  sql = "SELECT * "
                      + "FROM user "
                      + "WHERE firstname = ? AND "
                      + "surname = ? AND "
                      + "deleted_Pr = '0' ";

                  ps = conn.prepareStatement(sql);
                  ps.setString(1, user.getFirstname());
                  ps.setString(2, user.getSurname());

                  resultSet = ps.executeQuery();

                  if(resultSet.next())
                      throw new DuplicatedObjectException("ProductDAOJDBCImpl.create: Tentativo di inserimento di un utente già esistente.");
                  
                  try {
                      sql
                      = " INSERT INTO user "
                      + "   ( userId,"
                      + "     username,"
                      + "     password,"
                      + "     firstname,"
                      + "     surname,"
                      + "     email,"
                      + "     address,"
                      + "     city,"
                      + "     cap,"
                      + "     deleted "
                      + "   ) "
                      + " VALUES (?,?,?,?,?,?,?,?,?,'0')";

                      ps = conn.prepareStatement(sql);

                      ps.setLong(1, user.getUserId());
                      ps.setString(2,user.getUsername());
                      ps.setString(3,user.getPassword() );
                      ps.setString(4, user.getFirstname());
                      ps.setString(5,user.getSurname());
                      ps.setString (6,user.getEmail());
                      ps.setString (7, user.getAddress());
                      ps.setString (8, user.getCity());
                      ps.setString (9, user.getCap());

                      ps.executeUpdate();
                  }
                  catch(SQLIntegrityConstraintViolationException e){
                      throw new DuplicatedObjectException("UserDAOJDBCImpl.create: Tentativo di inserimento di un utente già esistente.");
                  }
              }
              catch(SQLException e)
              {
                  throw new RuntimeException(e);
              }

              return user;
  
  }

  @Override
  public void update(User user)
  {
        PreparedStatement ps;
    
        try {

            String sql;
            sql
            ="UPDATE user"
            +"SET "
            + "     username = ?, "
            + "     password = ?, "
            + "     firstname = ?, "
            + "     surname = ?, "
            + "     email = ?, "
            + "     address = ?, "
            + "     city = ?, "
            + "     cap = ?,"
            + "WHERE "
            +"admin_Id = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getFirstname());
            ps.setString(5,user.getSurname());
            ps.setString(6,user.getEmail());
            ps.setString(7,user.getAddress());
            ps.setString(8,user.getCity());
            ps.setString(9,user.getCap());
            
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
  }

  @Override
  public void delete(User user) {
    
      PreparedStatement ps;

        try {

            String sql
                    = "DELETE "
                    + "FROM user "
                    + "WHERE "
                    + "userId = ? ";

            ps = conn.prepareStatement(sql);

            ps.setLong(1, user.getUserId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      
  }

  @Override
  public User findByUserId(Long userId) {

    PreparedStatement ps;
    User user = null;

    try {

      String sql
              = " SELECT * "
              + "   FROM user "
              + " WHERE "
              + "   userId = ?";

      ps = conn.prepareStatement(sql);
      ps.setLong(1, userId);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        user = read(resultSet);
      }
      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return user;

  }

@Override
  public User findByUsername(String username) {

    PreparedStatement ps;
    User user = null;

    try {

      String sql
              = " SELECT * "
              + "   FROM user "
              + " WHERE "
              + "   username = ?";

      ps = conn.prepareStatement(sql);
      ps.setString(1, username);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        user = read(resultSet);
      }
      resultSet.close();
      ps.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return user;

  }  
  
}
