/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merklehellman;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Crypto class can create a public key when the instance crypto is created, and
 * can call method encryption(String s) and decryption(BigInteger) to do crypto.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 09/16/2015
 */
public class Crypto {

    private final static int RANDOMSOLUTION = 10;
    private final static int NODESNUMBER = 640;
    private LinkedList w;
    private BigInteger q;
    private BigInteger r;
    private LinkedList b;

    /**
     * Constructor 
     * when calling the constructor, creates a key b. The key will
     * not change for the encryption and decryption this time
     */
    public Crypto() {
        //Step1:generateSuperIncreasingSequence
        LinkedList seq = new LinkedList();
        SecureRandom sr = new SecureRandom();

        for (int i = 0; i < NODESNUMBER; i++) {
            BigInteger sumBefore = new BigInteger("1");
            for (int j = 0; j < i; j++) {
                sumBefore = sumBefore.add((BigInteger) seq.get(j));
            }

            BigInteger bigInt = new BigInteger(String.valueOf(sr.nextInt(RANDOMSOLUTION)));
            BigInteger newAdd = sumBefore.add(bigInt);
            seq.add(newAdd);
        }
        w = seq;

        // step2: sum of w
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < seq.size(); i++) {
            BigInteger bigInt = (BigInteger) seq.get(i);
            sum = sum.add(bigInt);
        }

        //Step3: get q
        q = sum.nextProbablePrime();
        //System.out.println(q);

        //step4:get r
        int bitLength = q.bitLength();
        int random;

        do {
            random = sr.nextInt(bitLength) + 1;//0<= sr.nextInt(bitLength)<bitLength
            r = BigInteger.probablePrime(bitLength, sr);

        } while (q.compareTo(r) != 1);

       // Step5:genetrate b
        LinkedList publicKey = new LinkedList();
        for (int i = 0; i < seq.size(); i++) {
            BigInteger element = (BigInteger) seq.get(i);
            publicKey.add(element.multiply(r).mod(q));
        }
        b = publicKey;
    }

    /**
     * preconditions: s is a string. Crypto instance is correctly built so as to call this method.
     * postconditions:encrypt the user's input string to return a BigInteger encrypted number.
     * @param s the user's input string
     * @return a BigInteger as the encrypted number
     * Big-Theta(N^2) for any cases
     */
    public BigInteger encryption(String s) {
        //Step1: call convertToBinary method to convert input string to binary
        String bitsString = translateToBinary(s);
        //Step2: for each num in bitsString,multiply by b's each element
        BigInteger encryptedNum = BigInteger.ZERO;
        for (int i = 0; i < bitsString.length(); i++) {

            String c = "" + String.valueOf(bitsString.charAt(i));
            BigInteger a1 = (BigInteger) b.get(i);
            BigInteger a2 = a1.multiply(new BigInteger(c));
            encryptedNum = encryptedNum.add(a2);
        }
        return encryptedNum;

    }

    /**
     * precondition: big should be BigInteger type.
     * postcondition:this method decrypts a BigInteger encrypted number to the user's input string.
     *
     * @param big is the encrypted number that needs to be decrypted
     * @return the string that the user input.
     * Big-Theta(1) for any cases
     */
    public String decryption(BigInteger big) {
        String decryptedBits = "";
        String decryptedString = "";

        BigInteger decompsed = r.modInverse(q).multiply(big).mod(q).mod(q);
        //from the last to begin, find Wn
        //if Wn<= num: tmp = num - Wn, Wn->"1"
        //if Wn<= tmp: tmp = tmp-Wn,... 
        BigInteger tmp = decompsed;
        for (int i = w.size() - 1; i >= 0; i--) {

            if (tmp.compareTo((BigInteger) w.get(i)) != -1) {
                if (tmp != BigInteger.ZERO) {
                    tmp = tmp.subtract((BigInteger) w.get(i));
                }
                decryptedBits = "1" + decryptedBits;
            } else {
                decryptedBits = "0" + decryptedBits;
            }
        }
        //convert binary to text
        String each8;
        for (int i = 0; i < decryptedBits.length(); i = i + 8) {
            each8 = decryptedBits.substring(i, i + 8);
            if ("00000000".equals(each8)) {
                break;
            } else {
                decryptedString += new Character((char) Integer.parseInt(each8, 2)).toString();
            }

        }
        return decryptedString;
    }

    /**
     * precondition:input should be string
     * postcondition:
     * convert the input string into binary represented string. For instance,
     * convert "D" to '010000100'
     *
     * @param input is the text that user inputs.
     * @return a binary string
     * Big-Theta(N) for any cases
     */
    public String translateToBinary(String input) {
        String byteString;
        String finalString = "";
        int difference;
        for (int i = 0; i < input.length(); i++) {
            String c = "" + input.charAt(i);
            BigInteger s2 = new BigInteger(c.getBytes());
            byteString = s2.toString(2);

            difference = 8 - byteString.length();
            while (difference != 0) {
                byteString = "0" + byteString;
                difference = 8 - byteString.length();
            }
            finalString += byteString;

        }

        return finalString;
    }

}
