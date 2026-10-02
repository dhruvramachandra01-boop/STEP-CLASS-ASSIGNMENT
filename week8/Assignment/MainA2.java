
enum SubmissionStatus {
    SUBMITTED, GRADED
}

abstract class Assignment {

    private String title;
    private double maxMarks;
    private int dueDateDay; // Simplified day of month representation

    public Assignment(String title, double maxMarks, int dueDateDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDateDay = dueDateDay;
    }

    public String getTitle() {
        return title;
    }

    public double getMaxMarks() {
        return maxMarks;
    }

    public int getDueDateDay() {
        return dueDateDay;
    }

    // Abstract method to enforce open-closed principle for penalty rules
    public abstract double calculatePenaltyPercentage(int lateDays);
}

class CodingAssignment extends Assignment {

    public CodingAssignment(String title, double maxMarks, int dueDateDay) {
        super(title, maxMarks, dueDateDay);
    }

    @Override
    public double calculatePenaltyPercentage(int lateDays) {
        return Math.min(1.0, lateDays * 0.10); // 10% per day
    }
}

class WrittenAssignment extends Assignment {

    public WrittenAssignment(String title, double maxMarks, int dueDateDay) {
        super(title, maxMarks, dueDateDay);
    }

    @Override
    public double calculatePenaltyPercentage(int lateDays) {
        return Math.min(1.0, lateDays * 0.20); // 20% per day
    }
}

class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {

    private Student student;
    private Assignment assignment;
    private int submissionDateDay;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, int submissionDateDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDateDay = submissionDateDay;
        this.status = SubmissionStatus.SUBMITTED;

        int lateDays = Math.max(0, submissionDateDay - assignment.getDueDateDay());
        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: " + status + ".");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + lateDays + " days late). Status: " + status + ".");
        }
    }

    public void grade(double awardedMarks) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.println("Submission has already been graded.");
            return;
        }

        int lateDays = Math.max(0, submissionDateDay - assignment.getDueDateDay());
        double penaltyPct = assignment.calculatePenaltyPercentage(lateDays);
        this.finalMarks = awardedMarks * (1.0 - penaltyPct);
        this.status = SubmissionStatus.GRADED;

        if (penaltyPct > 0) {
            int penaltyDisplay = (int) Math.round(penaltyPct * 100);
            System.out.println(student.getName() + " graded: " + (int) finalMarks + "/" + (int) assignment.getMaxMarks()
                    + " after " + penaltyDisplay + "% late penalty. Status: " + status + ".");
        } else {
            System.out.println(student.getName() + " graded: " + (int) finalMarks + "/" + (int) assignment.getMaxMarks()
                    + " Status: " + status + ".");
        }
    }

    public void resubmit(int newSubmissionDateDay) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return;
        }
        this.submissionDateDay = newSubmissionDateDay;
        System.out.println(student.getName() + " resubmitted '" + assignment.getTitle() + "'.");
    }
}

public class MainA2 {

    public static void main(String[] args) {
        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, 10);
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, 12);

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission sub1 = new Submission(asha, linkedListLab, 10);
        Submission sub2 = new Submission(ravi, designEssay, 14);

        sub1.grade(45);
        sub2.grade(40);

        // Attempting resubmission after grading
        sub1.resubmit(15);
    }
}
