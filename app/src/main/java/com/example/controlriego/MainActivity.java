package com.example.controlriego;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;

import com.example.controlriego.Fragment.AcercaDeFragment;
import com.example.controlriego.Fragment.DialogAppFragment;
import com.example.controlriego.Fragment.ListaPropiedadesFragment;

public class MainActivity extends AppCompatActivity implements ListaPropiedadesFragment.OnFragmentInteractionListener , AcercaDeFragment.OnFragmentInteractionListener,DialogAppFragment.NoticeDialogListener{
    private boolean INICIO_APP_PRIMERA_VEZ = true;
    private static final int DIALOG_SIGN_OFF = 1;
    private DialogAppFragment dialogAppFragment; // Varibale para controlar el Dialogo de cerrar sesion.
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_salir) {
            openSignOffDialog();
            return true;

        }

        else if (id == R.id.navigation_acerca_de) {
            setFragment(new AcercaDeFragment());
        }

        return super.onOptionsItemSelected(item);
    }
    public void openSignOffDialog() {
        // Se crea una instancia de la clase DialogAppFragement y se la muestra.
        dialogAppFragment = new DialogAppFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("Type_Dialog", DIALOG_SIGN_OFF);
        dialogAppFragment.setArguments(bundle);

        dialogAppFragment.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }
    private void logOut() {
        goLogInScreen();
    }
    private void goLogInScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onDialogConfirmClick(DialogFragment dialog) { logOut(); }

    @Override
    public void onDialogCancelClick(DialogFragment dialog) { // Para cancelar la acción de cerrar sesión.
        dialog.getDialog().cancel();
    }
}
