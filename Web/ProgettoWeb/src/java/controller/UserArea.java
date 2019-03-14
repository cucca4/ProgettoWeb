
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;


import services.config.Configuration;
import services.logservice.LogService;

import model.dao.exception.DuplicatedObjectException;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.mo.Orders;
import model.mo.User;

import model.session.mo.LoggedUser;
import model.session.dao.LoggedUserDAO;
import model.session.dao.SessionDAOFactory;



public class UserArea {
    
    private UserArea(){
    }
    
    public static void view(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
                sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
                sessionDAOFactory.initSession(request, response);

                LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
                loggedUser = loggedUserDAO.find();
                
                System.out.println(loggedUser.getUserId());

                daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
                daoFactory.beginTransaction();

                UserDAO userDAO = daoFactory.getUserDAO();
                User user = userDAO.findByUserId(loggedUser.getUserId());
                
                daoFactory.commitTransaction();
                
                System.out.println(user.getFirstname());
                
                user.setPassword(null);
                request.setAttribute("user", user);
                request.setAttribute("loggedOn", loggedUser != null);
                request.setAttribute("loggedUser", loggedUser);
                request.setAttribute("actionPage", "account");
                request.setAttribute("viewUrl", "userAreaManagement/view");
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
    
    
    public static void createUser(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        String applicationMessage = null;
        Logger logger = LogService.getApplicationLogger();
        User vuser = new User();
       
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
           
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            
            vuser.setUsername(request.getParameter("username"));
            vuser.setPassword(request.getParameter("password"));
            vuser.setFirstname(request.getParameter("firstname"));
            vuser.setSurname(request.getParameter("surname"));
            vuser.setEmail(request.getParameter("email"));
            vuser.setAddress(request.getParameter("address"));
            vuser.setCity(request.getParameter("city"));
            vuser.setCap(request.getParameter("cap"));
            
            
            
            
            daoFactory.beginTransaction();
            UserDAO userDAO = daoFactory.getUserDAO();
            
            try{
                
                User user = userDAO.insert(vuser.getUsername(), vuser.getPassword(), vuser.getFirstname(),vuser.getSurname(), vuser.getEmail(),vuser.getAddress(),vuser.getCity(),vuser.getCap());

            } catch (DuplicatedObjectException e) {
                
                applicationMessage = "Codice o prodotto già esistente";
               
            }
            
            daoFactory.commitTransaction();
            
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "homeManagement/view");
            
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
    

    public static void modify(HttpServletRequest request, HttpServletResponse response){
        
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
      
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();

            UserDAO userDAO = daoFactory.getUserDAO();
            User user = new User();
            user.setUserId(new Long(request.getParameter("userId")));
            user.setUsername(request.getParameter("username"));
            user.setFirstname(request.getParameter("firstname"));
            user.setSurname(request.getParameter("surname"));
            user.setEmail(request.getParameter("email"));
            user.setAddress("address");
            user.setCity("city");
            user.setCap("cap");
            
            
            userDAO.update(user);
            loggedUser.setUsername(user.getUsername());
            loggedUser.setUserId(user.getUserId());
            
            daoFactory.commitTransaction();
            
            request.setAttribute("viewUrl", "userAreaManagement/userArea");
            request.setAttribute("user",user);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("actionPage", "account");
            request.setAttribute("applicationMessage", applicationMessage);
            

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    public static void modifyPassword(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String oldPassword = request.getParameter("oldpassword");
            String newPassword = request.getParameter("newpassword");
      
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findByUserId(loggedUser.getUserId());
            
            if(user.getPassword().equals(oldPassword)){
                user.setPassword(newPassword);
                userDAO.updatePassword(user);
            } else {
                applicationMessage = "Password errata";
                request.setAttribute("applicationMessage", applicationMessage);
            }
            
            daoFactory.commitTransaction();
            
            user.setPassword(null);
            
            request.setAttribute("user",user);
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("actionPage", "setpassword");
            request.setAttribute("viewUrl", "userAreaManagement/userArea");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
    
    public static void deleteAccount(HttpServletRequest request, HttpServletResponse response){
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedUser loggedUser;
        String applicationMessage = null;
        
        Logger logger = LogService.getApplicationLogger();
        
        try{
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();
            
            String password = request.getParameter("password");
      
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            UserDAO userDAO = daoFactory.getUserDAO();
            User user = userDAO.findByUserId(loggedUser.getUserId());
            
            if(user.getPassword().equals(password)){
                
                userDAO.delete(user);
                loggedUserDAO.destroy();
                loggedUser = null;
                /*commonView(daoFactory, request, loggedUser);*/
                
                request.setAttribute("viewUrl", "homeManagement/view");
                
            } else {
                user.setPassword(null);
                applicationMessage = "Password errata";
                request.setAttribute("applicationMessage", applicationMessage);
                request.setAttribute("user",user);
                request.setAttribute("actionPage", "delete");
                request.setAttribute("viewUrl", "userAreaManagement/userArea");
            }
            
            daoFactory.commitTransaction();
            
            request.setAttribute("loggedOn", loggedUser != null);
            request.setAttribute("loggedUser", loggedUser);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Controller Error", e);
            try {
                if (daoFactory != null) {
                    daoFactory.rollbackTransaction();
                }
            } catch (Throwable t) {
            }
            throw new RuntimeException(e);

        } finally {
            try {
                if (daoFactory != null) {
                    daoFactory.closeTransaction();
                }
            } catch (Throwable t) {
            }
        }
    }
  
}
  