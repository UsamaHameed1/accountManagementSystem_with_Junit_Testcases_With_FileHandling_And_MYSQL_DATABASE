import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class Main_MenuTest {
    Vector<Customer> Account_Holder;

    @Before
    public void setup()
    {
        Account_Holder=new Vector<>();
    }

    @Test
    //This method will test the creation of saving accounts check if the account is added or not
    public void Test_Create_SavingAccount() {
        String testInputs ="1 \nAnwar \nRawalpindi\n+9254055222\n1500000";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);
        Main_Menu.Account_Creation_MENU(Account_Holder);
        //Testing whether the method has entered the account object in the record or not
        assertEquals(1500000,Account_Holder.get(0).getSavingBankAccount().getBalance());
        System.setIn(sysInBackup);
    }


    @Test
    //We will try to create an account with negative balance and check.
    //The method will give us a null to tell us that we have given some input that is not accpetable by our system. Kindly restart the sytem
    public void Test_Create_SavingAccount_NegativeBalance() {
        String testInputs ="1 \nAnwar \nRawalpindi\n+9254055222\n-1500000";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);


        //Testing whether the method has entered the account object in the record or not
        assertEquals(null, Main_Menu.Account_Creation_MENU(Account_Holder));
        System.setIn(sysInBackup);
    }


    @Test
    //We will create a Checking account
    public void Test_Create_CheckingAccount() {
        String testInputs ="2 \nAnwar \nRawalpindi\n+9254055222\n1500000";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);

        Main_Menu.Account_Creation_MENU(Account_Holder);
        //Testing whether the method has entered the account object in the record or not
        assertEquals(1500000,Account_Holder.get(0).getCheckingBankAccount().getBalance());
        System.setIn(sysInBackup);
    }

    @Test
    //We will try to create an account with negative balance and check.
    //The method will give us a null to tell us that we have given some input that is not acceptable by our system. Kindly restart the system
    public void Test_Create_CheckingAccount_NegativeBalance() {
        String testInputs ="2 \nAnwar \nRawalpindi\n+9254055222\n-1500000";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);

        assertEquals(null, Main_Menu.Account_Creation_MENU(Account_Holder));
        System.setIn(sysInBackup);
    }


    @Test
    public void close_An_SavingAccount() {
        SavingAccount temp1=new SavingAccount(4500,1.5);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        Account_Holder.add(acc_holder1);

        String tempAccNo=acc_holder1.getAccountNumber();
        tempAccNo=tempAccNo.substring(4,tempAccNo.length());

        String testInputs =tempAccNo+"\n2\n";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);

        Account_Holder=Main_Menu.Close_An_Account(Account_Holder);

        assertEquals(null, Account_Holder.get(0).getSavingBankAccount());
        System.setIn(sysInBackup);
    }

    @Test
    public void close_An_CheckingAccount() {
        CheckingAccount temp1=new CheckingAccount(4500);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        Account_Holder.add(acc_holder1);

        String tempAccNo=acc_holder1.getAccountNumber();
        tempAccNo=tempAccNo.substring(4,tempAccNo.length());

        String testInputs =tempAccNo+"\n1\n";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);

        Main_Menu.Close_An_Account(Account_Holder);

        assertEquals(null, Account_Holder.get(0).getCheckingBankAccount());
        System.setIn(sysInBackup);
    }

    //Trying to close Not Exiting Checking Acount Invliad Case
    @Test
    public void Invalid_close_An_CheckingAccount() {
        CheckingAccount temp1=new CheckingAccount(4500);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        Account_Holder.add(acc_holder1);

        String tempAccNo="5006"; //---> Dummy Account Value To Close


        String testInputs =tempAccNo+"\n1\n";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);


        Account_Holder=Main_Menu.Close_An_Account(Account_Holder);
        assertEquals(null,Account_Holder);
        System.setIn(sysInBackup);
    }

    @Test
    public void Invalid_close_An_SavingAccount() {
        SavingAccount temp1=new SavingAccount(4500,1.5);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        Account_Holder.add(acc_holder1);

        String tempAccNo="50012";

        String testInputs =tempAccNo+"\n2\n";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);

        Account_Holder=Main_Menu.Close_An_Account(Account_Holder);

        assertEquals(null,Account_Holder);
        System.setIn(sysInBackup);
    }

    /// Testing For Closing Both The Accounts
    @Test
    public void closeBothAccounts()
    {
        CheckingAccount temp1=new CheckingAccount(4500);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        SavingAccount temps1=new SavingAccount(150000,2.5);
        acc_holder1.setSavingBankAccount(temps1);
        Account_Holder.add(acc_holder1);

        CheckingAccount temp2=new CheckingAccount(4500);
        Customer acc_holder2=new Customer("Amir Khan","Karachi","+920510394",temp2);
        SavingAccount temps2=new SavingAccount(150000,2.5);
        acc_holder1.setSavingBankAccount(temps1);
        Account_Holder.add(acc_holder2);

        String tempAccNo=acc_holder1.getAccountNumber();
        tempAccNo=tempAccNo.substring(4,tempAccNo.length());

        String testInputs =tempAccNo+"\n3\n";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);

        Account_Holder=Main_Menu.Close_An_Account(Account_Holder);
        boolean closedAccoutStatus=false;
        for(Customer obj:Account_Holder)
        {
            if(obj.getAccountNumber().equals("ABL-"+tempAccNo))
            {
                closedAccoutStatus=true;
            }
        }


        assertEquals(false, closedAccoutStatus);
        System.setIn(sysInBackup);
    }

    @Test
    public void InvalidcloseBothAccounts()
    {
        CheckingAccount temp1=new CheckingAccount(4500);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        SavingAccount temps1=new SavingAccount(150000,2.5);
        acc_holder1.setSavingBankAccount(temps1);
        Account_Holder.add(acc_holder1);

        String tempAccNo="4009";

        String testInputs =tempAccNo+"\n3\n";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);

        Account_Holder=Main_Menu.Close_An_Account(Account_Holder);

        assertEquals(null,Account_Holder);
        System.setIn(sysInBackup);
    }


//    @Test
//    public void perform_Accout_Operations() {
//    }
//
//    @Test
//    public void display_All_Accounts() {
//    }
//
//    @Test
//    public void display_All_Deductions() {
//    }
//
    @Test
    public void specify_Interest_Rate() {
        SavingAccount temp1=new SavingAccount(4500,1.5);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        Account_Holder.add(acc_holder1);

        String testInputs ="2.5";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);
        Account_Holder = Main_Menu.Specify_Interest_Rate(Account_Holder);

        assertEquals(2.5,Account_Holder.get(0).getSavingBankAccount().getInterestRate(),0.00);
    }
//
//    @Test
//    public void MAIN_MENU() {
//    }
}