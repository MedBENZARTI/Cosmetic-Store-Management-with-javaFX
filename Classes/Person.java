package Classes;

public class Person {
    private String ID;
    private String FirstName;
    private String LastName;
    private int PhoneNbr;

    public Person(String iD, String firstName, String lastName, int phoneNbr) {
        ID = iD;
        FirstName = firstName;
        LastName = lastName;
        PhoneNbr = phoneNbr;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getPhoneNbr() {
        return PhoneNbr;
    }

    public void setPhoneNbr(int phoneNbr) {
        PhoneNbr = phoneNbr;
    }


//     public void affiche() {
//    System.out.println("Person [FirstName=" + FirstName + ", ID=" + ID + ", LastName=" + LastName + ", PhoneNbr=" + PhoneNbr
//    + "]");
    // }
}