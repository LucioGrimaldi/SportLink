package com.project.is.sportlink.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.project.is.sportlink.DataModel.Campo;
import com.project.is.sportlink.DataModel.Struttura;
import com.project.is.sportlink.R;
import com.project.is.sportlink.logic.RicercaController;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class RisultatiRicercaFragment extends Fragment {


    private RicercaAdapter ricercaAdapter;
    private ListView listViewRisultati;
    private RicercaController controller;
    private String città,utenteId;

    public RisultatiRicercaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_risultati_ricerca, container, false);

        ricercaAdapter = new RicercaAdapter(getActivity().getApplicationContext(), R.layout.list_element_risultati_ricerca);
        listViewRisultati = (ListView)v.findViewById(R.id.listViewRisultatiRicerca);
        listViewRisultati.setAdapter(ricercaAdapter);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("sharedPrefs",Context.MODE_PRIVATE);
        città=sharedPref.getString("città",null);
        utenteId=sharedPref.getString("UTENTE_ID",null);

        controller=new RicercaController(this);
        controller.ricercaRequest(città);

        return v;
    }

    public void onTaskCompleted(List<Campo> risultati){
        ricercaAdapter.clear();
        if(risultati.isEmpty()) {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(),"La tua ricerca non ha prodotto risultati.",Toast.LENGTH_LONG);
            toast.show();
        }else{

            ricercaAdapter.addAll(risultati);
        }
    }
}
