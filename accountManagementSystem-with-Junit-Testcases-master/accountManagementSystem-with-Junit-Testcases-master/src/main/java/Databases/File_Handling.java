package Databases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class File_Handling {
    public static void  addDataInFile(String name,String address, String accountNumber, long balance,String phoneNo, int accountType)
    {
        if(accountType ==1)
        {
            addData_CheckingAccount( name, address,  accountNumber,  balance, phoneNo);
        }
        else if(accountType ==2)
        {
            addData_SavingAccount( name, address,  accountNumber,  balance, phoneNo);
        }
    }

    private static void addData_CheckingAccount(String name, String address, String accountNumber, long balance, String phoneNo)
    {
        String line=name +'|'+ address +'|' +accountNumber +'|'+ balance +'|'+ phoneNo +'|';
        File file =new File("C:\\Dev\\Java\\AdvanceProg\\Task04\\accountManagementSystem-with-Junit-Testcases-master\\accountManagementSystem-with-Junit-Testcases-master\\DB\\Checking_Account.txt");
        try {
            FileWriter fr=new FileWriter(file,true);
            fr.write(line);
            fr.close();
            System.out.println(line);
            System.out.println("New Recorded Added in file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addData_SavingAccount(String name, String address, String accountNumber, long balance, String phoneNo)
    {
        String line=name +'|'+ address +'|' +accountNumber +'|'+ balance +'|'+ phoneNo +'|';
        File file =new File("C:\\Dev\\Java\\AdvanceProg\\Task04\\accountManagementSystem-with-Junit-Testcases-master\\accountManagementSystem-with-Junit-Testcases-master\\DB\\Saving_Account.txt");
        try {
            FileWriter fr=new FileWriter(file,true);
            fr.write(line);
            fr.close();
            System.out.println(line);
            System.out.println("New Recorded Added in file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
