package solidprinciples;

public class Crow implements Birds{
    private String name;

    public Crow(String name) {
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
