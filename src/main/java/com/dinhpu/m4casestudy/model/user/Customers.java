package com.dinhpu.m4casestudy.model.user;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class Customers {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name="buyer_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name="real_estate_id")
    private RealEstate realEstate;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;

    public Customers() {
        this.status=false;
    }

    public Customers(User buyer, RealEstate realEstate, User owner) {
        this.buyer = buyer;
        this.realEstate = realEstate;
        this.owner = owner;
    }

    public Customers(boolean status, User buyer, RealEstate realEstate, User owner) {
        this.status = status;
        this.buyer = buyer;
        this.realEstate = realEstate;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
