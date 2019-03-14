package model.dao.mySQLJDBCImpl;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import services.config.Configuration;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.AdminDAO;
import model.dao.OrdersDAO;
import model.dao.ProductDAO;
import model.dao.PushedProductDAO;

public class MySQLJDBCDAOFactory extends DAOFactory {

  private Connection connection;

  @Override
  public void beginTransaction() {

    try {
      Class.forName(Configuration.DATABASE_DRIVER);
      this.connection = DriverManager.getConnection(Configuration.DATABASE_URL);
      this.connection.setAutoCommit(false);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void commitTransaction() {
    try {
      this.connection.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void rollbackTransaction() {

    try {
      this.connection.rollback();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void closeTransaction() {
    try {
      this.connection.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public UserDAO getUserDAO() {
    return new UserDAOMySQLJDBCImpl(connection);
  }
  
  
  @Override
  public AdminDAO getAdminDAO() {
    return new AdminDAOMySQLJDBCImpl(connection);
  }

  @Override
    public PushedProductDAO getPushedProductDAO() {
        return new PushedProductDAOMySQLJDBCImpl(connection);
    }

    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOMySQLJDBCImpl(connection);
    }
    
    @Override
    public  OrdersDAO getOrdersDAO(){
        return new OrdersDAOMySQLJDBCImpl(connection);
    }
}