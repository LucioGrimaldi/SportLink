package com.project.is.sportlink.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.DataModel.Prenotazione;
import com.project.is.sportlink.R;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class PrenotazioneActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener{

    private ImageButton backFromPrenotazioneButton;
    private Button buttonSelezionaData;
    private TextView textViewDataSelezionata;
    private TextView textViewNomeCampoRisultati;
    private TextView textViewNomeStrutturaRisultati;
    private TextView textViewIndirizzoRisultati;
    private String nomeCampo;
    private String id_c;
    private String nomeStruttura;
    private String indirizzo;
    private String mIdUtente;
    private String dataSelezionata;
    private Spinner spinnerOrari;
    private Logger logger;
    private String[] orari= new String[]{"09:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00"};
    private MobileServiceClient mClient;
    private MobileServiceTable<Prenotazione> mPrenotazioneTable;
    private List<String> orariDisp=new ArrayList<>();
    private ArrayList<String> helper=new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione);

        context=this;
        logger = Logger.getLogger("info");

        backFromPrenotazioneButton = (ImageButton)findViewById(R.id.backFromPrenotazione);
        backFromPrenotazioneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        textViewDataSelezionata = (TextView)findViewById(R.id.textViewDataSelezionata);
        textViewNomeCampoRisultati = (TextView)findViewById(R.id.textViewNomeCampoRisultati);
        textViewNomeStrutturaRisultati = (TextView)findViewById(R.id.textViewNomeStrutturaRisultati);
        textViewIndirizzoRisultati = (TextView)findViewById(R.id.textViewIndirizzoRisultati);

        Intent i = getIntent();
        id_c=i.getStringExtra("ID_CAMPO");
        nomeCampo = i.getStringExtra("NOME_CAMPO");
        nomeStruttura = i.getStringExtra("NOME_STRUTTURA");
        indirizzo = i.getStringExtra("INDIRIZZO");
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        mIdUtente=sharedPref.getString("UTENTE_ID",null);
        Log.d("debug",mIdUtente+" ");

        try {
            // Create the Mobile Service Client instance, using the provided
            // Mobile Service URL and key
            mClient = new MobileServiceClient(
                    "https://sportlinkservice.azurewebsites.net",
                    this);

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

        textViewNomeCampoRisultati.setText(nomeCampo);
        textViewNomeStrutturaRisultati.setText(nomeStruttura);
        textViewIndirizzoRisultati.setText(indirizzo);

        spinnerOrari = (Spinner)findViewById(R.id.spinner_orari);

        buttonSelezionaData = (Button)findViewById(R.id.button_seleziona_data);
        buttonSelezionaData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(),"datePickerFragment");

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public String getDataSelezionata(){
        return textViewDataSelezionata.getText().toString();
    }

    public String getNomeCampo(){
        return nomeCampo;
    }

    public String getNomeStruttura(){
        return nomeStruttura;
    }

    public String getIndirizzo(){
        return indirizzo;
    }

    public void effettuaPrentazione(View v){
        String orarioPrenotazione = spinnerOrari.getSelectedItem().toString();
        logger.info("nome campo = " + getNomeCampo() + " nome struttura = " + getNomeStruttura() + " indirizzo = " + getIndirizzo()+"id utente"+mIdUtente+"orario:"+orarioPrenotazione);
        if(id_c!=null && mIdUtente!=null && orarioPrenotazione!=null && dataSelezionata!=null){
        registazioneNuovaPrenotazione(dataSelezionata,mIdUtente,id_c,orarioPrenotazione);
        }else{
            Toast.makeText(context,"Mi dispiace ma non è stato possibile effettuare la prenotazione.\nRiprova",Toast.LENGTH_LONG).show();
        }
    }

    //questo metodo si occupa di creare un'adapter con le informazioni degli orari ed aggiungerlo allo spinner.
    public void createSpinnerContentFromArray(Spinner spinner, List<String> arrayListResorce){

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListResorce);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //questo metodo specifica le azioni da effettuare nel momento in cui viene selezionata la data nel DatePicker
    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        //Aggiungere +1 al mese perchè parte da 0
        dataSelezionata = dayOfMonth + "/" + monthOfYear + 1 + "/" + year;
        logger.info("DATA SELEZIONATA = " + dataSelezionata);
        textViewDataSelezionata.setText(dataSelezionata);
        impostaOrariDisponibiliComboBox(dataSelezionata,id_c);
    }

    //in questo metodo vengono prese le informazioni riguardanti le prenotazioni effettuate in un determinato giorno per un determinato campo
    //e vengono calcolati gli orari ancora disponibili.
    public void impostaOrariDisponibiliComboBox(final String data_p,final String FK_campo){
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
                    List<Prenotazione> results=mPrenotazioneTable.where().field("data_p").eq(data_p).and().field("FK_campo").eq(FK_campo).execute().get();

                    for (int i = 0; i < orari.length; i++) {
                        orariDisp.add(orari[i]);
                        helper.add(orari[i]);
                    }

                    if(results.isEmpty()){
                        return 1;
                    }else {
                        for (Prenotazione a : results) {
                            String orarioTemp = a.getmOrario();
                            for (String i : helper) {
                                if (i.equals(orarioTemp)) {
                                    orariDisp.remove(i);
                                }
                            }
                        }
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
                if(dialog.isShowing()){dialog.dismiss();}
                if(r==1&&orariDisp.isEmpty()){
                    Toast toast=Toast.makeText(context,"non è sono stati trovati orari disponibili per il giorno selezionato!",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    createSpinnerContentFromArray(spinnerOrari,orariDisp);
                    Toast toast=Toast.makeText(context,"seleziona l'orario per la prenotazione tra quelli disponibili!",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        };

        runAsyncTask(task);
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

}


