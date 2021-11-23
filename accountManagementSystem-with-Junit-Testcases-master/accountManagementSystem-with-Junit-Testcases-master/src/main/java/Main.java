import java.util.Vector;

public class Main {
    public static void main(String[] args) {
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
        SavingAccount s4=new SavingAccount(500,2.5);
        SavingAccount s5=new SavingAccount(50000,2.5);
        SavingAccount s6=new SavingAccount(50,2.5);
        SavingAccount s7=new SavingAccount(150,2.5);
        SavingAccount s8=new SavingAccount(150000,2.5);
        SavingAccount s9=new SavingAccount(15000,2.5);
        SavingAccount s10=new SavingAccount(4500,2.5);


        CheckingAccount ch1=new CheckingAccount(500);
        CheckingAccount ch2=new CheckingAccount(50000);
        CheckingAccount ch3=new CheckingAccount(1500);
        CheckingAccount ch4=new CheckingAccount(150);
        CheckingAccount ch5=new CheckingAccount(200);
        CheckingAccount ch6=new CheckingAccount(300);
        CheckingAccount ch7=new CheckingAccount(600);
        CheckingAccount ch8=new CheckingAccount(700);
        CheckingAccount ch9=new CheckingAccount(900);
        CheckingAccount ch10=new CheckingAccount(1000);


        Customer c1=new Customer("Imran Khan","Islamabad","+9251123456",s1);
        Customer c2=new Customer("Amir Ahmed","Rawalpindi","+9287456123",s2);
        Customer c3=new Customer("Aslam Ahmed","Lahore","+9287456123",s3);
        Customer c4=new Customer("Arslan Ahmed","Karachi","+9287456123",s4);
        Customer c5=new Customer("Shohiab Ahmed","Karachi","+9287456123",s5);
        Customer c6=new Customer("Sarfaz Ahmed","Karachi","+9287456123",s6);
        Customer c7=new Customer("Baba Ahmed","Rawalpindi","+9287456123",s7);
        Customer c8=new Customer("Amir Ahmed","Lahore","+9287456123",s8);
        Customer c9=new Customer("Hafeez Ahmed","Islamabad","+9287456123",s9);
        Customer c10=new Customer("Hassan Ahmed","Islamabad","+9287456123",s10);

        c1.setCheckingBankAccount(ch1);
        c2.setCheckingBankAccount(ch2);
        c3.setCheckingBankAccount(ch3);
        c4.setCheckingBankAccount(ch4);
        c5.setCheckingBankAccount(ch5);
        c6.setCheckingBankAccount(ch6);
        c7.setCheckingBankAccount(ch7);
        c8.setCheckingBankAccount(ch8);
        c9.setCheckingBankAccount(ch9);
        c10.setCheckingBankAccount(ch10);


        Account_Holders.add(c1);
        Account_Holders.add(c2);
        Account_Holders.add(c3);
        Account_Holders.add(c4);
        Account_Holders.add(c5);
        Account_Holders.add(c6);
        Account_Holders.add(c7);
        Account_Holders.add(c8);
        Account_Holders.add(c9);
        Account_Holders.add(c10);


        CheckingAccount ch11=new CheckingAccount(1500000L);
        Customer c11=new Customer("Wasim Imran","Lahore","+9251874156",ch11);
        Account_Holders.add(c11);

        SavingAccount s11=new SavingAccount(150000L,1.25);
        c11.setSavingBankAccount(s11);


        //**************************************************************** BANK CUSTOMERS LIST *************************************************************************************


        RUN.START(Admin_Bank_List,Account_Holders);
    }
}
