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

import co.com.petslove.petslovers.fragments.Alimento;
import co.com.petslove.petslovers.fragments.AnimalDetail;
import co.com.petslove.petslovers.fragments.Estilista;
import co.com.petslove.petslovers.fragments.Paseador;
import co.com.petslove.petslovers.fragments.RedSocial;
import co.com.petslove.petslovers.fragments.Veterinaria;
import co.com.petslove.petslovers.fragments.home;
import co.com.petslove.petslovers.fragments.profile;
import co.com.petslove.petslovers.fragments.search;
import co.com.petslove.petslovers.interfaces.enviarDatos;
import co.com.petslove.petslovers.model.TransaccionPojo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, enviarDatos, AnimalDetail.OnFragmentInteractionListener, home.OnFragmentInteractionListener, profile.OnFragmentInteractionListener, search.OnFragmentInteractionListener, RedSocial.OnFragmentInteractionListener, Paseador.OnFragmentInteractionListener, Veterinaria.OnFragmentInteractionListener, Alimento.OnFragmentInteractionListener, Estilista.OnFragmentInteractionListener {

    private RedSocial redSocial;
    private CardView filtro;
    private Button perro, gato, vaca, cerdo, pez, ave, caballo, oveja;
    private BottomNavigationView menuInferior;
    private home home;
    private search search;
    private AnimalDetail animalDetail;
    private profile profile;
    private Alimento alimento;
    private Estilista estilista;
    private Paseador paseador;
    private Veterinaria veterinaria;


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
        alimento = new Alimento();
        veterinaria = new Veterinaria();
        estilista = new Estilista();
        paseador = new Paseador();
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.veterinarias) {
            filtro.setVisibility(View.GONE);
            transaction.replace(R.id.mainFrame, veterinaria).commit();
        } else if (id == R.id.paseadores) {
            filtro.setVisibility(View.GONE);
            transaction.replace(R.id.mainFrame, paseador).commit();
        } else if (id == R.id.alimentos) {
            filtro.setVisibility(View.GONE);
            transaction.replace(R.id.mainFrame, alimento).commit();
        } else if (id == R.id.estilistas) {
            filtro.setVisibility(View.GONE);
            transaction.replace(R.id.mainFrame, estilista).commit();
        } else if (id == R.id.nav_share) {
            filtro.setVisibility(View.GONE);
            transaction.replace(R.id.mainFrame, home).commit();
        } else if (id == R.id.nav_send) {
            filtro.setVisibility(View.GONE);
            transaction.replace(R.id.mainFrame, home).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void EnviarDetalle(TransaccionPojo datos) {
        animalDetail = new AnimalDetail();
        Bundle detalleEnvio = new Bundle();
        detalleEnvio.putSerializable("detalle", datos);
        animalDetail.setArguments(detalleEnvio);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, animalDetail).addToBackStack(null).commit();
    }
}
