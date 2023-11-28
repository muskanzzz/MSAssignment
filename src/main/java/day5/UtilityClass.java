package Stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UtilityClass {
    public static List<Employee> generateEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee("John", "Doe", 1, "23/10/1998", new BigDecimal(2000), "HR"));
        employeeList.add(new Employee("Alice", "Smith", 2, "15/05/1995", new BigDecimal(2500), "IT"));
        employeeList.add(new Employee("Bob", "Johnson", 3, "08/12/2023", new BigDecimal(3000), "Finance"));
        employeeList.add(new Employee("Eva", "Williams", 4, "05/09/1985", new BigDecimal(3500), "Marketing"));
        employeeList.add(new Employee("Mike", "Brown", 5, "18/03/2000", new BigDecimal(2800), "Operations"));

        return employeeList;
    }

}
