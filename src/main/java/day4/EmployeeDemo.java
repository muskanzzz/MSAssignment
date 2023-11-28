package Java;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EmployeeDemo {
    public static void main(String[] args) {
//Question 1: Create list of employees and print list of employess whose salary is above 2000.

      //Write java 8 consumer which will print employee datails. Use this consumer to print results of methods whenever needed.
       List<Employees> employeeList = createEmployeeList();

        Consumer<Employees> printEmployeeDetails = employees -> {
            System.out.println("Employee : " + employees.getFirstName() + " " + employees.getLastName()
            + " " + employees.getId() +" " + employees.getSalary() + " " + employees.getDept());
        };

        //Write java 8 predicate which can tell if employee salary is above 2000
        Predicate<Employees> employeesPredicate = employees -> employees.getSalary()>2000;
        System.out.println("Employees with salary above 2000:");
        employeeList.stream().filter(employeesPredicate).forEach(printEmployeeDetails);

        //Another option is Write BiPredicate where this 2000 is sent as another argument.
        BiPredicate<Employees , Integer > biPredicate = ((employees, integer) -> employees.getSalary()>=integer);
        System.out.println("\nEmployees with salary above 3000:");
        employeeList.stream().filter(employees -> biPredicate.test(employees,3000)).forEach(printEmployeeDetails);
    }
    private static List<Employees> createEmployeeList() {
        List<Employees> employeeList = new ArrayList<>();
        employeeList.add(new Employees("John", "Doe", 1, 2000, "HR"));
        employeeList.add(new Employees("Jane", "Smith", 2, 2500, "IT"));
        employeeList.add(new Employees("Bob", "Johnson", 3, 3000, "Finance"));
        return employeeList;
    }
}

class Employees {
    private String firstName;
    private String lastName;
    private int id;
    private int salary;
    private String dept;

    public Employees(String firstName, String lastName, int id, int salary, String dept) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.salary = salary;
        this.dept = dept;
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

    public int getSalary() {
        return salary;
    }

    public String getDept() {
        return dept;
    }
}
