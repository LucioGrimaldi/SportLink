package com.project.is.sportlink.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.is.sportlink.dataModel.Prenotazione;
import com.project.is.sportlink.R;

/**
 * Created by luciogrimaldi on 24/01/17.
 */

public class VisualizzaPrenotazioniAdapter extends ArrayAdapter<Prenotazione> {

    private Context mContext;
    private int mElementLayout;
    private TextView textViewNomeStruttura;
    private TextView textViewNomeCampo;
    private TextView textViewDataPrenotazione;
    private TextView textViewOrarioPrenotazione;
    private ImageView imageViewCampo;
    private Button buttonDisdici;

    public VisualizzaPrenotazioniAdapter(Context context, int elementLayout) {
        super(context, elementLayout);
        mContext = context;
        mElementLayout = elementLayout;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View element = convertView;

        Prenotazione currentItem = getItem(position);

        if (element == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            element = inflater.inflate(mElementLayout, parent, false);
        }
        element.setTag(currentItem);

        final String idPrenotazione = currentItem.getmId();
        final String orarioPrenotazione = currentItem.getmOrario();
        final String dataPrenotazione = currentItem.getmData_p();
        final String nomeStruttura = currentItem.getmNomeStruttura() ;
        final String nomeCampo = currentItem.getmNomeCampo();
        final String indirizzo = currentItem.getmIndirizzo();
        final String sport = currentItem.getmSport();


        textViewDataPrenotazione = (TextView)element.findViewById(R.id.dataPrenotazione);
        textViewDataPrenotazione.setText(dataPrenotazione);

        textViewOrarioPrenotazione = (TextView)element.findViewById(R.id.orarioPrenotazione);
        textViewOrarioPrenotazione.setText(orarioPrenotazione);

        textViewNomeCampo = (TextView)element.findViewById(R.id.nomeCampoPrenotazione);
        textViewNomeCampo.setText(nomeCampo);

        textViewNomeStruttura = (TextView)element.findViewById(R.id.nomeStrutturaPrenotazione);
        textViewNomeStruttura.setText(nomeStruttura);

        imageViewCampo = (ImageView)element.findViewById(R.id.imageViewCampo);

        switch(sport){
            case "Calcio a 5":
                imageViewCampo.setBackgroundResource(R.drawable.calcio_a_5);
                imageViewCampo.setAdjustViewBounds(true);
                break;
            case "Tennis":
                imageViewCampo.setBackgroundResource(R.drawable.tennis);
                imageViewCampo.setAdjustViewBounds(true);
                imageViewCampo.setCropToPadding(true);
                break;
            default:
                imageViewCampo.setBackgroundResource(R.drawable.default_placeholder);
                break;
        }



        buttonDisdici = (Button)element.findViewById(R.id.buttonDisdici);
        buttonDisdici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), DisdettaActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("ID_PRENOTAZIONE", idPrenotazione);
                i.putExtra("DATA_PRENOTAZIONE", dataPrenotazione);
                i.putExtra("ORARIO_PRENOTAZIONE", orarioPrenotazione);
                i.putExtra("NOME_CAMPO", nomeCampo);
                i.putExtra("NOME_STRUTTURA", nomeStruttura);
                i.putExtra("INDIRIZZO",indirizzo);
                i.putExtra("SPORT", sport);
                v.getContext().startActivity(i);
            }
        });
        return element;
    }
}
