package io.github.mazuh.terminal588;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author mazuh
 */
public class MainActivity extends AppCompatActivity {

    /* MAPEAMENTO DO LAYOUT */

    // accordion: elementos
    private TextView accordionBtnDiretoProximo;
    private TextView accordionBtnDiretoAnterior;
    private TextView accordionBtnInversoProximo;
    private TextView accordionBtnInversoAnterior;
    private LinearLayout accordionContainerDiretoProximo;
    private LinearLayout accordionContainerDiretoAnterior;
    private LinearLayout accordionContainerInversoProximo;
    private LinearLayout accordionContainerInversoAnterior;
    // accordions: atributos
    private static final int VISIBILITY_ON  = View.VISIBLE;
    private static final int VISIBILITY_OFF = View.GONE;
    private static Drawable ICON_ON, ICON_OFF; // initAccordion()
    private static Drawable BACKGROUND_ON, BACKGROUND_OFF; // initAccordion()



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init(){
        //Log.d("MAZUH", "4");

        initAccordion();

        Sentido direto = new Sentido(Sentido.DIRETO);
        //Sentido inverso = new Sentido(Sentido.INVERSO);

    }

    private void initAccordion(){
        accordionBtnDiretoProximo   = (TextView) findViewById(R.id.accordion_btn_direto_proximo);
        accordionBtnDiretoAnterior  = (TextView) findViewById(R.id.accordion_btn_direto_anterior);
        accordionBtnInversoProximo  = (TextView) findViewById(R.id.accordion_btn_inverso_proximo);
        accordionBtnInversoAnterior = (TextView) findViewById(R.id.accordion_btn_inverso_anterior);

        accordionContainerDiretoProximo   = (LinearLayout) findViewById(R.id.accordion_container_direto_proximo);
        accordionContainerDiretoAnterior  = (LinearLayout) findViewById(R.id.accordion_container_direto_anterior);
        accordionContainerInversoProximo  = (LinearLayout) findViewById(R.id.accordion_container_inverso_proximo);
        accordionContainerInversoAnterior = (LinearLayout) findViewById(R.id.accordion_container_inverso_anterior);

        BACKGROUND_ON  = accordionBtnDiretoProximo.getBackground();
        BACKGROUND_OFF = accordionBtnDiretoAnterior.getBackground();
        ICON_ON        = accordionBtnDiretoProximo.getCompoundDrawables()[0];
        ICON_OFF       = accordionBtnDiretoAnterior.getCompoundDrawables()[0];

        /*if (Build.VERSION.SDK_INT > 21) {
            ICON_ON = getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp, getTheme());
            ICON_OFF = getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black_24dp, getTheme());
        } else{
            // getDrawable(int) decrecated da api lv 22 em diante
            ICON_ON = getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
            ICON_OFF = getResources().getDrawable(R.drawable.ic_keyboard_arrow_right_black_24dp);
        }*/

        accordionBtnDiretoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accordionDiretoProximo(true);
                accordionDiretoAnterior(false);
            }
        });

        accordionBtnDiretoAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accordionDiretoProximo(false);
                accordionDiretoAnterior(true);
            }
        });

        accordionBtnInversoProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accordionInversoProximo(true);
                accordionInversoAnterior(false);
            }
        });

        accordionBtnInversoAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accordionInversoProximo(false);
                accordionInversoAnterior(true);
            }
        });
    }


    private void accordionDiretoProximo(boolean ativar){
        if(ativar){
            accordionContainerDiretoProximo.setVisibility(VISIBILITY_ON);
            accordionBtnDiretoProximo.setBackground(BACKGROUND_ON);
            accordionBtnDiretoProximo.setCompoundDrawables(ICON_ON, null, null, null);
        } else{
            accordionContainerDiretoProximo.setVisibility(VISIBILITY_OFF);
            accordionBtnDiretoProximo.setBackground(BACKGROUND_OFF);
            accordionBtnDiretoProximo.setCompoundDrawables(ICON_OFF, null, null, null);
        }
    }

    private void accordionDiretoAnterior(boolean ativar){
        if(ativar){
            accordionContainerDiretoAnterior.setVisibility(VISIBILITY_ON);
            accordionBtnDiretoAnterior.setBackground(BACKGROUND_ON);
            accordionBtnDiretoAnterior.setCompoundDrawables(ICON_ON, null, null, null);
        } else{
            accordionContainerDiretoAnterior.setVisibility(VISIBILITY_OFF);
            accordionBtnDiretoAnterior.setBackground(BACKGROUND_OFF);
            accordionBtnDiretoAnterior.setCompoundDrawables(ICON_OFF, null, null, null);
        }
    }

    private void accordionInversoProximo(boolean ativar){
        if(ativar){
            accordionContainerInversoProximo.setVisibility(VISIBILITY_ON);
            accordionBtnInversoProximo.setBackground(BACKGROUND_ON);
            accordionBtnInversoProximo.setCompoundDrawables(ICON_ON, null, null, null);
        } else{
            accordionContainerInversoProximo.setVisibility(VISIBILITY_OFF);
            accordionBtnInversoProximo.setBackground(BACKGROUND_OFF);
            accordionBtnInversoProximo.setCompoundDrawables(ICON_OFF, null, null, null);
        }
    }

    private void accordionInversoAnterior(boolean ativar){
        if(ativar){
            accordionContainerInversoAnterior.setVisibility(VISIBILITY_ON);
            accordionBtnInversoAnterior.setBackground(BACKGROUND_ON);
            accordionBtnInversoAnterior.setCompoundDrawables(ICON_ON, null, null, null);
        } else{
            accordionContainerInversoAnterior.setVisibility(VISIBILITY_OFF);
            accordionBtnInversoAnterior.setBackground(BACKGROUND_OFF);
            accordionBtnInversoAnterior.setCompoundDrawables(ICON_OFF, null, null, null);
        }
    }
}
