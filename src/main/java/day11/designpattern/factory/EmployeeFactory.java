package designpattern.factory;

public class EmployeeFactory {
    public static Employee getEmployee(String empType){
        if(empType.trim().equalsIgnoreCase("ANDROID")){
            return new AndroidDeveloper();
        }
       else if(empType.trim().equalsIgnoreCase("WEB")){
            return new WebDeveloper();
        }
        else
            return null;
        }
}
