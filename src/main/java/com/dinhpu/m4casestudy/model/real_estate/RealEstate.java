package com.dinhpu.m4casestudy.model.real_estate;

import com.dinhpu.m4casestudy.model.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="real_estate")
public class RealEstate {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="category")
    private String category;


    @Column(name="province")
    private String province;


    @Column(name="district")
    private String district;


    @Column(name="ward")
    private String ward;

    @Column(name="address")
    private String address;

    @Column(name="title",columnDefinition="TEXT")
    private String title;

    @Column(name="description",columnDefinition = "TEXT")
    private String description;

    @Column(name="price_unit")
    private String priceUnit;

    @Column(name="total_price")
    private String totalprice;

//    @Column(name="billions")
//    private String billions;
//
//    @Column(name="millions")
//    private String millions;

    @Column(name="total_area")
    private String totalArea;


    @Column(name="area_type")
    private String areaType;

    @Column(name="direction")
    private String direction;

    @Column(name="bedroom_quantity")
    private String bedRoomQuantity;

    @Column(name="bathroom_quantity")
    private String bathRoomQuantity;

    @Column(name="legal_paper")
    private String legalPaper;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="internal_utilities_id")
    private InternalUtilities internalUtilities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="external_utilities_id")
    private ExternalUtilities  externalUtilities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="around_utilities_id")
    private AroundUtilities aroundUtilities;

    @Column(name="real_estate_type")
    private String realEstateType;

    @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

//    @OneToMany(fetch=FetchType.LAZY,mappedBy = "realEstate",cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "realEstate",cascade =CascadeType.ALL)
    private List<RealEstateImage> realEstateImages;

    @Column(name="advertise")
    private boolean advertise;

    public void addRealEstateImage(RealEstateImage realEstateImage){
        if (realEstateImages==null){
            realEstateImages=new ArrayList<>();
        }
        realEstateImages.add(realEstateImage);
    }

    public RealEstate() {
    }

    public RealEstate(String category, String province, String district, String ward, String address, String title, String description, String priceUnit, String totalprice, String totalArea, String areaType, String direction, String bedRoomQuantity, String bathRoomQuantity, String legalPaper, InternalUtilities internalUtilities, ExternalUtilities externalUtilities, AroundUtilities aroundUtilities, String realEstateType, User user) {
        this.category = category;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.title = title;
        this.description = description;
        this.priceUnit = priceUnit;
        this.totalprice = totalprice;
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

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
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

    public String getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(String realEstateType) {
        this.realEstateType = realEstateType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RealEstateImage> getRealEstateImages() {
        return realEstateImages;
    }

    public void setRealEstateImages(List<RealEstateImage> realEstateImages) {
        this.realEstateImages = realEstateImages;
    }

    public boolean isAdvertise() {
        return advertise;
    }

    public void setAdvertise(boolean advertise) {
        this.advertise = advertise;
    }
}
