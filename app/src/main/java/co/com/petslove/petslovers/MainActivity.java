package co.com.petslove.petslovers;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import co.com.petslove.petslovers.fragments.RedSocial;
import co.com.petslove.petslovers.fragments.home;
import co.com.petslove.petslovers.fragments.profile;
import co.com.petslove.petslovers.fragments.search;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, home.OnFragmentInteractionListener, profile.OnFragmentInteractionListener, search.OnFragmentInteractionListener, RedSocial.OnFragmentInteractionListener {

    private RedSocial redSocial;
    private CardView filtro;
    private Button perro, gato, vaca, cerdo, pez, ave, caballo, oveja;
    private BottomNavigationView menuInferior;
    private home home;
    private search search;
    private profile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        referenciar();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        filtro.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, redSocial).commit();


        menuInferior.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        filtro.setVisibility(View.GONE);
                        transaction.replace(R.id.mainFrame, redSocial).commit();
                        break;
                    case R.id.ic_ventaAdopcion:
                        filtro.setVisibility(View.VISIBLE);
                        transaction.replace(R.id.mainFrame, home).commit();
                        break;
                    case R.id.ic_search:
                        filtro.setVisibility(View.GONE);
                        transaction.replace(R.id.mainFrame, search).commit();
                        break;
                    case R.id.ic_profile:
                        filtro.setVisibility(View.GONE);
                        transaction.replace(R.id.mainFrame, profile).commit();
                        break;
                }
            }
        });


    }

    private void referenciar() {
        redSocial = new RedSocial();
        home = new home();
        search = new search();
        profile = new profile();
        filtro = findViewById(R.id.filtroBusqueda);
        perro = findViewById(R.id.btnPerro);
        gato = findViewById(R.id.btnGato);
        vaca = findViewById(R.id.btnVaca);
        cerdo = findViewById(R.id.btnCerdo);
        pez = findViewById(R.id.btnPez);
        ave = findViewById(R.id.btnAve);
        caballo = findViewById(R.id.btnCaballo);
        oveja = findViewById(R.id.btnOveja);
        menuInferior = findViewById(R.id.menuInferior);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
