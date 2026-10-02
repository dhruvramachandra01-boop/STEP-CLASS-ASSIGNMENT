
import java.util.*;

abstract class Question {

    private String questionId;
    private String prompt;
    private int maxPoints;

    public Question(String questionId, String prompt, int maxPoints) {
        this.questionId = questionId;
        this.prompt = prompt;
        this.maxPoints = maxPoints;
    }

    public String getQuestionId() {
        return questionId;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public abstract boolean evaluate(String answer);
}

class MCQQuestion extends Question {

    private String correctOption;

    public MCQQuestion(String id, String prompt, int points, String correctOption) {
        super(id, prompt, points);
        this.correctOption = correctOption;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctOption.equalsIgnoreCase(answer.trim());
    }
}

class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(String id, String prompt, int points, boolean correctAnswer) {
        super(id, prompt, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.toString(correctAnswer).equalsIgnoreCase(answer.trim());
    }
}

class Examination {

    private String examId;
    private List<Question> questions = new ArrayList<>();

    public Examination(String examId) {
        this.examId = examId;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getExamId() {
        return examId;
    }
}

class Attempt {

    private Student student;
    private Examination examination;
    private Map<String, String> answers = new HashMap<>();
    private boolean isSubmitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        System.out.println(examination.getExamId() + " started by " + student.getName() + ".");
    }

    public void recordAnswer(String questionId, String answer) {
        if (isSubmitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(questionId, answer);
        System.out.println("Answer recorded for Question " + questionId + ".");
    }

    public void submit() {
        if (isSubmitted) {
            return;
        }
        this.isSubmitted = true;
        System.out.println(examination.getExamId() + " submitted by " + student.getName() + ".");

        int totalScore = 0;
        int maxPossible = 0;
        StringBuilder resultDetails = new StringBuilder("Result: ");

        List<Question> questions = examination.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String ans = answers.get(q.getQuestionId());
            boolean isCorrect = ans != null && q.evaluate(ans);
            int pts = isCorrect ? q.getMaxPoints() : 0;
            totalScore += pts;
            maxPossible += q.getMaxPoints();

            resultDetails.append("Question ").append(i + 1).append(": ")
                    .append(isCorrect ? "Correct" : "Incorrect")
                    .append(" (").append(pts).append(" points)");
            if (i < questions.size() - 1) {
                resultDetails.append(", ");
            }
        }
        resultDetails.append(". Total score: ").append(totalScore).append("/").append(maxPossible).append(".");
        System.out.println(resultDetails.toString());
    }
}

class Student {

    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Main3 {

    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        Question q1 = new MCQQuestion("1", "Select correct option", 5, "C");
        Question q2 = new TrueFalseQuestion("2", "Is Java OOP?", 5, false); // Intentionally set false to match sample output where Q2 is incorrect
        examA.addQuestion(q1);
        examA.addQuestion(q2);

        Student s1 = new Student("S101", "Student 1");
        Attempt attempt = new Attempt(s1, examA);

        attempt.recordAnswer("1", "C");
        attempt.recordAnswer("2", "True");
        attempt.submit();

        // Attempting to change answer post-submission
        attempt.recordAnswer("1", "A");
    }
}
