import java.util.Vector;

public class CheckingAccount extends BankAccount {
    //Data Members
    final private long transactionFee;
    private int withdrawTransactionCount;
    private int depositTransactionCount;
    final private long maxWithdrawalAmountCheckingAcc; //max checking account holder can with draw money in case the want to with draw more money than account balance which is 5000


    //Parameterized Constructor
    public CheckingAccount(long balance) {
        super(balance);
        this.transactionFee = 20;
        this.withdrawTransactionCount = 2;
        this.depositTransactionCount = 2;
        this.maxWithdrawalAmountCheckingAcc = 5000L;
    }

    public void setCheckingAccountNumber(String accountNumber)
    {
        super.setAccountNumber(accountNumber);
    }

    //1. Make Withdraw()
    public void makeWithdrawal(long withdrawal_amount) {
        if(withdrawal_amount <0)
        {
            System.out.println("The Withdrawal Amount Cannot be Negative");
        }
        else
        {
            if (withdrawal_amount <= maxWithdrawalAmountCheckingAcc && withdrawTransactionCount > 0) {
                super.makeWithdrawal(withdrawal_amount);
                --this.withdrawTransactionCount;
            } else if (withdrawal_amount <= maxWithdrawalAmountCheckingAcc && withdrawTransactionCount == 0) {
                //withdraw amount +10
                super.makeWithdrawal(withdrawal_amount + transactionFee);
                Transaction tempObj = new Transaction(super.getAccountNumber(), super.Get_Current_Date(), "Withdrawal Fee", transactionFee, super.getBalance());
                super.getDeduction_List().add(tempObj);
            } else if (withdrawal_amount > maxWithdrawalAmountCheckingAcc && withdrawTransactionCount == 0) {
                System.out.println("Max Withdraw Limit for Checking Account is 5000PKR .... \nYou are Trying To with draw more amount than Bank Defaults ... \n Quiting System ....");
            } else if (withdrawal_amount > maxWithdrawalAmountCheckingAcc && withdrawTransactionCount > 0) {
                System.out.println("Max Withdraw Limit for Checking Account is 5000PKR .... \nYou are Trying To with draw more amount than Bank Defaults ... \n Quiting System ....");
            }
        }

    }

    //2. Make Deposit
    public void makeDeposit(long deposit_amount) {
     if(deposit_amount <0)
     {
         System.out.println("Deposit Amount Cannot be Less Then 0");
         System.out.println("Please Check Input");
     }
     else
     {
         if (depositTransactionCount > 0) {
             super.makeDeposit(deposit_amount);
             --this.depositTransactionCount;

             Transaction tempObj = new Transaction(super.getAccountNumber(), super.Get_Current_Date(), "Deposit Fee", 0, super.getBalance());
             super.getDeduction_List().add(tempObj);
         } else if ( depositTransactionCount == 0) {
             //deposit amount +10
             super.makeDeposit(deposit_amount-transactionFee);
             Transaction tempObj = new Transaction(super.getAccountNumber(), super.Get_Current_Date(), "Deposit Fee", transactionFee, super.getBalance());
             super.getDeduction_List().add(tempObj);
         }
     }

    }


    //3. Print Bank Statement of Checking Account
    public void printStatement()
    {
        System.out.println("====== Printing Bank Statement ====== ");
        System.out.println("Account ID : "+getAccountNumber());

        System.out.println("--------- Complete Bank Statement --------- ");
        System.out.println("\n------------------------------------------------------------------------------------------------------------");
        System.out.println("Account Number\tDate & Time\t\t\t\tTransaction Type\t\tTransaction Amount\t\t\tRemaining Balance");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Transaction object: super.getBankStatement())
        {
            object.displayTransaction();
        }

        System.out.println("-----------------------------------------------\n");

    }

    //4. Print Display All the Deductions
    public void displayAllDeductions()
    {
        System.out.println("=========== Display All Deductions ===========");
        System.out.println("Account Number : "+getBalance());

        System.out.println("Date\t\t\t\t\tType\t\t\t\tDeduction Type\tDeduction Amount\tRemaining Balance");
        System.out.println("-----------------------------------------------");
        for (Transaction object: super.getDeduction_List())
        {
            object.displayTransaction();
        }
        System.out.println("-----------------------------------------------\n");
    }

    //5.Transfer Amount
    public Vector<Customer> transferAmount(long transferAmount, String recipient_account, Vector<Customer> Account_Holders, int Account_Type_Index)
    {
        /*
        Account Type Index=0 For Checking Account
         Account_Type_Index =1 For Saving Account
         */

        if(transferAmount > this.getBalance() )
        {
            System.out.println("\n=================================================================");
            System.out.println("-->Transfer From Saving Account To Saving Account UnSuccessful Due To Insufficient Funds");
            System.out.println("Your Current Balance is : " + this.getBalance());
            System.out.println("Kindly Increase Your Balance To Make A Fund Transfer ThankYou !!!! ");
            System.out.println("=================================================================");
            return null;
        }


        int recipientAccountIndex=-1;

        for(int i=0; i<Account_Holders.size();i++)
        {
            if(recipient_account.equals(Account_Holders.get(i).getAccountNumber()))
            {
                recipientAccountIndex=i;
            }
        }

        if(recipientAccountIndex==-1 )
        {
            System.out.println("Recipent Account Not Found in the System");
            System.out.println("Quiting the System .....");
            return  null;
        }


        if(Account_Type_Index==0)   //saving to checking account transfer
        {
            Customer Temp_Recipient_Acc=Account_Holders.get(recipientAccountIndex);
            Account_Holders.remove(recipientAccountIndex);

            Temp_Recipient_Acc.getCheckingBankAccount().transferAddBalance(transferAmount);
            this.transferMinusBalance(transferAmount);

            Account_Holders.add(recipientAccountIndex,Temp_Recipient_Acc);
            System.out.println("\n=================================================================");
            System.out.println("-->Transfer From Saving Account To Checking Account Successful ");
            System.out.println("Your New Balance is : " + this.getBalance());
            System.out.println("=================================================================");

            return Account_Holders;
        }
        else if(Account_Type_Index==1)
        {
            Customer Temp_Recipient_Acc=Account_Holders.get(recipientAccountIndex);
            Account_Holders.remove(recipientAccountIndex);

            Temp_Recipient_Acc.getSavingBankAccount().transferAddBalance(transferAmount);
            this.transferMinusBalance(transferAmount);

            Account_Holders.add(recipientAccountIndex,Temp_Recipient_Acc);
            System.out.println("\n=================================================================");
            System.out.println("-->Transfer From Saving Account To Saving Account Successful ");
            System.out.println("Your New Balance is : " + this.getBalance());
            System.out.println("=================================================================");
            return Account_Holders;
        }
        return null;
    }

}