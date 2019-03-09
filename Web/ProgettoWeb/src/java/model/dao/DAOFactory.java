package model.dao;

import model.dao.mySQLJDBCImpl.MySQLJDBCDAOFactory;

public abstract class DAOFactory {

 
  public static final String MYSQLJDBCIMPL = "MySQLJDBCImpl";

  public abstract void beginTransaction();
  public abstract void commitTransaction();
  public abstract void rollbackTransaction();
  public abstract void closeTransaction();
  
  public abstract UserDAO getUserDAO();

  public abstract ProductDAO getProductDAO();

  public static DAOFactory getDAOFactory(String whichFactory) {

    if (whichFactory.equals(MYSQLJDBCIMPL)) {
      return new MySQLJDBCDAOFactory();
    } else {
      return null;
    }
  }

    public AdminDAO getAdminDAO() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

