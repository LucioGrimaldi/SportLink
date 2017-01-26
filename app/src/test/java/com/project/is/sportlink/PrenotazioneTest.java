package com.project.is.sportlink;

import android.os.RemoteException;

import com.project.is.sportlink.DataModel.Prenotazione;

import junit.framework.Test;
import junit.framework.TestCase;

import org.junit.Before;

import static junit.framework.Assert.assertEquals;

/**
 * Created by bitol on 26/01/2017.
 */

public class PrenotazioneTest {
    protected Prenotazione prenotazione;
    @Before
    public void setUp() throws Exception{
        prenotazione = new Prenotazione();
    }
    public void tearDown() throws Exception{
        prenotazione = null;
    }
    public Test suite(TestCase obj){
        return (Test) obj;
    }

    @org.junit.Test
    public void id(){
        prenotazione.setmId("7");
        assertEquals("7",prenotazione.getmId());
    }
    @org.junit.Test
    public void data(){
        prenotazione.setmData_p("29/04/2016");
        assertEquals("29/04/2016",prenotazione.getmData_p());
    }
    @org.junit.Test
    public void orario(){
        prenotazione.setmOrario("20:00");
        assertEquals("20:00",prenotazione.getmOrario());
    }
    @org.junit.Test
    public void campo(){
        prenotazione.setmFK_campo("Antessano");
        assertEquals("Antessano",prenotazione.getmFK_campo());
    }
    @org.junit.Test
    public void utente(){
        prenotazione.setmFK_utente("Andrea");
        assertEquals("Andrea",prenotazione.getmFK_utente());
    }
}
