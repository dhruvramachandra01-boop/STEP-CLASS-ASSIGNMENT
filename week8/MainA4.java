
enum MembershipStatus {
    ACTIVE, FROZEN, EXPIRED
}

interface MembershipPlan {

    String getName();

    int getDurationMonths();

    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {

    @Override
    public String getName() {
        return "Monthly";
    }

    @Override
    public int getDurationMonths() {
        return 1;
    }

    @Override
    public double calculateFee() {
        return 1000.00;
    }
}

class QuarterlyPlan implements MembershipPlan {

    @Override
    public String getName() {
        return "Quarterly";
    }

    @Override
    public int getDurationMonths() {
        return 3;
    }

    @Override
    public double calculateFee() {
        return 3 * 1000.00 * 0.90;
    } // 10% off
}

class AnnualPlan implements MembershipPlan {

    @Override
    public String getName() {
        return "Annual";
    }

    @Override
    public int getDurationMonths() {
        return 12;
    }

    @Override
    public double calculateFee() {
        return 12 * 1000.00 * 0.75;
    } // 25% off
}

class Member {

    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {

    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;
    private double fee;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
        this.status = MembershipStatus.ACTIVE;

        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: %s.\n",
                plan.getName(), member.getName(), fee, formatStatus(status));
    }

    public void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + formatStatus(status) + ".");
        }
    }

    public void freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }
        if (status == MembershipStatus.FROZEN) {
            System.out.println("Membership is already Frozen.");
            return;
        }
        this.status = MembershipStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
    }

    public void unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }
        this.status = MembershipStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
    }

    public void expire() {
        this.status = MembershipStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: Expired.");
    }

    private String formatStatus(MembershipStatus s) {
        switch (s) {
            case ACTIVE:
                return "Active";
            case FROZEN:
                return "Frozen";
            case EXPIRED:
                return "Expired";
            default:
                return "";
        }
    }
}

public class MainA4 {

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn(); // Should be denied

        raviMembership.expire();
        raviMembership.freeze();  // Should fail
    }
}
