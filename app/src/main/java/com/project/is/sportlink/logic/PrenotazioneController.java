package com.project.is.sportlink.logic;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.dataModel.Prenotazione;
import com.project.is.sportlink.ui.PrenotazioneActivity;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mario on 23/01/2017.
 */

public class PrenotazioneController {

    private PrenotazioneActivity activity;
    private MobileServiceClient mClient;
    private String[] orari= new String[]{"09:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00",
            "15:00-16:00","16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00","20:00-21:00","21:00-22:00","22:00-23:00",
            "23:00-24:00"};
    private MobileServiceTable<Prenotazione> mPrenotazioneTable;
    private List<String> orariDisp=new ArrayList<>();
    private ArrayList<String> helper=new ArrayList<>();

    public PrenotazioneController(PrenotazioneActivity activity){
        this.activity=activity;

        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://sportlinkservice.azurewebsites.net",
                    activity);

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

    //Notify activity of async task completion
    private void notifyActivityTaskCompleted()
    {
        if ( null != activity ) {
            activity.onTaskCompleted(orariDisp);
        }
    }

    //in questo metodo vengono prese le informazioni riguardanti le prenotazioni effettuate in un determinato giorno per un determinato campo
    //e vengono calcolati gli orari ancora disponibili.
    public void impostaOrariDisponibiliComboBox(final String data_p,final String FK_campo){
        AsyncTask<Void, Void, List<String>> task = new AsyncTask<Void, Void, List<String>>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(activity);
                dialog.setMessage("Attendere prego...");
                dialog.setIndeterminate(true);
                dialog.show();
            }

            @Override
            protected List<String> doInBackground(Void... params) {

                try {
                    List<Prenotazione> results=mPrenotazioneTable.where().field("data_p").eq(data_p).and().field("FK_campo").eq(FK_campo).execute().get();
                    orariDisp.clear();
                    helper.clear();
                    for (int i = 0; i < orari.length; i++) {
                        orariDisp.add(orari[i]);
                        helper.add(orari[i]);
                    }

                    if(results.isEmpty()){
                        return null;
                    }else {
                        for (Prenotazione a : results) {
                            String orarioTemp = a.getmOrario();
                            for (String i : helper) {
                                if (i.equals(orarioTemp)) {
                                    orariDisp.remove(i);
                                }
                            }
                        }
                        return orariDisp;
                    }
                } catch (final Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<String> r) {
                super.onPostExecute(r);
                if(dialog.isShowing()){dialog.dismiss();}
                if(r==null&&orariDisp.isEmpty()){
 //                   Toast toast=Toast.makeText(activity,"Non è sono stati trovati orari disponibili per il giorno selezionato!",Toast.LENGTH_SHORT);
 //                   toast.show();
                }else{
                    notifyActivityTaskCompleted();
 //                   Toast toast=Toast.makeText(activity,"Seleziona l'orario tra quelli disponibili!",Toast.LENGTH_SHORT);
 //                   toast.show();
                }
            }
        };

        runAsyncTask(task);
    }

    //in questo metodo viene crata una nuova prenotazione e dichiarata la query per effettuare l'inserimento.

    public void creazioneNuovaPrenotazione (String data_p,String FK_utente,String FK_campo,String orario,String nomeStruttura,String nomeCampo,String indirizzo,String sport)throws ExecutionException,InterruptedException{
        Prenotazione prenotazione= new Prenotazione();
        prenotazione.setmData_p(data_p);
        prenotazione.setmOrario(orario);
        prenotazione.setmFK_utente(FK_utente);
        prenotazione.setmFK_campo(FK_campo);
        prenotazione.setmNomeStruttura(nomeStruttura);
        prenotazione.setmNomeCampo(nomeCampo);
        prenotazione.setmIndirizzo(indirizzo);
        prenotazione.setmSport(sport);
        mPrenotazioneTable.insert(prenotazione).get();
    }

    //questo metodo registra in modo effettivo la nuova registrazione sul db effettuando la richiesta al server.Produce un toast che indica se l'operazione è andata a buon fine.
    public void registazioneNuovaPrenotazione(final String data_p, final String FK_utente, final String FK_campo, final String orario,final String nomeStruttura,final String nomeCampo,final String indirizzo,final String sport) {
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                try{
                    creazioneNuovaPrenotazione(data_p,FK_utente,FK_campo,orario,nomeStruttura,nomeCampo,indirizzo,sport);
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
  //                  Toast toast=Toast.makeText(activity,"La tua prenotazione è stata completata con successo!",Toast.LENGTH_LONG);
  //                  toast.show();
                }else {
                    Toast toast=Toast.makeText(activity,"Mi dispiace ma non è stato possibile effettuare la prenotazione riprova!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };
        runAsyncTask2(task);
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     */
    private AsyncTask<Void, Void, List<String>> runAsyncTask(AsyncTask<Void, Void, List<String>> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     */
    private AsyncTask<Void, Void, Integer> runAsyncTask2(AsyncTask<Void, Void, Integer> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

}

