import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.assertEquals;

public class SavingAccountTest {
    private SavingAccount s1;
    private SavingAccount s2;
    private CheckingAccount ch1;
    private CheckingAccount ch2;
    private Customer c1;
    private Customer c2;


    @Before
    public void setup()
    {
        s1=new SavingAccount(2000L,2.5);
        c1=new Customer("Imran Khan","Islamabad","+9251123456",s1);

        CheckingAccount ch3=new CheckingAccount(1500);
        Customer c2=new Customer("Amir Ahmed","Rawalpindi","+9287456123",ch3);
    }

    @Test
    //Testing for Valid Withdrawal Amount
    public void Valid_makeWithdrawal() {
        c1.getSavingBankAccount().makeWithdrawal(500);
        assertEquals(1500L,c1.getSavingBankAccount().getBalance());
    }

    //Testing for Negative Withdrawal Amount
    //Testing for Valid Withdrawal Amount
   @Test
    public void inValid_makeWithdrawal() {
        //in this case no transactions will be performed
        c1.getSavingBankAccount().makeWithdrawal(-500);
        assertEquals(2000L,c1.getSavingBankAccount().getBalance());
    }

    @Test
    public void inValid_makeWithdrawal_GreaterThenCurrentBalance() {
        //in this case no transactions will be performed
        c1.getSavingBankAccount().makeWithdrawal(2500000L);
        assertEquals(2000L,c1.getSavingBankAccount().getBalance());
    }


    @Test
    public void Valid_makeDeposit() {
        c1.getSavingBankAccount().makeDeposit(1500L);
        assertEquals(3500L,c1.getSavingBankAccount().getBalance());
    }

    @Test
    public void inValid_makeDeposit() {
        c1.getSavingBankAccount().makeDeposit(-1500L);
        assertEquals(2000L,c1.getSavingBankAccount().getBalance());
    }

    @Test
    public void calculateInterest() {
        c1.getSavingBankAccount().calculateInterest();
        assertEquals(5000L,c1.getSavingBankAccount().getInterestAmount());
    }

    @Test
    //if zakat apply balance greater then 20000PKR
    public void Valid_calculateZakat() {
        SavingAccount s3=new SavingAccount(500000L,2.5);
        Customer c3=new Customer("ABC","Islamabad","+92154789",s3);
        c3.getSavingBankAccount().calculateZakat();

        assertEquals(12500,c3.getSavingBankAccount().getZakatAmount());
    }

    @Test
    //if zakat apply balance less then 20000PKR
    public void InValid_CalculateZakat() {
        SavingAccount s3=new SavingAccount(1500L,2.5);
        Customer c3=new Customer("ABC","Islamabad","+92154789",s3);
        c3.getSavingBankAccount().calculateZakat();
        assertEquals(0,c3.getSavingBankAccount().getZakatAmount());
    }


    @Test
    public void setSavingAccountNumber() {
        CheckingAccount checkingAccTest=new CheckingAccount(16000L);
        Customer tempCustomer=new Customer("Amir Khan","Karachi","+920510394",checkingAccTest);
        SavingAccount s10=new SavingAccount(4500,2.5);
        tempCustomer.setSavingBankAccount(s10);
        assertEquals(s10,tempCustomer.getSavingBankAccount());

    }

    @Test
    //+ive case checking valid interest rate
    public void Valid_setInterestRate() {

        SavingAccount s10=new SavingAccount(4500,2.5);
        Customer tempCustomer=new Customer("Amir Khan","Karachi","+920510394",s10);
        tempCustomer.setSavingBankAccount(s10);
        assertEquals(2.5,tempCustomer.getSavingBankAccount().getInterestRate(),0.00);
    }
    //-ive case checking invalid interest rate

    @Test
    public void Invalid_setInterestRate() {

        SavingAccount s10=new SavingAccount(4500,-1.5);
        Customer tempCustomer=new Customer("Amir Khan","Karachi","+920510394",s10);
        tempCustomer.setSavingBankAccount(s10);
        assertEquals(0.00,tempCustomer.getSavingBankAccount().getInterestRate(),0.00);
    }

    /*
        From Saving To Saving
        From Saving To Checking
     */
    @Test
    public void transferAmount_Saving_to_Saving() {
        SavingAccount temp1=new SavingAccount(4500,1.5);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        acc_holder1.setSavingBankAccount(temp1);

        SavingAccount temp2=new SavingAccount(4500,1.5);
        Customer acc_holder2=new Customer("Amir Khan","Karachi","+920510394",temp2);


        acc_holder2.getAccountNumber();

        Vector<Customer> acc_holders=new Vector<>();
        acc_holders.add(acc_holder1);
        acc_holders.add(acc_holder2);


        //checking transfer from acc_holder1 to acc_holder2
        acc_holders=acc_holders.get(0).getSavingBankAccount().transferAmount(500, acc_holder2.getAccountNumber(),acc_holders,1);
        Assert.assertEquals(5000,acc_holder2.getSavingBankAccount().getBalance());
    }

    @Test
    public void transferAmount_Saving_to_Checking() {
        SavingAccount temp1=new SavingAccount(4500,1.5);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);
        acc_holder1.setSavingBankAccount(temp1);

        CheckingAccount temp2=new CheckingAccount(4500);
        Customer acc_holder2=new Customer("Amir Khan","Karachi","+920510394",temp2);


        acc_holder2.getAccountNumber();

        Vector<Customer> acc_holders=new Vector<>();
        acc_holders.add(acc_holder1);
        acc_holders.add(acc_holder2);

        //checking transfer from acc_holder1 to acc_holder2
        acc_holders=acc_holders.get(0).getSavingBankAccount().transferAmount(500, acc_holder2.getAccountNumber(),acc_holders,0);
        Assert.assertEquals(5000,acc_holder2.getCheckingBankAccount().getBalance());
    }
}