package io.github.mazuh.terminal588;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;


/**
 * @author mazuh
 */
public class MainActivity extends AppCompatActivity {

    /* ATRIBUTOS DA ACTIVITY */
    private Sentido direto, inverso;

    /* MAPEAMENTO DO LAYOUT */

    // accordion: elementos
    private TextView accordionBtnDireto;
    private TextView accordionBtnInverso;
    private TableLayout accordionContainerDireto;
    private TableLayout accordionContainerInverso;
    // accordions: atributos
    private static final int VISIBILITY_ON  = View.VISIBLE;
    private static final int VISIBILITY_OFF = View.GONE;
    private static Drawable ICON_ON, ICON_OFF; // initAccordion()
    private static Drawable BACKGROUND_ON, BACKGROUND_OFF; // initAccordion()

    // textviews de horários do Direto
    private TextView tvDiretoProximo2Horario;
    private TextView tvDiretoProximo2Empresa;
    private TextView tvDiretoProximoHorario;
    private TextView tvDiretoProximoEmpresa;
    private TextView tvDiretoAnteriorHorario;
    private TextView tvDiretoAnteriorEmpresa;
    // textviews de horários do Inverso
    private TextView tvInversoProximo2Horario;
    private TextView tvInversoProximo2Empresa;
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
                startActivity(new Intent(this, SobreActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Reúne as funções extras que devem ser executadas on create.
     */
    private void init(){
        //Log.d("MAZUH", "0");

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
        tvDiretoProximo2Horario  = (TextView) findViewById(R.id.direto_proximo2_horario);
        tvDiretoProximo2Empresa  = (TextView) findViewById(R.id.direto_proximo2_empresa);
        tvDiretoAnteriorHorario  = (TextView) findViewById(R.id.direto_anterior_horario);
        tvDiretoAnteriorEmpresa  = (TextView) findViewById(R.id.direto_anterior_empresa);

        tvInversoProximoHorario  = (TextView) findViewById(R.id.inverso_proximo_horario);
        tvInversoProximoEmpresa  = (TextView) findViewById(R.id.inverso_proximo_empresa);
        tvInversoProximo2Horario = (TextView) findViewById(R.id.inverso_proximo2_horario);
        tvInversoProximo2Empresa = (TextView) findViewById(R.id.inverso_proximo2_empresa);
        tvInversoAnteriorHorario = (TextView) findViewById(R.id.inverso_anterior_horario);
        tvInversoAnteriorEmpresa = (TextView) findViewById(R.id.inverso_anterior_empresa);
    }

    /**
     * Atualiza os textos do sentido direto com novas previsões
     */
    private void atualizarDireto(){
        Onibus[] onibus = direto.findOnibusAnteriorEProximos();
        Onibus anterior = onibus[0],
                proximo  = onibus[1],
                proximo2 = onibus[2];

        tvDiretoAnteriorHorario.setText(anterior == null ? "Bom dia..." : anterior.getHorarioStr());
        tvDiretoAnteriorEmpresa.setText(anterior == null ? "Ainda nenhum partiu hoje." : anterior.getEmpresa());

        tvDiretoProximoHorario.setText(proximo == null ? "zZZz" : proximo.getHorarioStr());
        tvDiretoProximoEmpresa.setText(proximo == null ? "zZZz" : proximo.getEmpresa());

        tvDiretoProximo2Horario.setText(proximo2 == null ? "Eita!" : proximo2.getHorarioStr());
        tvDiretoProximo2Empresa.setText(proximo2 == null ? "Esconde esse celular e sebo nas canelas!" : proximo2.getEmpresa());

    }


    /**
     * Atualiza os textos do sentido inverso com novas previsões
     */
    private void atualizarInverso(){
        Onibus[] onibus = inverso.findOnibusAnteriorEProximos();
        Onibus anterior = onibus[0],
                proximo  = onibus[1],
                proximo2 = onibus[2];

        tvInversoAnteriorHorario.setText(anterior == null ? "Bom dia..." : anterior.getHorarioStr());
        tvInversoAnteriorEmpresa.setText(anterior == null ? "Nenhum saiu ainda." : anterior.getEmpresa());

        tvInversoProximoHorario.setText(proximo == null ? "zZZz" : proximo.getHorarioStr());
        tvInversoProximoEmpresa.setText(proximo == null ? "zZZZ" : proximo.getEmpresa());

        tvInversoProximo2Horario.setText(proximo2 == null ? "Boa noite..." : proximo2.getHorarioStr());
        tvInversoProximo2Empresa.setText(proximo2 == null ? "Fim das previsões!" : proximo2.getEmpresa());

    }

    /**
     * Mapeia e configura objetos do layout relacionados ao accordion
     */
    private void initAccordion(){
        accordionBtnDireto = (TextView) findViewById(R.id.accordion_btn_direto);
        accordionBtnInverso = (TextView) findViewById(R.id.accordion_btn_inverso);

        accordionContainerDireto  = (TableLayout) findViewById(R.id.accordion_container_direto);
        accordionContainerInverso = (TableLayout) findViewById(R.id.accordion_container_inverso);

        BACKGROUND_ON  = accordionBtnDireto.getBackground();
        BACKGROUND_OFF = accordionBtnInverso.getBackground();
        ICON_ON        = accordionBtnDireto.getCompoundDrawables()[0];
        ICON_OFF       = accordionBtnInverso.getCompoundDrawables()[0];

        accordionBtnDireto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accordionDireto(true);
                accordionInverso(false);
            }
        });

        accordionBtnInverso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accordionDireto(false);
                accordionInverso(true);
            }
        });

    }


    /**
     * Alterna estado da aba accordion: direto
     *
     * @param ativar true se o estado deve ser para visível, ligado etc
     */
    private void accordionDireto(boolean ativar){
        if(ativar){
            accordionContainerDireto.setVisibility(VISIBILITY_ON);
            accordionBtnDireto.setBackground(BACKGROUND_ON);
            accordionBtnDireto.setCompoundDrawables(ICON_ON, null, null, null);
        } else{
            accordionContainerDireto.setVisibility(VISIBILITY_OFF);
            accordionBtnDireto.setBackground(BACKGROUND_OFF);
            accordionBtnDireto.setCompoundDrawables(ICON_OFF, null, null, null);
        }
    }

    /**
     * Alterna estado da aba accordion: inverso
     *
     * @param ativar true se o estado deve ser para visível, ligado etc
     */
    private void accordionInverso(boolean ativar){
        if(ativar){
            accordionContainerInverso.setVisibility(VISIBILITY_ON);
            accordionBtnInverso.setBackground(BACKGROUND_ON);
            accordionBtnInverso.setCompoundDrawables(ICON_ON, null, null, null);
        } else{
            accordionContainerInverso.setVisibility(VISIBILITY_OFF);
            accordionBtnInverso.setBackground(BACKGROUND_OFF);
            accordionBtnInverso.setCompoundDrawables(ICON_OFF, null, null, null);
        }
    }

}
