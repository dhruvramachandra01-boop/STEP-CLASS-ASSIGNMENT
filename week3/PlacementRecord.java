import java.util.Scanner;

class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    public void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlacementRecord[] records = new PlacementRecord[3];

        for (int i = 0; i < 3; i++) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String company = parts[1].trim();
                double lpa = Double.parseDouble(parts[2].trim());
                records[i] = new PlacementRecord(name, company, lpa);
            }
        }

        for (PlacementRecord record : records) {
            if (record != null) {
                record.printRecord();
            }
        }
        scanner.close();
    }
}