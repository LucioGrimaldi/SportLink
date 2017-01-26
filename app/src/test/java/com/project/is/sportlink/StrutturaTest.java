package com.project.is.sportlink;

import com.project.is.sportlink.ui.StrutturaRegistrationActivity;

import junit.framework.Test;
import junit.framework.TestCase;

import org.junit.Before;

import java.security.spec.ECField;

import static junit.framework.Assert.assertEquals;

/**
 * Created by bitol on 16/01/2017.
 */

public class StrutturaTest {
    protected StrutturaRegistrationActivity struttura;
    @Before
    public void setUp() throws Exception{
        struttura = new StrutturaRegistrationActivity();
    }
    public void tearDown() throws Exception{
        struttura = null;
    }
    public Test suite(TestCase obj){
        return (Test) obj;
    }

    @org.junit.Test
    public void nome() throws Exception{
        struttura.setNome("Calcio");
        assertEquals("Calcio",struttura.getNome());
    }
    @org.junit.Test
    public void indirizzo() throws Exception{
        struttura.setIndirizzo("Via Unità d'Italia");
        assertEquals("Via Unità d'Italia",struttura.getIndirizzo());
    }
    @org.junit.Test
    public void citta() throws Exception{
        struttura.setCitta("Salerno");
        assertEquals("Salerno",struttura.getCitta());
    }
    @org.junit.Test
    public void telefono() throws Exception{
        struttura.setTelefono("3482930453");
        assertEquals("3482930453",struttura.getTelefono());
    }
}
