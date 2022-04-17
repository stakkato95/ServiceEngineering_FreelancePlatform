package com.stakkato95.freelance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.stakkato95.freelance.DbConfig.DB;
import static com.stakkato95.freelance.DbConfig.TABLE_NAMESPACE;
import static com.stakkato95.freelance.domain.Client.CLIENT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = DB, schema = TABLE_NAMESPACE, name = CLIENT)
public class Client {

    static final String CLIENT = "client";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;

    String secondName;

    String email;

    public Client(String firstName, String secondName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    //TODO use getter from lombok
    //https://github.com/micronaut-projects/micronaut-data/issues/159
    //https://howtodoinjava.com/gradle/convert-maven-project-to-gradle-project/?msclkid=65875d3fbe4b11ecb0bffdea6ed48992
    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
