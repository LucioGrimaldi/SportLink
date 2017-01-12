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
import com.project.is.sportlink.DataModel.Campo;
import com.project.is.sportlink.DataModel.Gestore;
import com.project.is.sportlink.DataModel.Struttura;
import com.project.is.sportlink.ui.LoginActivity;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mario on 11/01/2017.
 */

public class GestoreRegistrationController {
    private MobileServiceClient mClient;
    private MobileServiceTable<Gestore> mGestoreTable;
    private MobileServiceTable<Struttura> mStrutturaTable;
    private MobileServiceTable<Campo> mCampoTable;
    private Context context;
    private Gestore newGestore;
    private Struttura newStruttura;
    private Campo newCampo;


    public GestoreRegistrationController(Context context){this.context=context;}

    //connessione DB
    public void GestoreRegistrationRequest(String nome_g,String email,String password,String nome_s,String telefono_s,String indirizzo_s,String città_s,String nome_c,String sport_c){

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

            newStruttura= new Struttura();
            newStruttura.setmNome(nome_s);
            newStruttura.setmTelefono(telefono_s);
            newStruttura.setmIndirizzo(indirizzo_s);
            newStruttura.setmCittà(città_s);

            newCampo= new Campo();
            newCampo.setmNome(nome_c);
            newCampo.setmSport(sport_c);


            newGestore =new Gestore();
            newGestore.setmNome(nome_g);
            newGestore.setmEmail(email);
            newGestore.setmPass(password);

            mGestoreTable =mClient.getTable("Gestore",Gestore.class);
            mStrutturaTable = mClient.getTable("Struttura",Struttura.class);
            mCampoTable = mClient.getTable("Campo",Campo.class);

            registrazioneNuovaStruttura(newStruttura,newCampo,newGestore,email,nome_c,nome_s);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void inserisciGestore(Gestore gestore)throws MobileServiceException,ExecutionException,InterruptedException{
        mGestoreTable.insert(gestore).get();

    }

    public List<Gestore> riceviInfoGestoriRegistrati(String email)throws MobileServiceException,ExecutionException,InterruptedException{
        return mGestoreTable.where().field("email_u").eq(email).execute().get();}


    public void inserisciStruttura(Struttura struttura)throws MobileServiceException,ExecutionException,InterruptedException{
        mStrutturaTable.insert(struttura).get();

    }

    public List<Struttura> riceviInfoStruttureRegistrate(String nome_s)throws MobileServiceException,ExecutionException,InterruptedException{
        return mStrutturaTable.where().field("nome_s").eq(nome_s).execute().get();}


    public void inserisciCampo(Campo campo)throws MobileServiceException,ExecutionException,InterruptedException{
        mCampoTable.insert(campo).get();

    }
    public List<Campo> riceviInfoCampiRegistrati(String email)throws MobileServiceException,ExecutionException,InterruptedException{
        return mCampoTable.where().field("email_u").eq(email).execute().get();}

    public void registrazioneNuovaStruttura(final Struttura s,final Campo c,final Gestore g,final String email,final String nome_c,final String nome_s){
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
                    final List<Struttura> results = riceviInfoStruttureRegistrate(nome_s);
                    if(results.isEmpty()){
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
                    try {
                        inserisciStruttura(s);
                    } catch (MobileServiceException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    registrazioneNuovoCampoEGestore(c,g,email,nome_s,nome_c);
                }else{
                    Toast toast= Toast.makeText(context,"sono stati riscontrati degli errori durante l'inserimento della struttura",Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        };

        runAsyncTask(task);
    }

    public void registrazioneNuovoCampoEGestore(final Campo c,final Gestore g,final String email,final String nome_s,final String nome_c){
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
                    final List<Struttura> result_s = riceviInfoStruttureRegistrate(nome_s);
                    final List<Gestore> result_g = riceviInfoGestoriRegistrati(email);
                    final List<Campo> result_c = riceviInfoCampiRegistrati(nome_c);
                    if(result_s.isEmpty()){
                        return 1;
                    }else if(result_g.isEmpty() || result_c.isEmpty()){
                        return 1;
                    }else if(result_g.isEmpty() && result_c.isEmpty()){
                        Struttura temp = result_s.get(0);
                        String temp2 = temp.getmId();
                        g.setmFK_struttura(temp2);
                        c.setmFK_struttura(temp2);
                        inserisciGestore(g);
                        inserisciCampo(c);
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
                    intent.putExtra("IS_UTENTE",false);
                    intent.putExtra("IS_GESTORE",true);
                    context.startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(context,"l'email inserita è già registrata.\nRiprova inserendo una email diversa.",Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent= new Intent(context,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("IS_UTENTE",false);
                    intent.putExtra("IS_GESTORE",true);
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


