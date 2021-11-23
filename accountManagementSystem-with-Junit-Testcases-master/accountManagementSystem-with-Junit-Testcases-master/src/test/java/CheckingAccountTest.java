import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

public class CheckingAccountTest {

    CheckingAccount checkingTempAcc;
    Customer tempCustomer;
    SavingAccount savingTempAcc;

    @Before
    public void setUp()  {
        savingTempAcc =new SavingAccount(15000,2.5);
        checkingTempAcc =new CheckingAccount(20000L);
        tempCustomer=new Customer("Ali Khan","Rawalpindi","+92541236",savingTempAcc);
        tempCustomer.setCheckingBankAccount(checkingTempAcc);

    }

    @Test
    public void makeWithdrawal_Greater_Withdrawal_Limit() {
        tempCustomer.getCheckingBankAccount().makeWithdrawal(60000L);
        Assert.assertEquals(20000L,tempCustomer.getCheckingBankAccount().getBalance());
    }

    @Test
    public void makeWithdrawal_Valid_Withdrawal() {
        tempCustomer.getCheckingBankAccount().makeWithdrawal(5000L);
        Assert.assertEquals(15000L,tempCustomer.getCheckingBankAccount().getBalance());
    }

    @Test
    public void makeWithdrawal_Valid_Withdrawal_But_Negative_Val() {
        tempCustomer.getCheckingBankAccount().makeWithdrawal(-5000L);
        Assert.assertEquals(20000L,tempCustomer.getCheckingBankAccount().getBalance());
    }

    @Test
    public void CheckingWithdrawTax() {
        tempCustomer.getCheckingBankAccount().makeWithdrawal(500);
        tempCustomer.getCheckingBankAccount().makeWithdrawal(500);
        tempCustomer.getCheckingBankAccount().makeWithdrawal(500);
        Assert.assertEquals(18480,tempCustomer.getCheckingBankAccount().getBalance());
    }


    @Test
    public void Valid_makeDeposit() {
        tempCustomer.getCheckingBankAccount().makeDeposit(5000L);
        Assert.assertEquals(25000L,tempCustomer.getCheckingBankAccount().getBalance());
    }

    @Test
    public void inValid_makeDeposit() {
        tempCustomer.getCheckingBankAccount().makeDeposit(-5000L);
        Assert.assertEquals(20000L,tempCustomer.getCheckingBankAccount().getBalance());
    }
    @Test
    public void CheckingDepositTax() {
        tempCustomer.getCheckingBankAccount().makeDeposit(500);
        tempCustomer.getCheckingBankAccount().makeDeposit(500);
        tempCustomer.getCheckingBankAccount().makeDeposit(500);
        Assert.assertEquals(21480,tempCustomer.getCheckingBankAccount().getBalance());
    }

    /*
    Transfer Test Cases Will Have 2 Sub Suitituations
    1. Transfer From Checking to Checking
    2.Transfer From Checking to Saving
     */


    @Test
    public void transferAmount_Checking_to_Saving() {
       CheckingAccount tempCheckingAcc=new CheckingAccount(1500);
       Customer acc_holder1 =new Customer("Rizwan Khan","Rawalpindi","+9254000",tempCheckingAcc);

        SavingAccount temp1=new SavingAccount(4500,1.5);
        Customer acc_holder2=new Customer("Amir Khan","Karachi","+920510394",temp1);

        Vector<Customer> acc_holders=new Vector<>();
        acc_holders.add(acc_holder1);
        acc_holders.add(acc_holder2);


        //checking account transfer from acc_holder1 to acc_holder2
        acc_holders=acc_holders.get(0).getCheckingBankAccount().transferAmount(500, acc_holder2.getAccountNumber(),acc_holders,1);
        Assert.assertEquals(5000,acc_holder2.getSavingBankAccount().getBalance());

    }

    @Test
    public void transferAmount_Checking_to_Checking() {
        CheckingAccount temp1=new CheckingAccount(4500);
        Customer acc_holder1=new Customer("Amir Khan","Karachi","+920510394",temp1);


        CheckingAccount temp2=new CheckingAccount(1500);
        Customer acc_holder2=new Customer("Amir Khan","Karachi","+920510394",temp2);




        Vector<Customer> acc_holders=new Vector<>();
        acc_holders.add(acc_holder1);
        acc_holders.add(acc_holder2);

        //checking transfer from acc_holder1 to acc_holder2
        acc_holders=acc_holders.get(0).getCheckingBankAccount().transferAmount(500, acc_holder2.getAccountNumber(),acc_holders,0);
        Assert.assertEquals(2000,acc_holder2.getCheckingBankAccount().getBalance());
    }
}