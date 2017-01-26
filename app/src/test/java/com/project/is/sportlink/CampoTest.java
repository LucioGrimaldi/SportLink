package com.project.is.sportlink;

import com.project.is.sportlink.ui.CampoRegistrationActivity;

import junit.framework.Test;
import junit.framework.TestCase;

import org.junit.Before;

import static junit.framework.Assert.assertEquals;

/**
 * Created by bitol on 16/01/2017.
 */

public class CampoTest {
    protected CampoRegistrationActivity campo;
    @Before
    public void setUp() throws Exception{
        campo = new CampoRegistrationActivity();
    }
    public void tearDown() throws Exception{
        campo = null;
    }
    public Test suite(TestCase obj){
        return obj;
    }

    @org.junit.Test
    public void nome() throws Exception{
        campo.setNome("Polisportiva");
        assertEquals("Polisportiva",campo.getNome());
    }
    @org.junit.Test
    public void sport() throws Exception{
        campo.setSport("Calcio");
        assertEquals("Calcio",campo.getSport());
    }
}
