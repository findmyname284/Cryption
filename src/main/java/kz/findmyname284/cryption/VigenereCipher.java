package kz.findmyname284.cryption;

public class VigenereCipher {

    private static final String ALPHABET = "АӘБВГҒДЕЁЖЗИЙКҚЛМНҢОӨПРСТУҰҮФХҺЦЧШЩЪЫІЬЭЮЯ";
    private static final int ALPHABET_SIZE = ALPHABET.length();
    public static String encrypt(String plaintext, String key) {
        key = key.toUpperCase().trim();
        if (!isCyrillic(key)) {
            return "Ключ должен быть на кирилице";
        }
        try {
            StringBuilder ciphertext = new StringBuilder();
            plaintext = plaintext.toUpperCase().trim();
            int keyIndex = 0;

            for (int i = 0; i < plaintext.length(); i++) {
                char c = plaintext.charAt(i);
                int charIndex = ALPHABET.indexOf(c);
                if (charIndex != -1) {
                    char encryptedChar = ALPHABET.charAt(
                            (charIndex + ALPHABET.indexOf(key.charAt(keyIndex % key.length()))) % ALPHABET_SIZE);
                    ciphertext.append(encryptedChar);
                    keyIndex++;
                } else {
                    ciphertext.append(c);
                }
            }

            return ciphertext.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String decrypt(String ciphertext, String key) {
        key = key.toUpperCase().trim();
        if (!isCyrillic(key)) {
            return "Ключ должен быть на кирилице";
        }
        try {
            StringBuilder plaintext = new StringBuilder();
            ciphertext = ciphertext.toUpperCase().trim();
            int keyIndex = 0;

            for (int i = 0; i < ciphertext.length(); i++) {
                char c = ciphertext.charAt(i);
                if (ALPHABET.indexOf(c) != -1) {
                    char decryptedChar = ALPHABET.charAt(
                            (ALPHABET.indexOf(c) - ALPHABET.indexOf(key.charAt(keyIndex % key.length()))
                                    + ALPHABET_SIZE)
                                    % ALPHABET_SIZE);
                    plaintext.append(decryptedChar);
                    keyIndex++;
                } else {
                    plaintext.append(c);
                }
            }

            return plaintext.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static boolean isCyrillic(String text) {
        for (char c : text.toCharArray()) {
            if (ALPHABET.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
