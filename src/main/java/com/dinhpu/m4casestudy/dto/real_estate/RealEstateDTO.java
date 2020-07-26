package com.dinhpu.m4casestudy.dto.real_estate;

import com.dinhpu.m4casestudy.model.real_estate.*;
import com.dinhpu.m4casestudy.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RealEstateDTO {

    private Long id;

    @NotEmpty
    private String category;

    @NotEmpty
    private String province;

    @NotEmpty
    private String district;

    @NotEmpty
    private String ward;

    @NotBlank
    private String address;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String priceUnit;

    @NotNull
    private String billions;
    @NotNull
    private String millions;

    @NotBlank
    private String totalArea;

    @NotEmpty
    private String areaType;

    @NotEmpty
    private String direction;

    @NotBlank
    private String bedRoomQuantity;

    @NotBlank
    private String bathRoomQuantity;

    @NotEmpty
    private String legalPaper;

    @NotEmpty
    private InternalUtilities internalUtilities;

    @NotEmpty
    private ExternalUtilities  externalUtilities;

    @NotEmpty
    private AroundUtilities aroundUtilities;

    @NotEmpty
    private RealEstateType realEstateType;


    private User user;

    @NotEmpty
    private RealEstateImage realEstateImage;

    public RealEstateDTO() {
    }

    public RealEstateDTO(@NotEmpty String category, @NotEmpty String province, @NotEmpty String district, @NotEmpty String ward, @NotBlank String address, @NotBlank String title, @NotBlank String description, @NotBlank String priceUnit, @NotNull String billions, @NotNull String millions, @NotBlank String totalArea, @NotEmpty String areaType, @NotEmpty String direction, @NotBlank String bedRoomQuantity, @NotBlank String bathRoomQuantity, @NotEmpty String legalPaper, @NotEmpty InternalUtilities internalUtilities, @NotEmpty ExternalUtilities externalUtilities, @NotEmpty AroundUtilities aroundUtilities, @NotEmpty RealEstateType realEstateType, User user, @NotEmpty RealEstateImage realEstateImage) {
        this.category = category;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.title = title;
        this.description = description;
        this.priceUnit = priceUnit;
        this.billions = billions;
        this.millions = millions;
        this.totalArea = totalArea;
        this.areaType = areaType;
        this.direction = direction;
        this.bedRoomQuantity = bedRoomQuantity;
        this.bathRoomQuantity = bathRoomQuantity;
        this.legalPaper = legalPaper;
        this.internalUtilities = internalUtilities;
        this.externalUtilities = externalUtilities;
        this.aroundUtilities = aroundUtilities;
        this.realEstateType = realEstateType;
        this.user = user;
        this.realEstateImage = realEstateImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getBillions() {
        return billions;
    }

    public void setBillions(String billions) {
        this.billions = billions;
    }

    public String getMillions() {
        return millions;
    }

    public void setMillions(String millions) {
        this.millions = millions;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBedRoomQuantity() {
        return bedRoomQuantity;
    }

    public void setBedRoomQuantity(String bedRoomQuantity) {
        this.bedRoomQuantity = bedRoomQuantity;
    }

    public String getBathRoomQuantity() {
        return bathRoomQuantity;
    }

    public void setBathRoomQuantity(String bathRoomQuantity) {
        this.bathRoomQuantity = bathRoomQuantity;
    }

    public String getLegalPaper() {
        return legalPaper;
    }

    public void setLegalPaper(String legalPaper) {
        this.legalPaper = legalPaper;
    }

    public InternalUtilities getInternalUtilities() {
        return internalUtilities;
    }

    public void setInternalUtilities(InternalUtilities internalUtilities) {
        this.internalUtilities = internalUtilities;
    }

    public ExternalUtilities getExternalUtilities() {
        return externalUtilities;
    }

    public void setExternalUtilities(ExternalUtilities externalUtilities) {
        this.externalUtilities = externalUtilities;
    }

    public AroundUtilities getAroundUtilities() {
        return aroundUtilities;
    }

    public void setAroundUtilities(AroundUtilities aroundUtilities) {
        this.aroundUtilities = aroundUtilities;
    }

    public RealEstateType getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(RealEstateType realEstateType) {
        this.realEstateType = realEstateType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RealEstateImage getRealEstateImage() {
        return realEstateImage;
    }

    public void setRealEstateImage(RealEstateImage realEstateImage) {
        this.realEstateImage = realEstateImage;
    }
}
