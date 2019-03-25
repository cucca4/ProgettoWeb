
package model.session.mo;

import java.util.List;

public class Cart {
    
    private List<Long> productList;
    private List<Integer> productQty;
    private List<Float> productPrice;

    public List<Float> getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(List<Float> productPrice) {
        this.productPrice = productPrice;
    }

    public List<Integer> getProductQty() {
        return productQty;
    }

    public void setProductQty(List<Integer> productQty) {
        this.productQty = productQty;
    }

    public List<Long> getProductList() {
        return productList;
    }

    public void setProductList(List<Long> productList) {
        this.productList = productList;
    }
    
}
