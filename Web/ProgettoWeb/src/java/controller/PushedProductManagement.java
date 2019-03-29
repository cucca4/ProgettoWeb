
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DAOFactory;
import model.dao.ProductDAO;
import model.dao.PushedProductDAO;
import model.dao.UserDAO;
import model.mo.Product;
import model.mo.PushedProduct;
import model.mo.User;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;
import model.session.mo.LoggedUser;
import services.config.Configuration;
import services.logservice.LogService;


public class PushedProductManagement {
    
    private PushedProductManagement(){
    }
    
    public static void click(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    LoggedUser loggedUser;
    DAOFactory daoFactory = null;
    PushedProductDAO productDAO;
    String found = "trovato";
    Logger logger = LogService.getApplicationLogger();
    
    try {

        sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);

        LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUser = loggedUserDAO.find();

        daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        daoFactory.beginTransaction();

        String model = request.getParameter("Prod_Id");
        Long id = Long.parseLong(model);
        
        productDAO = daoFactory.getPushedProductDAO();
        PushedProduct product = productDAO.findByProdId(id);
        ProductDAO productdao = daoFactory.getProductDAO();
        Product product2 = productdao.findByProdId(product.getProd_Id());
        product.setQty(product2.getQty()); // imposto la quantità del magazzino del product nel pushed in modo che non possa ordinare quantità non disponibili

        daoFactory.commitTransaction();
        System.out.println("CHECK POINT 1: " + product.getModel() + " VALUE.");
        if(product.getBrand()== null)
            found = "ERRORE!PRODOTTO NON TROVATO";
        System.out.println("CHECK POINT 2 " + found);

        if(loggedUser!=null) 
            request.setAttribute("applicationMessage","Benvenuto " + loggedUser.getUsername());
            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("notfoundMessage", found);
            request.setAttribute("product", product);
            request.setAttribute("viewUrl", "prodottoManagement/pushedproductview");

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Controller Error", e);
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction(); //FONDAMENTALE ALTRIMENTI SI PIANTA IL DB
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction(); //IMPORTANTE PERCHE ALTRIMENTI AVREI TROPPE CONNESSIONI FISICHE VERSO IL DB 
        }
      } catch (Throwable t) {
      }
    }
  }

  public static void logon(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    String applicationMessage = null;
    String found = "trovato";
     
    Logger logger = LogService.getApplicationLogger();
    
    try {

      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();

      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();

      String username = request.getParameter("username");
      String password = request.getParameter("password");
      
      String model = request.getParameter("model");
      Long id = Long.parseLong(model);
        
      PushedProductDAO productDAO = daoFactory.getPushedProductDAO();
      PushedProduct product = productDAO.findByProdId(id) ;
      
      UserDAO userDAO = daoFactory.getUserDAO();
      User user = userDAO.findByUsername(username);
      
      if (user == null || !user.getPassword().equals(password)) {
        loggedUserDAO.destroy(); //distrugge il cookie perche l'utente/password sono errati
        applicationMessage = "Username e password errati!";
        loggedUser=null;
      } else {
        loggedUser = loggedUserDAO.create(user.getUserId(), user.getUsername());
        applicationMessage="Benvenuto " + loggedUser.getUsername();
      }

      daoFactory.commitTransaction(); //IMPORTANTISSIMA

      if(product.getBrand()== null)
          found = "ERRORE!PRODOTTO NON TROVATO";
            
      if(loggedUser!=null) 
            request.setAttribute("applicationMessage","Benvenuto " + loggedUser.getUsername());
            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("notfoundMessage", found);
            request.setAttribute("product", product);
            request.setAttribute("viewUrl", "prodottoManagement/pushedproductview");

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Controller Error", e);
      try {
        if (daoFactory != null) {
          daoFactory.rollbackTransaction(); //FONDAMENTALE ALTRIMENTI SI PIANTA IL DB
        }
      } catch (Throwable t) {
      }
      throw new RuntimeException(e);

    } finally {
      try {
        if (daoFactory != null) {
          daoFactory.closeTransaction(); //IMPORTANTE PERCHE ALTRIMENTI AVREI TROPPE CONNESSIONI FISICHE VERSO IL DB 
        }
      } catch (Throwable t) {
      }
    }
  }

  public static void logout(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    String found = "trovato";
    Logger logger = LogService.getApplicationLogger();
    DAOFactory daoFactory = null;
    
    try {

        sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);

        daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        daoFactory.beginTransaction();

        LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUserDAO.destroy();

        String model = request.getParameter("model");
        Long id = Long.parseLong(model);
        
        PushedProductDAO productDAO = daoFactory.getPushedProductDAO();
        PushedProduct product = productDAO.findByProdId(id) ;

        daoFactory.commitTransaction(); //IMPORTANTISSIMA

        if(product.getBrand()== null)
            found = "ERRORE!PRODOTTO NON TROVATO";
            
        request.setAttribute("loggedOn",false);
        request.setAttribute("loggedUser", null);
        request.setAttribute("notfoundMessage", found);
        request.setAttribute("product", product);
        request.setAttribute("viewUrl", "prodottoManagement/pushedproductview");
      
    } catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if(daoFactory != null){
                    daoFactory.rollbackTransaction();
                }
            }catch (Throwable t){
        }
        throw new RuntimeException(e);
        } finally {
            try {
                if(daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            }catch(Throwable t){
            }
        }
    }
    
}
