package week4;

public class FeeAccount {
    public void pay() {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void pay() {
        System.out.println("Paid in two installments (hostel account)");
    }
}

class PaymentProcessor {
    private int hostelCount = 0;
    private int dayScholarCount = 0;

    public void processPayment(FeeAccount account, double amount) {
        // Checking instance type using instanceof
        if (account instanceof HostelFeeAccount) {
            hostelCount++;
        } else if (account instanceof FeeAccount) {
            dayScholarCount++;
        }
        account.pay();
    }

    public void printBatchSummary() {
        System.out.println("Hostel accounts processed: " + hostelCount + 
                           " | Day-scholar accounts processed: " + dayScholarCount);
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        PaymentProcessor processor = new PaymentProcessor();
        double amount = 60000;

        for (FeeAccount account : accounts) {
            processor.processPayment(account, amount);
        }

        processor.printBatchSummary();
    }
} {
    
}
