package com.cc;

public class Checking extends Accounts
{
    //additional member
    private int debitCardNum;

    //constructors
    public Checking(int acNum, String f_Name, String l_Name, double amount, int debitCardNum)
    {
        super(acNum, f_Name, l_Name, amount); //invocation
        this.setDebitCardNum(debitCardNum);
    }

    public int getDebitCardNum() { return this.debitCardNum; }
    public void setDebitCardNum(int debitCardNum) { this.debitCardNum = debitCardNum; }

    public String toDat()
    {
        return this.getAcNum() + "; "
                + this.getFName() + " "
                + this.getLName() + "; "
                + this.getAmount() + "; "
                + this.getDebitCardNum() + ";\n";
    }

    public String toString()
    {
        return "Account number : " + this.getAcNum()
                + " \nName : " + this.getFName() + " " + this.getLName()
                + " \nBalance : " + this.getAmount()
                + "\nDebit_Card_Number : " + this.getDebitCardNum() + "\n\n";
    }
}