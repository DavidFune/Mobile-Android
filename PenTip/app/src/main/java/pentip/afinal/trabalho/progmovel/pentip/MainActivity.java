package pentip.afinal.trabalho.progmovel.pentip;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity




        implements NavigationView.OnNavigationItemSelectedListener {

     TextView resumo;
     private Spinner spiReceita;
     private Spinner spConta;
     private Spinner spMoedas;
     TextView spmesConta;
     TextView spmesesReceitas;
     private Balanco balanco;
     private OrdenaMes ordenaMes;
     private ProgressDialog dialog;
    static int cont = Calendar.getInstance().getTime().getMonth();
    static int ano = Calendar.getInstance().getTime().getYear();
    TextView filtrmeses;
     private TextView USD;
    private TextView EUR;
    private TextView GBP;
    private TextView JPY;


     private static final String MESES []  = {"Janeiro","Fevereiro","Março","Abrir","Maio","Junho","Julho"
            ,"Agosto","Setembro","Outrubo","Novembro","Dezembro"};
    private static final String MESESR []  = {"Janeiro","Fevereiro","Março","Abrir","Maio","Junho","Julho"
            ,"Agosto","Setembro","Outrubo","Novembro","Dezembro"};

    private static final String MOEDAS [] = {"USD","EUR","GBP","JPY"};

    String meses []  = {"Janeiro","Fevereiro","Março","Abrir","Maio","Junho","Julho"
            ,"Agosto","Setembro","Outrubo","Novembro","Dezembro"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedResources.getInstance().loadContas(this);
        SharedResourcesReceitas.getInstance().loadReceitas(this);
          balanco = new Balanco();
          ordenaMes = new OrdenaMes();
        Calendar calendar = new GregorianCalendar();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Aguaede um pouco...");

        spmesConta = findViewById(R.id.spMesContas);
        spmesesReceitas = findViewById(R.id.spMesReceita);
        //### filter meses########
        filtrmeses = findViewById(R.id.textMeses);
        filtrmeses.setText(String.valueOf(meses[calendar.getTime().getMonth()]+"/"
        +String.valueOf(+ calendar.getTime().getYear()).charAt(1)
        +String.valueOf(+ calendar.getTime().getYear()).charAt(2)));


        //########text view  cotaçao ###################

        USD = findViewById(R.id.usdtext);
        EUR = findViewById(R.id.eurtext);
        GBP = findViewById(R.id.gbptext);
        JPY = findViewById(R.id.jpytext);

       /* ArrayAdapter<String> adapterSpCotacao =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_dropdown_item_1line,MOEDAS);
        spMoedas.setAdapter(adapterSpCotacao);
          spMoedas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                new DownloadMOEDAask().execute();
                *//*
                        if(MOEDAS[position].equals("USD")){
                        //dialog.show();
                        //valorcotacao.setText(web.getUSD());
                        new DownloadMOEDAask().execute();
                    }
                   else if (MOEDAS[position].equals("EUR")){
                       // dialog.show();
                        valorcotacao.setText(web.getEUR());
                        Toast.makeText(MainActivity.this,
                                web.getEUR(), Toast.LENGTH_SHORT).show();
                    }else if (MOEDAS[position].equals("GBP")){
                       // dialog.show();
                        valorcotacao.setText(web.getGBP());
                    }else if (MOEDAS[position].equals("JPY")){
                       // dialog.show();
                        valorcotacao.setText(web.getJPY());
                    }*//*
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/




        resumo = findViewById(R.id.textresumo);
        //int x = calendar.getTime().getMonth();

        // resumo do mes corrente
        try {
            resumo.setText(String.valueOf(balanco.resumo(
                    ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                            calendar.getTime().getMonth(),calendar.getTime().getYear()),

                    ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                            ,calendar.getTime().getMonth(),calendar.getTime().getYear()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            spmesesReceitas.setText(String.valueOf(balanco.getSumReceitas(
                    ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas(),
                            calendar.getTime().getMonth(),calendar.getTime().getYear()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            spmesConta.setText(String.valueOf(balanco.getSumContas(
                    ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                            calendar.getTime().getMonth(),calendar.getTime().getYear()))));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //############################################################################################


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

  //### metodo para obter resultados da busca

    public void buscarConversa(View view) {
        // analisando a conexao
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            new DownloadMOEDAask().execute();
        }
         else {
            new AlertDialog.Builder(this)
                    .setTitle("Sem Internet!")
                    .setMessage("Não tem nenhuma conexão de rede disponível!")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // nada
                        }
                    })
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }


    //##### classe para obter o resultado

    private class DownloadMOEDAask extends AsyncTask<String, Void, Web> {

        protected Web doInBackground(String... strings) {
            Web web = null;

            try {
                web = new Web(MainActivity.this);
            } finally {
                dialog.dismiss();
                return web;
            }
        }


        @Override
        protected void onPostExecute(Web result) {
            if (result != null && result.isEncontrou()) {


                USD.setText("USD 1:"+" R$ "+String.valueOf(1/(Float.parseFloat(result.getUSD()))).substring(0,4));
                EUR.setText("EUR 1:"+" R$ "+String.valueOf(1/(Float.parseFloat(result.getEUR()))).substring(0,4));
                GBP.setText("GBP 1:"+" R$ "+String.valueOf(1/(Float.parseFloat(result.getGBP()))).substring(0,4));
                JPY.setText("JPY 1:"+" R$ "+String.valueOf(1/(Float.parseFloat(result.getJPY()))).substring(0,4));

            }
            else
                Toast.makeText(MainActivity.this,
                        "Conversão não sucedida!", Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_resumo) {
            // Handle the camera action

        } else if (id == R.id.nav_list1) {
            Intent it = new Intent(this, ListaContasActivity.class);
            startActivity(it);

        } else if (id == R.id.nav_list2) {

            Intent it = new Intent(this, ListaReceitasActivity.class);
            startActivity(it);

        } else if (id == R.id.nav_relatorio) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showmesesContas(View view) {

        Intent it = new Intent(this, MesesActivity.class);
        startActivity(it);
    }

    public void showmesesReceitas(View view) {

        Intent it = new Intent(this, MesesReceitaActivity.class);
        startActivity(it);
    }

    public void decrementMain(View view) throws ParseException {


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

        resumo.setText(String.valueOf(balanco.resumo(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont,ano),

                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                        ,cont,ano))));


        spmesesReceitas.setText(String.valueOf(balanco.getSumReceitas(
                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas(),
                        cont,ano))));

        spmesConta.setText(String.valueOf(balanco.getSumContas(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont,ano))));



    }

    public void incrementMain(View view) throws ParseException {


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

        resumo.setText(String.valueOf(balanco.resumo(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont,ano),

                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas()
                        ,cont,ano))));


        spmesesReceitas.setText(String.valueOf(balanco.getSumReceitas(
                ordenaMes.ordeR(SharedResourcesReceitas.getInstance().getReceitas(),
                        cont,ano))));

        spmesConta.setText(String.valueOf(balanco.getSumContas(
                ordenaMes.ordeC(SharedResources.getInstance().getContas(),
                        cont,ano))));

    }

}
