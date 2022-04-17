package com.stakkato95.freelance.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.stakkato95.freelance.domain.DbConfig.DB;
import static com.stakkato95.freelance.domain.DbConfig.TABLE_NAMESPACE;

@Data
@NoArgsConstructor
@Entity
@Table(catalog = DB, schema = TABLE_NAMESPACE, name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
}
