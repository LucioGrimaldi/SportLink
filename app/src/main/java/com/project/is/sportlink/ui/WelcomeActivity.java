package com.project.is.sportlink.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 20/12/16.
 * Version 1.0
 */

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    TextView welcomeMessageTextView;
    TextView welcomeAnswerMesssageTextView;
    TextView userTextView;
    TextView managerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeMessageTextView = (TextView) findViewById(R.id.welcome_message_text_view);
        welcomeAnswerMesssageTextView = (TextView) findViewById(R.id.welcome_answer_message_text_view);
        userTextView = (TextView) findViewById(R.id.user_text_view);
        managerTextView = (TextView) findViewById(R.id.manager_text_view);

        userTextView.setOnClickListener(this);
        managerTextView.setOnClickListener(this);

        //Set custom fonts
        Typeface RobotoThinFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        welcomeMessageTextView.setTypeface(RobotoThinFont);
        welcomeAnswerMesssageTextView.setTypeface(RobotoThinFont);
        userTextView.setTypeface(RobotoThinFont);
        managerTextView.setTypeface(RobotoThinFont);
    }

    @Override
    public void onClick(View v) {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

    }
}
