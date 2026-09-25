// Abstract Class with Overloading
public abstract class DeliveryNote {
    public abstract String confirmDelivery();

    // Overloaded Method
    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

// Concrete Subclass 1
public class ParcelNote extends DeliveryNote {
    private String trackingId;

    public ParcelNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}

// Concrete Subclass 2
public class LetterNote extends DeliveryNote {
    private String trackingId;

    public LetterNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}

// Utility Logger
public class DeliveryLogger {
    public static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }
}