package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import services.config.Configuration;
import services.logservice.LogService;

import model.mo.User;
import model.dao.DAOFactory;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import model.mo.Product;

import model.session.mo.LoggedUser;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;

public class CatalogManagement {
    
    private CatalogManagement(){
    }
    
    public static void view(HttpServletRequest request, HttpServletResponse response) {

        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;

        Logger logger = LogService.getApplicationLogger();

        try {

            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();

            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();

            ProductDAO productDAO = daoFactory.getProductDAO();
            List<Product> product = productDAO.getProduct();

            if(loggedUser!=null) 
                request.setAttribute("applicationMessage","Benvenuto " + loggedUser.getUsername());

            daoFactory.commitTransaction();

            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("categoria", null);
            request.setAttribute("marca", null);
            request.setAttribute("product", product);
            request.setAttribute("viewUrl", "catalogManagement/view");

        } catch (Exception e) {
          logger.log(Level.SEVERE, "Controller Error", e);
          throw new RuntimeException(e);
        }
    }
  
  public static void filter(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    Logger logger = LogService.getApplicationLogger();

    try {

        sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);

        daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        daoFactory.beginTransaction();

        LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
        loggedUser = loggedUserDAO.find();

        String categoria = request.getParameter("categoria");
        String marca = request.getParameter("marca");

        ProductDAO productDAO = daoFactory.getProductDAO();
        List<Product> product = productDAO.findByFilter(categoria,marca);

        System.out.println("CHECK POINT " + marca + " AND " + categoria);

        if(loggedUser!=null) 
            request.setAttribute("applicationMessage","Benvenuto " + loggedUser.getUsername());

        daoFactory.commitTransaction();

        request.setAttribute("loggedOn",loggedUser!=null);
        request.setAttribute("loggedUser", loggedUser);
        request.setAttribute("categoria", categoria);
        request.setAttribute("marca", marca);
        request.setAttribute("product", product);
        request.setAttribute("viewUrl", "catalogManagement/view");;

    }catch (Exception e) {
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

            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findByUsername(username);

            ProductDAO productDAO = daoFactory.getProductDAO();
            List<Product> product = productDAO.getProduct();

            if (user == null || !user.getPassword().equals(password)) {
              loggedUserDAO.destroy(); //distrugge il cookie perche l'utente/password sono errati
              applicationMessage = "Username e password errati!";
              loggedUser=null;
            } else {
              loggedUser = loggedUserDAO.create(user.getUserId(), user.getUsername());
              applicationMessage="Benvenuto " + loggedUser.getUsername();
            }

            daoFactory.commitTransaction(); //IMPORTANTISSIMA

            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("product", product);
            request.setAttribute("categoria", null);
            request.setAttribute("marca", null);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "catalogManagement/view");

        }catch (Exception e) {
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
        DAOFactory daoFactory = null;

        Logger logger = LogService.getApplicationLogger();

        try {

            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();

            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUserDAO.destroy();

            ProductDAO productDAO = daoFactory.getProductDAO();
            List<Product> product = productDAO.getProduct();

            daoFactory.commitTransaction();

            request.setAttribute("loggedOn",false);
            request.setAttribute("loggedUser", null);
            request.setAttribute("categoria", null);
            request.setAttribute("marca", null);
            request.setAttribute("product", product);
            request.setAttribute("viewUrl", "catalogManagement/view");

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
}
