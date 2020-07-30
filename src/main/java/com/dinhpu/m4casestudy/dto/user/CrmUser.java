package com.dinhpu.m4casestudy.dto.user;

import com.dinhpu.m4casestudy.validation.FieldMatch;
import com.dinhpu.m4casestudy.validation.ValidEmail;
import com.dinhpu.m4casestudy.validation.ValidPhoneNumber;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = " không khớp")
})
public class CrmUser {
    private Long id;
//    @NotNull(message = "is required")
//    @Size(min = 1, message = "is required")
    private String userName;

    @NotNull(message = "không để trống")
    @Size(min = 1, message = "nhiều hơn 1 ký tự")
    private String password;

    @NotNull(message = "không để trống")
    @Size(min = 1, message = "nhiều hơn 1 ký tự")
    private String matchingPassword;

    @NotNull(message = "không để trống")
    @Size(min = 1, message = "nhiều hơn 1 ký tự")
    private String fullName;

    @ValidPhoneNumber(value={"0", "+84"}, message="số điện thoại phải bắt đầu với 0 hoặc +84")
    @NotNull(message = "is required")
    @Size(min = 9,max=11, message = "số điện thoại không đúng định dạng")
    private String phoneNumber;

    @NotNull(message = "không để trống")
    @Size(min = 2, message = "không đúng định dạng ")
    private String address;

    private MultipartFile logoFile;

    private double moneyAmount;

    @ValidEmail
    @NotNull(message = "không để trống")
    private String email;

    public CrmUser() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(MultipartFile logoFile) {
        this.logoFile = logoFile;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CrmUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", logoFile=" + logoFile +
                ", moneyAmount=" + moneyAmount +
                ", email='" + email + '\'' +
                '}';
    }
}