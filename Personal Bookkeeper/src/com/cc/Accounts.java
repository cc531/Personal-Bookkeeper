package com.cc;

public class Accounts
{
    private int acNum;
    private String f_Name;
    private String l_Name;
    private double amount;

    //constructors
    public Accounts(int acNum, String f_Name, String l_Name, double amount)
    {
        this.setNum(acNum);
        this.setFName(f_Name);
        this.setLName(l_Name);
        this.setAmount(amount);
    }
    //method getter
    public int getAcNum() { return this.acNum; }
    public String getFName() { return this.f_Name; }
    public String getLName() { return this.l_Name; }
    public double getAmount() { return this.amount; }

    //method setter
    public void setNum(int acNum) { this.acNum = acNum; }
    public void setFName(String f_name) { this.f_Name = f_name; }
    public void setLName(String l_name) { this.l_Name = l_name; }
    public void setAmount(double amount) { this.amount = amount; }

    public String toDat() //write to file (format ; ; ;)
    {
        return this.getAcNum() + "; "
                + this.getFName() + " "
                + this.getLName() + "; "
                + this.getAmount() + ";\n";
    }

    public String toString() //easier reading
    {
        return "Account number : " + this.getAcNum()
                + " \nName : " + this.getFName() + " " + this.getLName()
                + " \nBalance : " + this.getAmount() + "\n\n";
    }

    //method newAmount
    //public double newAmount(double modAmount)
    //{
    //    this.modAmount = amount - modAmount;
    //}

}