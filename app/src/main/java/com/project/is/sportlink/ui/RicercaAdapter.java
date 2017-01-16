package com.project.is.sportlink.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.project.is.sportlink.DataModel.Campo;
import com.project.is.sportlink.DataModel.Utente;
import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 15/01/17.
 */

public class RicercaAdapter extends ArrayAdapter<Campo> {

    Context mContext;
    int mElementLayout;

    public RicercaAdapter(Context context, int elementLayout){
        super(context, elementLayout);

        mContext = context;
        mElementLayout = elementLayout;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;

        final Campo currentItem = getItem(position);

        if (element == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            element = inflater.inflate(mElementLayout, parent, false);
        }

        element.setTag(currentItem);

        Campo Campo = (Campo)getItem(position);

        TextView textViewNomeStruttura = (TextView)element.findViewById(R.id.nomeStrutturaRisultati);
        textViewNomeStruttura.setText(currentItem.getmNome_s());

        TextView textViewNomeCampo = (TextView)element.findViewById(R.id.nomeCampoRisultati);
        textViewNomeCampo.setText(currentItem.getmNome());

        TextView textViewIndirizzo = (TextView)element.findViewById(R.id.indirizzoRisultati);
        textViewIndirizzo.setText(currentItem.getmId());

        Button buttonPrenota = (Button)element.findViewById(R.id.buttonPrenota);
        buttonPrenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return element;
    }
}
