package Java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    public static void main(String[] args) {
        // Create a list to store employees
        List<Employee> employeeList = new ArrayList<>();

        // Create 5 employees and add them to the list with different salaries
        for (int i = 1; i <= 5; i++) {
            BigDecimal salary = new BigDecimal("20").add(new BigDecimal(i * 1000)); // Example salary calculation
            Employee employee = createEmployee(i, salary);
            employeeList.add(employee);
        }

        // Print the details of each employee
        for (Employee employee : employeeList) {
            System.out.println("Employee ID: " + employee.getId());
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("Date of Birth: " + employee.getDateOfBirth());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Department: " + employee.getDept());
            System.out.println("-------------");
        }

        List<Integer> highSalaryEmployeeIds = employeeList.stream()
                .filter(employee -> employee.getSalary().compareTo(new BigDecimal("2000")) > 0)
                .map(Employee::getId)
                .toList();

//        List<Integer> highSalaryEmployeeIds = employeeList.stream()
//                .filter(employee -> (employee.getSalary()>2000))
//                .map(Employee::getId)
//                .toList();

        // Print the IDs of filtered employees
        System.out.println("Employee IDs with Salary > 2000: " + highSalaryEmployeeIds);

        // Filter employees with a id greater than 4
        List<Integer> highSalaryEmployeeIdsInt = employeeList.stream()
                .map(Employee::getId)
                .filter(id -> id > 4)
                .toList();

        // Sort employees by department alphabetically
        employeeList.sort(Comparator.comparing(Employee::getDept));
        System.out.println("Employees sorted by department:");
        employeeList.forEach(System.out::println);


    }

    private static Employee createEmployee(int id, BigDecimal salary) {
     //   Date dateOfBirth = new Date(98, 9, 23); // Example date of birth
        String firstName = "Employee" + id;
        String lastName = "Lastname" + id;
        String dept = "Department" + (char) ('A' + id - 1); // Assign departments alphabetically


        return new Employee(firstName, lastName, id, "23/10/1998", new BigDecimal(String.valueOf(salary)), dept);
    }
}
