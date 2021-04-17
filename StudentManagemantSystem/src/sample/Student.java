package sample;

import java.sql.Date;

public class Student {
    int id, phonenumber;
    String lastname, firstname, sex, email, address;
    Date dob;

    public Student(int id, String lastname, String firstname, Date dob, String sex,
                   String email, String address, int phonenumber) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.dob = dob;
        this.sex = sex;
        this.email = email;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
