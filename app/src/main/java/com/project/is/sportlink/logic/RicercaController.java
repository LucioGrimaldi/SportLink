package com.project.is.sportlink.logic;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.DataModel.Campo;
import com.project.is.sportlink.DataModel.Struttura;
import com.project.is.sportlink.ui.RisultatiRicercaFragment;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mario on 23/01/2017.
 */

public class RicercaController {

    private RisultatiRicercaFragment ricercaFragment;
    private MobileServiceClient mClient;
    private MobileServiceTable<Struttura> mStrutturaTable;
    private MobileServiceTable<Campo> mCampoTable;
    private List<Campo> risultati= new ArrayList<>();

    public RicercaController(RisultatiRicercaFragment ricercaFragment) {
        this.ricercaFragment=ricercaFragment;
        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://sportlinkservice.azurewebsites.net",
                    ricercaFragment.getActivity().getApplicationContext());

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

            mStrutturaTable = mClient.getTable("Struttura", Struttura.class);
            mCampoTable = mClient.getTable("Campo", Campo.class);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Struttura> ricercaStrutture(String città) throws ExecutionException, InterruptedException {
        return mStrutturaTable.where().field("citta_s").eq(città).execute().get();
    }

    public List<Campo> ricercaCampo(String strutturaId)throws ExecutionException,InterruptedException {
        return mCampoTable.where().field("FK_struttura").eq(strutturaId).execute().get();
    }
    /**
     * Refresh the list with the items in the Table
     */
    public void ricercaRequest(final String città) {

        AsyncTask<Void, Void, List<Campo>> task = new AsyncTask<Void, Void, List<Campo>>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(ricercaFragment.getActivity());
                dialog.setMessage("Please wait...");
                dialog.setIndeterminate(true);
                dialog.show();
            }


            @Override
            protected List<Campo> doInBackground(Void... params) {

                try {
                    risultati.clear();
                    final List<Struttura> results = ricercaStrutture(città);

                    for (Struttura i:
                            results) {
                        List<Campo> temp=ricercaCampo(i.getmId());
                        risultati.addAll(temp);
                    }
                    return risultati;

                } catch (final Exception e){
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(List<Campo> _risultati) {
                super.onPostExecute(risultati);
                risultati=_risultati;
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
                notifyActivityTaskCompleted();
            }
        };

        runAsyncTask(task);
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     */
    private AsyncTask<Void, Void, List<Campo>> runAsyncTask(AsyncTask<Void, Void, List<Campo>> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    //Notify activity of async task completion
    private void notifyActivityTaskCompleted()
    {
        if ( null != ricercaFragment ) {
            ricercaFragment.onTaskCompleted(risultati);
        }
    }
}
