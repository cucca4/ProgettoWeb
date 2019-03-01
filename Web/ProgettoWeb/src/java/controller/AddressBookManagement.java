package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.config.Configuration;
import services.logservice.LogService;

import model.mo.User;
import model.mo.Admin;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.exception.DuplicatedObjectException;

import model.session.mo.LoggedUser;
import model.session.dao.SessionDAOFactory;
import model.session.dao.LoggedUserDAO;
import model.dao.AdminDAO;

public class AddressBookManagement {

  private AddressBookManagement() {
  }

  public static void view(HttpServletRequest request, HttpServletResponse response) {

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

      commonView(daoFactory, sessionDAOFactory, request);

      daoFactory.commitTransaction();

      request.setAttribute("loggedOn",loggedUser!=null);
      request.setAttribute("loggedUser", loggedUser);
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "addressBookManagement/view");

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

  public static void delete(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;

    Logger logger = LogService.getApplicationLogger();

    try {

      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();

      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();

      Long contactId = new Long(request.getParameter("contactId"));

      AdminDAO contactDAO = daoFactory.getContactDAO();
      Admin contact = contactDAO.findByContactId(contactId);
      contactDAO.delete(contact);

      commonView(daoFactory, sessionDAOFactory, request);

      daoFactory.commitTransaction();

      request.setAttribute("loggedOn",loggedUser!=null);
      request.setAttribute("loggedUser", loggedUser);
      request.setAttribute("viewUrl", "addressBookManagement/view");

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

  public static void insertView(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    LoggedUser loggedUser;

    Logger logger = LogService.getApplicationLogger();

    try {

      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();

      String selectedInitial = request.getParameter("selectedInitial");

      request.setAttribute("loggedOn",loggedUser!=null);
      request.setAttribute("loggedUser", loggedUser);
      request.setAttribute("selectedInitial", selectedInitial);
      request.setAttribute("viewUrl", "addressBookManagement/insModView");

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Controller Error", e);
      throw new RuntimeException(e);
    }

  }

  public static void insert(HttpServletRequest request, HttpServletResponse response) {

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

      UserDAO userDAO = daoFactory.getUserDAO();
      User user = userDAO.findByUserId(loggedUser.getUserId());
      
      AdminDAO contactDAO = daoFactory.getContactDAO();

      try {

        contactDAO.insert(
                user,
                request.getParameter("firstname"),
                request.getParameter("surname"),
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("city"),
                request.getParameter("phone"),
                request.getParameter("sex"));

      } catch (DuplicatedObjectException e) {
        applicationMessage = "Contatto già esistente";
        logger.log(Level.INFO, "Tentativo di inserimento di contatto già esistente");
      }

      commonView(daoFactory, sessionDAOFactory, request);

      daoFactory.commitTransaction();

      request.setAttribute("loggedOn",loggedUser!=null);
      request.setAttribute("loggedUser", loggedUser);
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "addressBookManagement/view");

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

  public static void modifyView(HttpServletRequest request, HttpServletResponse response) {

    SessionDAOFactory sessionDAOFactory;
    DAOFactory daoFactory = null;
    LoggedUser loggedUser;
    
    Logger logger = LogService.getApplicationLogger();

    try {

      sessionDAOFactory = SessionDAOFactory.getSesssionDAOFactory(Configuration.SESSION_IMPL);
      sessionDAOFactory.initSession(request, response);

      LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
      loggedUser = loggedUserDAO.find();

      daoFactory = DAOFactory.getDAOFactory(Configuration.DAO_IMPL);
      daoFactory.beginTransaction();

      String selectedInitial = request.getParameter("selectedInitial");
      Long contactId = new Long(request.getParameter("contactId"));

      AdminDAO contactDAO = daoFactory.getContactDAO();
      Admin contact = contactDAO.findByContactId(contactId);

      daoFactory.commitTransaction();

      request.setAttribute("loggedOn",loggedUser!=null);
      request.setAttribute("loggedUser", loggedUser);
      request.setAttribute("contact", contact);
      request.setAttribute("selectedInitial", selectedInitial);
      request.setAttribute("viewUrl", "addressBookManagement/insModView");

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

  public static void modify(HttpServletRequest request, HttpServletResponse response) {

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

      AdminDAO contactDAO = daoFactory.getContactDAO();
      Admin contact = contactDAO.findByContactId(new Long(request.getParameter("contactId")));

      contact.setFirstname(request.getParameter("firstname"));
      contact.setSurname(request.getParameter("surname"));
      contact.setEmail(request.getParameter("email"));
      contact.setAddress(request.getParameter("address"));
      contact.setCity(request.getParameter("city"));
      contact.setPhone(request.getParameter("phone"));
      contact.setSex(request.getParameter("sex"));

      try {

        contactDAO.update(contact);

      } catch (DuplicatedObjectException e) {
        applicationMessage = "Contatto già esistente";
        logger.log(Level.INFO, "Tentativo di inserimento di contatto già esistente");
      }

      commonView(daoFactory, sessionDAOFactory, request);

      daoFactory.commitTransaction();

      request.setAttribute("loggedOn",loggedUser!=null);
      request.setAttribute("loggedUser", loggedUser);
      request.setAttribute("applicationMessage", applicationMessage);
      request.setAttribute("viewUrl", "addressBookManagement/view");

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

  private static void commonView(DAOFactory daoFactory, SessionDAOFactory sessionDAOFactory, HttpServletRequest request) {

    List<String> initials;
    List<Admin> contacts;

    LoggedUserDAO loggedUserDAO = sessionDAOFactory.getLoggedUserDAO();
    LoggedUser loggedUser = loggedUserDAO.find();

    UserDAO userDAO = daoFactory.getUserDAO();
    User user = userDAO.findByUserId(loggedUser.getUserId());

    AdminDAO contactDAO = daoFactory.getContactDAO();
    initials = contactDAO.findInitialsByUser(user);

    String selectedInitial = request.getParameter("selectedInitial");

    if (selectedInitial == null || (!selectedInitial.equals("*") && !initials.contains(selectedInitial))) {
      if (initials.size() > 0) {
        selectedInitial = initials.get(0);
      } else {
        selectedInitial = "*";
      }
    }

    contacts = contactDAO.findByInitialAndSearchString(user,
            (selectedInitial.equals("*") ? null : selectedInitial), null);

    request.setAttribute("selectedInitial", selectedInitial);
    request.setAttribute("initials", initials);
    request.setAttribute("contacts", contacts);

  }
}
