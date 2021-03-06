package model.dao.mySQLJDBCImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
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
  public User insert(//Long userId,
        String username,
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
        //user.setUserId(userId);
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
                + "WHERE  "
                + "firstname = ? AND "
                + "surname = ? AND "
                + "deleted = '0';";

            ps = conn.prepareStatement(sql);
            //ps.setLong(1, user.getUserId());
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getSurname());

            resultSet = ps.executeQuery();

            if(resultSet.next())
                throw new DuplicatedObjectException("ProductDAOJDBCImpl.create: Tentativo di inserimento di un utente già esistente.");

            try {
                sql
                = " INSERT INTO user "
                + "    (username,"
                + "     password,"
                + "     firstname,"
                + "     surname,"
                + "     email,"
                + "     address,"
                + "     city,"
                + "     cap,"
                + "     deleted "
                + "   ) "
                + " VALUES (?,?,?,?,?,?,?,?,'0');";

                ps = conn.prepareStatement(sql);

                //ps.setLong(1, user.getUserId());
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword() );
                ps.setString(3, user.getFirstname());
                ps.setString(4,user.getSurname());
                ps.setString (5,user.getEmail());
                ps.setString (6, user.getAddress());
                ps.setString (7, user.getCity());
                ps.setString (8, user.getCap());

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
            = " UPDATE user"
            + " SET "
            + "     username = ?, "
            + "     firstname = ?, "
            + "     surname = ?, "
            + "     email = ?, "
            + "     address = ?, "
            + "     city = ?, "
            + "     cap = ? "
            + "WHERE userId = ?;";

            ps = conn.prepareStatement(sql);

            ps.setString(1,user.getUsername());
            ps.setString(2,user.getFirstname());
            ps.setString(3,user.getSurname());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getAddress());
            ps.setString(6,user.getCity());
            ps.setString(7,user.getCap());
            ps.setLong(8,user.getUserId());
            
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
  }

  @Override
    public void updatePassword(User user){
        PreparedStatement ps;
        
        try {
            String sql 
            = "UPDATE user "
            + "SET "
            + "password = ? "              
            + "WHERE "
            + "userId = ?;";

            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getPassword());
            ps.setLong(2, user.getUserId());

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
                    + "userId = ?;";

            ps = conn.prepareStatement(sql);

            ps.setLong(1, user.getUserId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      
  }
  
  public List<User> Allview(){
      PreparedStatement ps;
        ArrayList<User> user = new ArrayList<User>();

        try {
            String sq1
              = "SELECT * "
              + "FROM user;";

            ps = conn.prepareStatement(sq1);

            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){

                user.add(read(resultSet));
            }

            resultSet.close();
            ps.close();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return user;
  }

  @Override
  public User findByUserId(Long userId) {

    PreparedStatement ps;
    User user = null;

    try {

      String sql
              = " SELECT * "
              + " FROM user "
              + " WHERE "
              + " userId = ?;";

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
              + " FROM user "
              + " WHERE "
              + " username = ?;";

      ps = conn.prepareStatement(sql);
      ps.setString(1, username);

      ResultSet resultSet = ps.executeQuery();

      if (resultSet.next()) {
        user = read(resultSet);
      }
      resultSet.close();
      ps.close();
      return user;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }  
}
