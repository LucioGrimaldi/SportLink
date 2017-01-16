package com.project.is.sportlink.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class RisultatiRicercaFragment extends Fragment {

    RicercaAdapter ricercaAdapter;
    ListView listViewRisultati;

    public RisultatiRicercaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_risultati_ricerca, container, false);

        ricercaAdapter = new RicercaAdapter(getActivity(), R.layout.list_element_risultati_ricerca);
        listViewRisultati = (ListView)v.findViewById(R.id.listViewRisultatiRicerca);
        listViewRisultati.setAdapter(ricercaAdapter);

        return v;
    }
}
