
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Helper class to represent a Book record
    public static class Book {

        private String isbn;
        private String title;

        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }
    }

    // Binary search implementation for sorted ISBN catalog lookup
    public static String findBook(List<Book> catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midIsbn = catalog.get(mid).getIsbn();

            int cmp = midIsbn.compareTo(targetIsbn);
            if (cmp == 0) {
                return catalog.get(mid).getTitle();
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<Book> catalog = new ArrayList<>();
        catalog.add(new Book("0001112223", "Introduction to Algebra"));
        catalog.add(new Book("0002223334", "Beginning Python"));
        catalog.add(new Book("0003334445", "Classic Mythology"));
        catalog.add(new Book("0004445556", "Data and Society"));
        catalog.add(new Book("0005556667", "European History"));

        // Example 1
        String target1 = "0003334445";
        System.out.println("Target: " + target1 + " -> Output: " + findBook(catalog, target1));

        // Example 2
        String target2 = "0009998887";
        System.out.println("Target: " + target2 + " -> Output: " + findBook(catalog, target2));
    }
}
