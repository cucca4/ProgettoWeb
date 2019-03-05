

package controller;

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

import model.session.mo.LoggedAdmin;

public class AdminManagement {
    
    private AdminManagement(){
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
            
            if(loggedAdmin != null){
                loggedAdminDAO.destroy();
                loggedAdmin = null;
            }
            
            
            request.setAttribute("viewUrl", "adminManagement/login");
            
            request.setAttribute("loggedadmin", loggedAdmin);
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Controller Error", e);
            throw new RuntimeException(e);
        }
    }
    
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
    
    public static void login (HttpServletRequest request, HttpServletResponse response){
        
        Logger logger = LogService.getApplicationLogger();
        
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        LoggedAdmin loggedAdmin;
        String applicationMessage;
        
        try{
            
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedAdminDAO loggedAdminDAO = sessionDAOFactory.getLoggedAdminDAO();
            loggedAdmin = loggedAdminDAO.find();
            
            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            
            daoFactory.beginTransaction();
            
            AdminDAO adminDAO = daoFactory.getAdminDAO();
            
            String username_Ad = request.getParameter("username_Ad");
            
            Long admin_Id = new Long(request.getParameter("admin_Id"));
            String password_Ad = request.getParameter("password_Ad");
            
            Admin admin = adminDAO.findAdminByAdminId(admin_Id);
            
            daoFactory.commitTransaction();
            
            if(admin == null || !admin.getPassword_Ad().equals(password_Ad)){
                loggedAdminDAO.destroy();
                applicationMessage = "username o password errati";
                loggedAdmin=null;
                
                request.setAttribute("viewUrl", "adminManagement/login");
            } else {
                applicationMessage = "Corretti";
                loggedAdmin = loggedAdminDAO.create(admin.getUsername_Ad(),admin.getAdmin_Id());
                
                request.setAttribute("loggedadmin", loggedAdmin);
                request.setAttribute("loggedAdminOn",loggedAdmin!=null);
                request.setAttribute("viewUrl", "adminManagement/home");
            }
            
            request.setAttribute("adminApplicationMessage", applicationMessage);
          
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
}