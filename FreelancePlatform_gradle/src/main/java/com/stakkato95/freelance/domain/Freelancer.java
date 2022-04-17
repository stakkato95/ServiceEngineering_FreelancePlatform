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
@Table(catalog = DB, schema = TABLE_NAMESPACE, name = Freelancer.FREELANCER)
public class Freelancer {

    static final String FREELANCER = "freelancer";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    //TODO validation for fields
    String secondName;

    String nickName;

    String email;

//    @Lob
//    List<String> technologies;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "freelancers", fetch = FetchType.EAGER)
    List<Project> projects;
}
