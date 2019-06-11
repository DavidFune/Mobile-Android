package pentip.afinal.trabalho.progmovel.pentip;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ContaeditActivity extends AppCompatActivity {



    @Override
    public boolean onSupportNavigateUp() {// cira o botao back

        onBackPressed();
        return true;
    }

    private int position;
    private Conta conta;
    private EditText etValor;
    private EditText etData;
    private EditText etFpagamento;
    private EditText etFrecebimento;
    private EditText etDescricao;

    private int idImage;
    private String etSource;


    private ArrayList<SpinnerIntem> addspinnerList;
    private SpinnerAdapter mSpinnerAdapter;
    private Spinner spinnerSource;


    private DatePickerDialog dislogPickerDialog;
    private Calendar calendar;
    private DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Editar Conta");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contaedit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        position = it.getIntExtra("position",0);

        calendar = new GregorianCalendar();

        calendar.setTimeZone(TimeZone.getDefault());

        dateFormat = DateFormat.getDateInstance();

       // etData.setText(dateFormat.format(calendar.getTime()));


        //################# spinner #############################
        initList();
        spinnerSource = findViewById(R.id.spnaddsources);
        mSpinnerAdapter = new SpinnerAdapter(this,addspinnerList );
        spinnerSource.setAdapter(mSpinnerAdapter);

        spinnerSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                idImage = 0;
                SpinnerIntem clickItem = (SpinnerIntem) parent.getItemAtPosition(position);
                idImage = position;
                        //clickItem.getSourceImage();
                etSource = clickItem.getNomeItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //################fim spinner############################



        dislogPickerDialog = new DatePickerDialog(this,
                AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        etData.setText(dateFormat.format(calendar.getTime()));
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));




        conta = SharedResources.getInstance()
                .getContas().get(position);

        //etSource = findViewById(R.id.Editsource);
        etValor = findViewById(R.id.EditValor);
        etData = findViewById(R.id.EditData);
        etFpagamento = findViewById(R.id.EditFpagamento);
        etFrecebimento = findViewById(R.id.EditFrecebimento);
        etDescricao = findViewById(R.id.EditDescricao);

        if (conta != null){

            etValor.setText(String.valueOf(conta.getValor()));
           // etSource.setText(conta.getSource());

            etData.setText(conta.getData());

            idImage = conta.getIdimage();
            etFrecebimento.setText(conta.getfRecebimento());
            etFpagamento.setText(conta.getfPagamento());
            etDescricao.setText(conta.getDescricao());
        }

    }

    public void confirmeEdit(View view){


        conta.setIdimage(idImage);
        conta.setValor(Float.parseFloat(etValor.getText().toString()));
        conta.setSource(etSource);
        conta.setData(dateFormat.format(calendar.getTime()));
        conta.setfPagamento(etFpagamento.getText().toString());
        conta.setfPagamento(etFrecebimento.getText().toString());
        conta.setDescricao(etDescricao.getText().toString());

        SharedResources.getInstance().
                getContas().set(position,conta);
        SharedResources.getInstance().saveContas(this);

        Toast.makeText(this, "Conta editada com sucesso",
                Toast.LENGTH_SHORT).show();

        finish();


    }

    public void deleteEdit(View view){

        SharedResources.getInstance()
                .getContas().remove(position);
        SharedResources.getInstance().saveContas(this);

        Toast.makeText(this, "Conta removida com sucesso", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void showDatePicker(View view) {
        dislogPickerDialog.show();
    }

    private void initList(){
        addspinnerList = new ArrayList<>();
        addspinnerList.add(new SpinnerIntem("Alimentacão",R.drawable.alimentaca));
        addspinnerList.add(new SpinnerIntem("Escola",R.drawable.escola ));
        addspinnerList.add(new SpinnerIntem("Esporte",R.drawable.esporte ));
        addspinnerList.add(new SpinnerIntem("Feira",R.drawable.feira ));
        addspinnerList.add(new SpinnerIntem("Mercado",R.drawable.mercado ));
        addspinnerList.add(new SpinnerIntem("Trans.Bancaria",R.drawable.tranferenciabanc ));

    }


}
