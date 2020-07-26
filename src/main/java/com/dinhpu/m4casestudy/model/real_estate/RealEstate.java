package com.dinhpu.m4casestudy.model.real_estate;

import com.dinhpu.m4casestudy.model.user.User;

import javax.persistence.*;

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

    @Column(name="billions")
    private String billions;

    @Column(name="millions")
    private String millions;

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

    @ManyToOne
    @JoinColumn(name="real_estate_type_id")
    private RealEstateType realEstateType;

    @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="image_id")
    private RealEstateImage realEstateImage;

    public RealEstate() {
    }

    public RealEstate(String category, String province, String district, String ward, String address, String title, String description, String priceUnit, String billions, String millions, String totalArea, String areaType, String direction, String bedRoomQuantity, String bathRoomQuantity, String legalPaper, InternalUtilities internalUtilities, ExternalUtilities externalUtilities, AroundUtilities aroundUtilities, RealEstateType realEstateType, User user, RealEstateImage realEstateImage) {
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

    @Override
    public String toString() {
        return "RealEstate{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                ", address='" + address + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priceUnit='" + priceUnit + '\'' +
                ", billions='" + billions + '\'' +
                ", millions='" + millions + '\'' +
                ", totalArea='" + totalArea + '\'' +
                ", areaType='" + areaType + '\'' +
                ", direction='" + direction + '\'' +
                ", bedRoomQuantity='" + bedRoomQuantity + '\'' +
                ", bathRoomQuantity='" + bathRoomQuantity + '\'' +
                ", legalPaper='" + legalPaper + '\'' +
                ", internalUtilities=" + internalUtilities +
                ", externalUtilities=" + externalUtilities +
                ", aroundUtilities=" + aroundUtilities +
                ", realEstateType=" + realEstateType +
                ", user=" + user +
                ", realEstateImage=" + realEstateImage +
                '}';
    }
}
