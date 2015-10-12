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

    public String baugern;

}
