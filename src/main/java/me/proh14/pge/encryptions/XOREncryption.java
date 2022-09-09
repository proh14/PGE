package me.proh14.pge.encryptions;

public class XOREncryption {

    private static final long key = 1111111111111111L;

    public static String Encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            encrypted.append(input.charAt(i) ^ key);
        return encrypted.toString();
    }

    public static String Decrypt(String input) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            decrypted.append(input.charAt(i) ^ key);
        return decrypted.toString();
    }
}
