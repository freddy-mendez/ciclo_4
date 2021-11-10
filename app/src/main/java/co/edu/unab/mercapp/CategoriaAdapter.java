package co.edu.unab.mercapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.mercapp.entity.Categoria;

public class CategoriaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Categoria> datos;

    public CategoriaAdapter(Context context, ArrayList<Categoria> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        if (this.datos==null) {
            return 0;
        } else {
            return this.datos.size();
        }
    }

    @Override
    public Object getItem(int pos) {
        return this.datos.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return this.datos.get(pos).getId();
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {

        if (view==null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_lista, viewGroup, false);
        }

        //Categoria categoria = (Categoria) this.getItem(pos);
        final Categoria categoria = this.datos.get(pos);

        TextView nombre = view.findViewById(R.id.lbNombre);
        TextView descripcion = view.findViewById(R.id.lbDescripcion);

        ImageView imagen = view.findViewById(R.id.imagen);

        nombre.setText(categoria.getNombre());
        descripcion.setText(categoria.getDescripcion());

        Picasso.get().load(categoria.getUrlImagen())
                .placeholder(R.drawable.no_foto)
                .error(R.drawable.error_load)
             //   .resize(200, 200)
                .into(imagen);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), ""+categoria.getNombre()+"!!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
