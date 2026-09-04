package homework;

class MembershipCard {
    static final String LIBRARY_NAME;
    static final String VALID_UNTIL;
    String studentName;

    // Static initialization block
    static {
        LIBRARY_NAME = "SRM Central Library";
        VALID_UNTIL = "May 2027";
        System.out.println("Library info loaded: " + LIBRARY_NAME + " | Valid until: " + VALID_UNTIL);
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
    }

    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            MembershipCard card = new MembershipCard(name);
            System.out.println("Membership card issued: " + card.studentName);
        }
    }
}
