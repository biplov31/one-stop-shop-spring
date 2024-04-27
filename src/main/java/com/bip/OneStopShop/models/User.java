package com.bip.OneStopShop.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("user_account")
public class User {
    @Id
    private Integer id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotNull
    private String password;
    @Email
    private String email;
    @NotNull
    private LocalDate createdOn;
    // @NotBlank
    // private String phoneNumber;
    // private String address;
    // private Boolean accountNonLocked;
    // private Boolean accountNonExpired;
    // private Boolean enabled;

    public User(String firstname, String lastname, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.createdOn = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public String getPhoneNumber() {
    //     return phoneNumber;
    // }
    //
    // public void setPhoneNumber(String phoneNumber) {
    //     this.phoneNumber = phoneNumber;
    // }
    //
    // public String getAddress() {
    //     return address;
    // }
    //
    // public void setAddress(String address) {
    //     this.address = address;
    // }
    //
    // public Boolean getAccountNonLocked() {
    //     return accountNonLocked;
    // }
    //
    // public void setAccountNonLocked(Boolean accountNonLocked) {
    //     this.accountNonLocked = accountNonLocked;
    // }
    //
    // public Boolean getAccountNonExpired() {
    //     return accountNonExpired;
    // }
    //
    // public void setAccountNonExpired(Boolean accountNonExpired) {
    //     this.accountNonExpired = accountNonExpired;
    // }
    //
    // public Boolean getEnabled() {
    //     return enabled;
    // }
    //
    // public void setEnabled(Boolean enabled) {
    //     this.enabled = enabled;
    // }
}
