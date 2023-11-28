package solidprinciples;

public class Parrot implements Birds{
    private String name;

    public Parrot(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying.");
    }
}
