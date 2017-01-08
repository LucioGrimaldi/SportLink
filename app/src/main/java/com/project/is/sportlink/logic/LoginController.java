package com.project.is.sportlink.logic;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.Utente;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

/**
 * Created by luciogrimaldi on 22/12/16.
 */
public class LoginController {

    private MobileServiceClient mClient;
    private MobileServiceTable<Utente> mUtenteTable;
    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    //prova connessione DB
    public void MetodoLogin(){

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

            // Get the Mobile Service Table instance to use

            mUtenteTable = mClient.getTable("utente",Utente.class);




            stampaDatiUtente();
            // Load the items from the Mobile Service
            //refreshItemsFromTable();

        } catch (MalformedURLException e) {
            Log.e("Erroraccio",e.getMessage());
        } catch (Exception e){
            Log.e("CATTIVO",e.getMessage());
        }

    }


    public List<Utente> riceviInfoUtenti()throws MobileServiceException,ExecutionException,InterruptedException{
        return mUtenteTable.execute().get();}

    public void stampaDatiUtente(){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final List<Utente> results = riceviInfoUtenti();

                    //Offline Sync
                    //final List<ToDoItem> results = refreshItemsFromMobileServiceTableSyncTable();

                    for (Utente i:results
                            ) {
                        Log.d("Debug",i.getmNome());
                    }

                } catch (final Exception e){
                    e.printStackTrace();
                }

                return null;
            }
        };

        runAsyncTask(task);
    }

    /**
     * Run an ASync task on the corresponding executor
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }
}



































