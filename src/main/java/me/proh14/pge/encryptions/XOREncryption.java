package me.proh14.pge.encryptions;

public class XOREncryption {

    private static long key = 1111111111111111L;






    public static String Encrypt(String input) {

        String Encrypted = "";


        for(int i = 0; i < input.length(); i++){


            Encrypted += (input.charAt(i) ^ key);



        }



        return Encrypted;
    }

    public static String Decrypt(String input) {

        String Decrypted = "";


        for(int i = 0; i < input.length(); i++){


            Decrypted += (input.charAt(i) ^ key);



        }



        return Decrypted;
    }





}
