import java.util.Scanner;

public class BookInventory {
    String title;
    String author;
    int copiesAvailable;

    public BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public void printEntry() {
        System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookInventory[] inventory = new BookInventory[4];

        for (int i = 0; i < 4; i++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    int copies = Integer.parseInt(parts[2].trim());
                    inventory[i] = new BookInventory(title, author, copies);
                }
            }
        }

        for (BookInventory book : inventory) {
            if (book != null) {
                book.printEntry();
            }
        }
        scanner.close();
    }
}