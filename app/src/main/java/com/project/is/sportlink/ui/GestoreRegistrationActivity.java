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

/**
 * Created by luciogrimaldi on 08/01/17.
 */

public class GestoreRegistrationActivity extends AppCompatActivity{

    Button buttonAvantiGestore;
    EditText editTextNomeGestore;
    EditText editTextCognomeGestore;
    EditText editTextEmailGestore;
    EditText editTextPasswordGestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_gestore);

        editTextNomeGestore = (EditText)findViewById(R.id.editTextNomeGestore);
        editTextCognomeGestore = (EditText)findViewById(R.id.editTextCognomeGestore);
        editTextEmailGestore = (EditText)findViewById(R.id.editTextEmailGestore);
        editTextPasswordGestore = (EditText)findViewById(R.id.ediTextPasswordGestore);
        buttonAvantiGestore = (Button)findViewById(R.id.button_avanti_gestore);

    }

    public void checkFormGestore(View v){
        if(isEmpty(editTextNomeGestore) || isEmpty(editTextCognomeGestore) || isEmpty(editTextEmailGestore) || isEmpty(editTextPasswordGestore)){
            Toast.makeText(getApplicationContext(), "Per favore inserire tutti i campi", Toast.LENGTH_SHORT).show();
        }
        else{
            openStrutturaSignUpForm();
        }
    }

    public void openStrutturaSignUpForm(){
        Intent i = new Intent(this, StrutturaRegistrationActivity.class);
        startActivity(i);
    }

    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }
}
