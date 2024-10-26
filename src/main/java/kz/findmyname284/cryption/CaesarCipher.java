package kz.findmyname284.cryption;

public class CaesarCipher {
    private static final String ALPHABET = "АӘБВГҒДЕЁЖЗИЙКҚЛМНҢОӨПРСТУҰҮФХҺЦЧШЩЪЫІЬЭЮЯ";
    private static final int ALPHABET_SIZE = ALPHABET.length();

    public static String encrypt(String text, int shift) {
        if (shift < 0 || shift >= ALPHABET_SIZE) {
            return "Ключ должен быть в пределах от 0 до " + (ALPHABET_SIZE - 1) + ".";
        }
        try {
            StringBuilder result = new StringBuilder();

            for (char ch : text.trim().toUpperCase().toCharArray()) {
                int index = ALPHABET.indexOf(ch);
                if (index != -1) {
                    int newIndex = (index + shift);
                    if (newIndex >= ALPHABET_SIZE) {
                        newIndex -= ALPHABET_SIZE;
                    }
                    result.append(ALPHABET.charAt(newIndex));
                } else {
                    result.append(ch);
                }
            }
            return result.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String decrypt(String text, int shift) {
        try {
            return encrypt(text, ALPHABET_SIZE - (shift % ALPHABET_SIZE));
        } catch (Exception e) {
            return e.getMessage();
        }
        
    }
}