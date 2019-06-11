package pentip.afinal.trabalho.progmovel.pentip;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;



public class SpinnerAdapter extends ArrayAdapter<SpinnerIntem> {

    public SpinnerAdapter(Context context, ArrayList<SpinnerIntem> spinnerIntemlist) {
        super(context, 0, spinnerIntemlist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinnerview, parent, false
            );
        }

        ImageView imageViewSource = convertView.findViewById(R.id.imageContaSource);
        TextView  textViewSource = convertView.findViewById(R.id.spinnerTextConta);

        SpinnerIntem spinnerIntem = getItem(position);

        if (spinnerIntem!=null){
            imageViewSource.setImageResource(spinnerIntem.getSourceImage());
            textViewSource.setText(spinnerIntem.getNomeItem());
        }

        return convertView;
    }
}
