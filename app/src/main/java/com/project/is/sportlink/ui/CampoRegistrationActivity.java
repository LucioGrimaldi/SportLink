package com.project.is.sportlink.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 09/01/17.
 */

public class CampoRegistrationActivity extends AppCompatActivity {

    Button buttonSignUp;
    EditText editTextNomeCampo;
    Spinner spinner;

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
    }

    public void checkFormCampo(View v){
        if(isEmpty(editTextNomeCampo)){
            Toast.makeText(getApplicationContext(), "Per favore inserire tutti i campi", Toast.LENGTH_SHORT).show();
        }
        else {
            signUpGestore();
        }
    }
    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

    private void signUpGestore(){

    }
}


