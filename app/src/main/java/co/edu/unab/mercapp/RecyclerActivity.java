package co.edu.unab.mercapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import co.edu.unab.mercapp.entity.Categoria;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        final RecyclerView lista = findViewById(R.id.lista_recycler);

        LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());

        lista.setLayoutManager(linear);

        /*ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                DataTemp.CATEGORIAS
        );*/

        CategoriaRecyclerAdapter adapter =
                new CategoriaRecyclerAdapter(DataTemp.CATEGORIAS,
                        new CategoriaRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Categoria categoria) {
                                //Log.i("Categoria=",categoria.getNombre());
                                DataTemp.CATEGORIAS_INTERES.add(categoria);
                            }
                        });

        lista.setAdapter(adapter);

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("View=", ""+view.toString());
            }
        });



    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }
}