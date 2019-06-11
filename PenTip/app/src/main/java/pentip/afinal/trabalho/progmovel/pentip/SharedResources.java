package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SharedResources {

    private static SharedResources shared = null;


    //Singleton elements
    private static ArrayList<Conta> contas;

    public static SharedResources getInstance() {
        if(shared == null) {
            shared = new SharedResources();
        }
        return shared;
    }

    private SharedResources() {
        contas = new ArrayList<Conta>();
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void saveContas(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(("contas.dat"),
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contas);
            clone();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void loadContas(Context context){
        try{
            FileInputStream fis = context.openFileInput("contas.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            contas = (ArrayList<Conta>)
                    ois.readObject();
            ois.close();
        }catch (Exception e){

            e.printStackTrace();

        }
    }
}
