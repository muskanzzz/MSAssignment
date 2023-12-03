package designpattern.factory;

public class WebDeveloper implements Employee{
    @Override
    public int salary() {
        System.out.println("Web Dev Salary");
        return 40000;
    }
}
