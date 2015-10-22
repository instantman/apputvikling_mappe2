package s180859_s198527.mappe2;
// Edit Test
public class Contact {

    private String surname,lastname, birthdate, phoneNr;
    private Long dbId;

    // Tom konstruktør
    public Contact(){ }

    public Contact(String inSurname, String inLastname, String inPhone, String inBirthDate){
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

    public String getPhoneNr(){
        return this.phoneNr;
    }

    public String getBirthdate(){
        return this.birthdate;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
