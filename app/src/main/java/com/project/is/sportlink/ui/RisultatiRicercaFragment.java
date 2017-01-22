package com.project.is.sportlink.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.DataModel.Campo;
import com.project.is.sportlink.DataModel.Struttura;
import com.project.is.sportlink.R;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class RisultatiRicercaFragment extends Fragment {


    private RicercaAdapter ricercaAdapter;
    private ListView listViewRisultati;
    private MobileServiceClient mClient;
    private MobileServiceTable<Struttura> mStrutturaTable;
    private MobileServiceTable<Campo> mCampoTable;
    private String città,utenteId;
    private List<Campo> risultati= new ArrayList<>();

    public RisultatiRicercaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_risultati_ricerca, container, false);

        ricercaAdapter = new RicercaAdapter(getActivity().getApplicationContext(), R.layout.list_element_risultati_ricerca);
        listViewRisultati = (ListView)v.findViewById(R.id.listViewRisultatiRicerca);
        listViewRisultati.setAdapter(ricercaAdapter);
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        città=sharedPref.getString("città",null);
        utenteId=sharedPref.getString("UTENTE_ID",null);


        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://sportlinkservice.azurewebsites.net",
                    getActivity().getApplicationContext());

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

            mStrutturaTable=mClient.getTable("Struttura",Struttura.class);
            mCampoTable=mClient.getTable("Campo",Campo.class);
            ricercaRequest();
            Log.d("debug",utenteId+" ");


        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return v;
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
    private void ricercaRequest() {

        // Get the items that weren't marked as completed and add them in the
        // adapter

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(getActivity());
                dialog.setMessage("Please wait...");
                dialog.setIndeterminate(true);
                dialog.show();
            }


            @Override
            protected Void doInBackground(Void... params) {

                try {
                    risultati.clear();
                    final List<Struttura> results = ricercaStrutture(città);

                    for (Struttura i:
                            results) {
                        List<Campo> temp=ricercaCampo(i.getmId());
                        risultati.addAll(temp);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ricercaAdapter.clear();

                            for (Campo item : risultati) {
                                ricercaAdapter.add(item);
                            }
                            if(ricercaAdapter.isEmpty()) {
                                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"La tua ricerca non ha prodotto risultati.",Toast.LENGTH_LONG);
                                toast.show();

                            }
                        }
                    });

                } catch (final Exception e){
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        };

        runAsyncTask(task);
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }
}
