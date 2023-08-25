package com.driver;

import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);
        if (balance < 5000) throw new Exception("Insufficient Balance");
        this.tradeLicenseId = tradeLicenseId;

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        char [] charArray = this.tradeLicenseId.toCharArray();
//        int i = 0;
//        int j = i+1;
//
//        while (i < charArray.length - 1 && j < charArray.length) {
//            if (charArray[i] != charArray[j]) {
//                i++;
//                j++;
//            }
//
//            else {
//                while (charArray[i] == charArray[j]) {
//                    j++;
//                    if (j >= charArray.length) throw new Exception("Valid License can not be generated");
//
//                }
//
//                char temp = charArray[i+1];
//                charArray[i+1] = charArray[j];
//                charArray[j] = temp;
//                i++;
//                j = i+1;
//            }
//        }
//
//        String str = new String(charArray);
//        this.tradeLicenseId = str;
        if (isValid(charArray)) return;

        int [] freqMap = new int[26];
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            freqMap[ch - 'a']++;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return b.freq - a.freq;
        });

        for (int i = 0; i < 26; i++) {
            if (freqMap[i] > 0) {
                pq.add(new Pair((char)(i + 'a'), freqMap[i]));
            }
        }

        StringBuilder ans = new StringBuilder();
        Pair block = pq.remove();
        ans.append(block.ch);
        block.freq--;

        while (pq.size() > 0) {
            Pair temp = pq.remove();
            ans.append(temp.ch);
            temp.freq--;
            if(block.freq > 0) pq.add(block);
            block = temp;
        }

//        if (block.freq > 0) {
//            throw new Exception("Valid License can not be generated");
//        }
        String id = ans.toString();
        char[] newArr = id.toCharArray();
        if (isValid(newArr)) {
            this.tradeLicenseId = id;
        }

        else throw new Exception("Valid License can not be generated");

    }

    private boolean isValid (char [] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) return false;
        }

        return true;
    }

    static class Pair {
        char ch;
        int freq;
        public Pair (char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }



}
