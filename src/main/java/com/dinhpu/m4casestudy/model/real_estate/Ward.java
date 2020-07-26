package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="ward")
public class Ward {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @ManyToOne
    @JoinColumn(name="district_id")
    private District district;

    public Ward() {
    }

    public Ward(String name, String type, District district) {
        this.name = name;
        this.type = type;
        this.district = district;
    }

    public Ward(Long id, String name, String type, District district) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.district = district;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", district=" + district +
                '}';
    }
}
