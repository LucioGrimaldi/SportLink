package com.project.is.sportlink;
import com.project.is.sportlink.ui.LoginActivity;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by bitol on 16/01/2017.
 */

public class LoginTest {
    protected LoginActivity login;
    @Before
    public void setUp() throws Exception{
        login = new LoginActivity();
    }
    public void tearDown() throws Exception{
        login = null;
    }
    public Test suite(TestSuite obj){
        return (Test) obj;
    }
    @Test
    public void login_gestore() throws Exception{
        assertFalse(login.isGestore());
    }
    @Test
    public void login_utente() throws Exception{
        assertFalse(login.isUtente());
    }
    @Test
    public void email() throws Exception{
        login.setEmail("mar3pic@gmail.com");
        assertEquals("mar3pic@gmail.com",login.getEmail());
    }
    @Test
    public void pass() throws Exception{
        login.setPass("pippo");
        assertEquals("pippo",login.getPass());
    }
}
