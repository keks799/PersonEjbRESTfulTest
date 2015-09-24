package models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Business_Book on 11.09.2015.
 */


public class Person implements Serializable {
    public Person() {
    }

    public Person(String firstname, String lastname, String middlename, Date birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.birthDate = birthDate;
    }

    private Long id;

    private String firstname;

    private String lastname;

    private String middlename;

    private Date birthDate;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
