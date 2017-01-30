package com.project.is.sportlink.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.project.is.sportlink.dataModel.Prenotazione;
import com.project.is.sportlink.R;
import com.project.is.sportlink.logic.VisualizzaPrenotazioniController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by luciogrimaldi on 23/01/17.
 */

public class VisualizzaPrenotazioniFragment extends Fragment {

    private VisualizzaPrenotazioniAdapter visualizzaPrenotazioniAdapter;
    private VisualizzaPrenotazioniController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_visualizza_prenotazioni_utente, container, false);

        visualizzaPrenotazioniAdapter = new VisualizzaPrenotazioniAdapter(getActivity().getApplicationContext(), R.layout.list_element_prenotazione_attiva_utente);
        ListView listViewListaPrenotazioniUtente = (ListView)v.findViewById(R.id.list_view_visualizza_prenotazioni_utente);
        listViewListaPrenotazioniUtente.setAdapter(visualizzaPrenotazioniAdapter);

        controller=new VisualizzaPrenotazioniController(this);
        controller.ottieniPrenotazioniUtente();

        return v;
    }

    public void onTaskCompleted(List<Prenotazione> prenotazioni){
        List<Prenotazione> risultati=new ArrayList<>();
        Calendar oggi = Calendar.getInstance();
        for (Prenotazione i:prenotazioni){
            String data_p=i.getmData_p();
            int giorno=Integer.parseInt(data_p.substring(0,2));
            int mese=Integer.parseInt(data_p.substring(3,5));
            int anno=Integer.parseInt(data_p.substring(6));
            Calendar pren =Calendar.getInstance();
            pren.set(anno,mese-1,giorno);
            if(oggi.after(pren)){
                risultati.add(i);
            }
        }
        prenotazioni.removeAll(risultati);
        if(prenotazioni.isEmpty()){
            Toast.makeText(this.getActivity(),"Non sono state trovate prenotazioni attive!",Toast.LENGTH_LONG).show();
        }else {
        visualizzaPrenotazioniAdapter.addAll(prenotazioni);
        }
    }
}
