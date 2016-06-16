package io.github.mazuh.terminal588;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CronogramaActivity extends AppCompatActivity {

    private ListView lvCronograma;
    private TextView tvNomeSentido;

    private Sentido sentido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
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

    /**
    * Executa ações iniciais da Activity, inicializando componentes.
    */
    private void init(){
        tvNomeSentido = (TextView) findViewById(R.id.cronograma_titulo);
        lvCronograma  = (ListView) findViewById(R.id.cronograma);

        sentido = (Sentido) getIntent().getSerializableExtra("sentido");

        tvNomeSentido.setText(sentido.getNome());

        initLista();
    }

    /**
     * Configura completamente a listview de cronograma.
      */
    private void initLista(){

        final Onibus[] onibusQuePartem = sentido.getOnibusQuePartem();

        ArrayAdapter<Onibus> cronogramaAdapter = new ArrayAdapter<Onibus>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                onibusQuePartem) {
            @Override
            public View getView(int pos, View convertView, ViewGroup parent) {
                View view = super.getView(pos, convertView, parent);
                TextView txt1 = (TextView) view.findViewById(android.R.id.text1);
                TextView txt2 = (TextView) view.findViewById(android.R.id.text2);

                txt1.setText(onibusQuePartem[pos].getHorarioStr());
                txt2.setText(onibusQuePartem[pos].getEmpresa());
                return view;
            }
        };

        lvCronograma.setAdapter(cronogramaAdapter);
    }

}
