package com.isil.efinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.isil.efinal.R;
import com.isil.efinal.entity.Orden;

import java.util.List;

public class OrdenesAdapter extends BaseAdapter {

    private Context context;
    private List<Orden> ordenesList;

    public OrdenesAdapter(Context context, List<Orden> ordenesList) {
        this.context = context;
        this.ordenesList = ordenesList;
    }

    @Override
    public int getCount() {
        return ordenesList.size();
    }

    @Override
    public Object getItem(int position) {
        return ordenesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_orden, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Orden orden = ordenesList.get(position);
        holder.tvNombrePlatoOrden.setText(orden.getNomPlato());
        holder.tvCantidadOrden.setText(String.valueOf(orden.getCantidad()));
        holder.tvPrecioTotalOrden.setText("S/ " + orden.getPrecioTotalPlato());

        return view;
    }

    private static class ViewHolder {
        private TextView tvNombrePlatoOrden;
        private TextView tvCantidadOrden;
        private TextView tvPrecioTotalOrden;

        private ViewHolder(View view) {
            tvNombrePlatoOrden = view.findViewById(R.id.tvNombrePlatoOrden);
            tvCantidadOrden = view.findViewById(R.id.tvCantidadOrden);
            tvPrecioTotalOrden = view.findViewById(R.id.tvPrecioTotalOrden);
        }
    }
}
