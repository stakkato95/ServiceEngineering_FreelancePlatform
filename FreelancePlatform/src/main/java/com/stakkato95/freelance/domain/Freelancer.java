package com.stakkato95.freelance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static com.stakkato95.freelance.DbConfig.DB;
import static com.stakkato95.freelance.DbConfig.TABLE_NAMESPACE;
import static com.stakkato95.freelance.domain.Freelancer.FREELANCER;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(catalog = DB, schema = TABLE_NAMESPACE, name = FREELANCER)
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

    public Long getId() {
        return id;
    }

    public Freelancer(String firstName, String secondName, String nickName, String email/*, List<String> technologies*/) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.nickName = nickName;
        this.email = email;
//        this.technologies = technologies;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
