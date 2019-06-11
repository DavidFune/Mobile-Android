package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
public class ReceitaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Receita> receitas;


    @Override
    public int getCount() {
        return receitas.size();
    }

    @Override
    public Object getItem(int position) {
        return receitas.get(position);
    }

    public ReceitaAdapter(Context context, ArrayList<Receita> receitas) {
        this.context = context;
        this.receitas = receitas;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Receita receita = receitas.get(position);
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View ver = inflater.inflate(R.layout.activity_receita_adapter, null);

        TextView tvOrigem = ver.findViewById(R.id.SourceL);

        TextView tvValor = ver.findViewById(R.id.ValorL);
        tvValor.setText(String.valueOf(receita.getValor()));

        TextView tvData = ver.findViewById(R.id.DataL);
        tvData.setText(receita.getData());


        ImageView tvImagem = ver.findViewById(R.id.imageR);

        switch (receita.getIdimageR()){
            case 0:
                tvImagem.setImageResource(R.drawable.salario);
                tvOrigem.setText("Salário");
                break;
            case 1:
                tvImagem.setImageResource(R.drawable.negocios);
                tvOrigem.setText("Negocios");
                break;

            case 2:
                tvImagem.setImageResource(R.drawable.tranferenciabanc);
                tvOrigem.setText("Tranferência");
                break;
        }


        return ver;
    }


}
