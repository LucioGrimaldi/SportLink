package com.project.is.sportlink.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.project.is.sportlink.R;

import java.util.logging.Logger;

/**
 * Created by luciogrimaldi on 20/12/16.
 * Version 1.0
 */

public class WelcomeActivity extends AppCompatActivity{

    boolean IS_UTENTE;
    boolean IS_GESTORE;
    TextView welcomeMessageTextView;
    TextView welcomeAnswerMesssageTextView;
    TextView utenteTextView;
    TextView gestoreTextView;
    Logger logger = Logger.getLogger("DEBUG");

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeMessageTextView = (TextView) findViewById(R.id.welcome_message_text_view);
        welcomeAnswerMesssageTextView = (TextView) findViewById(R.id.welcome_answer_message_text_view);
        utenteTextView = (TextView) findViewById(R.id.user_text_view);
        gestoreTextView = (TextView) findViewById(R.id.manager_text_view);


        //cliccando sulla utenteTextView IS_UTENTE sarà true e IS_GESTORE sarà false
        //e i valori verranno passati alla login activity
        utenteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IS_UTENTE = true;
                IS_GESTORE = false;
                logger.info("IS_UTENTE = " + IS_UTENTE + " " + "IS_GESTORE = " + IS_GESTORE);
                loadLoginActivity();

            }
        });

        //cliccando sulla gestoreTextView IS_UTENTE sarà false e IS_GESTORE sarà true
        //e i valori verranno passati alla login activity
        gestoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IS_UTENTE = false;
                IS_GESTORE = true;
                logger.info("IS_UTENTE = " + IS_UTENTE + " " + "IS_GESTORE = " + IS_GESTORE);
                loadLoginActivity();
            }
        });

        //Set custom fonts
        Typeface RobotoThinFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        welcomeMessageTextView.setTypeface(RobotoThinFont);
        welcomeAnswerMesssageTextView.setTypeface(RobotoThinFont);
        utenteTextView.setTypeface(RobotoThinFont);
        gestoreTextView.setTypeface(RobotoThinFont);
    }

    public void loadLoginActivity(){
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("IS_UTENTE",IS_UTENTE);
        i.putExtra("IS_GESTORE",IS_GESTORE);
        i.putExtra("IS_UTENTE", IS_UTENTE);
        i.putExtra("IS_GESTORE", IS_GESTORE);
        startActivity(i);
    }

}
