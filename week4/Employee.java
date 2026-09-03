class Employee {
    private String empId;
    private double salary;

    public Employee(String empId, double salary) {
        this.empId = empId;     // Resolving field/parameter name clash
        this.salary = salary;   // Resolving field/parameter name clash
    }

    public void raiseSalary(double salary) {
        this.salary += salary;  // Resolving field/parameter name clash
    }

    public void printSummary() {
        System.out.println(empId + " | Final Salary: Rs " + salary);
    }

    public static void main(String[] args) {
        Employee[] employees = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        double bonus = 5000;
        for (Employee emp : employees) {
            emp.raiseSalary(bonus);
            emp.printSummary();
        }
    }
}
}
