package pentip.afinal.trabalho.progmovel.pentip;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
public class ContaaddActivity extends AppCompatActivity {


    @Override
    public boolean onSupportNavigateUp() {// cira o botao back

        onBackPressed();
        return true;
    }


    private EditText etValor;
   // private EditText etSource;
    private EditText etData;
    private EditText etFpagamento;
    private EditText etFrecebimento;
    private EditText etDescricao;
    private DatePickerDialog dialogDatePicker;
    private Calendar calendar;
    //private EditText textDate;
    private DateFormat dateFormat;

    private ArrayList<SpinnerIntem> addspinnerList;
    private SpinnerAdapter mSpinnerAdapter;
    private Spinner spinnerSource;

    private int idImage;
    private String etSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Add Conta");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contaadd);
        etValor = findViewById(R.id.AddValor);
       // etSource = findViewById(R.id.Addsource);
        etData = findViewById(R.id.AddData);
        etFpagamento = findViewById(R.id.AddFpagamento);
        etFrecebimento = findViewById(R.id.AddFrecebimento);
        etDescricao = findViewById(R.id.AddDescricao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
                etSource = clickItem.getNomeItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //################fim spinner############################

        calendar = new GregorianCalendar();

        calendar.setTimeZone(TimeZone.getDefault());

        dateFormat = DateFormat.getDateInstance();
        //textDate = findViewById(R.id.textDate);

        etData.setText(dateFormat.format(calendar.getTime()));

        dialogDatePicker = new DatePickerDialog(this,
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
    }

    public  void confirmeAdd(View view){



        Float valor = Float.parseFloat(etValor.getText().toString());
        //String source = etSource.getText().toString();
        String data = etData.getText().toString();
        String pag = etFpagamento.getText().toString();
        String recb = etFrecebimento.getText().toString();
        String desc = etDescricao.getText().toString();

        Conta cont = new Conta(valor,data,etSource,pag,recb,desc,idImage);

        SharedResources.getInstance().getContas().add(cont);
        SharedResources.getInstance().saveContas(this);

        Toast.makeText(this, "Conta adicionada com sucesso",
                Toast.LENGTH_SHORT).show();

        etSource = "";
        etValor.setText("");
        etData.setText("");
        etDescricao.setText("");
        etFrecebimento.setText("");
        etFpagamento.setText("");
        etDescricao.setText("");

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

    public void showDatePicker(View view) {
        dialogDatePicker.show();
    }

}
