

package model.dao;


import java.util.List;
import model.mo.PushedProduct;
import model.session.mo.LoggedUser;


public interface PushedProductDAO {
    public List<PushedProduct> getPushedProduct();
}
