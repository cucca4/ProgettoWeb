
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import services.config.Configuration;
import services.logservice.LogService;

import model.mo.User;
import model.mo.Product;
import model.dao.DAOFactory;
import model.dao.OrdersDAO;
import model.dao.UserDAO;
import model.dao.ProductDAO;
import model.dao.exception.DuplicatedObjectException;
import model.mo.Orders;
import model.session.dao.LoggedAdminDAO;

import model.session.mo.LoggedUser;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;
import model.session.mo.LoggedAdmin;
import model.session.mo.Cart;
import model.session.dao.CartDAO;


public class CartManagement {
    public static void add(HttpServletRequest request, HttpServletResponse response) {
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        Cart cart;
        LoggedUser loggedUser;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {

            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();

            CartDAO cartDAO = sessionDAOFactory.getCartDAO();
            cart = cartDAO.find();

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();

            Long prodId =  Long.parseLong(request.getParameter("prodId"));
            Integer qty = Integer.parseInt(request.getParameter("qty"));
            Float price = Float.parseFloat(request.getParameter("price"));

            ProductDAO productDAO = daoFactory.getProductDAO();  
            Product product = productDAO.findByProdId(prodId);
            
            if(product.getQty() < qty || qty == 0){
                cartDAO.destroy(); //distrugge il cookie perche la quantità inserita è errata
                applicationMessage = "quantità inserita non valida";
              } else{
                    if(cart == null){
                    cart = cartDAO.create(prodId,qty,price);
                    applicationMessage="Prodotto inserito nel carrello";
                    }else{
                        cartDAO.update(cart);
                        applicationMessage="Carrello aggiornato";
                    }
                }
            
            /*List<Long> productId = new ArrayList();
            productId = cart.getProductList();
            List<Integer> Qty = new ArrayList();
            Qty = cart.getProductQty();*/
            
            daoFactory.commitTransaction(); //IMPORTANTISSIMA
            request.setAttribute("cart", cart);
            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "homeManagement/view");
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
    
    public static void order(HttpServletRequest request, HttpServletResponse response) {
        SessionDAOFactory sessionDAOFactory;
        DAOFactory daoFactory = null;
        Cart cart;
        LoggedUser loggedUser;
        String applicationMessage = null;

        Logger logger = LogService.getApplicationLogger();

        try {
            sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
            sessionDAOFactory.initSession(request, response);
            
            LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
            loggedUser = loggedUserDAO.find();

            CartDAO cartDAO = sessionDAOFactory.getCartDAO();
            

            daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
            daoFactory.beginTransaction();
            
            OrdersDAO ordersDAO = daoFactory.getOrdersDAO();
            String buyer = request.getParameter("buyer");
            Float tot = Float.parseFloat(request.getParameter("total"));
            Orders order = ordersDAO.insert(buyer,tot);
            
            cartDAO.destroy();
            daoFactory.commitTransaction(); //IMPORTANTISSIMA
            request.setAttribute("loggedOn",loggedUser!=null);
            request.setAttribute("loggedUser", loggedUser);
            request.setAttribute("applicationMessage", applicationMessage);
            request.setAttribute("viewUrl", "homeManagement/view");
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
}
