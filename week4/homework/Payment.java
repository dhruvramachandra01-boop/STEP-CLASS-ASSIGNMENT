package homework;

class Payment {
    public double pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
        return amount;
    }
}

class CardPayment extends Payment {
    public double payWithProcessingFee(double amount) {
        double total = amount + (amount * 0.02);
        System.out.println("Charged (card, incl. fee): Rs " + total);
        return total;
    }
}

class Main {
    public static double processTransaction(Payment payment, double amount) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        if (payment instanceof CardPayment cardPayment) {
            return cardPayment.payWithProcessingFee(amount);
        } else {
            return payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};
        double totalCollected = 0.0;

        for (int i = 0; i < payments.length; i++) {
            totalCollected += processTransaction(payments[i], amounts[i]);
        }

        System.out.println("Total Collected: Rs " + totalCollected);
    }
}
