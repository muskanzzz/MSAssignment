package Java;

import java.util.List;
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<List<String>> namesOptional = getNames();

        // Check if the value is present before accessing it
        if (namesOptional.isPresent()) {
            List<String> names = namesOptional.get();
            System.out.println("Names: " + names);
        } else {
            System.out.println("Names are not present");
        }
    }

    public static Optional<List<String>> getNames() {
        List<String> names = List.of("musk an", "kajal","");

        // Wrap the list in an Optional
        return Optional.of(names);
    }
}
