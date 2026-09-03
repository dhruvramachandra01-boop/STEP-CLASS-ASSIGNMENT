public class CompanyStaff {
    private String empName;
    private double salary;

    public static String companyName = "Bright Horizon Technologies";
    public static int employeeCount = 0;

    public CompanyStaff(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        CompanyStaff emp1 = new CompanyStaff("Divya", 65000);
        CompanyStaff emp2 = new CompanyStaff("Arjun", 45000);
        CompanyStaff emp3 = new CompanyStaff("Ravi", 50000);

        CompanyStaff.printCompanyInfo();
    }
}
