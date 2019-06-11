package pentip.afinal.trabalho.progmovel.pentip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class OrdenaMes {

    public ArrayList<Conta> ordeC(ArrayList<Conta> contas, int index, int ano) throws ParseException {

        ArrayList<Conta> xs = new ArrayList<Conta>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        for (int i=0; i<contas.size();i++){
            date = formato.parse(contas.get(i).getData());
            if(date.getMonth() == index && date.getYear()==ano){
                xs.add(contas.get(i));
            }
        }
        return xs;
    }


    public ArrayList<Receita> ordeR(ArrayList<Receita> receitas, int index, int ano) throws ParseException {

        ArrayList<Receita> ys = new ArrayList<Receita>();

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date;

        for (int i=0; i<receitas.size();i++){
            date = formato.parse(receitas.get(i).getData());
            if(date.getMonth() == index && date.getYear()==ano){
                ys.add(receitas.get(i));
            }
        }
        return ys;
    }
}
