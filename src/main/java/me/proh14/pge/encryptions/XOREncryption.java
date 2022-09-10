package me.proh14.pge.encryptions;

public final class XOREncryption {

    private static XOREncryption instance;

    private long key = 1111111111111111L;

    public static XOREncryption getInstance() {
        if (instance == null) instance = new XOREncryption();
        return instance;
    }

    public String encryptDecrypt(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key));
        }
        return output.toString();
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }
}
