package Classes;

public class Employee extends Person {
    private String Grade;
    private Double Salary;

    public Employee(String iD, String firstName, String lastName, int phoneNbr,String grade, Double salary ) {
        super(iD,firstName,lastName,phoneNbr);
        Grade=grade;
        Salary=salary;

    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }


    // public void afficheemp() {
    //     System.out.println("Employee [Grade=" + Grade + ", Salary=" + Salary + "]");
    //     super.affiche();
    // }

}