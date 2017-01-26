package com.project.is.sportlink;

import com.project.is.sportlink.DataModel.Utente;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by bitol on 13/01/2017.
 */

public class UtenteTest{
    protected Utente utente;
    @Before
    public void setUp(){
        utente = new Utente();
    }
    public void tearDown(){
        utente = null;
    }
    public static Test suite(TestSuite obj){
        return (Test) obj;
    }
    @Test
    public void nome() throws Exception{
        utente.setmNome("Andrea");
        assertEquals("Andrea",utente.getmNome());
    }
    @Test
    public void cognome() throws Exception{
        utente.setmCognome("Vitale");
        assertEquals("Vitale",utente.getmCognome());
    }
    @Test
    public void telefono() throws Exception{
        utente.setmTelefono("3478283995");
        assertEquals("3478283995",utente.getmTelefono());
    }
    @Test
    public void email() throws Exception{
        utente.setmEmail("bitols_95@outlook.it");
        assertEquals("bitols_95@outlook.it",utente.getmEmail());
    }
    @Test
    public void pass() throws Exception{
        utente.setmPass("password");
        assertEquals("password",utente.getmPass());
    }
}
