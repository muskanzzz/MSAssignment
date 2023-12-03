package designpattern.factory;

public class AndroidDeveloper implements Employee{
    @Override
    public int salary() {
        System.out.println("Android Dev Salary");
        return 50000;
    }
}
