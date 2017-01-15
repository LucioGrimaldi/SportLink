package com.project.is.sportlink.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.is.sportlink.R;
import com.project.is.sportlink.logic.UserRegistrationController;

/**
 * Created by luciogrimaldi on 29/12/16.
 */

public class UtenteRegistrationActivity extends AppCompatActivity {

    private EditText editTextNomeUtente;
    private EditText editTextCognomeUtente;
    private EditText editTextTelefonoUtente;
    private EditText editTextEmailUtente;
    private EditText editTextPasswordUtente;
    private Button registrationButton;
    private UserRegistrationController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user);

        editTextNomeUtente = (EditText)findViewById(R.id.editTextNomeUtente);
        editTextCognomeUtente = (EditText)findViewById(R.id.editTextCognomeUtente);
        editTextTelefonoUtente = (EditText)findViewById(R.id.ediTextTelefonoUtente);
        editTextEmailUtente = (EditText)findViewById(R.id.editTextEmailUtente);
        editTextPasswordUtente = (EditText)findViewById(R.id.ediTextPasswordUtente);
        registrationButton = (Button) findViewById(R.id.signup_button_utente);
        controller= new UserRegistrationController(this);

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFormUtente(view);
            }
        });


    }

    public void checkFormUtente(View v){
        if(isEmpty(editTextNomeUtente) || isEmpty(editTextCognomeUtente) || isEmpty(editTextTelefonoUtente) || isEmpty(editTextEmailUtente) || isEmpty(editTextPasswordUtente)){
            Toast.makeText(getApplicationContext(), "Per favore inserire tutti i campi", Toast.LENGTH_SHORT).show();
        }
        else{
            signUpUtente();
        }
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    private void signUpUtente(){
        controller.userRegistrationRequest(getNome(),getCognome(),getEmail(),getPassword(),getTelefono());
    }

    public String getEmail(){
        return editTextEmailUtente.getText().toString();
    }

    public String getPassword(){return editTextPasswordUtente.getText().toString();}

    public String getNome(){
        return editTextNomeUtente.getText().toString();
    }

    public String getCognome(){return editTextCognomeUtente.getText().toString();}

    public String getTelefono(){return editTextTelefonoUtente.getText().toString();}
}

