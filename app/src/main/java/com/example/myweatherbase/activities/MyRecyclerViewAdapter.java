package com.example.myweatherbase.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Root root; //Lista de usuarios a mostrar
    //    private List<Usuario> usuarios;
    private final LayoutInflater inflater;

    public MyRecyclerViewAdapter(Context context, Root root) {
        this.root = Connector.getConector().get(Root.class,"&lat=39.4913900&lon=-0.4634900");
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

//    public MyRecyclerViewAdapter(Context context, List<Usuario> usuarios) {
//        this(context);
//        this.usuarios = usuarios;
//    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        View view = inflater.inflate(R.layout.activity_main, parent, false);
        return new ViewHolder(view);
    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder holder, int position) {



        holder.txtView.setText(root.list.get(0).weather.get(0).description);
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + root.list.get(0).weather.get(0).icon + Parameters.ICON_URL_POST, holder.imageView);

        Date date = new Date((long)root.list.get(0).dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("EEEE");
        SimpleDateFormat dateComplete = new SimpleDateFormat("d/MMMM/yyyy");
        SimpleDateFormat dateHour = new SimpleDateFormat("HH:mm"); // "EEE, d MMM yyyy HH:mm"
        holder.textTempAct.setText("Temp: " +root.list.get(0).main.temp+"ºC");
        holder.textTempMax.setText("TempMax: " +root.list.get(0).main.temp_max+"ºC");
        holder.textTempMin.setText("TempMin: " +root.list.get(0).main.temp_min+"ºC");

        holder.textDay.setText(dateDayOfWeek.format(date));
        holder.textFecha.setText(dateComplete.format(date));
        holder.textViewDay.setText(dateHour.format(date));
    }

    // Indicamos el número de elementos de la lista
    @Override
    public int getItemCount() {
        return root.list.size();
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtView, textViewDay, textFecha, textDay, textTempAct, textTempMax, textTempMin ;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.txtView);
            textViewDay = itemView.findViewById(R.id.textViewDay);
            txtView = itemView.findViewById(R.id.txtView);
            imageView = itemView.findViewById(R.id.imageView);
            textDay = itemView.findViewById(R.id.textDay);
            textFecha = itemView.findViewById(R.id.textFecha);
            textTempAct = itemView.findViewById(R.id.textTempAct);
            textTempMax = itemView.findViewById(R.id.textTempMax);
            textTempMin = itemView.findViewById(R.id.textTempMin);
        }
    }

}
