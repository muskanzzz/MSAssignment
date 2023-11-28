package Java;

import java.math.BigDecimal;
import java.util.function.Supplier;

@FunctionalInterface
interface UserNameGenerator{
    String generate(String firstName, String lastName, int yearOfBirth, int id);

}

public class Main {
 //   Write own functional interface UserNameGenerator which has generate method which accepts 4 parameters firstName,LastName,YearOfBirth,id. Write logic to implement generate() method using lamda and use it to generate User in question 2 above.
    public static void main(String[] args) {
        // Implementation of UserNameGenerator using lambda expression
        UserNameGenerator userNameGenerator = (firstName, lastName, yearOfBirth, id) ->
                firstName.substring(0, 1) + lastName.substring(0, 1) + yearOfBirth % 100 + id;

        Supplier<String> passwordSupplier = () -> {
            return "RandomPassword1234";
        };
        Employee employee = new Employee("John", "Doe", 1, "23/10/1998", new BigDecimal(2000), "HR");
        User user = convertToUser(employee, userNameGenerator, passwordSupplier);

        System.out.println("Generated User: " + user.getUsername());
    }

    private static User convertToUser(Employee employee, UserNameGenerator userNameGenerator, Supplier<String> passwordSupplier) {
        String username = userNameGenerator.generate(employee.getFirstName(), employee.getLastName(),
                employee.getDateOfBirth().getYear(), employee.getId());
        String password = passwordSupplier.get();
        return new User(username, employee.getId(),password);
    }
}

