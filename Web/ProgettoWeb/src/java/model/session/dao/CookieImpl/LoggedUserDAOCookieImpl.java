package model.session.dao.CookieImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.session.mo.LoggedUser;
import model.session.dao.LoggedUserDAO;

public class LoggedUserDAOCookieImpl implements LoggedUserDAO {

  HttpServletRequest request;
  HttpServletResponse response;

  public LoggedUserDAOCookieImpl(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }

  @Override
  public LoggedUser create(
          Long userId,
          String username ){

    LoggedUser loggedUser = new LoggedUser();
    loggedUser.setUserId(userId);
    loggedUser.setUsername(username);

    Cookie cookie;
    cookie = new Cookie("loggedUser", encode(loggedUser));
    cookie.setPath("/");
    response.addCookie(cookie);

    return loggedUser;

  }

  @Override
  public void update(LoggedUser loggedUser) {

    Cookie cookie;
    cookie = new Cookie("loggedUser", encode(loggedUser));
    cookie.setPath("/");
    response.addCookie(cookie);

  }

  @Override
  public void destroy() {

    Cookie cookie;
    cookie = new Cookie("loggedUser", "");
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);

  }

  @Override
  public LoggedUser find() {

    Cookie[] cookies = request.getCookies();
    LoggedUser loggedUser = null;

    if (cookies != null) {
      for (int i = 0; i < cookies.length && loggedUser == null; i++) {
        if (cookies[i].getName().equals("loggedUser")) {
          loggedUser = decode(cookies[i].getValue());
        }
      }
    }

    return loggedUser;

  }

  private String encode(LoggedUser loggedUser) {

    String encodedLoggedUser;
    encodedLoggedUser = loggedUser.getUserId() + "#" + loggedUser.getUsername();
    return encodedLoggedUser;

  }

  private LoggedUser decode(String encodedLoggedUser) {

    LoggedUser loggedUser = new LoggedUser();
    
    String[] values = encodedLoggedUser.split("#");

    loggedUser.setUserId(Long.parseLong(values[0]));
    loggedUser.setUsername(values[1]);
 

    return loggedUser;
    
  }
  
}

