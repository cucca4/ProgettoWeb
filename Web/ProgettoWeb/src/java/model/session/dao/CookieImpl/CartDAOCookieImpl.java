
package model.session.dao.CookieImpl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.mo.Product;
import model.session.dao.CartDAO;
import model.session.mo.Cart;


public class CartDAOCookieImpl implements CartDAO {
    
    HttpServletRequest request;
    HttpServletResponse response;
    
    public CartDAOCookieImpl(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    @Override
    public Cart create(Long productId,Integer Qty,Float price) {
        Cart cart= new Cart();
        List<Long> productList = new ArrayList();
        List<Integer> productQty = new ArrayList();
        List<Float> productPrice = new ArrayList();
        productList.add(productId);
        productQty.add(Qty);
        productPrice.add(price);
        
        cart.setProductList(productList);
        cart.setProductQty(productQty);
        cart.setProductPrice(productPrice);
        
        Cookie cookie;
        cookie = new Cookie("cart", encode(cart));
        cookie.setPath("/");
        response.addCookie(cookie);
        
        return cart;
    }

    @Override
    public void update(Cart cart) {
        
        Cookie cookie;
        cookie = new Cookie("cart", encode(cart));
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void destroy() {
        Cookie cookie;
        cookie = new Cookie("cart","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public Cart find() {
        Cookie[] cookies = request.getCookies();
        Cart cart = null;
        
        if(cookies != null){
            for(int i=0; i<cookies.length && cart == null; i++){
                if(cookies[i].getName().equals("cart")){
                    cart = decode(cookies[i].getValue());
                }
            }
        }
        
        return cart;
    }
    
    @Override
    public void removeId(Cart cart,Long Id){
        Cookie cookie;
        cookie = new Cookie("cart", remove(cart,Id));
        cookie.setPath("/");
        response.addCookie(cookie);
    }
       
    private String encode(Cart cart) {
        String encodedCart = "";
        for(int i = 0; i<cart.getProductList().size(); i++){
            encodedCart = encodedCart + cart.getProductList().get(i).toString() + "#" + cart.getProductQty().get(i).toString()+ "#" + cart.getProductPrice().get(i).toString() + "#";
        }
        return encodedCart;
    }

    private Cart decode(String encodedCart) {
        Cart cart= new Cart();
        List<Long> productList = new ArrayList();
        List<Integer> productQty = new ArrayList();
        List<Float> productPrice = new ArrayList();
        
        String[] values = encodedCart.split("#");
        for(int i=0;i<values.length; i=i+3){
            productList.add(Long.parseLong(values[i]));
            productQty.add(Integer.parseInt(values[i+1]));
            productPrice.add(Float.parseFloat(values[i+2]));
        }
        
        cart.setProductList(productList);
        cart.setProductQty(productQty);
        cart.setProductPrice(productPrice);
        
        return cart;
    }
    
    private String remove(Cart cart, Long IdRemove){
        Cart cart2= new Cart();
        List<Long> productList = new ArrayList();
        List<Integer> productQty = new ArrayList();
        List<Float> productPrice = new ArrayList();
        for(int i = 0; i<cart.getProductList().size(); i++){
            if(IdRemove != cart.getProductList().get(i))
                productList.add(cart.getProductList().get(i));
                productQty.add(cart.getProductQty().get(i));
                productPrice.add(cart.getProductPrice().get(i));
        }
        cart2.setProductList(productList);
        cart2.setProductQty(productQty);
        cart2.setProductPrice(productPrice);
        
        String encoded = encode(cart2);
        return encoded;
    }
}
