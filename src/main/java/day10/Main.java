package solidprinciples;

public class Main {
    public static void main(String[] args) {
                Birds crow = new Crow("Caw Caw");
                crow.fly();

                Birds parrot = new Parrot("Chirpy");
                parrot.fly();

                Birds penguin = new Penguin("Happy Feet");
                penguin.fly(); // Penguins can't fly
            }
}
