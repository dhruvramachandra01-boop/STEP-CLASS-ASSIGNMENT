// Interface
interface Printable {
    String printLabel();
}

// PackageBox Class
public class PackageBox implements Printable {
    private final String trackingId;

    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

// Invoice Class
class Invoice implements Printable {
    private final String invoiceNumber;

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}

// Utility Class
class PrinterUtility {
    public static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }
}