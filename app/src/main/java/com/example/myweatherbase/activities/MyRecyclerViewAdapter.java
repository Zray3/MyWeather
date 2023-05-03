package com.example.myweatherbase.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;

public class MyRecyclerViewAdapter {

    private Root root; //Lista de usuarios a mostrar
    //    private List<Usuario> usuarios;
    private final LayoutInflater inflater;

    public MyRecyclerViewAdapter(Context context) {
        root = UsuarioRepository.getInstance();
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
        Usuario usuario = listaUsuarios.get(position);
        Profesion profesion = ProfesionRepository.getInstance().getProfesionByImage(usuario.getIdProfesion());
        holder.nombre.setText(usuario.getApellidos() + ", " + usuario.getNombre());
        holder.profesion.setText(profesion.getNombre());
        holder.imagen.setImageResource(profesion.getImg());
    }

    // Indicamos el número de elementos de la lista
    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView profesion;
        private ImageView imagen;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textViewNombre);
            profesion = itemView.findViewById(R.id.textViewOficio);
            imagen = itemView.findViewById(R.id.imageView);
        }
    }

}
