package com.project.is.sportlink.ui;

import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 22/12/16.
 */

public class LoginActivity extends AppCompatActivity {

    TextView titleAppNameTextView;
    EditText eMailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        titleAppNameTextView = (TextView) findViewById(R.id.titleAppName);
        eMailEditText = (EditText) findViewById(R.id.editTextEmail);
        passwordEditText = (EditText) findViewById(R.id.ediTextPassword);

        //Set custom font
        Typeface RobotoThinFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        titleAppNameTextView.setTypeface(RobotoThinFont);
        eMailEditText.setTypeface(RobotoThinFont);
        passwordEditText.setTypeface(RobotoThinFont);

        eMailEditText.setFocusableInTouchMode(true);
    }

    public void openSignUpForm(){

    }

    public boolean isUtente(){

        return true;
    }

    public boolean isGestore(){

        return true;
    }

}
