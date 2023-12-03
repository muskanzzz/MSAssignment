package designpattern.assg;

public class FactoryClass {

    public static Operations getOperation(String operation){
        if(operation.trim().equalsIgnoreCase("All")){
            return new All();
        }
        else if(operation.trim().equalsIgnoreCase("Even")){
            return new Even();
        }
        else if(operation.trim().equalsIgnoreCase("Odd")){
            return new Odd();
        }
        else if(operation.trim().equalsIgnoreCase("Prime")){
            return new Prime();
        }
        else
            return null;
    }
}
