
package model.mo;



public class PushedProduct {
    private Long Prod_Id;
    private String brand;
    private String model;
    private String description;
    private String category;
    private Float price;
    private Long qty;

    public Long getProd_Id() {
        return Prod_Id;
    }

    public void setProd_Id(Long Prod_Id) {
        this.Prod_Id = Prod_Id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
    
    
}
