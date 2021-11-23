import Databases.storingData_Menu;

import java.util.Scanner;
import java.util.Vector;

public class Main_Menu {

    //1.Account Creation Menu

    public static Vector<Customer>  Account_Creation_MENU(Vector<Customer> Account_Holder_List) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===========================================================================================");
        System.out.println("\t\t\t\t ACCOUNT CREATION MENU ");

        System.out.println("Welcome To Account Creation Menu");
        System.out.println("Which Type of Account You Want To Create ?");

        int choice = -1;
        do {
            System.out.println("1. Saving Account");
            System.out.println("2. Checking Account ");
            System.out.println("3. New Checking Account Over Saving Account");
            System.out.println("4. New Saving Account Over Checking Account");

            System.out.print("\nEnter Your Choice: ");
            choice = sc.nextInt();
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);


        // 1 to create saving account
        //2 to create checking account

        System.out.println();
        System.out.println();

        switch (choice)
        {
            case 1: //for creating a saving account only
            {
                System.out.println("\n*************************** Saving Account Creation Menu *****************************************");
                sc.nextLine();
                System.out.println("Saving Account Creation Menu\n");

                System.out.print("Please Enter Your Name: ");
                String Temp_Name = sc.nextLine();

                System.out.print("Please Enter Your Address: ");
                String Temp_Address = sc.nextLine();

                System.out.print("Please Enter Your Phone Number: ");
                String Temp_Phone_No = sc.nextLine();

                System.out.print("Enter The Account Balance: ");
                long temp_balance = sc.nextLong();
                if(temp_balance <0)
                {
                    return null;
                }

                SavingAccount savingAcc=new SavingAccount(temp_balance,1.25);
                Customer customerSaving=new Customer(Temp_Name,Temp_Address,Temp_Phone_No,savingAcc);

                customerSaving.displayCustomerDetails();
                Account_Holder_List.add(customerSaving);

                storingData_Menu.Store_Data_Menu(Temp_Name,Temp_Address, savingAcc.getAccountNumber(), temp_balance,Temp_Phone_No,2);

                return Account_Holder_List;
            }
            case 2:
            {
                    System.out.println("\n*************************** Checking Account Creation Menu *****************************************");
                    sc.nextLine();
                    System.out.println("Checking Account Creation Menu\n");

                    System.out.print("Please Enter Your Name: ");
                    String Temp_Name = sc.nextLine();

                    System.out.print("Please Enter Your Address: ");
                    String Temp_Address = sc.nextLine();

                    System.out.print("Please Enter Your Phone Number: ");
                    String Temp_Phone_No = sc.nextLine();

                    System.out.print("Enter The Account Balance: ");
                    long temp_balance = sc.nextLong();

                    if(temp_balance <0)
                    {
                        return null;
                    }
                    CheckingAccount checkingAcc=new CheckingAccount(temp_balance);
                    Customer customerChecking=new Customer(Temp_Name,Temp_Address,Temp_Phone_No,checkingAcc);

                    customerChecking.displayCustomerDetails();
                    Account_Holder_List.add(customerChecking);

                            //------------------------------------- Where to Store Data ----------------------------------------------------------------
                    storingData_Menu.Store_Data_Menu(Temp_Name,Temp_Address, customerChecking.getAccountNumber(), temp_balance,Temp_Phone_No,1);


                    return Account_Holder_List;
            }
            case 3:
            {
                sc.nextLine();
                System.out.println("\n*************************** New Checking Account on Existing Saving Creation Menu *****************************************");
                System.out.print("Please Enter Your Saving Account Number: ");
                String Temp_Acc_Number = sc.nextLine();
                Temp_Acc_Number ="ABL-"+Temp_Acc_Number;



                for (int i = 0; i < Account_Holder_List.size(); i++) {
                    if(Temp_Acc_Number.equals(Account_Holder_List.get(i).getAccountNumber()))
                    {
                        if(Account_Holder_List.get(i).getSavingBankAccount() !=null)
                        {
                            if(Account_Holder_List.get(i).getCheckingBankAccount() ==null)
                            {
                                System.out.print("Enter The Account Balance: ");
                                long temp_balance = sc.nextLong();

                                CheckingAccount checkingAcc=new CheckingAccount(temp_balance);
                                Account_Holder_List.get(i).setCheckingBankAccount(checkingAcc);

                                storingData_Menu.Store_Data_Menu(Account_Holder_List.get(i).getName(),
                                        Account_Holder_List.get(i).getAddress(),
                                        Account_Holder_List.get(i).getSavingBankAccount().getAccountNumber(),
                                        temp_balance,
                                        Account_Holder_List.get(i).getPhoneNumber(),1);

                                return Account_Holder_List;
                            }
                            else
                            {
                                System.out.println("Error Checking Account Already Exist");
                            }
                        }
                        else
                        {
                            System.out.println("Error : Saving Account Doesn't Exist");
                            return null;
                        }

                    }
                }
                return Account_Holder_List;
            }
            case 4:
            {

                sc.nextLine();
                System.out.println("\n*************************** New Saving Account on Existing Checking Creation Menu *****************************************");
                System.out.print("Please Enter Your Checking Account Number: ");
                String Temp_Acc_Number = sc.nextLine();
                Temp_Acc_Number ="ABL-"+Temp_Acc_Number;


                for (int i = 0; i < Account_Holder_List.size(); i++) {
                    if(Temp_Acc_Number.equals(Account_Holder_List.get(i).getAccountNumber()))
                    {
                        if(Account_Holder_List.get(i).getCheckingBankAccount() !=null)
                        {
                            if(Account_Holder_List.get(i).getSavingBankAccount() ==null)
                            {
                                System.out.print("Enter The Account Balance: ");
                                long temp_balance = sc.nextLong();

                                SavingAccount savingAccount=new SavingAccount(temp_balance,1.25);
                                Account_Holder_List.get(i).setSavingBankAccount(savingAccount);

                                storingData_Menu.Store_Data_Menu(Account_Holder_List.get(i).getName(),
                                        Account_Holder_List.get(i).getAddress(),
                                        Account_Holder_List.get(i).getSavingBankAccount().getAccountNumber(),
                                        temp_balance,
                                        Account_Holder_List.get(i).getPhoneNumber(),2);

                                return Account_Holder_List;
                            }
                            else
                            {
                                System.out.println("Error Saving Account Already Exist");
                            }
                        }
                        else
                        {
                            System.out.println("Error : Checking Account Doesn't Exist");
                            return null;
                        }

                    }
                }
            }
        }
        return null;
    }

    //2. Close An Account
    public static Vector<Customer> Close_An_Account(Vector<Customer>Account_Holder_List) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==>Account Delete Menu");
        System.out.print("Please Enter The Account Number : ");
        String Account_ID = sc.nextLine();
        Account_ID ="ABL-"+Account_ID;

        for (int i = 0; i < Account_Holder_List.size(); i++) {
            if(Account_ID.equals(Account_Holder_List.get(i).getAccountNumber()))
            {
                System.out.println("********** Checking And Saving Account Deletion");
                System.out.println("The Account Has Both Checking and Saving Accounts");

                System.out.println("Select Option According To Delete An Account");
                System.out.println("1. Delete Checking Account Only");
                System.out.println("2. Delete Saving Account Only");
                System.out.println("3. Delete Both Checking Account and Saving Account");

                System.out.print("Enter Your Choice : ");

                int choice_opt= sc.nextInt();
                switch (choice_opt)
                {
                    case 1:
                    {
                        Account_Holder_List.get(i).closeCheckingAccount();
                        System.out.println("Checking Account Delete Successfully");
                        return Account_Holder_List;
                    }
                    case 2:
                    {
                        Account_Holder_List.get(i).closeSavingAccount();
                        System.out.println("Saving Account Delete Successfully");
                        return Account_Holder_List;
                    }
                    case 3:
                    {
                        Account_Holder_List.remove(i);
                        System.out.println("Both Checking and Saving Accounts Are Deleted Successfully");
                        return Account_Holder_List;
                    }
                }
            }
        }

        System.out.println("The Account Number You Entered In Not a Valid Account Number");
        return  null;
    }

    public static Vector<Customer> Perform_Accout_Operations(Vector<Customer> Account_Holder_List) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===========================================================================================");
        System.out.println("\t\t\t Accout Operations Menu");
        System.out.print("Pleas Enter The Accout Number : ");
        String accountNumber = sc.nextLine();
        accountNumber ="ABL-"+accountNumber;

        int accountHolderIndex=-1;

        for (int i = 0; i <Account_Holder_List.size() ; i++) {
            if (accountNumber.equals(Account_Holder_List.get(i).getAccountNumber())) {
                accountHolderIndex = i;
            }
        }
        if(accountHolderIndex==-1)
            {
                System.out.println("Error : Founding the Account Number");
                return null;
            }

            System.out.println("\n\t\t\tVerification Success......\n");
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("Inorder To Continue You Must Have to Decide on What Accout Type You Want to Do A Accout Operation");
            System.out.println("1. Checking Account");
            System.out.println("2. Saving Account");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    ///Checking Account Operations
                    if (Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount() != null) {
                        System.out.println("\n\t\t\tChecking Account Operations Menu");
                        System.out.println("1. Make Deposit");
                        System.out.println("2. Make Withdraw");
                        System.out.println("3. Check Balance");
                        System.out.println("4. Print Statement");
                        System.out.println("5. Transfer Amount");
                        System.out.println("6. Display All Deductions");

                        System.out.print("\nEnter Your Choice : ");
                        int choice_opt = sc.nextInt();

                        switch (choice_opt) {
                            case 1: {
                                /*
                                All the code will work for only the checking account types only
                                 */
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Deposit Menu");
                                System.out.print("-->Please Enter The Deposit Amount: ");
                                long deposit_amount = sc.nextLong();
                                if(deposit_amount <0)
                                {
                                    System.out.println("Error In the Input of The Deposit Amount !!!");
                                    System.out.println("Changing Deposit Amount to 0 in order to cancel the deposit Amount");
                                }

                                Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().makeDeposit(deposit_amount);
                                System.out.println("New Balance : "+Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().getBalance());
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;

                            }
                            case 2: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Withdraw Menu");
                                System.out.print("-->Please Enter The Withdrawal Amount: ");
                                long withdraw_amount = sc.nextLong();
                                if(withdraw_amount <0)
                                {
                                    System.out.println("Error In the Input of The Withdrawal Amount !!!");
                                    System.out.println("Changing Withdrawal Amount to 0 in order to cancel the deposit Amount");
                                }

                                Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().makeWithdrawal(withdraw_amount);
                                System.out.println("New Balance : "+Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().getBalance());
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;
                            }
                            case 3: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Check Balance Menu");

                                System.out.println("Your Current Balance is : "+Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().getBalance());
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;
                            }
                            case 4: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Print Bank Statement Menu");

                                Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().printStatement();
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;
                            }
                            case 5: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Account Transfer Menu");

                                System.out.println("Inorder To Continue You Must Have to Decide on What Accout Type You Want to Do A Accout Operation");
                                System.out.println("1. Checking Account");
                                System.out.println("2. Saving Account");

                                sc.nextLine();
                                int accountTransferChoice = -1;
                                do {

                                    System.out.print("Enter Your Choice : ");
                                    accountTransferChoice = sc.nextInt();
                                } while (accountTransferChoice != 1 && accountTransferChoice != 2);
                                sc.nextLine();
                                System.out.print("Enter Recipient Accout : ");
                                String recipientAccoutNUmber = sc.nextLine();
                                recipientAccoutNUmber ="ABL-"+recipientAccoutNUmber;

                                System.out.print("--> Please Enter The Transfer Amount : ");
                                long transfer_Amount = sc.nextLong();

                                Account_Holder_List =Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().transferAmount(transfer_Amount,recipientAccoutNUmber,Account_Holder_List,accountTransferChoice-1);
                                return Account_Holder_List;
                            }
                            case 6: {
                                System.out.println("===========================================================================================");
                                System.out.println("\t\t\t Deduction Menu");

                                Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().displayAllDeductions();
                                System.out.println("===========================================================================================");
                                return Account_Holder_List;
                            }
                            default: {
                                System.out.println("Invalid Option Selected");
                                return null;
                            }
                        }
                    } else if (Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount() == null) {
                        System.out.println("Error Account Not Founded");
                        return null;
                    }
                }
                case 2: {
                    /*
                    All the code will work only for the saving account type only
                     */
                    if (Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount() != null) {
                        System.out.println("\n\t\t\tSaving Account Operations Menu");
                        System.out.println("1. Make Deposit");
                        System.out.println("2. Make Withdraw");
                        System.out.println("3. Check Balance");
                        System.out.println("4. Print Statement");
                        System.out.println("5. Transfer Amount");
                        System.out.println("7. Calculate Zakat");
                        System.out.println("8. Calculate Interest");


                        System.out.print("\nEnter Your Choice : ");
                        int choice_opt = sc.nextInt();

                        switch (choice_opt) {
                            case 1: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Deposit Menu");
                                System.out.print("-->Please Enter The Deposit Amount: ");
                                long deposit_amount = sc.nextLong();
                                if(deposit_amount <0)
                                {
                                    System.out.println("Error In the Input of The Deposit Amount !!!");
                                    System.out.println("Changing Deposit Amount to 0 in order to cancel the deposit Amount");
                                }

                                Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().makeDeposit(deposit_amount);
                                System.out.println("New Balance : "+Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().getBalance());
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;

                            }
                            case 2: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Withdraw Menu");
                                System.out.print("-->Please Enter The Withdrawal Amount: ");
                                long withdraw_amount = sc.nextLong();
                                if(withdraw_amount <0)
                                {
                                    System.out.println("Error In the Input of The Withdrawal Amount !!!");
                                    System.out.println("Changing Withdrawal Amount to 0 in order to cancel the deposit Amount");
                                }

                                Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().makeWithdrawal(withdraw_amount);
                                System.out.println("New Balance : "+Account_Holder_List.get(accountHolderIndex).getCheckingBankAccount().getBalance());
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;
                            }
                            case 3: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Check Balance Menu");

                                System.out.println("Your Current Account Balance is : "+Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().getBalance());
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;
                            }
                            case 4: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Print Bank Statement Menu");

                                Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().printStatement();
                                System.out.println("=========================================================================================");
                                return Account_Holder_List;
                            }
                            case 5: {
                                System.out.println("=========================================================================================");
                                System.out.println("\n\t\t\t Account Transfer Menu");

                                System.out.println("Inorder To Continue You Must Have to Decide on What Accout Type You Want to Do A Accout Operation");
                                System.out.println("1. Checking Account");
                                System.out.println("2. Saving Account");

                                sc.nextLine();
                                int accountTransferChoice = -1;
                                do {

                                    System.out.print("Enter Your Choice : ");
                                    accountTransferChoice = sc.nextInt();
                                } while (accountTransferChoice != 1 && accountTransferChoice != 2);
                                sc.nextLine();
                                System.out.print("Enter Recipient Accout : ");
                                String recipientAccoutNUmber = sc.nextLine();
                                recipientAccoutNUmber ="ABL-"+recipientAccoutNUmber;

                                System.out.print("--> Please Enter The Transfer Amount : ");
                                long transfer_Amount = sc.nextLong();

                                Account_Holder_List =Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().transferAmount(transfer_Amount,recipientAccoutNUmber,Account_Holder_List,accountTransferChoice-1);
                                return Account_Holder_List;
                            }
                            case 6: {
                                System.out.println("===========================================================================================");
                                System.out.println("\t\t\t Deduction Menu");

                                Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().displayAllDeductions();
                                System.out.println("===========================================================================================");
                                return Account_Holder_List;
                            }
                            case 7:
                            {
                                System.out.println("===========================================================================================");
                                System.out.println("\t\t\t Zakat Calculation Menu");
                                Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().calculateZakat();
                                System.out.println("Your Current Balance After Zakat Deduction is : "+Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().getBalance());
                                System.out.println("===========================================================================================");
                                return Account_Holder_List;
                            }
                            case 8:
                            {
                                System.out.println("===========================================================================================");
                                System.out.println("\t\t\t Interest Calculation Menu");
                                Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().calculateInterest();
                                if(Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().getBalance() > 20000)
                                {
                                    System.out.println("Zakat Deduction is not Applicable to tHis Account....");
                                    System.out.println("Your Current Balance is : "+Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().getBalance());
                                }
                                else
                                {
                                    System.out.println("Your Current Balance After Interest Calculation is : "+Account_Holder_List.get(accountHolderIndex).getSavingBankAccount().getBalance());
                                    System.out.println("===========================================================================================");
                                }

                                return Account_Holder_List;
                            }
                            default: {
                                System.out.println("Invalid Option Selected");
                                return null;
                            }
                        }
                    } else if (Account_Holder_List.get(accountHolderIndex).getSavingBankAccount() == null) {
                        System.out.println("Error Account Not Founded");
                        return null;
                    }
                }
            }

        return null;
    }


    public static void Display_All_Accounts(Vector<Customer> Account_Holder_List) {
        System.out
                .println("===========================================================================================");
        System.out.println("\t\t\tBANK DETAILS");
        System.out.println("Bank Name: ABL LTD");
        System.out.println("Bank Owner Name: ABC ABC");
        System.out.println("===========================================================================================");

        System.out.println("===========================================================================================");
        System.out.println("\t\t\tAccount Details");
        for (Customer accountHolder : Account_Holder_List) {
            accountHolder.displayCustomerDetails();
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }



    public static void Display_All_Deductions(Vector<Customer> Account_Holder_List) {
        System.out.println(
                "=========================================== Display All The Deductions =================================");

        System.out.println("=====> Displaying All The Check Accounts");
        for (Customer accountHolder : Account_Holder_List) {
            if (accountHolder.getCheckingBankAccount() !=null) {
                accountHolder.getCheckingBankAccount().displayAllDeductions();

                System.out.println();
                System.out.println("******************************************************************************************");
            }
        }

        System.out.println();

        System.out.println("=====> Displaying All The Saving Accounts");
        for (Customer accountHolder : Account_Holder_List) {
            if (accountHolder.getSavingBankAccount() !=null) {
                accountHolder.getSavingBankAccount().displayAllDeductions();

                System.out.println();
                System.out.println(
                        "******************************************************************************************");
            }
        }

    }

    public static Vector<Customer> Specify_Interest_Rate(Vector<Customer> Account_Holder_List) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "=============================================================================================");
        System.out.println("\t\t\tSpecify The Interest Rate Menu");

        System.out.print("Enter The Interest Rate : ");
        double interestRate = sc.nextDouble();

        for (Customer Acc_Holders : Account_Holder_List) {
            Acc_Holders.getSavingBankAccount().setInterestRate(interestRate);
        }
        System.out.println(
                "=============================================================================================");
        return Account_Holder_List;
    }

    public static void MAIN_MENU(Vector<Customer> Account_Holder_List) {
        boolean quit_check = true;
        do {

            Scanner sc = new Scanner(System.in);
            System.out.println("===========================================================================================");
            System.out.println("\t\t\t\t MAIN MENU ");
            System.out.println("1. Open a New Account");
            System.out.println("2. Close a Account");
            System.out.println("3. Perform Account Operations");
            System.out.println("4. Set The Interest Rate to All Saving Accounts");
            System.out.println("5. Display All The Account Details Including Bank Owner Details");
            System.out.println("6. Display All Accounts Deductions along with Account Details");
            System.out.println("7. Quit The Program");


            System.out.print("\nEnter Your Choice : ");
            int choice = sc.nextInt();

            System.out.println("===========================================================================================\n");
            switch (choice) {
                case 1: {
                    Account_Holder_List = Account_Creation_MENU(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Error in Adding A New Account Holder");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Account Added Successfully");
                        System.out.println("===========================================================================================\n");
                    }

                    break;
                }

                case 2: {
                    Account_Holder_List = Close_An_Account(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Error in Closing A New Account Holder");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Account Closed Successfully");
                        System.out.println("===========================================================================================\n");
                    }
                    break;
                }

                case 3: {
                    Account_Holder_List = Perform_Accout_Operations(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Error in Performing A Operation on Account ");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Operation Performed Successfully");
                        System.out.println("===========================================================================================\n");
                    }
                    break;
                }

                case 4: {
                    Account_Holder_List = Specify_Interest_Rate(Account_Holder_List);
                    if (Account_Holder_List == null) {
                        System.out.println("===========================================================================================\n");
                        System.out.println("Interest Rate Set:  Unsuccessful ");
                        System.exit(0);
                        System.out.println("===========================================================================================\n");
                    } else {
                        System.out.println("===========================================================================================");
                        System.out.println("Interest Rate Set: Successful");
                        System.out.println("===========================================================================================\n");
                    }
                    break;
                }

                case 5: {
                    Display_All_Accounts(Account_Holder_List);
                    break;
                }
                case 6: {
                    Display_All_Deductions(Account_Holder_List);
                    break;
                }
                case 7: {
                    System.out.println("Thank You For Using The Program 😀😀");
                    quit_check = false;
                    break;
                }


                default: {
                    System.out.println("Invalid Option Selected");
                }
            }
        }while (quit_check) ;
    }
}
