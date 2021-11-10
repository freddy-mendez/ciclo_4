package co.edu.unab.mercapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.mercapp.entity.Categoria;

public class CategoriaRecyclerAdapter
        extends RecyclerView.Adapter<CategoriaRecyclerAdapter.CategoriaViewHolder> {
    ArrayList<Categoria> categorias;
    OnItemClickListener listener;

    static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView descripcion;
        ImageView imagen;

        public CategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.txt_nom_card);
            this.descripcion = itemView.findViewById(R.id.txt_desc_card);
            this.imagen = itemView.findViewById(R.id.img_card);
        }

        public void bind(final Categoria categoria, final OnItemClickListener listener) {
            this.nombre.setText(categoria.getNombre());
            this.descripcion.setText(categoria.getDescripcion());
            Picasso.get().load(categoria.getUrlImagen()).into(this.imagen);
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(categoria);
                }
            });
        }
    }

    public CategoriaRecyclerAdapter(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public CategoriaRecyclerAdapter(ArrayList<Categoria> categorias, OnItemClickListener listener) {
        this.categorias = categorias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_recycler, parent, false);
        CategoriaViewHolder categoriaViewHolder = new CategoriaViewHolder(vista);
        return categoriaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        Categoria categoria = this.categorias.get(position);
        /*holder.nombre.setText(categoria.getNombre());
        holder.descripcion.setText(categoria.getDescripcion());
        Picasso.get().load(categoria.getUrlImagen()).into(holder.imagen);*/
        holder.bind(categoria, listener);

    }

    @Override
    public int getItemCount() {
        return this.categorias.size();
    }


    public interface OnItemClickListener {
        void onItemClick(Categoria categoria);
    }


}
