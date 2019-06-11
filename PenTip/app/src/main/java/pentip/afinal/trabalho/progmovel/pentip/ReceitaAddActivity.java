package pentip.afinal.trabalho.progmovel.pentip;

import android.support.v7.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ReceitaAddActivity extends AppCompatActivity {

    private String etOrigem;
    private EditText etValor;
    private EditText etData;
    private EditText etFrecebimento;
    private EditText etDescricao;

    private int idImage;


    private ArrayList<SpinnerIntem> addspinnerList;
    private SpinnerAdapter mSpinnerAdapter;
    private Spinner spinnerSource;


    private DatePickerDialog dialogDatePickerDialog;
    private Calendar calendar;
    private DateFormat dateFormat;

    @Override
    public boolean onSupportNavigateUp() {// cira o botao back

        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Add Receita");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_add);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        calendar = new GregorianCalendar();

        calendar.setTimeZone(TimeZone.getDefault());

        dateFormat = DateFormat.getDateInstance();


        //etOrigem = findViewById(R.id.Addsource);
        etValor = findViewById(R.id.AddValor);
        etData = findViewById(R.id.AddData);
        etFrecebimento = findViewById(R.id.AddFrecebimento);
        etDescricao = findViewById(R.id.AddDescricao);


        etData.setText(dateFormat.format(calendar.getTime()));

        dialogDatePickerDialog = new DatePickerDialog(this,
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

    public void confirmeAdd(View view){

        Float valor = Float.parseFloat(etValor.getText().toString());
        String source = etOrigem;
        String data = etData.getText().toString();
        String recb = etFrecebimento.getText().toString();
        String desc = etDescricao.getText().toString();

        Receita recipe = new Receita(valor,data,source,recb,desc,idImage);

        SharedResourcesReceitas.getInstance().getReceitas().add(recipe);
        SharedResourcesReceitas.getInstance().saveReceitas(this);


        Toast.makeText(this, "Receita adicionada com sucesso",
                Toast.LENGTH_SHORT).show();

        etOrigem="";
        etValor.setText("");
        etData.setText("");
        etDescricao.setText("");
        etFrecebimento.setText("");
        etDescricao.setText("");

    }

    public void showDatePicker(View view) {
        dialogDatePickerDialog.show();
    }

    private void initList(){
        addspinnerList = new ArrayList<>();
        addspinnerList.add(new SpinnerIntem("Salário",R.drawable.salario ));
        addspinnerList.add(new SpinnerIntem("Negocios",R.drawable.negocios ));
        addspinnerList.add(new SpinnerIntem("Trans.Bancaria",R.drawable.tranferenciabanc ));

    }
}
