package pentip.afinal.trabalho.progmovel.pentip;
import java.util.ArrayList;

public class Balanco {


    public Float getSumContas(ArrayList<Conta> x){
        float sumx = 0;

        for (int i=0; i<x.size();i++)
            sumx += x.get(i).getValor();

        return sumx;
    }

    public Float getSumReceitas(ArrayList<Receita> y){

        float sumy = 0;

        for (int i=0; i < y.size();i++)
            sumy += y.get(i).getValor();

        return sumy;
    }

    public Float resumo(ArrayList<Conta> x,ArrayList<Receita> y){

        float xy =0;

        xy = getSumReceitas(y) - getSumContas(x);

        return xy;
    }
}
