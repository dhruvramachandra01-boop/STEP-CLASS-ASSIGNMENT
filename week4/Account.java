class Account {
    private String regNo;
    private double totalFee;

    public Account(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    // final methods locked against overriding
    public final double calculateLateFee(int daysLate) {
        return totalFee * 0.01 * daysLate; // 1% per day late
    }

    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            double fee = calculateLateFee(daysLate);
            System.out.println(regNo + " Total Fee: Rs " + totalFee + " | Late Fee: Rs " + fee);
        }
    }

    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            Account acc = new Account(regNos[i], totalFees[i]);
            acc.printSummary(daysLate[i]);
        }
    }
}