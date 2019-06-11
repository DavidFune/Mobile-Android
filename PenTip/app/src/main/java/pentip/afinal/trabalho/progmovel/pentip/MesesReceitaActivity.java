package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MesesReceitaActivity extends AppCompatActivity {



    OrdenaMes ordenaMes = new OrdenaMes();
    ListView lvMesesC;
    ListView lvMesesR;
    TextView tvEmptyList;
    static int cont = Calendar.getInstance().getTime().getMonth();
    static int ano = Calendar.getInstance().getTime().getYear();
    TextView filtrmeses;
    Calendar calendar;

    String meses []  = {"Janeiro","Fevereiro","Março","Abrir","Maio","Junho","Julho"
            ,"Agosto","Setembro","Outrubo","Novembro","Dezembro"};


    public boolean onSupportNavigateUp() {// cira o botao back

        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meses_receita);

        setTitle("Mês Receita");

        filtrmeses = findViewById(R.id.idMeses);
        filtrmeses.setText(meses[cont]);
        lvMesesR = findViewById(R.id.lvmesesR);
        tvEmptyList = findViewById(R.id.tvEmptyListM);
        SharedResourcesReceitas.getInstance().loadReceitas(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        calendar =new GregorianCalendar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        filtrmeses.setText(meses[cont]+"/"+String.valueOf(ano).charAt(1)+
                String.valueOf(ano).charAt(2));

        super.onResume();

        try {
            lvMesesR.setAdapter(new ReceitaAdapter
                    (this,ordenaMes.ordeR(SharedResourcesReceitas.getInstance()
                                    .getReceitas()
                            ,cont,ano)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        lvMesesR.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        Intent it = new Intent(getApplicationContext(),
                                ReceitaEditActivity.class);
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

    public void increment(View view) throws ParseException {

        cont++;
        if(cont > 11  ) {
            cont =0;
            ano++;
        }
        else if (cont < 0){
            cont =11;
            ano--;
        }
        filtrmeses.setText(meses[cont]+"/"+String.valueOf(ano).charAt(1)+
                String.valueOf(ano).charAt(2));

        super.onResume();

        lvMesesR.setAdapter(new ReceitaAdapter
                (this,ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                        ,cont,ano)));
        lvMesesR.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        Intent it = new Intent(getApplicationContext(),
                                ReceitaEditActivity.class);
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

    public void decrement(View view) throws ParseException {

        cont--;
        if(cont > 11  ) {
            cont =0;
            ano++;
        }
        else if (cont < 0){
            cont =11;
            ano--;
        }
        filtrmeses.setText(meses[cont]+"/"+String.valueOf(ano).charAt(1)+
                String.valueOf(ano).charAt(2));

        super.onResume();

        //-------------------------------

        lvMesesR.setAdapter(new ReceitaAdapter
                (this,ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                        ,cont,ano)));
        lvMesesR.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        Intent it = new Intent(getApplicationContext(),
                                ReceitaEditActivity.class);
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

}
