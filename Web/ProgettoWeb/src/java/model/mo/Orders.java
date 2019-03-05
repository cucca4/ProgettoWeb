
package model.mo;



public class Orders {
  private Long order_Id;
  private String buyer;
  private float totprice;
  private String status;
  private boolean deleted_Ad;

    public Long getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(Long order_Id) {
        this.order_Id = order_Id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public float getTotprice() {
        return totprice;
    }

    public void setTotprice(float totprice) {
        this.totprice = totprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDeleted_Ad() {
        return deleted_Ad;
    }

    public void setDeleted_Ad(boolean deleted_Ad) {
        this.deleted_Ad = deleted_Ad;
    }
  
  
}
