

package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.config.Configuration;
import services.logservice.LogService;
import java.util.logging.Logger;
import java.util.logging.Level;

import model.mo.Admin;

import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedAdminDAO;

import model.dao.DAOFactory;
import model.dao.AdminDAO;
import model.dao.PushedProductDAO;
import model.mo.PushedProduct;

import model.session.mo.LoggedAdmin;

public class AdminManagement {
    
    private AdminManagement(){
    }
    
    
     public static void viewLogin (HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            if(loggedAdmin != null){
                loggedAdminDAO.destroy();
                loggedAdmin = null;
            }
            
            
            request.setAttribute("viewUrl", "adminManagement/loginAdmin");
            
            request.setAttribute("loggedadmin", loggedAdmin);
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    
    
    
    public static void view (HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();            
            
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("createMessage", " ");
            request.setAttribute("deleteMessage", " ");
            request.setAttribute("countMessage", " ");
            request.setAttribute("viewUrl", "adminManagement/home");           
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    /*
    public static void viewHome (HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        LoggedAdmin loggedAdmin;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            request.setAttribute("viewUrl", "adminManagement/home");
            request.setAttribute("loggedadmin", loggedAdmin);
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    */
    
    public static void login (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedAdmin loggedAdmin;
        String applicationMessage = null;
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
           
            
            String username_Ad = request.getParameter("username_Ad");
            String password_Ad = request.getParameter("password_Ad");
            
            AdminDAO adminDAO = daoFactory.getAdminDAO();
            Admin admin = adminDAO.findAdminByUsername(username_Ad);
          
            
            if(admin == null || !admin.getPassword_Ad().equals(password_Ad)){
                loggedAdminDAO.destroy();
                applicationMessage = "username o password errati";
                loggedAdmin=null;
                
                request.setAttribute("viewUrl", "adminManagement/loginAdmin");
            } else {
                applicationMessage = "Corretti";
                loggedAdmin = loggedAdminDAO.create(admin.getUsername_Ad(),admin.getAdmin_Id());
                
            }
            
            daoFactory.commitTransaction();
            
            request.setAttribute("loggedAdminOn",loggedAdmin!=null);
            request.setAttribute("loggedadmin", loggedAdmin);
            request.setAttribute("createMessage", " ");
            request.setAttribute("deleteMessage", " ");
            request.setAttribute("countMessage", " ");
            request.setAttribute("adminApplicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "adminManagement/home");
          
        }catch(Exception e){
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
    
    public static void logout(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    
    
    Logger logger = LogService.getApplicationLogger();

    try {

      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();
      
      
      LoggedAdminDAO loggedUserDAO = sessionDAOFactory.getLoggedAdminDAO();
      loggedUserDAO.destroy();
      PushedProductDAO pushedProductDAO = daoFactory.getPushedProductDAO();
      List<PushedProduct> pushedProduct = pushedProductDAO.getPushedProduct();

      
      daoFactory.commitTransaction();
      
      
      request.setAttribute("loggedAdminOn",false);
      request.setAttribute("loggedAdmin", null);
      request.setAttribute("loggedOn",false);
      request.setAttribute("loggedUser", null);
      request.setAttribute("pushedProduct", pushedProduct);
      request.setAttribute("viewUrl", "homeManagement/view");

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Controller Error", e);
      throw new RuntimeException(e);

    }
    
  }
    
    public static void count (HttpServletRequest request, HttpServletResponse response) {

        Logger logger = LogService.getApplicationLogger();
        
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedAdmin loggedAdmin;
        String applicationMessage = null;

    try {

        sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
        sessionDAOFactory.initSession(request, response);

        LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
        loggedAdmin = loggedAdminDAO.find();

        daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
        daoFactory.beginTransaction();
        String count;


        String username= request.getParameter("username");


        AdminDAO adminDAO = daoFactory.getAdminDAO();
        int numorders = adminDAO.countOrdersByBuyer(username);
        
        if(numorders!= 1)
         count = "L'utente "+username+" ha effettuato " + numorders +" ordini";
        else count = "L'utente "+username+" ha effettuato " + numorders +" ordine";
        
        request.setAttribute("loggedAdminOn",loggedAdmin!=null);
        request.setAttribute("loggedadmin", loggedAdmin);
        request.setAttribute("countMessage", count);
        request.setAttribute("adminApplicationMessage", applicationMessage);
        request.setAttribute("viewUrl", "adminManagement/home");
      
    }catch (Exception e) {
      logger.log(Level.SEVERE, "Controller Error", e);
      throw new RuntimeException(e);

    }
    }
    
}