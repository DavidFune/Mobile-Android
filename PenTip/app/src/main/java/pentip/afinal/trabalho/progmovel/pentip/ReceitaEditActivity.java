package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Intent;
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

import java.text.DateFormat;
public class ReceitaEditActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {// cira o botao back

        onBackPressed();
        return true;
    }

    private Receita receita;
    private int position;
    private EditText etValor;
    private EditText etData;
    private EditText etFrecebimento;
    private EditText etDescricao;

    private int idImage;
    private String etOrigem;



    private ArrayList<SpinnerIntem> addspinnerList;
    private SpinnerAdapter mSpinnerAdapter;
    private Spinner spinnerSource;


    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private DateFormat dateFormat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Editar Receita");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent it = getIntent();
        position = it.getIntExtra("position",0);

        receita = SharedResourcesReceitas.getInstance()
                .getReceitas().get(position);

        //etOrigem = findViewById(R.id.EditsourceR);
        etData = findViewById(R.id.EditDataR);
        etValor = findViewById(R.id.EditValorR);
        etFrecebimento = findViewById(R.id.EditFrecebimentoR);
        etDescricao = findViewById(R.id.EditDescricaoR);

        calendar = new GregorianCalendar();

        calendar.setTimeZone(TimeZone.getDefault());

        dateFormat = DateFormat.getDateInstance();

        //etData.setText(dateFormat.format(calendar.getTime()));


        datePickerDialog = new DatePickerDialog(this,
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





        if(receita!=null){


            idImage = receita.getIdimageR();
            etOrigem = receita.getSource();
            etValor.setText(String.valueOf(receita.getValor()));
            etData.setText(receita.getData());
            etFrecebimento.setText(receita.getfRecebimento());
            etDescricao.setText(receita.getDescricao());
        }

        //################# spinner #############################
        initList();
        spinnerSource = findViewById(R.id.spnaddsources);
        mSpinnerAdapter = new SpinnerAdapter(this,addspinnerList );
        spinnerSource.setAdapter(mSpinnerAdapter);

        spinnerSource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SpinnerIntem clickItem = (SpinnerIntem) parent.getItemAtPosition(position);
                idImage = position;
                        //clickItem.getSourceImage();
                etOrigem = clickItem.getNomeItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //################fim spinner############################



    }

    public void confirmaEditReceita(View view) {


        receita.setIdimageR(idImage);
        receita.setSource(etOrigem);
        receita.setValor(Float.parseFloat(etValor.getText().toString()));
        receita.setData(dateFormat.format(calendar.getTime()));
        receita.setfRecebimento(etFrecebimento.getText().toString());
        receita.setDescricao(etDescricao.getText().toString());

        SharedResourcesReceitas.getInstance()
                .getReceitas().set(position,receita);
        SharedResourcesReceitas.getInstance().saveReceitas(this);

        Toast.makeText(this, "Receita editada com sucesso"
                , Toast.LENGTH_SHORT).show();


    }

    public void deleteReceita(View view){

        SharedResourcesReceitas.getInstance()
                .getReceitas().remove(position);
        SharedResourcesReceitas.getInstance().saveReceitas(this);

        Toast.makeText(this, "Receita removida com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void showDatePicker(View view) {
        datePickerDialog.show();
    }

    private void initList(){
        addspinnerList = new ArrayList<>();
        addspinnerList.add(new SpinnerIntem("Salário",R.drawable.salario));
        addspinnerList.add(new SpinnerIntem("Negócio",R.drawable.negocios ));
        addspinnerList.add(new SpinnerIntem("Tranfe.",R.drawable.tranferenciabanc ));

    }

}
