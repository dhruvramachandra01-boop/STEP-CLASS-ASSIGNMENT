
import java.util.*;

interface NotificationChannel {

    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[Email → " + student.getName() + "] " + notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[SMS → " + student.getName() + "] " + notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {

    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[App → " + student.getName() + "] " + notice.getTitle());
    }
}

class Student {

    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels = new ArrayList<>();

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }

    public void addPreferredChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }
}

class Notice {

    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments != null ? targetDepartments : new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTargetDepartments() {
        return targetDepartments;
    }

    public boolean isValid() {
        return title != null && !title.trim().isEmpty() && !targetDepartments.isEmpty();
    }
}

class NoticeBoard {

    private List<Student> registeredStudents = new ArrayList<>();

    public void registerStudent(Student student) {
        registeredStudents.add(student);
    }

    public void postNotice(Notice notice) {
        if (!notice.isValid()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        String deptsStr = String.join(", ", notice.getTargetDepartments());
        System.out.println("Notice '" + notice.getTitle() + "' posted to " + deptsStr + ".");

        for (Student student : registeredStudents) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class MainA5 {

    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        // Asha (CSE) prefers Email and App
        Student asha = new Student("Asha", "CSE");
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        // Ravi (ECE) prefers SMS
        Student ravi = new Student("Ravi", "ECE");
        ravi.addPreferredChannel(new SmsChannel());

        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        Notice n1 = new Notice("Lab Closed Tomorrow", Arrays.asList("CSE"));
        noticeBoard.postNotice(n1);

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        Notice n2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice(n2);

        // Admin attempts to post notice with no target department
        Notice n3 = new Notice("Sports Day", Collections.emptyList());
        noticeBoard.postNotice(n3);
    }
}
