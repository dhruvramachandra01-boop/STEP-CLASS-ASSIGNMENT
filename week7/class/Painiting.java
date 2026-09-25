// Level 1: Abstract Base Class
public abstract class Instrument {
    public abstract String play();
}

// Level 2: Middle Class
public class StringInstrument extends Instrument {
    @Override
    public String play() {
        return "Strumming the strings";
    }
}

// Level 3: Leaf Subclass
public class Violin extends StringInstrument {
    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}