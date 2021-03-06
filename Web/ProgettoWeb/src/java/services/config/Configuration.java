package services.config;

import java.util.logging.Level;

import model.dao.DAOFactory;
import model.session.dao.SessionDAOFactory;

public class Configuration {
  
  /* Database Configruation */
  public static final String DAO_IMPL=DAOFactory.MYSQLJDBCIMPL;
  public static final String DATABASE_DRIVER="com.mysql.jdbc.Driver";
  public static final String DATABASE_URL="jdbc:mysql://localhost/dronazon?user=root&password="
          + "";
  
  /* Session Configuration */
  public static final String SESSION_IMPL=SessionDAOFactory.COOKIEIMPL;
  
  /* Logger Configuration */
  public static final String GLOBAL_LOGGER_NAME="dronazon";  
  public static final String GLOBAL_LOGGER_FILE="C:\\Users\\Cuccarello\\Documents\\logs\\dronazon_log.%g.txt";
  public static final Level GLOBAL_LOGGER_LEVEL=Level.ALL;
  
}
