package Stream;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Employee> employeeList = UtilityClass.generateEmployeeList();
       // 1. Write a program to print First Name of employees which joined in year 2023.
         employeeList.stream().filter(employee -> employee.getDateOfBirth().getYear() == 2023).map(Employee::getFirstName)
                .forEach(System.out::println);

        String targetDepartment = "HR";
//        2. Write a program to print count, min, max, sum, and the average of salary of all employees in a perticular department.
        List<Employee> departmentEmployees = employeeList.stream().filter(employee -> employee.getDept().equals(targetDepartment)).toList();
        long count = departmentEmployees.size();
        Optional<BigDecimal> minSalary = departmentEmployees.stream()
                .map(Employee::getSalary)
                .min(BigDecimal::compareTo);

        Optional<BigDecimal> maxSalary = departmentEmployees.stream()
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder());

        Optional<BigDecimal> sumSalary = departmentEmployees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal::add);

        double averageSalary = departmentEmployees.stream()
                .mapToDouble(employee -> employee.getSalary().doubleValue())
                .average()
                .orElse(0.0);

        System.out.println("Count: " + count);
        System.out.println("Min Salary: " + minSalary.orElse(BigDecimal.ZERO));
        System.out.println("Max Salary: " + maxSalary.orElse(BigDecimal.ZERO));
        System.out.println("Sum Salary: " + sumSalary);
        System.out.println("Average Salary: " + averageSalary);

//        3. Write a program to print sorted list of employees by firstName in all departments except HR department.
             employeeList.stream()
                .filter(employee -> !employee.getDept().equals("HR"))
                .sorted(Comparator.comparing(Employee::getFirstName))
                .forEach(System.out::println);


//        4. Write a program to increment salary of employees in perticular department by 10%.


        String targetDept = "HR";
        BigDecimal incrementPercentage = new BigDecimal("0.10");

        employeeList.stream()
                .filter(employee -> employee.getDept().equals(targetDept))
                .forEach(employee -> {
                    BigDecimal currentSalary = employee.getSalary();
                    BigDecimal salaryIncrement = currentSalary.multiply(incrementPercentage);
                    BigDecimal newSalary = currentSalary.add(salaryIncrement);
                    employee.setSalary(newSalary);
                });

//        5. Write a program using stream to print 50 odd numbers after 100.
        int start = 101;
        int limit = 50;

        IntStream.iterate(start, n -> n + 2)
                .limit(limit)
                .forEach(System.out::println);
//        6. Write a program to create comma seperated list of First names of employees order by dateOfbirth.
        String commaSeparatedFirstNames = employeeList.stream()
                .sorted((e1, e2) -> e1.getDateOfBirth().compareTo(e2.getDateOfBirth()))
                .map(Employee::getFirstName)
                .collect(Collectors.joining(", "));
        System.out.println(commaSeparatedFirstNames);

    }
}
