package com.bip.OneStopShop.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
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
    @Email
    private String email;
    @NotNull
    private String password;
    private LocalDate createdOn;

    // private String address;
    // private Boolean accountNonLocked;
    // private Boolean accountNonExpired;
    // private Boolean enabled;

    public User() {
        this.id = null;
        this.createdOn = LocalDate.now();
    }

    public User(String firstname, String lastname, String password, String email) {
        this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.createdOn = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
