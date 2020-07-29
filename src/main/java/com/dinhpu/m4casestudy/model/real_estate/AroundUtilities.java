package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="around_utilities")
public class AroundUtilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="market")
    private boolean market;

    @Column(name="school")
    private boolean school;

    @Column(name="hospital")
    private boolean hospital;

    @Column(name="police")
    private boolean police;

    @OneToOne(mappedBy = "aroundUtilities",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private RealEstate realEstate;

    public AroundUtilities() {
    }

    public AroundUtilities(boolean market, boolean school, boolean hospital, boolean police) {
        this.market = market;
        this.school = school;
        this.hospital = hospital;
        this.police = police;
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

    public boolean isMarket() {
        return market;
    }

    public void setMarket(boolean market) {
        this.market = market;
    }

    public boolean isSchool() {
        return school;
    }

    public void setSchool(boolean school) {
        this.school = school;
    }

    public boolean isHospital() {
        return hospital;
    }

    public void setHospital(boolean hospital) {
        this.hospital = hospital;
    }

    public boolean isPolice() {
        return police;
    }

    public void setPolice(boolean police) {
        this.police = police;
    }


}
