package com.project.is.sportlink.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class RisultatiRicercaFragment extends Fragment {

    public RisultatiRicercaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_risultati_ricerca, container, false);
        return v;
    }
}
