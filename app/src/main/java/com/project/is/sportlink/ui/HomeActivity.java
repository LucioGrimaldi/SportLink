package com.project.is.sportlink.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.is.sportlink.R;
import java.util.logging.Logger;

public class HomeActivity extends AppCompatActivity implements RicercaFragment.RicercaListener{

    private Logger logger = Logger.getLogger("log");
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageView searchHomeButton;
    private TextView textViewVisualizzazionePrenotazioniUtente;
    private TextView textViewNavHeaderNomeCognomeUtente;
    private TextView textViewNavHeaderEmailUtente;
    private HomeFragment homeFragment;
    private String mIdUtente;
    private String mIdGestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_utente);

        searchHomeButton = (ImageView)findViewById(R.id.search_button_home);
        searchHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.info("ON CLICK SEARCH BUTTON");
                setFragmentRicerca();
            }
        });

        textViewVisualizzazionePrenotazioniUtente = (TextView)findViewById(R.id.text_view_visualizza_prenotazioni_utente);
        textViewVisualizzazionePrenotazioniUtente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragmentVisualizzaPrenotazioni();
            }
        });

        mIdUtente=getIntent().getStringExtra("UTENTE_ID");
        mIdGestore=getIntent().getStringExtra("GESTORE_ID");
        //Visualizza il fragment iniziale nella home
        setFragmentHome();
        SharedPreferences sharedPref = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("UTENTE_ID",mIdUtente);
        editor.putString("GESTORE_ID",mIdGestore);
        editor.apply();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        textViewNavHeaderNomeCognomeUtente = (TextView)findViewById(R.id.textViewSideNavHeaderNomeCognomeUtente);

    }

    public void setFragmentHome(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
        fragmentTransaction.commit();
    }

    public void setFragmentVisualizzaPrenotazioni(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //Visualizzazione del fragment per vedere le prenotazioni attive dell'utente nella home
        VisualizzaPrenotazioniFragment visualizzaPrenotazioniFragment = new VisualizzaPrenotazioniFragment();
        fragmentTransaction.replace(R.id.fragment_container, visualizzaPrenotazioniFragment);
        fragmentTransaction.addToBackStack(null);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        fragmentTransaction.commit();

    }

    public void setFragmentRicerca(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        //Visualizzazione del fragment contenente la ricerca della città
        RicercaFragment ricercaFragment = new RicercaFragment();
        fragmentTransaction.replace(R.id.fragment_container, ricercaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


    }

    @Override
    public void effettuaRicerca(String città) {
        SharedPreferences sharedPref = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("città",città);
        editor.apply();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        RisultatiRicercaFragment risultatiRicercaFragment = new RisultatiRicercaFragment();
        fragmentTransaction.replace(R.id.fragment_container, risultatiRicercaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

}
