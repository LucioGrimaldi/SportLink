package com.project.is.sportlink.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.is.sportlink.DataModel.Struttura;
import com.project.is.sportlink.R;
import com.project.is.sportlink.logic.GestoreRegistrationController;

/**
 * Created by luciogrimaldi on 09/01/17.
 */

public class CampoRegistrationActivity extends AppCompatActivity {

    private Button buttonSignUp;
    private EditText editTextNomeCampo;
    private Spinner spinner;
    private String nomeGestore;
    private String cognomeGestore;
    private String emailGestore;
    private String passwordGestore;
    private String nomeStruttura;
    private String telefonoStruttura;
    private String indirizzoStruttura;
    private String cittaStruttura;
    private GestoreRegistrationController controller;
    private String nome;
    private String sport;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_campo);

        editTextNomeCampo = (EditText)findViewById(R.id.editTextNomeCampo);
        buttonSignUp = (Button)findViewById(R.id.button_signup_campo);
        spinner = (Spinner)findViewById(R.id.spinner_selezione_sport);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sport_selezionabili, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Setta il colore della voce selezionata dal menù a tendina
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent i = getIntent();
        nomeGestore = i.getStringExtra("NOME_GESTORE");
        cognomeGestore = i.getStringExtra("COGNOME_GESTORE");
        emailGestore =  i.getStringExtra("EMAIL_GESTORE");
        passwordGestore = i.getStringExtra("PASSWORD_GESTORE");
        nomeStruttura = i.getStringExtra("NOME_STRUTTURA");
        telefonoStruttura = i.getStringExtra("TELEFONO_STRUTTURA");
        indirizzoStruttura = i.getStringExtra("INDIRIZZO_STRUTTURA");
        cittaStruttura = i.getStringExtra("CITTA_STRUTTURA");


    }

    public String getNomeCampo(){
        return editTextNomeCampo.getText().toString();
    }

    public String getTipoSport(){
        return spinner.getSelectedItem().toString();
    }

    public String getNomeGestore() {
        return nomeGestore;
    }

    public String getCognomeGestore() {
        return cognomeGestore;
    }

    public String getEmailGestore() {
        return emailGestore;
    }

    public String getPasswordGestore() {
        return passwordGestore;
    }

    public String getNomeStruttura() {
        return nomeStruttura;
    }

    public String getTelefonoStruttura() {
        return telefonoStruttura;
    }

    public String getIndirizzoStruttura() {
        return indirizzoStruttura;
    }

    public String getCittaStruttura() {
        return cittaStruttura;
    }

    public void checkFormCampo(View v){
        if(isEmpty(editTextNomeCampo)){
            Toast.makeText(getApplicationContext(), "Per favore inserire tutti i campi", Toast.LENGTH_SHORT).show();
        }
        else {
            nome=editTextNomeCampo.getText().toString();
            sport=spinner.getSelectedItem().toString();
            signUpGestore();
        }
    }
    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    private void signUpGestore(){
        controller= new GestoreRegistrationController(this);
        Log.d("Prova","nome struttura"+getNomeStruttura());
        controller.GestoreRegistrationRequest(getNomeGestore(),getCognomeGestore(),getEmailGestore(),getPasswordGestore(),getNomeStruttura(),getTelefonoStruttura(),getIndirizzoStruttura(),getCittaStruttura(),getNomeCampo(),getTipoSport());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}


