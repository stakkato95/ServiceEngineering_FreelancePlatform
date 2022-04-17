package com.stakkato95.freelance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.stakkato95.freelance.DbConfig.DB;
import static com.stakkato95.freelance.DbConfig.TABLE_NAMESPACE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = DB, schema = TABLE_NAMESPACE, name = Project.PROJECT)
public class Project {

    static final String PROJECT = "project";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

//    @OneToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn
//    Client client;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_freelancer",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "freelancer_id")
    )
    List<Freelancer> freelancers;
}