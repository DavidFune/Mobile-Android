package pentip.afinal.trabalho.progmovel.pentip;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Receita implements Parcelable, Serializable {

    private Float valor;
    private String data;
    private String source;
    private String fRecebimento;
    private String descricao;
    private int idimageR;

    protected Receita(Parcel in){
        valor = in.readFloat();
        data = in.readString();
        source = in.readString();
        fRecebimento = in.readString();
        descricao = in.readString();
        idimageR = in.readInt();
    }


    public static final Creator<Receita> CREATOR = new Creator<Receita>() {
        @Override
        public Receita createFromParcel(Parcel in) {
            return new Receita(in);
        }

        @Override
        public Receita[] newArray(int size) {
            return new Receita[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeFloat(valor);
        dest.writeString(data);
        dest.writeString(source);
        dest.writeString(fRecebimento);
        dest.writeString(descricao);
        dest.writeInt(idimageR);

    }

    public int getIdimageR() {
        return idimageR;
    }

    public void setIdimageR(int idimageR) {
        this.idimageR = idimageR;
    }

    public Float getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public String getSource() {
        return source;
    }


    public String getfRecebimento() {
        return fRecebimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public void setfRecebimento(String fRecebimento) {
        this.fRecebimento = fRecebimento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Receita(Float valor, String data, String source
            , String fRecebimento, String descricao,  int idimageR) {
        this.valor = valor;
        this.data = data;
        this.source = source;
        this.fRecebimento = fRecebimento;
        this.descricao = descricao;
        this.idimageR = idimageR;
    }

}
