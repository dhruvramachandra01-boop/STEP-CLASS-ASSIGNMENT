public abstract class Toy {
    private static int counter = 1000;
    private final String toyId;
    protected String name;

    public Toy(String name) {
        this.name = name;
        this.toyId = "TOY-" + (++counter);
    }

    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }
}

public class ToyCar extends Toy {
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}

public class ToyRobot extends Toy {
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}
