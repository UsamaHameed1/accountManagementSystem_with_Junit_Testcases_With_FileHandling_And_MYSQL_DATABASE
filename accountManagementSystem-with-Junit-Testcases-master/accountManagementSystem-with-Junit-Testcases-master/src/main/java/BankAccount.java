import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public abstract class BankAccount {
    private String accountNumber;
    private String dateCreatedAccount;
    private long balance;
    private Vector<Transaction> BankStatement;
    private Vector<Transaction> Deduction_List;


    BankAccount(long balance)
    {
        this.accountNumber="";
        this.dateCreatedAccount=Get_Current_Date();
        this.balance=balance;
        BankStatement =new Vector<Transaction>();
        Deduction_List =new Vector<Transaction>();
    }

    //---- Getters
    //    Get Account Number will return the account number of the account holder
    public String getAccountNumber() {
        return accountNumber;
    }

    public Vector<Transaction> getBankStatement() {
        return BankStatement;
    }

    public Vector<Transaction> getDeduction_List() {
        return Deduction_List;
    }

    //*****************************************************   Functionality Methods *******************************************************************
    //1.Get Balance Method will return the current balance of the account holder
    public long getBalance() {
        return balance;
    }

    //2.Make Deposit()
    //Implementation of the Make Deposit Method
    public void makeDeposit(long depositAmount)
    {
            balance +=depositAmount;
            String currentTime=Get_Current_Date();

            Transaction tempObject=new Transaction(this.accountNumber,currentTime,"Deposit",depositAmount,this.getBalance());
            BankStatement.add(tempObject);

    }

    //3.Make Withdrawal()
    public  void makeWithdrawal(long withdrawalAmount)
    {
        this.balance -=withdrawalAmount;
        String currentTime=Get_Current_Date();

        Transaction tempObject=new Transaction(this.accountNumber,currentTime,"Withdrawal",-1*withdrawalAmount,this.getBalance());
        BankStatement.add(tempObject);
    }


    //4.Print Statement()
    public abstract void printStatement();

    //5.Calculate Zakat()
    //6.Change Account Balance
    //This will change the account balance if we needed during the interest calculation & Zakat
    final public void setAccountBalance(long accountBalance, boolean flag)
    {
        if(flag ==true)
        {
            this.balance -=accountBalance;
        }
        else if(flag==false)
        {
            this.balance +=accountBalance;
        }
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber=accountNumber;
    }

    //6.Display All Deductions()
    public abstract void displayAllDeductions();



    //7.Transfer Amount()
abstract public Vector<Customer> transferAmount(long transferAmount, String recipient_account, Vector<Customer> Account_Holders, int Account_Type_Index);


    public void transferAddBalance(long amount)
    {
        this.balance +=amount;
        String currentTime=Get_Current_Date();

        Transaction tempObject=new Transaction(this.accountNumber,currentTime,"Transfer Received",amount,this.getBalance());
        BankStatement.add(tempObject);
    }


    public void transferMinusBalance(long amount)
    {
        this.balance -=amount;
        String currentTime=Get_Current_Date();

        Transaction tempObject=new Transaction(this.accountNumber,currentTime,"Transfer Sent",amount,this.getBalance());
        BankStatement.add(tempObject);
    }
    //8.Calculate Interest()


    //Method to Get the Current Date And Time of the Any Transaction
    //------------------------------------------------------------ Helper Function to Get the System Time and Date -----------------------------------------------------------------------------------
    final public String Get_Current_Date()
    {
          /*  //GETTING CURRENT DATE AND TIME
            Code to Get the Current System Date And Time
            This will help in getting the exact time at which the transaction was performed in the system
            */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String stringDateTimeNow =now.format(dtf);  //getting the time and date at which we did the withdrawal

        return stringDateTimeNow;
    }

}
