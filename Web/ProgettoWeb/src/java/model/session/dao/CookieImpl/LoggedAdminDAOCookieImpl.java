
package model.session.dao.CookieImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.session.mo.LoggedAdmin;
import model.session.dao.LoggedAdminDAO;


public class LoggedAdminDAOCookieImpl implements LoggedAdminDAO{
    HttpServletRequest request;
    HttpServletResponse response;
    
    public LoggedAdminDAOCookieImpl(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
    
    @Override
    public LoggedAdmin create(String username_Ad, Long Admin_Id) {
        LoggedAdmin loggedAdmin = new LoggedAdmin();
        loggedAdmin.setUsername_Ad(username_Ad);
        loggedAdmin.setAdmin_Id(Admin_Id);
        
        Cookie cookie;
        cookie = new Cookie("loggedAdmin", encode(loggedAdmin));
        cookie.setPath("/");
        response.addCookie(cookie);
        
        return loggedAdmin;        
    }
    
    @Override
    public void update(LoggedAdmin loggedAdmin) {
        Cookie cookie;
        cookie = new Cookie("loggedAdmin", encode(loggedAdmin));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void destroy() {
        Cookie cookie;
        cookie = new Cookie("loggedAdmin","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        
    }

    @Override
    public LoggedAdmin find() {
        Cookie[] cookies = request.getCookies();
        LoggedAdmin loggedAdmin = null;
        
        if(cookies != null){
            for(int i=0; i<cookies.length && loggedAdmin == null; i++){
                if(cookies[i].getName().equals("loggedAdmin")){
                    loggedAdmin = decode(cookies[i].getValue());
                }
            }
        }
        
        return loggedAdmin;
    }
    
    private String encode(LoggedAdmin loggedAdmin){
        String encodedAdmin;
        encodedAdmin = loggedAdmin.getUsername_Ad()+"#"+ loggedAdmin.getAdmin_Id();
        return encodedAdmin;
    }
    
    private LoggedAdmin decode(String encodedAdmin){
        LoggedAdmin loggedAdmin = new LoggedAdmin();
        
        String[] values = encodedAdmin.split("#");
        loggedAdmin.setUsername_Ad(values[0]);
        loggedAdmin.setAdmin_Id(new Long(values[1]));
        
        return loggedAdmin;        
    }
}