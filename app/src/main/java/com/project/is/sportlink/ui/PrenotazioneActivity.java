package com.project.is.sportlink.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.project.is.sportlink.R;
import com.project.is.sportlink.logic.PrenotazioneController;

import java.util.List;
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
    private PrenotazioneController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione);

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
        SharedPreferences sharedPref = getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE);
        mIdUtente=sharedPref.getString("UTENTE_ID",null);
        Log.d("debug",mIdUtente+" ");

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
        controller.registazioneNuovaPrenotazione(dataSelezionata,mIdUtente,id_c,orarioPrenotazione);
        }else{
            Toast.makeText(this,"Mi dispiace ma non è stato possibile effettuare la prenotazione.\nRiprova",Toast.LENGTH_LONG).show();
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
        controller=new PrenotazioneController(this);
        controller.impostaOrariDisponibiliComboBox(dataSelezionata,id_c);
    }

    public void onTaskCompleted(List<String> _response)
    {
        createSpinnerContentFromArray(spinnerOrari,_response);
    }
}


