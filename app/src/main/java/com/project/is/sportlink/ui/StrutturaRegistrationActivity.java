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
 * Created by luciogrimaldi on 09/01/17.
 */

public class StrutturaRegistrationActivity extends AppCompatActivity {

    Button buttonAvantiStruttura;
    EditText editTextNomeStruttura;
    EditText editTextTelefonoStruttura;
    EditText editTextIndirizzoStruttura;
    EditText editTextCittaStruttura;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_struttura);

        editTextNomeStruttura = (EditText)findViewById(R.id.editTextNomeStruttura);
        editTextTelefonoStruttura = (EditText)findViewById(R.id.editTextNumeroTelefonoStruttura);
        editTextIndirizzoStruttura = (EditText)findViewById(R.id.ediTextIndirizzoStruttura);
        editTextCittaStruttura = (EditText)findViewById(R.id.ediTextCittaStruttura);
        buttonAvantiStruttura = (Button)findViewById(R.id.button_avanti_struttura);
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
        Intent i = new Intent(this, CampoRegistrationActivity.class);
        startActivity(i);
    }
}
