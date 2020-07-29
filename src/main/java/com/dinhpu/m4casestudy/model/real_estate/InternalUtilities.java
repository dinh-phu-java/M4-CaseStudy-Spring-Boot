package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="internal_utilities")
public class InternalUtilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="internet")
    private boolean internet;

    @Column(name="television")
    private boolean television;

    @Column(name="toilet")
    private boolean toilet;

    @Column(name="air_condition")
    private boolean airCondition;

    @Column(name="kitchen")
    private boolean kitchen;

    @OneToOne(mappedBy = "internalUtilities",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private RealEstate realEstate;

    public InternalUtilities() {
    }

    public InternalUtilities(boolean internet, boolean television, boolean toilet, boolean airCondition, boolean kitchen) {
        this.internet = internet;
        this.television = television;
        this.toilet = toilet;
        this.airCondition = airCondition;
        this.kitchen = kitchen;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isInternet() {
        return internet;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public void setAirCondition(boolean airCondition) {
        this.airCondition = airCondition;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }


}
