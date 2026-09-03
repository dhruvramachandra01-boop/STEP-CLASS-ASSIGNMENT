public class SrmStudent {
    private String name;
    public static String collegeName;
    public static String academicYear;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026";
        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String studentName : names) {
            new SrmStudent(studentName);
        }
    }
}