package com.project.is.sportlink.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.is.sportlink.R;
import com.project.is.sportlink.logic.DisdettaController;

import java.util.concurrent.ExecutionException;

/**
 * Created by luciogrimaldi on 24/01/17.
 */

public class DisdettaActivity extends AppCompatActivity {

    private TextView textViewDataPrenotazione;
    private TextView textViewOrarioSelezionato;
    private TextView textViewNomeCampoPrenotazione;
    private TextView textViewNomeStrutturaPrenotazione;
    private TextView textViewIndirizzoPrenotazione;
    private ImageButton buttonBackFromDisdetta;
    private ImageView imageViewBannerDisdetta;
    private String idPrenotazione;
    private String dataPrenotazione;
    private String orarioPrenotazione;
    private String nomeCampo;
    private String nomeStruttura;
    private String indirizzo;
    private String sport;
    private DisdettaController controller;
    private Button disdettaButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disdetta);

        textViewDataPrenotazione = (TextView)findViewById(R.id.dataPrenotazione);
        textViewOrarioSelezionato = (TextView)findViewById(R.id.orarioPrenotazione);
        textViewNomeCampoPrenotazione = (TextView)findViewById(R.id.textViewNomeCampoPrenotazione);
        textViewNomeStrutturaPrenotazione = (TextView)findViewById(R.id.textViewNomeStrutturaPrenotazione);
        textViewIndirizzoPrenotazione = (TextView) findViewById(R.id.textViewIndirizzoPrenotazione);
        disdettaButton = (Button) findViewById(R.id.bottoneDisdetta);

        buttonBackFromDisdetta = (ImageButton)findViewById(R.id.backFromDisdetta);
        buttonBackFromDisdetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent i = getIntent();
        idPrenotazione = i.getStringExtra("ID_PRENOTAZIONE");
        dataPrenotazione = i.getStringExtra("DATA_PRENOTAZIONE");
        orarioPrenotazione = i.getStringExtra("ORARIO_PRENOTAZIONE");
        nomeCampo = i.getStringExtra("NOME_CAMPO");
        nomeStruttura = i.getStringExtra("NOME_STRUTTURA");
        indirizzo = i.getStringExtra("INDIRIZZO");
        sport = i.getStringExtra("SPORT");
        controller=new DisdettaController(this,idPrenotazione);

        textViewOrarioSelezionato.setText(orarioPrenotazione);
        textViewDataPrenotazione.setText(dataPrenotazione);
        textViewNomeCampoPrenotazione.setText(nomeCampo);
        textViewNomeStrutturaPrenotazione.setText(nomeStruttura);
        textViewIndirizzoPrenotazione.setText(indirizzo);


        disdettaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    controller.requestRimuoviPrenotazione(idPrenotazione);
            }
        });

        imageViewBannerDisdetta = (ImageView)findViewById(R.id.imageViewBannerDisdetta);
        setImmagineBanner(imageViewBannerDisdetta, sport);
    }

    public void onTaskCompleted(int r){
        if(r==2){
            new AlertDialog.Builder(this).setIcon(R.drawable.ic_check_24dp).setTitle("Disdetta Effettuata!")
                    .setMessage("Hai effettuato la disdetta della prenotazione con successo!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();
                        }
                    }).show();
        }else {
            Toast toast=Toast.makeText(this,"Mi dispiace ma non è stato possibile effettuare la disdetta riprova!",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void setImmagineBanner(ImageView imageView, String sportPraticato) {

        switch (sportPraticato) {
            case "Calcio a 5":
                imageView.setBackgroundResource(R.drawable.calcio_a_5_banner_2);
                imageView.setAdjustViewBounds(true);
                break;
            case "Tennis":
                imageView.setBackgroundResource(R.drawable.tennis_banner);
                imageView.setAdjustViewBounds(true);
                break;
            default:
                imageView.setBackgroundResource(R.drawable.banner_placeholder);
                imageView.setAdjustViewBounds(true);
                break;
        }
    }
}
