package com.example.controlriego.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.controlriego.Fragment.Tab_control;
import com.example.controlriego.Fragment.Tab_lluvia;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
    //integramss un contador del numero de tabs
    int tabCount;
    String keyAtractivo;
    long idPropiedad;

    public TabPagerAdapter(FragmentManager fm, int tabCount, String keyAtractivo,long idPropiedad) {
        super(fm);
        this.tabCount= tabCount; // Inicializamos el contador de tabs
        this.keyAtractivo= keyAtractivo;
        this.idPropiedad=idPropiedad;
    }

    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Tab_control tab_control= new Tab_control();
                tab_control.keyatractivo = this.keyAtractivo;
                tab_control.idPropiedad=this.idPropiedad;
                return tab_control;
            case 1:
                Tab_lluvia tab_lluvia= new Tab_lluvia();
                tab_lluvia.idPropiedad = this.idPropiedad;
                return tab_lluvia;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
