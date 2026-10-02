
enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

// Base Employee Abstract Class
abstract class Employee {

    private String id;
    private String name;

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean validateLeaveRequest(int days);
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean validateLeaveRequest(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean validateLeaveRequest(int days) {
        return days <= 10;
    }
}

// Leave Request Class with Enforced State Transition Guard
class LeaveRequest {

    private String id;
    private Employee employee;
    private String dates;
    private int days;
    private LeaveStatus status;

    public LeaveRequest(String id, Employee employee, String dates, int days) {
        this.id = id;
        this.employee = employee;
        this.dates = dates;
        this.days = days;
        this.status = LeaveStatus.PENDING;
        System.out.println("Leave request submitted for " + employee.getName() + " (" + dates + "). Status: " + status + ".");
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDates() {
        return dates;
    }

    public void approve() {
        if (this.status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to Approved.");
            return;
        }
        this.status = LeaveStatus.APPROVED;
        System.out.println(employee.getName() + "'s leave request (" + dates + ") approved. Status: " + status + ".");
    }

    public void reject() {
        if (this.status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to Rejected.");
            return;
        }
        this.status = LeaveStatus.REJECTED;
        System.out.println(employee.getName() + "'s leave request (" + dates + ") rejected. Status: " + status + ".");
    }

    public void setStatus(LeaveStatus newStatus) {
        if (this.status == LeaveStatus.APPROVED || this.status == LeaveStatus.REJECTED) {
            System.out.println("Cannot change leave request status from " + this.status + " to " + newStatus + ".");
            return;
        }
        this.status = newStatus;
    }
}

public class Main2 {

    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("E1", "John");
        Employee jane = new PartTimeEmployee("E2", "Jane");

        LeaveRequest req1 = new LeaveRequest("LR1", john, "Jan 1-5", 5);
        req1.approve();

        LeaveRequest req2 = new LeaveRequest("LR2", jane, "Feb 10-11", 2);
        req2.reject();

        // Invalid State Transition Attempt
        req1.setStatus(LeaveStatus.PENDING);
    }
}
