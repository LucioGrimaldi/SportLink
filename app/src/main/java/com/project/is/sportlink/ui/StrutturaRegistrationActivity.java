package com.project.is.sportlink.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 09/01/17.
 */

public class StrutturaRegistrationActivity extends AppCompatActivity {

    private Button buttonAvantiStruttura;
    private EditText editTextNomeStruttura;
    private EditText editTextTelefonoStruttura;
    private EditText editTextIndirizzoStruttura;
    private EditText editTextCittaStruttura;
    private String nomeGestore;
    private String cognomeGestore;
    private String emailGestore;
    private String passwordGestore;
    private String nome;
    private String citta;
    private String telefono;
    private String indirizzo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_struttura);

        editTextNomeStruttura = (EditText)findViewById(R.id.editTextNomeStruttura);
        editTextTelefonoStruttura = (EditText)findViewById(R.id.editTextNumeroTelefonoStruttura);
        editTextIndirizzoStruttura = (EditText)findViewById(R.id.ediTextIndirizzoStruttura);
        editTextCittaStruttura = (EditText)findViewById(R.id.ediTextCittaStruttura);
        buttonAvantiStruttura = (Button)findViewById(R.id.button_avanti_struttura);

        Intent i = getIntent();
        nomeGestore = i.getStringExtra("NOME_GESTORE");
        cognomeGestore = i.getStringExtra("COGNOME_GESTORE");
        emailGestore =  i.getStringExtra("EMAIL_GESTORE");
        passwordGestore = i.getStringExtra("PASSWORD_GESTORE");

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

    public String getNomeStrutturaFromEditText(){
        return editTextNomeStruttura.getText().toString();
    }

    public String getTelefonoStrutturaFromEditText(){
        return editTextTelefonoStruttura.getText().toString();
    }

    public String getIndirizzoStrutturaFromEditText(){
        return editTextIndirizzoStruttura.getText().toString();
    }

    public String getCittaStrutturaFromEditText(){
        return editTextCittaStruttura.getText().toString();
    }

    public void checkFormStruttura(View v){
        if(isEmpty(editTextNomeStruttura) || isEmpty(editTextTelefonoStruttura) || isEmpty(editTextIndirizzoStruttura) || isEmpty(editTextCittaStruttura)){
            Toast.makeText(getApplicationContext(), "Per favore inserire tutti i campi", Toast.LENGTH_SHORT).show();
        }
        else {
            openCampoSignUpForm();
        }
    }
    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }
    public void openCampoSignUpForm(){
        nome=getNomeStrutturaFromEditText();
        citta=getCittaStrutturaFromEditText();
        indirizzo=getIndirizzoStrutturaFromEditText();
        telefono=getTelefonoStrutturaFromEditText();

        Intent i = new Intent(this, CampoRegistrationActivity.class);
        i.putExtra("NOME_GESTORE", getNomeGestore());
        i.putExtra("COGNOME_GESTORE", getCognomeGestore());
        i.putExtra("EMAIL_GESTORE", getEmailGestore());
        i.putExtra("PASSWORD_GESTORE", getPasswordGestore());
        i.putExtra("NOME_STRUTTURA", getNomeStrutturaFromEditText());
        i.putExtra("TELEFONO_STRUTTURA", getTelefonoStrutturaFromEditText());
        i.putExtra("INDIRIZZO_STRUTTURA", getIndirizzoStrutturaFromEditText());
        i.putExtra("CITTA_STRUTTURA", getCittaStrutturaFromEditText());
        startActivity(i);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
