package com.project.is.sportlink.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.is.sportlink.R;
import com.project.is.sportlink.logic.LoginController;

/**
 * Created by luciogrimaldi on 22/12/16.
 */

public class LoginActivity extends AppCompatActivity {

    private boolean IS_UTENTE;
    private boolean IS_GESTORE;
    private LoginController controller;
    private Button loginButton;
    private Button signUpButton;
    private TextView titleAppNameTextView;
    private EditText eMailEditText;
    private EditText passwordEditText;
    private String email;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        titleAppNameTextView = (TextView) findViewById(R.id.titleAppName);
        eMailEditText = (EditText) findViewById(R.id.editTextEmail);
        passwordEditText = (EditText) findViewById(R.id.ediTextPassword);
        loginButton=(Button)findViewById(R.id.login_button);
        signUpButton= (Button) findViewById(R.id.signup_button);

        //prendo i dati della welcome activity per sapere se è un utente o un gestore
        IS_UTENTE = getIntent().getBooleanExtra("IS_UTENTE",false);
        IS_GESTORE = getIntent().getBooleanExtra("IS_GESTORE",false);


        //Set custom font
        Typeface RobotoThinFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        titleAppNameTextView.setTypeface(RobotoThinFont);
        eMailEditText.setTypeface(RobotoThinFont);
        passwordEditText.setTypeface(RobotoThinFont);

        eMailEditText.setFocusableInTouchMode(true);

        controller= new LoginController(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpForm(view);
            }
        });

        IS_UTENTE = getIntent().getBooleanExtra("IS_UTENTE", false);
        IS_GESTORE = getIntent().getBooleanExtra("IS_GESTORE", false);
    }

    public String getEmailFromEditText(){
        return eMailEditText.getText().toString();
    }

    public String getPasswordFromEditText(){
        return passwordEditText.getText().toString();
    }

    public void openSignUpForm(View v){
        if (isUtente()) {
            Intent i = new Intent(this, UtenteRegistrationActivity.class);
            startActivity(i);
        }
        else if (isGestore()){
            Intent i = new Intent(this, GestoreRegistrationActivity.class);
            startActivity(i);
        }
    }

    public void login(View v){
        email=getEmailFromEditText();
        pass=getPasswordFromEditText();
        controller.LoginRequest(getEmailFromEditText(),getPasswordFromEditText(),IS_UTENTE);
    }

    public boolean isUtente(){
        if (IS_UTENTE) {
            return true;
        }
        else
            return false;
    }

    public boolean isGestore(){

        if (IS_GESTORE) {
            return true;
        }
        else
            return false;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
