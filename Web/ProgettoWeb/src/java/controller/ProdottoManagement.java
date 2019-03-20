
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import services.config.Configuration;
import services.logservice.LogService;

import model.mo.User;
import model.mo.Product;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.ProductDAO;
import model.dao.exception.DuplicatedObjectException;
import model.session.dao.LoggedAdminDAO;

import model.session.mo.LoggedUser;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;
import model.session.mo.LoggedAdmin;




public class ProdottoManagement {
    
    private ProdottoManagement(){
        
    }
    
    public static void view(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    LoggedUser loggedUser;
    DAOFactory daoFactory = null;
    ProductDAO productDAO;
    String found = "trovato";
    Logger logger = LogService.getApplicationLogger();
    
    try {

      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();
      
      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      
      String model= (String) request.getParameter("search");
      
      
      productDAO = daoFactory.getProductDAO();
      Product product = productDAO.findByModel(model) ;
      
      daoFactory.commitTransaction();
      
      if(product.getBrand()== null)
          found = "ERRORE!PRODOTTO NON TROVATO";
      System.out.println("<<<<<<<<<<"+found);
      
      if(loggedUser!=null) 
            request.setAttribute("applicationMessage","Benvenuto " + loggedUser.getUsername());
            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("notfoundMessage", found);
            request.setAttribute("product", product);
            request.setAttribute("viewUrl", "prodottoManagement/prodottoView");

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Controller Error", e);
      throw new RuntimeException(e);
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
      String model = (String) request.getParameter("model");
      
      ProductDAO productDAO = daoFactory.getProductDAO();
      Product product = productDAO.findByModel(model);
      
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
            request.setAttribute("viewUrl", "prodottoManagement/prodottoView");

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

    try {

        sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);

        DAOFactory daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        daoFactory.beginTransaction();

        LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUserDAO.destroy();

        String model = (String) request.getParameter("model");

        ProductDAO productDAO = daoFactory.getProductDAO();
        Product product = productDAO.findByModel(model);

        daoFactory.commitTransaction(); //IMPORTANTISSIMA

        if(product.getBrand()== null)
            found = "ERRORE!PRODOTTO NON TROVATO";
            
        request.setAttribute("loggedOn",false);
        request.setAttribute("loggedUser", null);
        request.setAttribute("notfoundMessage", found);
        request.setAttribute("product", product);
        request.setAttribute("viewUrl", "prodottoManagement/prodottoView");
      
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Controller Error", e);
      throw new RuntimeException(e);
    }
  }
  
  
  public static void createProduct(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        Logger logger = LogService.getApplicationLogger();
        String create;
        Product vprod = new Product();
        LoggedAdmin loggedAdmin = null;
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            vprod.setProd_Id( new Long (request.getParameter("Prod_Id")));
            vprod.setBrand(request.getParameter("brand"));
            vprod.setModel(request.getParameter("model"));
            vprod.setDescription(request.getParameter("description"));
            vprod.setCategory(request.getParameter("category"));
            vprod.setPrice(new Float(request.getParameter("price")));
            vprod.setQty(new Long (request.getParameter("qty")));
            
            daoFactory.beginTransaction();
            ProductDAO productDAO = daoFactory.getProductDAO();
            try{
                
                Product product = productDAO.insert(vprod.getProd_Id(),vprod.getBrand(), vprod.getModel(), vprod.getDescription(),vprod.getCategory(), vprod.getPrice(),vprod.getQty());
                 create ="prodotto "+vprod.getModel()+" creato";
            } catch (DuplicatedObjectException e) {
                
                create = "Codice o prodotto già esistente";
               
            }
            
            daoFactory.commitTransaction();
            
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("createMessage", create);
            request.setAttribute("deleteMessage", " ");
            request.setAttribute("viewUrl", "adminManagement/prodAdmin");
            
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);

            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } 
            catch (Throwable t) {
            }
            throw new RuntimeException(e);
        } 
        finally {
            try {
                    if (daoFactory != null) {
                        daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
  
  public static void deleteProduct(HttpServletRequest request, HttpServletResponse response){
        Logger logger = LogService.getApplicationLogger();
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        Product product;
        String delete;
        
        LoggedAdmin loggedAdmin = null;
        try{
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            String model = (String) request.getParameter("model");
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            ProductDAO productDAO = daoFactory.getProductDAO();
            product = productDAO.findByModel(model);
            productDAO.delete(product);
            delete = "prodotto "+model+" eliminato";
        
            daoFactory.commitTransaction();
            
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("createMessage", " ");
            request.setAttribute("deleteMessage", delete);
            request.setAttribute("viewUrl", "adminManagement/prodAdmin");
            
        }catch(Exception e){
            delete = "errore";
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
