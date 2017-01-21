package com.project.is.sportlink.logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.DataModel.Campo;
import com.project.is.sportlink.DataModel.Gestore;
import com.project.is.sportlink.DataModel.Prenotazione;
import com.project.is.sportlink.DataModel.Struttura;
import com.project.is.sportlink.ui.LoginActivity;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mario on 18/01/2017.
 */

public class PrenotazioneController {
    String[] orari= new String[]{"09:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00"};
    MobileServiceClient mClient;
    Context context;
    MobileServiceTable<Prenotazione> mPrenotazioneTable;
    List<String> orariFinali=new ArrayList<>();
    ArrayList<String> orariDisp=new ArrayList<>();
    ArrayList<String> helper=new ArrayList<>();

    public PrenotazioneController (Context context){
        this.context=context;

        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://sportlinkservice.azurewebsites.net",
                    context);

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });

            mPrenotazioneTable= mClient.getTable("Prenotazione",Prenotazione.class);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }

    }



    //in questo metodo viene crata una nuova prenotazione e dichiarata la query per effettuare l'inserimento.

    public void creazioneNuovaPrenotazione (String data_p,String FK_utente,String FK_campo,String orario)throws ExecutionException,InterruptedException{
        Prenotazione prenotazione= new Prenotazione();
        prenotazione.setmData_p(data_p);
        prenotazione.setmOrario(orario);
        prenotazione.setmFK_utente(FK_utente);
        prenotazione.setmFK_campo(FK_campo);
        mPrenotazioneTable.insert(prenotazione).get();
    }


    //in questo metodo vengono prese le informazioni riguardanti le prenotazioni effettuate in un determinato giorno per un determinato campo
    //e vengono calcolati gli orari ancora disponibili. Per ricevere l'ArrayList contenente gli orari disponibili basterà chiamare il metodo getOrariFinali().

    public void impostaOrariDisponibiliComboBox(final String data_p,final String FK_campo){
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... params) {

                try {
                    List<Prenotazione> results=mPrenotazioneTable.where().field("data_p").eq(data_p).and().field("FK_campo").eq(FK_campo).execute().get();

                    if(results.isEmpty()){
                        return 1;
                    }else {
                        for (int i = 0; i < orari.length; i++) {
                            orariDisp.add(orari[i]);
                            helper.add(orari[i]);
                        }
                        for (Prenotazione a : results) {
                            String orarioTemp = a.getmOrario();
                            for (String i : helper) {
                                if (i.equals(orarioTemp)) {
                                    orariDisp.remove(i);
                                }
                            }
                        }
                        orariFinali.addAll(orariDisp);
                        return 2;
                    }
                } catch (final Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Integer r) {
                super.onPostExecute(r);
                if(r==1){
                Toast toast=Toast.makeText(context,"non è sono stati trovati orari disponibili per il giorno selezionato!",Toast.LENGTH_LONG);
                toast.show();
                }else{
                    Toast toast=Toast.makeText(context,"seleziona l'orario per la prenotazione tra quelli disponibili!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };

        runAsyncTask(task);
    }


    //questo metodo registra in modo effettivo la nuova registrazione sul db effettuando la richiesta al server.Produce un toast che indica se l'operazione è andata a buon fine.
    public void registazioneNuovaPrenotazione(final String data_p, final String FK_utente, final String FK_campo, final String orario) {
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                try{
                creazioneNuovaPrenotazione(data_p,FK_utente,FK_campo,orario);
                    return 2;
                }catch (ExecutionException e){
                    e.printStackTrace();
                    return 1;
                }catch (InterruptedException e){
                    e.printStackTrace();
                    return 1;
                }
            }

            @Override
            protected void onPostExecute(Integer r) {
                super.onPostExecute(r);
                if(r==2){
                    Toast toast=Toast.makeText(context,"La tua registrazione è stata completata con successo!",Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    Toast toast=Toast.makeText(context,"Mi dispiace ma non è stato possibile effettuare la prenotazione riprova!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };
        runAsyncTask(task);
    }


    /**
     * Run an ASync task on the corresponding executor
     * @param task
     */
    private AsyncTask<Void, Void, Integer> runAsyncTask(AsyncTask<Void, Void, Integer> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    public List<String> getOrariFinali() {
        return orariFinali;
    }

    public void setOrariFinali(List<String> orariFinali) {
        this.orariFinali = orariFinali;
    }
}
