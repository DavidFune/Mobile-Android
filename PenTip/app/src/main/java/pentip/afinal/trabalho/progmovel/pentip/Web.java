package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


public class Web {

    private Context context;
    private String valorConv;
    private String moedaConv;
    private boolean encontrou;
    private String USD;
    private String EUR;
    private String GBP;
    private String JPY;

    public String getUSD() {
        return USD;
    }

    public String getEUR() {
        return EUR;
    }

    public String getGBP() {
        return GBP;
    }

    public String getJPY() {
        return JPY;
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }

    public void setEUR(String EUR) {
        this.EUR = EUR;
    }

    public void setGBP(String GBP) {
        this.GBP = GBP;
    }

    public void setJPY(String JPY) {
        this.JPY = JPY;
    }

    public Context getContext() {
        return context;
    }

    public String getValorConv() {
        return valorConv;
    }

    public String getMoedaConv() {
        return moedaConv;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setValorConv(String valorConv) {
        this.valorConv = valorConv;
    }

    public void setMoedaConv(String moedaConv) {
        this.moedaConv = moedaConv;
    }

    public boolean isEncontrou() {
        return encontrou;
    }

    public void setEncontrou(boolean encontrou) {
        this.encontrou = encontrou;
    }

    public Web(Context context) {
        this.context = context;
        this.buscar();
    }




    public final void buscar(){

        String url = "https://api.exchangeratesapi.io/latest?base=BRL";

        Log.d("CSI401", url);
        JSONObject obj = null;
        try {
            // recebendo a informação da url com uma string
            obj = new JSONObject(this.get(url));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(!obj.has("erro")){

            try {
                this.USD = obj.getJSONObject("rates").getString("USD");
                this.EUR = obj.getJSONObject("rates").getString("EUR");
                this.GBP = obj.getJSONObject("rates").getString("GBP");
                this.JPY = obj.getJSONObject("rates").getString("JPY");
                encontrou = true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            encontrou = false;
            Log.d("CSI401", "Não foi possível converter a moeda");
        }
    }

    public final String get(String urlToRead) {
        StringBuilder result = new StringBuilder();

        try {
            // criando a conexão
            URL url = new URL(urlToRead);
            // conexão http
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (MalformedURLException | ProtocolException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result.toString();
    }

}
