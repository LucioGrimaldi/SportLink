package com.project.is.sportlink.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 24/01/17.
 */

public class DisdettaActivity extends AppCompatActivity {

    private TextView textViewDataPrenotazione;
    private TextView textViewOrarioSelezionato;
    private TextView textViewNomeCampoPrenotazione;
    private TextView textViewNomeStrutturaPrenotazione;
    private TextView textViewIndirizzoPrenotazione;
    private String idPrenotazione;
    private String dataPrenotazione;
    private String orarioPrenotazione;
    private String nomeCampo;
    private String nomeStruttura;
    private String indirizzo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disdetta);

        textViewDataPrenotazione = (TextView)findViewById(R.id.dataPrenotazione);
        textViewOrarioSelezionato = (TextView)findViewById(R.id.orarioPrenotazione);
        textViewNomeCampoPrenotazione = (TextView)findViewById(R.id.textViewNomeCampoPrenotazione);
        textViewNomeStrutturaPrenotazione = (TextView)findViewById(R.id.textViewNomeStrutturaPrenotazione);

        Intent i = getIntent();
        idPrenotazione = i.getStringExtra("ID_PRENOTAZIONE");
        dataPrenotazione = i.getStringExtra("DATA_PRENOTAZIONE");
        orarioPrenotazione = i.getStringExtra("ORARIO_PRENOTAZIONE");
        nomeCampo = i.getStringExtra("NOME_CAMPO");
//        nomeStruttura = i.getStringExtra("NOME_STRUTTURA");

        textViewOrarioSelezionato.setText(orarioPrenotazione);
        textViewDataPrenotazione.setText(dataPrenotazione);
        textViewNomeCampoPrenotazione.setText(nomeCampo);
//        textViewNomeStrutturaPrenotazione.setText(nomeStruttura);

    }
}
