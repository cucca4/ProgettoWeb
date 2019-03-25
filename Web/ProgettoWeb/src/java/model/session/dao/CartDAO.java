
package model.session.dao;
import java.util.List;
import model.mo.Product;
import model.session.mo.Cart;

public interface CartDAO {
    
    public Cart create(Long productId,Integer Qty,Float price);   
    public void update(Cart cart);
    public void destroy();
    public Cart find();
    
}
