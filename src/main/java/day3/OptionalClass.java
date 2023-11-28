package Java;

import java.util.Optional;

public class OptionalClass {
    public static Optional<String> getName(){
        String name ="muskan";
        return Optional.ofNullable(name);
    }
    public static void main(String[] args) {

        String str = null;
        if(str==null){
            System.out.println("this is null object");
        }
        else{
            System.out.println(str.length());
        }

        Optional<String> optionalS=Optional.ofNullable(str);
        System.out.println(optionalS.isPresent());
        System.out.println(optionalS.orElse("No value in the string"));
  //      System.out.println(optionalS.get());

        Optional<String> name = getName();
        System.out.println(name.orElse("Value is null"));

        Optional<String> opt =Optional.ofNullable("Welcome Folks");
        System.out.println(opt.isPresent());
        opt.ifPresent( s->  System.out.println());

        Object objVal=Optional.ofNullable("Welcome Folks").orElseGet(()->  "some value");
        System.out.println(objVal);

        Optional.ofNullable("muskan@gmail.com")
                // .ifPresent(email -> System.out.println("Sending the email to : "+ email));
                .ifPresentOrElse(
                        email -> System.out.println("Sending the email to : "+ email),
                        () -> {
                            System.out.println("Cannot Send the email");
                        });
    }
}
