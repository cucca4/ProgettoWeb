package model.mo;

public class Admin {
  private Long admin_Id;
  private String username_Ad;
  private String password_Ad;
  private boolean deleted_Ad;

    public Long getAdmin_Id() {
        return admin_Id;
    }

    public void setAdmin_Id(Long admin_Id) {
        this.admin_Id = admin_Id;
    }

    public String getUsername_Ad() {
        return username_Ad;
    }

    public void setUsername_Ad(String username_Ad) {
        this.username_Ad = username_Ad;
    }

    public String getPassword_Ad() {
        return password_Ad;
    }

    public void setPassword_Ad(String password_Ad) {
        this.password_Ad = password_Ad;
    }

    public boolean isDeleted_Ad() {
        return deleted_Ad;
    }

    public void setDeleted_Ad(boolean deleted_Ad) {
        this.deleted_Ad = deleted_Ad;
    }

   

}
