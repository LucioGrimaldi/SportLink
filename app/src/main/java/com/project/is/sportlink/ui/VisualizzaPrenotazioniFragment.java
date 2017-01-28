package com.project.is.sportlink.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project.is.sportlink.dataModel.Prenotazione;
import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 23/01/17.
 */

public class VisualizzaPrenotazioniFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_visualizza_prenotazioni_utente, container, false);

        VisualizzaPrenotazioniAdapter visualizzaPrenotazioniAdapter = new VisualizzaPrenotazioniAdapter(getActivity().getApplicationContext(), R.layout.list_element_prenotazione_attiva_utente);
        ListView listViewListaPrenotazioniUtente = (ListView)v.findViewById(R.id.list_view_visualizza_prenotazioni_utente);
        listViewListaPrenotazioniUtente.setAdapter(visualizzaPrenotazioniAdapter);

        Prenotazione prenotazione1 = new Prenotazione("1","12/12/12","15:00-16:00", "FK_campo", "FK_struttura");
        visualizzaPrenotazioniAdapter.add(prenotazione1);

        return v;
    }
}
