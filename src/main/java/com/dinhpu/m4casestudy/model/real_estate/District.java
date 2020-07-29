package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="district")
public class District {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @ManyToOne
    @JoinColumn(name="province_id")
    private Province province;

    public District() {
    }

    public District(String name, Province province) {
        this.name = name;
        this.province = province;
    }

    public District(String name, String type, Province province) {
        this.name = name;
        this.type = type;
        this.province = province;
    }

    public District(Long id, String name, Province province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }


}
