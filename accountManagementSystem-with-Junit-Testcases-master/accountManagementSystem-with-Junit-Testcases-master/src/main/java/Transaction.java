public class Transaction {
    private String accountNumber;
    private String dateOfTransaction;
    private String typeOfTransaction;
    private long transactionAmount;
    private long remainingBalance; //Remaining Balance of the account holder


    //PARAMETERIZED CONSTRUCTOR
    public Transaction(String accountNumber, String dateOfTransaction, String typeOfTransaction, long transactionAmount, long remainingBalance) {
        this.accountNumber = accountNumber;
        this.dateOfTransaction = dateOfTransaction;
        this.typeOfTransaction = typeOfTransaction;
        this.transactionAmount = transactionAmount;
        this.remainingBalance = remainingBalance;
    }

    //Display The Transaction Object
    public void displayTransaction()
    {
        System.out.println(this.accountNumber+"\t\t\t"+this.dateOfTransaction+"\t\t\t"+this.typeOfTransaction+"\t\t\t\t\t\t"+this.transactionAmount+"\t\t\t\t\t\t"+this.remainingBalance);
    }
}
