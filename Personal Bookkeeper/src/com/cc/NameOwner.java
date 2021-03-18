package com.cc;

public class NameOwner extends AccountNumber
{
    private String f_Name;
    private String l_Name;

    //constructors
    public NameOwner(int acNum, String f_Name, String l_Name)
    {
        super(acNum);
        setFName(f_Name);
        setLName(l_Name);
        getFName(f_Name);
        getLName(l_Name);
    }
    //method getName
    public String getFName(String f_name) { return this.f_Name; }
    public String getLName(String l_name) { return this.l_Name; }

    //method setName
    public void setFName(String f_name) { this.f_Name = f_Name; }
    public void setLName(String l_name) { this.l_Name = l_Name; }
}
