package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


public class ListaContasActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {// cira o botao back

        onBackPressed();
        return true;
    }

    private ListView lvContas; // é a lista que visualiza as contas
    private TextView tvEmptyList;
    private Float sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Contas");
        super.onCreate(savedInstanceState);
        // carregando os arquivos salvos (contas)
        SharedResources.getInstance().loadContas(this);

        // criando o layout da tela principal
        setContentView(R.layout.activity_lista_contas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        lvContas = findViewById(R.id.lvContas);
        tvEmptyList = findViewById(R.id.tvEmptyList);

    }


    @Override
    protected void onResume() {
        super.onResume();
        lvContas.setAdapter(new ContasAdapter
                (this,SharedResources.getInstance().getContas()));
        lvContas.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        Intent it = new Intent(getApplicationContext(),
                                ContaeditActivity.class);
                        it.putExtra("position",position);

                        startActivity(it);

                    }
                });

        if (SharedResources.getInstance().getContas().isEmpty()){
            tvEmptyList.setVisibility(View.VISIBLE);
        }else {
            tvEmptyList.setVisibility(View.INVISIBLE);
        }

    }

    public void addContas(View view){

        Intent it = new Intent(this,
                ContaaddActivity.class);
        startActivity(it);
    }

}
