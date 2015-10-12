package s180859_s198527.mappe2;

import java.util.Date;

/**
 * Created by Christopher on 12/10/2015.
 */
public class Contact {
    private String surname, lastname;
    private int phoneNr;
    private Date birthdate;


    public Contact(String inSurname, String inLastname, int inPhone, Date inBirthDate){
        this.surname = inSurname;
        this.lastname = inLastname;
        this.phoneNr = inPhone;
        this.birthdate = inBirthDate;
    }

    public String getSurname(){
        return this.surname;
    }
    public String getLastname(){
        return this.lastname;
    }

    public int getPhoneNr(){
        return this.phoneNr;
    }

    public Date getBirthdate(){
        return this.birthdate;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}