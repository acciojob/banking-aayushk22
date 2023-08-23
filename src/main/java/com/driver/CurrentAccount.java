package com.driver;

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
        int i = 0;
        int j = i+1;

        while (i < charArray.length - 1 && j < charArray.length) {
            if (charArray[i] != charArray[j]) {
                i++;
                j++;
            }

            else {
                while (charArray[i] == charArray[j]) {
                    j++;
                    if (j >= charArray.length) throw new Exception("Valid License can not be generated");

                }

                char temp = charArray[i+1];
                charArray[i+1] = charArray[j];
                charArray[j] = temp;
                i++;
                j = i+1;
            }
        }

        String str = new String(charArray);
        this.tradeLicenseId = str;

    }



}
