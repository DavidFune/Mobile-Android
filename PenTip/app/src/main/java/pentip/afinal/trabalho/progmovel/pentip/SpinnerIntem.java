package pentip.afinal.trabalho.progmovel.pentip;

public class SpinnerIntem {

    private String nomeItem;
    private int sourceImage;

    public SpinnerIntem(String nomeItem, int sourceImage) {
        this.nomeItem = nomeItem;
        this.sourceImage = sourceImage;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public int getSourceImage() {
        return sourceImage;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public void setSourceImage(int sourceImage) {
        this.sourceImage = sourceImage;
    }
}
