package com.project.is.sportlink.logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.dataModel.Campo;
import com.project.is.sportlink.dataModel.Prenotazione;
import com.project.is.sportlink.dataModel.Struttura;
import com.project.is.sportlink.ui.RisultatiRicercaFragment;
import com.project.is.sportlink.ui.VisualizzaPrenotazioniFragment;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mario on 29/01/2017.
 */

public class VisualizzaPrenotazioniController {

    private VisualizzaPrenotazioniFragment visualizzaPrenotazioniFragment;
    private MobileServiceClient mClient;
    private MobileServiceTable<Prenotazione> mPrenotazioneTable;
    private String mUtenteId;
    private List<Prenotazione> prenotazioniEffettuate= new ArrayList<>();

    public VisualizzaPrenotazioniController(VisualizzaPrenotazioniFragment visualizzaPrenotazioniFragment) {
        this.visualizzaPrenotazioniFragment=visualizzaPrenotazioniFragment;
        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://sportlinkservice.azurewebsites.net",
                    visualizzaPrenotazioniFragment.getActivity().getApplicationContext());

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

            mPrenotazioneTable = mClient.getTable("Prenotazione", Prenotazione.class);
            SharedPreferences sharedPref = visualizzaPrenotazioniFragment.getActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
            mUtenteId=sharedPref.getString("UTENTE_ID",null);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Notify activity of async task completion
    private void notifyActivityTaskCompleted()
    {
        if ( null != visualizzaPrenotazioniFragment ) {
            visualizzaPrenotazioniFragment.onTaskCompleted(prenotazioniEffettuate);
        }
    }

    private List<Prenotazione> richiestaPrenotazioniUtente() throws InterruptedException,ExecutionException{
        return mPrenotazioneTable.where().field("FK_utente").eq(mUtenteId).execute().get();
    }
    public void ottieniPrenotazioniUtente(){
        AsyncTask<Void, Void, List<Prenotazione>> task = new AsyncTask<Void, Void, List<Prenotazione>>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(visualizzaPrenotazioniFragment.getActivity());
                dialog.setMessage("Attendere prego...");
                dialog.setIndeterminate(true);
                dialog.show();
            }

            @Override
            protected List<Prenotazione> doInBackground(Void... params) {

                try {
                    prenotazioniEffettuate.clear();
                    prenotazioniEffettuate=richiestaPrenotazioniUtente();

                    if(prenotazioniEffettuate.isEmpty())
                        return null;
                    else {
                        return prenotazioniEffettuate;
                    }
                } catch (final InterruptedException e){
                    e.printStackTrace();
                } catch (final ExecutionException e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Prenotazione> r) {
                super.onPostExecute(r);
                if(dialog.isShowing()){dialog.dismiss();}
                if(r!=null){
                    notifyActivityTaskCompleted();
                }else {
                    Toast.makeText(visualizzaPrenotazioniFragment.getActivity(),"Non sono state trovate prenotazioni attive!",Toast.LENGTH_LONG).show();
                }
            }
        };

        runAsyncTask(task);
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     */
    private AsyncTask<Void, Void, List<Prenotazione>> runAsyncTask(AsyncTask<Void, Void, List<Prenotazione>> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }
}
