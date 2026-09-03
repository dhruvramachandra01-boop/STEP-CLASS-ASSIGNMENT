public class LibraryIsbnNormalizer {
    public static String normalizeCode(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) {
            String msg = "Invalid: code must be exactly 13 characters";
            System.out.println(msg);
            return msg;
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                String msg = "Invalid: publisher code must be 3 letters";
                System.out.println(msg);
                return msg;
            }
        }

        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                String msg = "Invalid: remaining 10 characters must be digits";
                System.out.println(msg);
                return msg;
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ")
          .append(year).append(" | CATALOG: ").append(catalog);

        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String code1 = normalizeCode(" pen2026004251 ");
        validateAndFormat(code1);

        String code2 = normalizeCode("12N2026004251");
        validateAndFormat(code2);
    }
}