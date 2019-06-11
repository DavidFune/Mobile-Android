package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.String;

import java.util.ArrayList;
public class ContasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Conta> contas;

    public ContasAdapter(Context context, ArrayList<Conta> contas) {
        this.context = context;
        this.contas = contas;
    }

    @Override
    public int getCount() {
        return contas.size();
    }

    @Override
    public Object getItem(int position) {
        return contas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Conta conta = contas.get(position);
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View ver = inflater.inflate
                (R.layout.activity_conta_adapter,null);

        TextView tvValo = ver.findViewById(R.id.ValorC);
        tvValo.setText(String.valueOf(conta.getValor()));

        TextView tvsource = ver.findViewById(R.id.SourceC);


        TextView tvData = ver.findViewById(R.id.DataC);
        tvData.setText(conta.getData());

        ImageView tvImagem = ver.findViewById(R.id.imageC);
        
        switch (conta.getIdimage()){
            case 0:
                tvImagem.setImageResource(R.drawable.alimentaca);
                tvsource.setText("Alimentação");
                break;
            case 1:
                tvImagem.setImageResource(R.drawable.escola);
                tvsource.setText("Escola");
                break;

            case 2:
                tvImagem.setImageResource(R.drawable.esporte);
                tvsource.setText("Esporte");
                break;
            case 3:
                tvImagem.setImageResource(R.drawable.mercado);
                tvsource.setText("Mercado");
                break;
            case 4:
                tvImagem.setImageResource(R.drawable.feira);
                tvsource.setText("Feira");
                break;
            case 5:
                tvImagem.setImageResource(R.drawable.tranferenciabanc);
                tvsource.setText("Trans.Bancaria");
                break;
        }


        ArrayList<SpinnerIntem>  addspinnerList = new ArrayList<>();
        addspinnerList.add(new SpinnerIntem("Alimentacão",R.drawable.alimentaca));
        addspinnerList.add(new SpinnerIntem("Escola",R.drawable.escola ));
        addspinnerList.add(new SpinnerIntem("Esporte",R.drawable.esporte ));
        addspinnerList.add(new SpinnerIntem("Feira",R.drawable.feira ));
        addspinnerList.add(new SpinnerIntem("Mercado",R.drawable.mercado ));
        addspinnerList.add(new SpinnerIntem("Trans.Bancaria",R.drawable.tranferenciabanc ));


        /*TextView tvFrecebimento = ver.findViewById(R.id.FrecebimentoC);
        tvFrecebimento.setText(conta.getfRecebimento());

        TextView tvFpagamento = ver.findViewById(R.id.FpagamentoC);
        tvFpagamento.setText(conta.getfPagamento());

        TextView tvDescricao = ver.findViewById(R.id.Descrisao);
        tvDescricao.setText(conta.getDescricao());*/



        return ver;
    }

}
