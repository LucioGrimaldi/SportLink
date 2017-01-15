package com.project.is.sportlink.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.project.is.sportlink.R;
import java.util.logging.Logger;

public class HomeActivity extends AppCompatActivity implements RicercaFragment.RicercaListener{

    Logger logger = Logger.getLogger("log");
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ImageView searchHomeButton;
    RisultatiRicercaFragment risultatiRicercaFragment;
    RicercaAdapter ricercaAdapter;
    ListView listViewRisultati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_utente);

        ricercaAdapter = new RicercaAdapter(this, R.layout.list_element_risultati_ricerca);
        listViewRisultati = (ListView)findViewById(R.id.listViewRisultatiRicerca);
        listViewRisultati.setAdapter(ricercaAdapter);

        risultatiRicercaFragment = new RisultatiRicercaFragment();

        searchHomeButton = (ImageView)findViewById(R.id.search_button_home);
        searchHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.info("ON CLICK SEARCH BUTTON");
                setFragmentRicerca();
            }
        });

        //Visualizza il fragment iniziale nella home
        setFragmentHome();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    public void setFragmentHome(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment);
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
    public void effettuaRicerca(String s) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, risultatiRicercaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
