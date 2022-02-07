package com.example.rest.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries")
@Data
public class Country implements Serializable {

    @Id
    @Column(name = "COUNTRY_ID")
    private String id;
    @Column(name = "COUNTRY_NAME", nullable = false)
    private String name;
    @Column(name = "REGION_ID", nullable = false)
    private Integer region;

    public String toString() {
        return "id:" + id + " name:" + name + " region:" + region;
    }

}
