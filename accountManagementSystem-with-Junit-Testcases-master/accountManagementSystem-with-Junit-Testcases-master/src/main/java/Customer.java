public class Customer {

    // DATA MEMBERS
    private String name;
    private String address;
    private String phoneNumber;
    static int accountCounter=0;
    private String accountNumber;

    private CheckingAccount checkingBankAccount;
    private SavingAccount savingBankAccount;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static int getAccountCounter() {
        return accountCounter;
    }

    //PARAMETERIZED CONSTRUCTOR with Saving Account
    public Customer(String name, String address, String phoneNumber, CheckingAccount checkingBankAccount) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber="ABL-500"+accountCounter;

        checkingBankAccount.setAccountNumber(this.accountNumber);
        this.checkingBankAccount = checkingBankAccount;

        ++accountCounter;
    }

    //Constructor with Checking Account
    public Customer(String name, String address, String phoneNumber, SavingAccount savingBankAccount) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber="ABL-500"+accountCounter;

        savingBankAccount.setAccountNumber(this.accountNumber);
        this.savingBankAccount = savingBankAccount;
        ++accountCounter;
    }


    // --------------------------------------------------------  Set Checking Account -------------------------------------------------------------------------------------------------------
    public void setCheckingBankAccount(CheckingAccount account) {
        if(this.savingBankAccount ==null && this.checkingBankAccount ==null)
        {
            this.checkingBankAccount = account;
        }
        else if(this.savingBankAccount!=null)
        {
            String accountID =this.savingBankAccount.getAccountNumber();
            account.setCheckingAccountNumber(accountID);
            this.checkingBankAccount=account;
        }
    }

    // --------------------------------------------------------  Set Saving Account -------------------------------------------------------------------------------------------------------
    public void setSavingBankAccount(SavingAccount account)
    {
        if(this.savingBankAccount ==null && this.checkingBankAccount ==null)
        {
            this.savingBankAccount = account;
        }
        else if(this.checkingBankAccount!=null)
        {
            String accountID =this.checkingBankAccount.getAccountNumber();
            account.setSavingAccountNumber(accountID);
            this.savingBankAccount=account;
        }
    }

    // --------------------------------------------------------  Get Checking Account -------------------------------------------------------------------------------------------------------
    public CheckingAccount getCheckingBankAccount() {
        return checkingBankAccount;
    }

    // --------------------------------------------------------  Get Saving Account -------------------------------------------------------------------------------------------------------
    public SavingAccount getSavingBankAccount() {
        return savingBankAccount;
    }


    //Display The Customer Details
    public void displayCustomerDetails()
    {
        System.out.println("=========== Customer Details=======================");
        System.out.println("Account Holder Name: "+this.name);
        System.out.println("Account Number: "+this.accountNumber);
        System.out.println("Account Holder Address : "+this.address);

        // condition for the checking account
        if(checkingBankAccount ==null)
        {
            System.out.println("Checking Account Status = False");
        }
        else
        {
            System.out.println("Checking Account Status = True");
        }

        // Condition for the saving account
        if(savingBankAccount ==null)
        {
            System.out.println("Saving Account Status = False");
        }
        else
        {
            System.out.println("Saving Account Status = True");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void closeCheckingAccount()
    {
        this.checkingBankAccount=null;
    }

    public void closeSavingAccount()
    {
        this.savingBankAccount=null;
    }
    public Customer getSaving() {
        return this;
    }

    public Customer getCustomer()
    {
        return this;
    }
}
