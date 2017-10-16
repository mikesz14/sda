package com.sda.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WorkPlace {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
