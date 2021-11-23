import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Vector;

public class RUNTest {

    private SavingAccount s1;
    private SavingAccount s2;
    private SavingAccount s3;

    private CheckingAccount ch1;
    private CheckingAccount ch2;
    private CheckingAccount ch3;

    private Customer c1;
    private Customer c2;
    private Customer c3;

    Vector<AdminBank> Admin_Bank_List;
    Vector <Customer> Account_Holders;

    @Before
    public void setUp()
    {
        // Vector For Storing Customers in The Bank
        //**************************************************************** BANK ADMIN LIST *****************************************************************************************
        Vector<AdminBank> Admin_Bank_List =new Vector<>();
        AdminBank ad1=new AdminBank("admin","admin");
        AdminBank ad2=new AdminBank("abc","abc");
        AdminBank ad3=new AdminBank("riz123","qwerty");
        Admin_Bank_List.add(ad1);
        Admin_Bank_List.add(ad2);
        Admin_Bank_List.add(ad3);
        //**************************************************************** BANK ADMIN LIST *****************************************************************************************

        //**************************************************************** BANK CUSTOMERS LIST *************************************************************************************
        Vector <Customer> Account_Holders = new Vector<>();



        SavingAccount s1=new SavingAccount(100,2.5);
        SavingAccount s2=new SavingAccount(1500,2.5);
        SavingAccount s3=new SavingAccount(1500,2.5);



        CheckingAccount ch1=new CheckingAccount(500);
        CheckingAccount ch2=new CheckingAccount(50000);
        CheckingAccount ch3=new CheckingAccount(1500);



        Customer c1=new Customer("Imran Khan","Islamabad","+9251123456",s1);
        Customer c2=new Customer("Amir Ahmed","Rawalpindi","+9287456123",s2);
        Customer c3=new Customer("Aslam Ahmed","Lahore","+9287456123",s3);

        c1.setCheckingBankAccount(ch1);
        c2.setCheckingBankAccount(ch2);
        c3.setCheckingBankAccount(ch3);



        Account_Holders.add(c1);
        Account_Holders.add(c2);
        Account_Holders.add(c3);

    }
    @Test
    public void ADMIN_PANEL() {

        String testInputs ="1\nAdmin\nADMIN";
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(testInputs.getBytes());
        System.setIn(in);


        Vector<AdminBank> Admin_Bank_List =new Vector<>();
        AdminBank ad1=new AdminBank("admin","admin");
        AdminBank ad2=new AdminBank("abc","abc");
        AdminBank ad3=new AdminBank("riz123","qwerty");
        Admin_Bank_List.add(ad1);
        Admin_Bank_List.add(ad2);
        Admin_Bank_List.add(ad3);

        boolean flag=RUN.ADMIN_PANEL(Admin_Bank_List,Account_Holders);
        Assert.assertEquals(false,flag);        //-----> Checking if we enter the invalid admin then it should quit
    }

    @Test
    public void START() {
    }
}