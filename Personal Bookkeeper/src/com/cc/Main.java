package com.cc;
import java.io.*;
import java.util.Scanner;

public class Main
{
    //global variable
    //easier for changing to different files
    private static String FILENAME = "Accounts.dat";

    public static void main(String[] args) throws IOException
    {
        projectStart();
        return;
    }

    public static void projectStart() throws IOException
    {
        while (true)
        {
            String action = welcomeMsg();
            if (action.equals("I")) insert();
            else if (action.equals("M")) modify();
            else if (action.equals("V")) view();
            else if (action.equals("Q"))
            {
                System.out.println("Quit\n\n");
                break;
            }
            else System.out.println("Please enter a valid action\n");
        }
        return;
    }

    public static String welcomeMsg() //the main page from user's view
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Accounts Manager");
        System.out.println("Please Select From One of the Following : ");
        System.out.println("I – in order to insert a new account data;");
        System.out.println("M – in order to modify the data for a particular account;");
        System.out.println("V – in order to view/list all the accounts;");
        System.out.println("Q – in order to quit this application");
        String action = scan.nextLine(); //put the input into variable "action"
        return action;
    }

    public static void w2f(Accounts ac) //write to file method //throws FileNotFoundException
    {
        try
        {
            FileWriter out = new FileWriter("Accounts.dat", true);
            out.append(ac.toDat());
            out.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("w2f error");
        }
    }

    public static void insert() throws FileNotFoundException // create new account
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please insert an account number : ");
        int acNumber = scan.nextInt();
        scan.nextLine(); // make sure it will appear next question after input

        //account number should be 6-digits integer
        if (acNumber >= 1000000 || acNumber < 100000)
        {
            System.out.println("Account number should be 6-digits integer.\n");
            return;
        }

        //no two user can have the same account
        String duplicateAc = findAccount(acNumber);
        if (!duplicateAc.equals("")) //account number already exist (!=empty)
        {
            System.out.println("Account is already exist. Please enter another account number.");
            return;
        }

        System.out.print("Please insert your first name : ");
        String firstName = scan.nextLine();
        System.out.print("Please insert your last name : ");
        String lastName = scan.nextLine();
        System.out.print("Please insert your amount : ");
        double amount = scan.nextDouble();
        scan.nextLine();

        //positive amount checking
        if(amount < 0)
        {
            System.out.println("Amount should be positive");
            return;
        };

        //rounded to two decimal
        amount = Math.round(amount * 100.0) / 100.0;

        //create an instance of an Accounts
        Accounts ac = new Accounts(acNumber, firstName, lastName, amount);
        System.out.println(ac);

        w2f(ac); //write newly input into the file

        return;
    }

    //if account is existed
    public static String findAccount(int acNum) throws FileNotFoundException
    {
        //String filename = "Accounts.dat";
        if (new File(FILENAME).exists() == false)
        {
            System.out.println("Account not found");
            return "";
        }
        //read file
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(FILENAME));
            //read line by line
            String line = reader.readLine();
            while (line != null)
            {
                if (line.startsWith(Integer.toString(acNum) + ";"))
                {
                    return line;
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static void modify() throws IOException
    {
        while (true)
        {
            Scanner scan = new Scanner(System.in);
            System.out.print("Please insert an account number you want to modify : ");
            int modAcNumber = scan.nextInt();
            scan.nextLine();

            // find ac -> String
            String ac = findAccount(modAcNumber);
            if (ac.equals(""))
            {
                System.out.println("Account not found");
                return;
            }

            System.out.println("Please select C/W/D/Q from below functions : ");
            System.out.println("C – Change Name");
            System.out.println("W – withdraw");
            System.out.println("D – deposit");
            System.out.println("Q – quit");
            String modFunction = scan.nextLine();

            // different options to modify
            if (modFunction.equals("C")){ changeName(ac); return; }
            else if (modFunction.equals("W")){ withdraw(ac);return; }
            else if (modFunction.equals("D")){ deposit(ac);return; }
            else if (modFunction.equals("Q"))
            {
                System.out.println("quit");
                break; //enter quit will stop while loop
            }
            else System.out.println("Please enter a valid action\n");
        }
        return;
    }

    public static void updateAc()
    {
        //String filename = "Accounts.dat";
        if (new File(FILENAME).exists() == false)
        {
            System.out.println("updateAc : file not found");
            System.out.println("This is not a valid account and you'll be redirected to the main page");
            return;
        }
    }

    public static void changeName(String ac) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Change First Name to : ");
        String newFirstName = new String();
        newFirstName = scan.next();
        System.out.print("Change Last Name to: ");
        String newLastName = new String();
        newLastName = scan.next();

        System.out.println("New First Name is : " + newFirstName);
        System.out.println("New Last Name is : " + newLastName +"\n");

        String filePath = "Accounts.dat";

        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));

        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();

        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();

        //closing the Scanner object
        sc.close();

        String[] acSplit = ac.split(";");
        String oldLine = acSplit[0] + ";" + acSplit[1];
        String newLine = acSplit[0] + "; " + newFirstName + " "
                + newLastName;

        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(oldLine, newLine);

        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        System.out.println("");
        System.out.println("current data: \n"+fileContents);
        writer.write(fileContents);
        writer.flush();
        writer.close();
    }

    public static void updateAmount(String ac, boolean isDeposit) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        if (isDeposit) { System.out.print("Please input deposit amount : "); }
        else { System.out.print("Please input withdraw amount : "); }

        double updateAmount;
        updateAmount = scan.nextDouble();
        scan.nextLine();

        //positive amount
        if (updateAmount < 0)
        {
            System.out.println("Amount should be positive\n\n");
            return;
        }

        //rounded to two decimal
        updateAmount = Math.round(updateAmount * 100.0) / 100.0;
        //System.out.print(wAmount);

        String filePath = "Accounts.dat";

        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));

        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();

        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();

        //closing the Scanner object
        sc.close();

        String[] acSplit = ac.split(";");

        //withdraw method sufficient funds checking
        if (!isDeposit && Double.parseDouble(acSplit[2]) < updateAmount)
        {
            System.out.println("in-sufficient funds\n\n");
            return;
        }

        String oldLine = acSplit[0] + ";" + acSplit[1] + ";" + acSplit[2];

        //rounded to two decimal
        if(!isDeposit){ updateAmount *= (-1); } //if withdraw, amount * -1
        double newAmount = Math.round((Double.parseDouble(acSplit[2]) + (updateAmount)) * 100.0) / 100.0;

        String newLine = acSplit[0] + ";" + acSplit[1] + "; "
                + String.valueOf(newAmount);

        //Replacing the old line with new line
        fileContents = fileContents.replaceAll(oldLine, newLine);

        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        System.out.println("");
        System.out.println("current data: \n"+fileContents);
        writer.write(fileContents);
        writer.flush();
        writer.close();
    }

    public static void withdraw(String ac) throws IOException
    {
        updateAmount(ac, false);
    }

    public static void deposit(String ac) throws IOException
    {
        updateAmount(ac, true);
    }

    public static void view() throws FileNotFoundException //check existing account info
    {
        String filePath = "Accounts.dat";

        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));

        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();

        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine())
        {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();

        //closing the Scanner object
        sc.close();

        System.out.print(fileContents + "\n\n");
    }
}