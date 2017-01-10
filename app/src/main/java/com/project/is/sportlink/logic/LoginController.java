package com.project.is.sportlink.logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.DataModel.Gestore;
import com.project.is.sportlink.DataModel.Utente;
import com.project.is.sportlink.ui.HomeActivity;
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
    private MobileServiceTable<Gestore> mGestoreTable;
    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    //connessione DB
    public void LoginRequest(String email, String password,boolean IS_UTENTE){

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

            if(IS_UTENTE) {
                // Get the Mobile Service Table instance to use
                mUtenteTable = mClient.getTable("utente", Utente.class);
                //controllo se i dati inseriti sono corretti e se esiste l'utente con email e password inserite negli appositi campi
                controlloDatiLoginUtente(email, password);
            }else{
                // Get the Mobile Service Table instance to use
                mGestoreTable = mClient.getTable("gestore", Gestore.class);
                //controllo se i dati inseriti sono corretti e se esiste il gestore con email e password inserite negli appositi campi
                controlloDatiLoginGestore(email, password);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    esegue una ricerca in base alla email e alla password inserite dall'utente
     */
    public List<Utente> riceviInfoUtente(String email,String password)throws MobileServiceException,ExecutionException,InterruptedException{
        return mUtenteTable.where().field("email_u").eq(email).and().field("password_u").eq(password).execute().get();}

    public void controlloDatiLoginUtente(final String email, final String password){
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(context);
                dialog.setMessage("Please wait...");
                dialog.setIndeterminate(true);
                dialog.show();
            }

            @Override
            protected Integer doInBackground(Void... params) {

                try {
                    final List<Utente> results = riceviInfoUtente(email,password);
                    if(results.isEmpty()){
                        return 1;
                    }else{
                        return 2;
                    }
                } catch (final Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Integer b) {
                super.onPostExecute(b);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                if(b==1){
                    Toast toast = Toast.makeText(context,"l'username o la password inserite sono errate",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Intent intent= new Intent(context,HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }

            }
        };

        runAsyncTask(task);
    }


    /*
        esegue una ricerca in base alla email e alla password inserite dal gestore
    */
    public List<Gestore> riceviInfoGestore(String email,String password)throws MobileServiceException,ExecutionException,InterruptedException{
        return mGestoreTable.where().field("email_g").eq(email).and().field("password_g").eq(password).execute().get();}

    public void controlloDatiLoginGestore(final String email, final String password){
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(context);
                dialog.setMessage("Please wait...");
                dialog.setIndeterminate(true);
                dialog.show();
            }

            @Override
            protected Integer doInBackground(Void... params) {

                try {
                    final List<Gestore> results = riceviInfoGestore(email,password);
                    if(results.isEmpty()){
                        return 1;
                    }else{
                        return 2;
                    }
                } catch (final Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Integer b) {
                super.onPostExecute(b);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                if(b==1){
                    Toast toast = Toast.makeText(context,"l'username o la password inserite sono errate",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Intent intent= new Intent(context,HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
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
}



































