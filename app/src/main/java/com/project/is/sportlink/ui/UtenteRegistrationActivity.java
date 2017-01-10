package com.project.is.sportlink.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 29/12/16.
 */

public class UtenteRegistrationActivity extends AppCompatActivity {

    EditText editTextNomeUtente;
    EditText editTextCognomeUtente;
    EditText editTextTelefonoUtente;
    EditText editTextEmailUtente;
    EditText editTextPasswordUtente;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user);

        editTextNomeUtente = (EditText)findViewById(R.id.editTextNomeUtente);
        editTextCognomeUtente = (EditText)findViewById(R.id.editTextCognomeUtente);
        editTextTelefonoUtente = (EditText)findViewById(R.id.ediTextTelefonoUtente);
        editTextEmailUtente = (EditText)findViewById(R.id.editTextEmailUtente);
        editTextPasswordUtente = (EditText)findViewById(R.id.ediTextPasswordUtente);
    }

    public void checkFormGestore(View v){
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

    }
}

