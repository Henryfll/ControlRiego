package com.example.controlriego;


import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.example.controlriego.Fragment.ListaPropiedadesFragment;

public class MainActivity extends AppCompatActivity implements ListaPropiedadesFragment.OnFragmentInteractionListener {
    private boolean INICIO_APP_PRIMERA_VEZ = true;

    private ListaPropiedadesFragment listPropiedadesFragment = new ListaPropiedadesFragment();
    private Bundle args = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(listPropiedadesFragment);
        ButterKnife.bind(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(!INICIO_APP_PRIMERA_VEZ){
            fragmentTransaction.setCustomAnimations(R.anim.animation_slide_in_left, R.anim.animation_slide_out_right, R.anim.animation_slide_in_right, R.anim.animation_slide_out_left);
        }else{
            INICIO_APP_PRIMERA_VEZ = false;
        }
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();
    }

}
