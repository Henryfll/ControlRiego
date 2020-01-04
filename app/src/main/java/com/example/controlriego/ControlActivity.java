package com.example.controlriego;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import com.example.controlriego.Adapters.TabPagerAdapter;
import com.example.controlriego.Adapters.ViewPagerAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ControlActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    public ViewPager viewPager;
    public CircleIndicator circleIndicator; // Indicador de viewpager imagenes

    ViewPagerAdapter viewPagerAdapter ;


    private TabLayout tabLayout; // Este es nuestro tabLayout

    private ViewPager viewPagerTab;

    private static ArrayList<String> listaImagenes = new ArrayList<>();
    String nombrePropiedad="";
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        Appbar = (AppBarLayout)findViewById(R.id.appbar);
        Appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0)
                {
                    // Fully expanded
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                else
                {
                    // Not fully expanded or collapsed
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        circleIndicator = (CircleIndicator) findViewById(R.id.ciViewPagerImages);


        CoolToolbar = (CollapsingToolbarLayout)findViewById(R.id.ctolbar);
        toolbar = (Toolbar) findViewById(R.id.toolAtractivo);

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        parametros();

    }
    public void parametros(){
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){


            this.nombrePropiedad = getIntent().getExtras().getString("atractivoNombre");

            if(getSupportActionBar() != null){
                getSupportActionBar().setTitle(nombrePropiedad);
            }

            // Recupera toda la informacion
            getPropiedad();


            setTabs();// INICALIZAMOS Y COLOCAPOS LOS VIEWPAGER EN EL TABLAYOUT

        }
    }

    private void getPropiedad() {
        //cargar data
        listaImagenes.clear();// Limpia el arreglo de imagenes

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int width = metrics.widthPixels; // ancho absoluto en pixels
        final int height = metrics.heightPixels; // alto absoluto en pixels

        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), listaImagenes , width,height);
        viewPager.setAdapter(viewPagerAdapter);
        circleIndicator.setViewPager(viewPager);
        viewPagerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPagerTab = null;
        viewPagerAdapter = null;
    }
    public void setTabs(){
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Detalle"));
        tabLayout.addTab(tabLayout.newTab().setText("Lluvia"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Initializing viewPager
        viewPagerTab = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),nombrePropiedad);
        //Adding adapter to pager
        viewPagerTab.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPagerTab.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views

        tabLayout.addOnTabSelectedListener(this);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPagerTab.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
