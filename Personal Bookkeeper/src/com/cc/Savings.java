package com.cc;

public class Savings extends Accounts
{
    //additional member
    private double rate;

    //constructors
    public Savings(int acNum, String f_Name, String l_Name, double amount, double rate)
    {
        super(acNum, f_Name, l_Name, amount); //invocation
        this.setRate(rate);
    }

    public double getRate() { return this.rate; }
    public void setRate(double rate) { this.rate = rate; }

    public String toDat()
    {
        return this.getAcNum() + "; "
                + this.getFName() + " "
                + this.getLName() + "; "
                + this.getAmount() + "; "
                + this.getRate() + ";\n";
    }

    public String toString()
    {
        return "Account number : " + this.getAcNum()
                + " \nName : " + this.getFName() + " " + this.getLName()
                + " \nBalance : " + this.getAmount()
                + "\nInterest Rate : " + this.getRate() + "\n\n";
    }

}