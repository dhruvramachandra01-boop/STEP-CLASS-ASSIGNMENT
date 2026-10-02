
import java.util.*;

interface PaymentMethod {

    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        return true; // Simulating success
    }
}

class PayPalPayment implements PaymentMethod {

    private boolean shouldSucceed;

    public PayPalPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed; // Simulating outcome
    }
}

class Product {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

enum OrderStatus {
    PENDING, PAID
}

class Order {

    private String orderId;
    private Customer customer;
    private Map<Product, Integer> items = new HashMap<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        System.out.println("Order created for " + customer.getName() + ".");
    }

    public void addProduct(Product product, int qty) {
        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void processPayment(PaymentMethod paymentMethod, String methodName) {
        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via " + methodName + " for Order " + customer.getName().substring(customer.getName().length() - 1) + ".");
        boolean success = paymentMethod.processPayment(calculateTotal());

        if (success) {
            this.status = OrderStatus.PAID;
            System.out.println("Payment for Order " + customer.getName().substring(customer.getName().length() - 1) + " successful. Order status: Paid.");
        } else {
            System.out.println("Payment for Order " + customer.getName().substring(customer.getName().length() - 1) + " failed. Order status: " + status + ".");
        }
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Main5 {

    public static void main(String[] args) {
        Product pA = new Product("Product A", 10.0);
        Product pB = new Product("Product B", 20.0);
        Product pC = new Product("Product C", 15.0);

        Customer cX = new Customer("Customer X");
        Order orderX = new Order("O1", cX);
        orderX.addProduct(pA, 2);
        orderX.addProduct(pB, 1);
        orderX.processPayment(new CreditCardPayment(), "Credit Card");

        Customer cY = new Customer("Customer Y");
        Order orderY = new Order("O2", cY);
        orderY.processPayment(new CreditCardPayment(), "Credit Card");

        Customer cZ = new Customer("Customer Z");
        Order orderZ = new Order("O3", cZ);
        orderZ.addProduct(pC, 1);
        orderZ.processPayment(new PayPalPayment(false), "PayPal");
    }
}
