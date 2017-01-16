package com.project.is.sportlink.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 12/01/17.
 */

public class RicercaFragment extends Fragment {

    private EditText editTextRicercaCitta;
    private RicercaListener ricercaListener;

    public RicercaFragment(){

    }

    public interface RicercaListener{
        public void effettuaRicerca(String s);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ricercaListener = (RicercaListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement RicercaFragment.RicercaListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_ricerca, container, false);
        editTextRicercaCitta = (EditText)v.findViewById(R.id.editTextRicercaCitta);
        editTextRicercaCitta.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    HomeActivity.closeKeyboard(getActivity().getApplicationContext(), editTextRicercaCitta.getWindowToken());
                    ricercaListener.effettuaRicerca(getCittaFromEditText());

                    return true;
                }
                return false;
            }
        });
        return v;
    }

    public String getCittaFromEditText(){
        return editTextRicercaCitta.getText().toString();
    }
}
