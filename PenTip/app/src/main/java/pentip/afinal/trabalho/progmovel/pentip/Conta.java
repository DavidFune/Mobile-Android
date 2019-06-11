package pentip.afinal.trabalho.progmovel.pentip;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Conta implements Parcelable, Serializable {

    private Float valor;
    private String data;
    private String source;
    private String fPagamento;
    private String fRecebimento;
    private String descricao;
    private int    idimage;


    protected Conta(Parcel in){

        valor = in.readFloat();
        data = in.readString();
        source = in.readString();
        fPagamento = in.readString();
        fRecebimento = in.readString();
        descricao = in.readString();
        idimage = in.readInt();
    }

    public static final Creator<Conta> CREATOR = new Creator<Conta>() {
        @Override
        public Conta createFromParcel(Parcel in) {
            return new Conta(in);
        }

        @Override
        public Conta[] newArray(int size) {
            return new Conta[size];
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
        dest.writeString(fPagamento);
        dest.writeString(fRecebimento);
        dest.writeString(descricao);
        dest.writeInt(idimage);

    }

    public int getIdimage() {
        return idimage;
    }

    public void setIdimage(int idimage) {
        this.idimage = idimage;
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
    public String getfPagamento() {
        return fPagamento;
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

    public void setfPagamento(String fPagamento) {
        this.fPagamento = fPagamento;
    }

    public void setfRecebimento(String fRecebimento) {
        this.fRecebimento = fRecebimento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Conta(Float valor, String data, String source,
                 String fPagamento, String fRecebimento, String descricao,int idimage) {
        this.valor = valor;
        this.data = data;
        this.source = source;
        this.fPagamento = fPagamento;
        this.fRecebimento = fRecebimento;
        this.descricao = descricao;
        this.idimage = idimage;
    }

}
