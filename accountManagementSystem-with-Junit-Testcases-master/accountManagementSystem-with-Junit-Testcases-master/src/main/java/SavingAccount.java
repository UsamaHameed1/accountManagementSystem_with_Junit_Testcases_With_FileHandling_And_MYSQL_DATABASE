import java.util.Vector;

public class SavingAccount extends BankAccount {

    //Data Members
    private double interestRate;
    private long interestAmount;
    final private int interestPeriod;
    private long zakatAmount;

    //Parameterized Constructor
    public SavingAccount(long balance, double interestRate) {
        super(balance);
        this.setInterestRate(interestRate);
        this.interestAmount = 0L;
        this.interestPeriod = 1; //-- 1 YEAR
        this.zakatAmount = 0L;
    }


    // Functionality Methods

    //1. Make Withdrawal
    public void makeWithdrawal(long withdrawalAmount)
    {
        if(withdrawalAmount >0)
        {
            if(super.getBalance() < withdrawalAmount)
            {
                System.out.println("Error Withdrawal Amount is Greater then the Current Account Balance ");
                System.out.println("Your Current Balance is : " +super.getBalance());
                System.out.println("Insufficient Funds");
            }
            else
            {
                super.makeWithdrawal(withdrawalAmount);
            }
        }
        else
        {
            System.out.println("Invalid Input for Withdrawal");
        }

    }

    //2. Make Deposit
    public void makeDeposit(long depositAmount)
    {
        if(depositAmount < 0 )
        {
            System.out.println("Error Invalid Input Deposit !!! Try Again");
        }
        else
        {
            super.makeDeposit(depositAmount);
        }

    }


    //3. Calculate Interest
    public void calculateInterest()
    {
        this.interestAmount =(long) (super.getBalance()*(this.interestPeriod*this.interestRate));
    }

    //4. Calculate Zakat
    public void calculateZakat()
    {
        if(super.getBalance() >=20000L)
        {
            this.zakatAmount =(long)(super.getBalance() *2.5)/100L;
            super.setAccountBalance(this.zakatAmount,true);

            Transaction tempObject =new Transaction(super.getAccountNumber(),super.Get_Current_Date(),"Zakat Deduction",this.zakatAmount,super.getBalance());
            super.getDeduction_List().add(tempObject);
        }
        else
        {
            System.out.println("Your Current Balance is : "+this.getBalance()+ "Your Account doesn't meet the requirements for the zakat calculations");
        }
    }


    //3. Print Bank Statement of Saving Account
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
        System.out.println("\n=========== Display All Deductions ===========");
        System.out.println("Account Number : "+super.getAccountNumber());
        System.out.println("\n------------------------------------------------------------------------------------------------------------");
        System.out.println("Account Number\tDate & Time\t\t\t\tDeduction Type\t\tDeduction Amount\t\t\tRemaining Balance");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Transaction object: super.getDeduction_List())
        {
            object.displayTransaction();
        }
        System.out.println("-----------------------------------------------\n");
    }

    public void setSavingAccountNumber(String accountNumber)
    {
        super.setAccountNumber(accountNumber);
    }

    public void setInterestRate(double interestRate) {
        if(interestRate <0)
        {
            this.interestRate=0.00;
        }
        else
        {
            this.interestRate = interestRate;
        }
    }

    //5.Transfer Amount
    public Vector<Customer> transferAmount(long transferAmount, String recipient_account, Vector<Customer> Account_Holders, int Account_Type_Index)
    {
        /*
        Account Type Index=0 For Checking Account
         Account_Type_Index =1 For Saving Account
         */

        if (transferAmount > this.getBalance()) {
            System.out.println("\n=================================================================");
            System.out.println("-->Transfer From Saving Account To Saving Account UnSuccessful Due To Insufficient Funds");
            System.out.println("Your Current Balance is : " + this.getBalance());
            System.out.println("Kindly Increase Your Balance To Make A Fund Transfer ThankYou !!!! ");
            System.out.println("=================================================================");
            return null;
        }


        int recipientAccountIndex = -1;

        for (int i = 0; i < Account_Holders.size(); i++) {
            if (recipient_account.equals(Account_Holders.get(i).getAccountNumber())) {
                recipientAccountIndex = i;
            }
        }

        if (recipientAccountIndex == -1) {
            System.out.println("Recipient Account Not Found in the System");
            System.out.println("Quiting the System .....");
        }


        if (Account_Type_Index == 0)   //saving to checking account transfer
        {
            Customer Temp_Recipient_Acc = Account_Holders.get(recipientAccountIndex);
            Account_Holders.remove(recipientAccountIndex);

            Temp_Recipient_Acc.getCheckingBankAccount().transferAddBalance(transferAmount);
            this.transferMinusBalance(transferAmount);

            Account_Holders.add(recipientAccountIndex, Temp_Recipient_Acc);
            System.out.println("\n=================================================================");
            System.out.println("-->Transfer From Saving Account To Checking Account Successful ");
            System.out.println("Your New Balance is : " + this.getBalance());
            System.out.println("=================================================================");

            return Account_Holders;
        } else if (Account_Type_Index == 1)
        {
            Customer Temp_Recipient_Acc = Account_Holders.get(recipientAccountIndex);
            Account_Holders.remove(recipientAccountIndex);

            Temp_Recipient_Acc.getSavingBankAccount().transferAddBalance(transferAmount);
            this.transferMinusBalance(transferAmount);

            Account_Holders.add(recipientAccountIndex, Temp_Recipient_Acc);
            System.out.println("\n=================================================================");
            System.out.println("-->Transfer From Saving Account To Saving Account Successful ");
            System.out.println("Your New Balance is : " + this.getBalance());
            System.out.println("=================================================================");
            return Account_Holders;
        }
        return  null;
    }

    public long getInterestAmount() {
        return interestAmount;
    }

    public long getZakatAmount() {
        return zakatAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

}
