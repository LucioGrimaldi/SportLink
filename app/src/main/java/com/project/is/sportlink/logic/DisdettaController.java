package com.project.is.sportlink.logic;

import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.dataModel.Campo;
import com.project.is.sportlink.dataModel.Prenotazione;
import com.project.is.sportlink.dataModel.Struttura;
import com.project.is.sportlink.ui.DisdettaActivity;
import com.project.is.sportlink.ui.RisultatiRicercaFragment;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mario on 29/01/2017.
 */

public class DisdettaController {

    private DisdettaActivity activity;
    private MobileServiceClient mClient;
    private MobileServiceTable<Prenotazione> mPrenotazioneTable;
    private String idPrenotazione;
    private List<Prenotazione> risultati= new ArrayList<>();

    public DisdettaController(DisdettaActivity activity,String idPrenotazione) {
        this.activity=activity;
        this.idPrenotazione=idPrenotazione;
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

            mPrenotazioneTable = mClient.getTable("Prenotazione", Prenotazione.class);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Prenotazione> ricercaPrenotazione(String idPrenotazione)throws InterruptedException,ExecutionException{
        return mPrenotazioneTable.where().field("id").eq(idPrenotazione).execute().get();
    }

    public void rimuoviPrenotazione(String idPrenotazione)throws InterruptedException,ExecutionException{
        mPrenotazioneTable.delete(idPrenotazione);
    }

    public void requestRimuoviPrenotazione(final String idPrenotazione) {
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Void... voids) {
                try{
                    List<Prenotazione> risultati=ricercaPrenotazione(idPrenotazione);
                    if(risultati.isEmpty()){
                        return 1;
                    }else {
                        rimuoviPrenotazione(idPrenotazione);
                        return 2;
                    }
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
                    notifyActivityTaskCompleted(r);
                }else {
                    notifyActivityTaskCompleted(1);
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

    //Notify activity of async task completion
    private void notifyActivityTaskCompleted(int r)
    {
        if ( null != activity ) {
            activity.onTaskCompleted(r);
        }
    }
}
