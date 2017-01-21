package com.project.is.sportlink.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.project.is.sportlink.DataModel.Campo;
import com.project.is.sportlink.DataModel.Struttura;
import com.project.is.sportlink.DataModel.Utente;
import com.project.is.sportlink.R;

import java.util.List;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class RicercaAdapter extends ArrayAdapter<Campo> {

    private Context mContext;
    private int mElementLayout;


    public RicercaAdapter(Context context, int elementLayout){
        super(context, elementLayout);

        mContext = context;
        mElementLayout = elementLayout;
    }

    public interface OnClickPrenotaListener{
        void apriPrenotazioneCampo();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;

        Campo currentItem = getItem(position);

        if (element == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            element = inflater.inflate(mElementLayout, parent, false);
        }

        element.setTag(currentItem);
        final String id_c=currentItem.getmId();
        final String nome_s=currentItem.getmNome_s();
        final String nome_c=currentItem.getmNome();
        final String indirizzo=currentItem.getmIndirizzo_s();
        Log.d("Debug","ecco le info da inserire:"+nome_c+" "+nome_s+" "+indirizzo);
        TextView textViewNomeStruttura = (TextView)element.findViewById(R.id.nomeStrutturaRisultati);
        textViewNomeStruttura.setText(nome_s);

        TextView textViewNomeCampo = (TextView)element.findViewById(R.id.nomeCampoRisultati);
        textViewNomeCampo.setText(nome_c);

        TextView textViewIndirizzo = (TextView)element.findViewById(R.id.indirizzoRisultati);
        textViewIndirizzo.setText(indirizzo);

        Button buttonPrenota = (Button)element.findViewById(R.id.buttonPrenota);
        buttonPrenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), PrenotazioneActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID_CAMPO",id_c);
                i.putExtra("NOME_CAMPO", nome_c);
                i.putExtra("NOME_STRUTTURA", nome_s);
                i.putExtra("INDIRIZZO", indirizzo);
                v.getContext().startActivity(i);

            }
        });

        return element;
    }
}
