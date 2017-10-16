package com.sda.jpa.entity;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String street;

    //@OneToOne(mappedBy = "address")
    //private Person person;

    @ManyToMany(mappedBy = "addresses")
    private Collection<Person> persons;
}
