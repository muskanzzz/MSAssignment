package solidprinciples;

public class Penguin implements Birds{
    private String name;

    public Penguin(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void fly() {
        System.out.println(name + " can't fly.");
    }
}
