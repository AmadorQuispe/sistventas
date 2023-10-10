package com.amsoft.models;

public class UserDetail {
    private String email;
    private String completeName;
    private Long customerId;
    
    public UserDetail() {
    }
    public UserDetail(String email, String completeName, Long customerId) {
        this.email = email;
        this.completeName = completeName;
        this.customerId = customerId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCompleteName() {
        return completeName;
    }
    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    
}
