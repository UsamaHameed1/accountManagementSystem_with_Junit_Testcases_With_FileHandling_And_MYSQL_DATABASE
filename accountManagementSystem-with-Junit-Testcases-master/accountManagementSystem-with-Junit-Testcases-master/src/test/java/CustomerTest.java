import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private SavingAccount s1;
    private SavingAccount s2;
    private CheckingAccount ch1;
    private CheckingAccount ch2;
    private Customer c1;
    private Customer c2;
    @Before
    public void setup()
    {
       s1=new SavingAccount(100,2.5);
       ch1=new CheckingAccount(500);
       c1=new Customer("Imran Khan","Islamabad","+9251123456",s1);

         s2=new SavingAccount(1500,2.5);
         ch2=new CheckingAccount(1500);
         c2=new Customer("Aslam Ahmed","Lahore","+9287456123",ch2);

    }
    @Test
    public void setCheckingBankAccount() {
        c1.setCheckingBankAccount(ch1);
        assertEquals(ch2,c2.getCheckingBankAccount());
    }

    @Test
    public void setSavingBankAccount() {
        c2.setSavingBankAccount(s2);
        assertEquals(s1,c1.getSavingBankAccount());
    }

    @Test
    public void getCheckingBankAccount() {
        assertEquals(ch2, c2.getCheckingBankAccount());

    }

    @Test
    public void getSavingBankAccount() {
        assertEquals(s1, c1.getSavingBankAccount());
    } 

    @Test
    public void closeCheckingAccount() {
        c2.closeCheckingAccount();
        assertEquals(null,c2.getCheckingBankAccount());
    }

    @Test
    public void closeSavingAccount() {
        c1.closeSavingAccount();
        assertEquals(null,c1.getSavingBankAccount());
    }

    @Test
    public void getCustomer() {
        Assert.assertEquals(c1,c1.getCustomer());
    }
}