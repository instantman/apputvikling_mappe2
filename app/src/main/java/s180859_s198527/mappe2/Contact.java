/** 
 * This is a class used to create contacts (Contact-objects).
 * Has constructor that accepts parameters, as well as an empty constructor.
 * Includes GET- and SET-methods for all Contact-parameters.
*/

package s180859_s198527.mappe2;

public class Contact {

    private String firstname,lastname, birthdate, phoneNr;
    private Long dbId;

    /* Empty constructor */
    public Contact(){ }
    
    /* Created a new contact based on input-paramteres */
    public Contact(String inFirstname, String inLastname, String inPhone, String inBirthDate){
        this.firstname = inFirstname;
        this.lastname = inLastname;
        this.phoneNr = inPhone;
        this.birthdate = inBirthDate;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getPhoneNr(){
        return this.phoneNr;
    }

    public String getBirthdate(){
        return this.birthdate;
    }

    public void setFirstname(String surname) {
        this.firstname = surname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setDbId(Long id){
        this.dbId = id;
    }

    public Long getDbId(){
        return this.dbId;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
