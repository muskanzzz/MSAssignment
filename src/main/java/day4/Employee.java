package Java;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private LocalDate dateOfBirth;
    private BigDecimal salary;
    private String dept;

    public Employee(String firstName, String lastName, int id, String dateOfBirth, BigDecimal salary, String dept) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;

        // Parse the dateOfBirth string to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);

        this.salary = salary;
        this.dept = dept;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getDept() {
        return dept;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", salary=" + salary +
                ", dept='" + dept + '\'' +
                '}';
    }

}
