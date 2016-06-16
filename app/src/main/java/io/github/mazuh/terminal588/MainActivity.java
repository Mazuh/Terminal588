package io.github.mazuh.terminal588;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

    /* ATRIBUTOS DA ACTIVITY */
    private Sentido direto, inverso;

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

    // textview de horários
    private TextView tvDiretoProximoHorario;
    private TextView tvDiretoProximoEmpresa;
    private TextView tvDiretoAnteriorHorario;
    private TextView tvDiretoAnteriorEmpresa;
    private TextView tvInversoProximoHorario;
    private TextView tvInversoProximoEmpresa;
    private TextView tvInversoAnteriorHorario;
    private TextView tvInversoAnteriorEmpresa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        // Ver: http://www.mkyong.com/android/how-to-send-email-in-android/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizarDireto();
        atualizarInverso();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_cronograma_direto:
            case R.id.action_cronograma_inverso:
                Intent intent = new Intent(this, CronogramaActivity.class);

                Sentido sentido = (id == R.id.action_cronograma_direto) ? direto : inverso;
                intent.putExtra("sentido", sentido);

                startActivity(intent);
                return true;

            case R.id.action_sobre:
                Log.d("Mazuh", "mazuh@ufrn.edu.br");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Reúne as funções extras que devem ser executadas on create.
     */
    private void init(){
        Log.d("MAZUH", "3");

        initAccordion();
        initTextHorarios();

        direto = new Sentido(Sentido.DIRETO);
        inverso = new Sentido(Sentido.INVERSO);
    }

    /**
     * Mapeia e configura objetos do layout relacionados às previsões dos sentidos
     */
    private void initTextHorarios(){
        tvDiretoProximoHorario   = (TextView) findViewById(R.id.direto_proximo_horario);
        tvDiretoProximoEmpresa   = (TextView) findViewById(R.id.direto_proximo_empresa);
        tvDiretoAnteriorHorario  = (TextView) findViewById(R.id.direto_anterior_horario);
        tvDiretoAnteriorEmpresa  = (TextView) findViewById(R.id.direto_anterior_empresa);
        tvInversoProximoHorario  = (TextView) findViewById(R.id.inverso_proximo_horario);
        tvInversoProximoEmpresa  = (TextView) findViewById(R.id.inverso_proximo_empresa);
        tvInversoAnteriorHorario = (TextView) findViewById(R.id.inverso_anterior_horario);
        tvInversoAnteriorEmpresa = (TextView) findViewById(R.id.inverso_anterior_empresa);
    }

    /**
     * Atualiza os textos do sentido direto com novas previsões
     */
    private void atualizarDireto(){
        Onibus[] onibus = direto.findOnibusAnteriorEProximo();
        Onibus anterior = onibus[0];
        Onibus proximo  = onibus[1];

        tvDiretoAnteriorHorario.setText(anterior == null ? "Ops..." : anterior.getHorarioStr());
        tvDiretoAnteriorEmpresa.setText(anterior == null ? "Nenhum partiu hoje." : anterior.getEmpresa());

        tvDiretoProximoHorario.setText(proximo == null ? "Esconde o celular" : proximo.getHorarioStr());
        tvDiretoProximoEmpresa.setText(proximo == null ? "e estica as pernas." : proximo.getEmpresa());
    }


    /**
     * Atualiza os textos do sentido inverso com novas previsões
     */
    private void atualizarInverso(){
        Onibus[] onibus = inverso.findOnibusAnteriorEProximo();
        Onibus anterior = onibus[0];
        Onibus proximo  = onibus[1];

        tvInversoAnteriorHorario.setText(anterior == null ? "Ish..." : anterior.getHorarioStr());
        tvInversoAnteriorEmpresa.setText(anterior == null ? "Nenhum até agora." : anterior.getEmpresa());

        tvInversoProximoHorario.setText(proximo == null ? "Caramba..." : proximo.getHorarioStr());
        tvInversoProximoEmpresa.setText(proximo == null ? "O último já partiu!" : proximo.getEmpresa());
    }

    /**
     * Mapeia e configura objetos do layout relacionados ao accordion
     */
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


    /**
     * Alterna estado da aba accordion: próximo ônibus do sentido
     *
     * @param ativar true se o estado deve ser para visível, ligado etc
     */
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

    /**
     * Alterna estado da aba accordion: ônibus anterior do sentido direto
     *
     * @param ativar true se o estado deve ser para visível, ligado etc
     */
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

    /**
     * Alterna estado da aba accordion: próximo ônibus do sentido inverso
     *
     * @param ativar true se o estado deve ser para visível, ligado etc
     */
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

    /**
     * Alterna estado da aba accordion: ônibus anterior do sentido inverso
     *
     * @param ativar true se o estado deve ser para visível, ligado etc
     */
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
