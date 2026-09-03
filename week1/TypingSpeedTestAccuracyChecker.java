public class TypingSpeedTestAccuracyChecker {
    public static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
                origChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        double accuracy = ((double) matched / total) * 100;
        String accStr = String.format("%.2f%%", accuracy);

        StringBuilder sb = new StringBuilder();
        sb.append("Matched: ").append(matched).append("/").append(total)
          .append(" | Accuracy: ").append(accStr).append(" | ");

        if (firstMismatchPos == -1) {
            sb.append("No Mismatches");
        } else {
            sb.append("First Mismatch at position ").append(firstMismatchPos)
              .append(" ('").append(origChar).append("' vs '").append(typedChar).append("')");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
