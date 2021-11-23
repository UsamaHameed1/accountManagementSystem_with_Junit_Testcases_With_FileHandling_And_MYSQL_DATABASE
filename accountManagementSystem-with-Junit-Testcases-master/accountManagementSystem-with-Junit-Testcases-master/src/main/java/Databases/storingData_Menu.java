package Databases;

import java.util.Scanner;

public class storingData_Menu {
    public static void Store_Data_Menu(String name,String address, String accountNumber, long balance,String phoneNo, int accountType)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("\nYour Account has been created");
        System.out.println("\nPlease Select Which Platform You Want to Store Data : ");
        System.out.println("1. File Handling");
        System.out.println("2. MySQL");
        //System.out.println("3. Oracle");

        System.out.println("Enter Your Choice : ");
        int choice =sc.nextInt();

        switch (choice)
        {
            case 1: //file handling
            {
                File_Handling.addDataInFile( name, address,  accountNumber ,balance, phoneNo,  accountType);
                break;
            }
            case 2: //MYSQL
            {
                System.out.println("\nInserting the Record In MYSQL DATABASE");
                System.out.println("Please wait .......");

                if(accountType ==1)  //checking account
                {
                    //inserting data in the checking account
                    connectMySQL.insertMYSQL_DB(name,address,accountNumber,balance,phoneNo,1);
                }
                else if(accountType ==2) //saving account
                {
                    connectMySQL.insertMYSQL_DB(name,address,accountNumber,balance,phoneNo,2);
                }
                else
                {
                    System.out.println("Invalid Account Type");
                }
                System.out.println("Data Inserted Successfully...");
                System.out.println("Thank..... For Using.......");

                break;
            }
//            case 3: //Oracle
//            {
//
//            }
            default:
            {
                System.out.println("Invalid Option !!!");
                break;
            }
        }
    }
}
