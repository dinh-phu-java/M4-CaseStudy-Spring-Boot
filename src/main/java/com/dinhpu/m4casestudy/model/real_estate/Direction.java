package com.dinhpu.m4casestudy.model.real_estate;

import javax.persistence.*;

@Entity
@Table(name="direction")
public class Direction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="direction_name")
    private String directionName;

    public Direction() {
    }

    public Direction(String directionName) {
        this.directionName = directionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }


}
