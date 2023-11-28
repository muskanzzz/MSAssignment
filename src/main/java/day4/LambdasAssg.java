package Java;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;


public class LambdasAssg {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        List<Employee> list = createEmployeeList();
        List<User> user = list.stream().map(employeeToUserConverter).toList();
       // Sort the list of employees by month in dateOfBirth
        List<Employee> employeeList = list.stream()
                .sorted(Comparator.comparing(Employee::getDateOfBirth))
                .toList();
        employeeList.forEach(System.out::println);

        List<Employee> compareWithMonth = list.stream().filter(employee -> employee.getDateOfBirth()
                        .getMonth()
                        .equals(Month.DECEMBER))
                         .toList();
        compareWithMonth.forEach(System.out::println);



       // Create 2 threads using lambda. One thread will print list of employees another will print list of users.
        new Thread(()->list.forEach(System.out::println)).start();

        new Thread(()->user.forEach(System.out::println)).start();

    }

    public static Function<Employee, User> employeeToUserConverter = employee -> {
        User user = new User();
        user.setUsername(employee.getFirstName() + employee.getLastName() +   employee.getDateOfBirth().getYear()  + employee.getId());
        user.setPassword(generateRandomPassword());
        user.setId(employee.getId());
        return user;
    };
    private static List<Employee> createEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("John", "Doe", 1,"23/12/1998" ,new BigDecimal(2000), "HR"));
        employeeList.add(new Employee("Jane", "Smith", 2, "23/08/1998",new BigDecimal(2500), "IT"));
        employeeList.add(new Employee("Bob", "Johnson", 3, "23/06/1998",new BigDecimal(3000), "Finance"));
        return employeeList;
    }

    private static String generateRandomPassword() {
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}
