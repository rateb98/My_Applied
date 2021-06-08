package com.example.myapplied.Model;

public class Users {


    private String id;
    private String firstName;
    private String lastName;
    private String section;
    private String academic_year;
    private String account;
    private String ready;


    public Users()
    {

    }

    public Users(String id,String firstName, String lastName, String section, String academic_year, String account, String ready) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.section = section;
        this.academic_year = academic_year;
        this.account = account;
        this.ready = ready;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAcademic_year() {
        return academic_year;
    }

    public void setAcademic_year(String academic_year) {
        this.academic_year = academic_year;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getReady() {
        return ready;
    }

    public void setReady(String ready) {
        this.ready = ready;
    }
}

