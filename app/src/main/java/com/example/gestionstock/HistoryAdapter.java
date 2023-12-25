package com.example.gestionstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<Histitem> {
    List<Histitem> his;


    public HistoryAdapter(@NonNull Context context, List<Histitem> list) {
        super(context, 0);
        this.his = list;
    }

    @Override
    public int getCount() {
        return his.size();
    }

    @Nullable
    @Override
    public Histitem getItem(int position) {
        return his.get(position);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View myview = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        TextView idHistory, idUser, idComp, quantite, date;

        idHistory = myview.findViewById(R.id.id_hist);
        idComp = myview.findViewById(R.id.id_comp);
        idUser = myview.findViewById(R.id.id_user);
        quantite = myview.findViewById(R.id.quan_history);
        date = myview.findViewById(R.id.date_hist);

        Histitem item = his.get(position);

        idHistory.setText("ID: " + item.getIdhist());
        idUser.setText("ID User: " + item.getId_user());
        idComp.setText("ID Composant: " + item.getId_comp());
        quantite.setText("QUT:  " + item.getQuantite());
        date.setText("Date:  " + item.getDate());
        
        return myview;

    }

}
