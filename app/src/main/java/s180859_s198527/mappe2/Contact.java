package s180859_s198527.mappe2;

public class Contact {
    private String surname,lastname, birthdate   ;
    private int phoneNr;

    // Tom konstruktør
    public Contact(){ }

    public Contact(String inSurname, String inLastname, int inPhone, String inBirthDate){
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

    public String getBirthdate(){
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

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}