
package model.session.dao;

import model.session.mo.LoggedAdmin;


public interface LoggedAdminDAO {
    
    public LoggedAdmin create (  String username_Ad,Long admin_Id);
        
    
    public void update(LoggedAdmin loggedAdmin);
    public void destroy();
    public LoggedAdmin find();
}
