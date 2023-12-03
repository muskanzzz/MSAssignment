package designpattern.factory;

public class FactoryPattern {
    public static void main(String[] args) {
        Employee e = new AndroidDeveloper();//tightly coupled

        Employee android = EmployeeFactory.getEmployee("web");
        System.out.println(android.salary());
    }
}
