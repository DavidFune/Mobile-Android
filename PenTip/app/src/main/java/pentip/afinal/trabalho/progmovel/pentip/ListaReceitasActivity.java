package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ListaReceitasActivity extends AppCompatActivity {


    private ListView lvReceitas;
    private TextView tvEmptyListaReceita;

    @Override
    public boolean onSupportNavigateUp() {// cira o botao back

        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedResourcesReceitas.getInstance()
                .loadReceitas(this);
        setContentView(R.layout.activity_lista_receitas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        lvReceitas = findViewById(R.id.lvReceitas);
        tvEmptyListaReceita = findViewById(R.id.tvEmptyListReceita);


    }

    @Override
    protected void onResume() {
        setTitle("Receitas");
        super.onResume();
        lvReceitas.setAdapter(new ReceitaAdapter(this,
                SharedResourcesReceitas.getInstance().getReceitas()));
        lvReceitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent it = new Intent(getApplicationContext(),ReceitaEditActivity.class);
                it.putExtra("position",position);
                startActivity(it);

            }
        });

        if (SharedResourcesReceitas.getInstance().getReceitas().isEmpty()){
            tvEmptyListaReceita.setVisibility(View.VISIBLE);
        }else
            tvEmptyListaReceita.setVisibility(View.INVISIBLE);
    }

    public void addReceitas(View view) {

        Intent it = new Intent(this,
                ReceitaAddActivity.class);
        startActivity(it);
    }

}
