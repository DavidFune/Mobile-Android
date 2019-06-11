package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SharedResourcesReceitas {
    private static SharedResourcesReceitas shared = null;

    //Singleton elements
    private static ArrayList<Receita> receitas;

    public static SharedResourcesReceitas getInstance() {
        if(shared == null) {
            shared = new SharedResourcesReceitas();
        }
        return shared;
    }

    private SharedResourcesReceitas() {
        receitas = new ArrayList<Receita>();
    }

    public ArrayList<Receita> getReceitas() {
        return receitas;
    }

    public void saveReceitas(Context context){
        try {
            FileOutputStream fos = context.openFileOutput("receitas.dat",
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(receitas);
            clone();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void loadReceitas(Context context){
        try{
            FileInputStream fis = context.openFileInput("receitas.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            receitas = (ArrayList<Receita>)
                    ois.readObject();
            ois.close();
        }catch (Exception e){

            e.printStackTrace();

        }
    }
}
