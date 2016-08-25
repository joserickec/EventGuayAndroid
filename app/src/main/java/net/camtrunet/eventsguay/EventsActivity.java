package net.camtrunet.eventsguay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import net.camtrunet.eventsguay.Fragments.RegistroFragment;
import net.camtrunet.eventsguay.FragmentsEventos.EventosFragment;
import net.camtrunet.eventsguay.FragmentsFotos.FotosFragment;

public class EventsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //Salir de la Aplicacion
        if (id == R.id.action_exit) {

            finish();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Boolean FragmentTransaction=false;
        Fragment fragment =null;


        if (id == R.id.nav_registro) {

            fragment = new RegistroFragment();
            FragmentTransaction = true;



        } else if (id == R.id.nav_sitios) {



            fragment = new FotosFragment();
            FragmentTransaction = true;

            //Intent i=new Intent(this, FotosActivity.class);
            //startActivity(i);



        } else if (id == R.id.nav_eventos) {


            fragment = new EventosFragment();
            FragmentTransaction = true;

        } else if (id == R.id.nav_herramientas) {

        } else if (id == R.id.nav_compartir) {

        } else if (id == R.id.nav_enviar) {

        }


        if (FragmentTransaction)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_event, fragment )
                    .commit();

            item.setCheckable(true);
            getSupportActionBar().setTitle(item.getTitle());

        }

        // Me Cambia el Titulo




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);



        return true;
    }
}
