package com.project.is.sportlink.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import com.project.is.sportlink.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class PrenotazioneActivity extends AppCompatActivity {

    private ImageButton backFromPrenotazioneButton;
    private Button buttonSelezionaData;
    private TextView textViewDataSelezionata;
    private TextView textViewNomeCampoRisultati;
    private TextView textViewNomeStrutturaRisultati;
    private TextView textViewIndirizzoRisultati;
    private String nomeCampo;
    private String nomeStruttura;
    private String indirizzo;
    private Spinner spinnerOrari;
    private Logger logger;

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
        nomeCampo = i.getStringExtra("NOME_CAMPO");
        nomeStruttura = i.getStringExtra("NOME_STRUTTURA");
        indirizzo = i.getStringExtra("INDIRIZZO");

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


        //Arraylist di prova per verifica dello spinner
        ArrayList<String> arrayListDiProva = new ArrayList<String>();
        arrayListDiProva.add("prova1");
        arrayListDiProva.add("prova2");
        arrayListDiProva.add("prova3");
        arrayListDiProva.add("prova4");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");
        arrayListDiProva.add("prova5");

        createSpinnerContentFromArray(spinnerOrari, arrayListDiProva);




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
/*
    public String getIdUtente(){

    }
*/
    public void effettuaPrentazione(View v){

        logger.info("nome campo = " + getNomeCampo() + " nome struttura = " + getNomeStruttura() + " indirizzo = " + getIndirizzo());

    }

    public void createSpinnerContentFromArray(Spinner spinner, List<String> arrayListResorce){

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListResorce);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}
