package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="external_utilities")
public class ExternalUtilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="services")
    private boolean services;

    @Column(name="garden")
    private boolean garden;

    @Column(name="balcony")
    private boolean balcony;

    @Column(name="swimming_pool")
    private boolean swimmingPool;

    @Column(name="security")
    private boolean security;

    @OneToOne(mappedBy = "externalUtilities",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private RealEstate realEstate;

    public ExternalUtilities() {
    }

    public ExternalUtilities(boolean services, boolean garden, boolean balcony, boolean swimmingPool, boolean security) {
        this.services = services;
        this.garden = garden;
        this.balcony = balcony;
        this.swimmingPool = swimmingPool;
        this.security = security;
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

    public boolean isServices() {
        return services;
    }

    public void setServices(boolean services) {
        this.services = services;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isSecurity() {
        return security;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }


}
