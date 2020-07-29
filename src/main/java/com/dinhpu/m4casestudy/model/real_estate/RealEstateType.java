package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="real_estate_type")
public class RealEstateType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="type")
    private String type;

    public RealEstateType() {
    }

    public RealEstateType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
