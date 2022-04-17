package com.stakkato95.freelance.domain;

import javax.persistence.*;

@Entity
@Table(catalog = "freelance", schema = "public", name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String firstName;
}
