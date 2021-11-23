import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdminBankTest {

    private AdminBank admin;
    @Before
    public void setup()
    {
        admin=new AdminBank("Admin","12345");
    }
    @Test
    public void getPassword() {
        assertEquals("12345",admin.getPassword());
    }

    @Test
    public void getUserName() {
        assertEquals("Admin",admin.getUserName());
    }


}