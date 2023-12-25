package com.example.gestionstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class ComponentAdapter extends ArrayAdapter<ItemComponent> {


    List<ItemComponent> listComponents;

    public ComponentAdapter(@NonNull Context context, List<ItemComponent> listComponents) {
        super(context, 0);
        this.listComponents = listComponents;
    }


    @Override
    public int getCount() {
        return listComponents.size();
    }

    @Nullable
    @Override
    public ItemComponent getItem(int position) {
        return listComponents.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_component, parent, false);
        ItemComponent item = listComponents.get(position);

        TextView name, date, quantity, id_cat;
        name = layout.findViewById(R.id.textViewName);
        date = layout.findViewById(R.id.textViewDate);
        quantity = layout.findViewById(R.id.textViewQuantity);
        id_cat = layout.findViewById(R.id.textViewCategoryId);

        name.setText("Name: " + item.getName());
        date.setText("Date Aquisition: " + item.getDate().toString());
        quantity.setText("Quantity: " + item.getQuantity());
        id_cat.setText("Category ID: " + item.getCategorie_id());
        return layout;
    }
}
