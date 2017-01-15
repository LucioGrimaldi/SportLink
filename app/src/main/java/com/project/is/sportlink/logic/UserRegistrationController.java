package com.project.is.sportlink.logic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.DataModel.Utente;
import com.project.is.sportlink.ui.LoginActivity;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by luciogrimaldi on 22/12/16.
 */

public class UserRegistrationController {
    private MobileServiceClient mClient;
    private MobileServiceTable<Utente> mUtenteTable;
    private Context context;
    private Utente newUtente;


    public UserRegistrationController(Context context){this.context=context;}

    //connessione DB
    public void userRegistrationRequest(String nome, String cognome,String email,String password,String telefono){

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

            newUtente=new Utente();
            newUtente.setmNome(nome);
            newUtente.setmCognome(cognome);
            newUtente.setmEmail(email);
            newUtente.setmPass(password);
            newUtente.setmTelefono(telefono);

            mUtenteTable=mClient.getTable("utente",Utente.class);

            registrazioneNuovoUtente(email,newUtente);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void inserisciUtente(Utente utente)throws MobileServiceException,ExecutionException,InterruptedException{
        mUtenteTable.insert(utente).get();

        }

    public List<Utente> riceviInfoUtentiRegistrati(String email)throws MobileServiceException,ExecutionException,InterruptedException{
        return mUtenteTable.where().field("email_u").eq(email).execute().get();}

    public void registrazioneNuovoUtente(final String email,final Utente u){
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
                    final List<Utente> results = riceviInfoUtentiRegistrati(email);
                    if(results.isEmpty()){
                        inserisciUtente(u);
                        return 2;
                    }else{
                        return 1;
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

                if(b==2){
                    Toast toast = Toast.makeText(context,"La registrazione è avvenuta con successo.\nOra puoi effettuare la login.",Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent= new Intent(context,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("IS_UTENTE",true);
                    intent.putExtra("IS_GESTORE",false);
                    context.startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(context,"l'email inserita è già registrata.\nRiprova inserendo una email diversa.",Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent= new Intent(context,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("IS_UTENTE",true);
                    intent.putExtra("IS_GESTORE",false);
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
