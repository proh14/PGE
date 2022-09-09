package me.proh14.pge.encryptions;

public final class XOREncryption {

    private static XOREncryption instance;

    private long key = 1111111111111111L;

    public static XOREncryption getInstance() {
        if (instance == null)
            instance = new XOREncryption();
        return instance;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            encrypted.append(input.charAt(i) ^ key);
        return encrypted.toString();
    }

    public String decrypt(String input) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++)
            decrypted.append(input.charAt(i) ^ key);
        return decrypted.toString();
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long getKey() {
        return key;
    }
}
