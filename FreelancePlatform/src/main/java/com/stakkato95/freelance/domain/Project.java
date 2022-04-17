package com.stakkato95.freelance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.stakkato95.freelance.DbConfig.DB;
import static com.stakkato95.freelance.DbConfig.TABLE_NAMESPACE;
import static com.stakkato95.freelance.domain.Project.PROJECT;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(catalog = DB, schema = TABLE_NAMESPACE, name = PROJECT)
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

    public Project(String name, List<Freelancer> freelancers) {
        this.name = name;
        this.freelancers = freelancers;
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

    public List<Freelancer> getFreelancers() {
        return freelancers;
    }

    public void setFreelancers(List<Freelancer> freelancers) {
        this.freelancers = freelancers;
    }
}
