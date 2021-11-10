package co.edu.unab.mercapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.unab.mercapp.entity.Categoria;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("onCreate","OK MenuActivity");
        setContentView(R.layout.activity_menu);
        String user = getIntent().getStringExtra("userName");
        if (DataTemp.CATEGORIAS.size()==0) {
            DataTemp.cargarCategorias();
        }

        String [] datos = {"Aseo Personal", "Alimentos", "Bebidas", "Electrodomesticos",
        "Lacteos", "Cereales", "Aseo Hogar", "Frios"};

        Spinner comboBox = findViewById(R.id.spinner);

        ArrayAdapter<Categoria> adapterSpinner = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                DataTemp.CATEGORIAS
        );

        comboBox.setAdapter(adapterSpinner);

        // Lista Basica
        /*ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1 ,
                categorias
        );*/

        // Lista Single Choice
        /*ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_single_choice ,
                categorias
        );*/

        // Lista Multiple Choice
        /*ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice ,
                categorias
        );*/

        CategoriaAdapter adapter = new CategoriaAdapter(
                getApplicationContext(),
                //DataTemp.CATEGORIAS
                DataTemp.CATEGORIAS_INTERES
        );


        final ListView lista = findViewById(R.id.lista);
        //lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lista.setAdapter(adapter);

        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Categoria valor = (Categoria) lista.getItemAtPosition(pos);
                Toast.makeText(getApplicationContext(), valor.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });*/

        Button volver = findViewById(R.id.btnVolver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        TextView lbMsg = findViewById(R.id.lbSaludo);
        lbMsg.setText("Hola "+user+" Bienvenido");
        TextView celular = findViewById(R.id.textView5);
        celular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //EditText cel = (EditText) view;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:3001234567"));
                startActivity(intent);
            }
        });

    }

    ActivityResultLauncher<Intent> llamada_recycler = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK) {
                        ListView lista = findViewById(R.id.lista);
                        ((CategoriaAdapter) lista.getAdapter()).notifyDataSetChanged();
                    }
                }
            }
    );

    public void callRecycler(View view) {
        Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);
        //startActivity(intent);
        llamada_recycler.launch(intent);
    }


    public void enviar(View view) {
        /*EditText txtEdad = findViewById(R.id.txtEdad);
        int edad = -1;
        if (txtEdad.getText().toString().length()>0) {
            edad = Integer.parseInt(txtEdad.getText().toString());
        }
        Intent intent = new Intent();
        intent.putExtra("edad", edad);
        setResult(RESULT_OK, intent);
        //setResult(RESULT_OK);
        finish();*/
        ListView lista = findViewById(R.id.lista);
        //String valor = (String) lista.getItemAtPosition(lista.getCheckedItemPosition());
        /*Log.i("Pos=", ""+lista.getCheckedItemPosition());
        Categoria valor = (Categoria) lista.getItemAtPosition(lista.getCheckedItemPosition());
        //String valor = (String) lista.getSelectedItem();
        Toast.makeText(getApplicationContext(), valor.getNombre(), Toast.LENGTH_SHORT).show();/**/

        String cadena = "";
        SparseBooleanArray valores = lista.getCheckedItemPositions();
        Log.i("Elementos=", ""+valores.size());
        for (int i=0; i<valores.size(); i++) {
            if (valores.valueAt(i)) {
                Categoria valor = (Categoria) lista.getItemAtPosition(valores.keyAt(i));
                cadena += valor.getNombre()+"\n";
            }
        }
        Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();

        Spinner comboBox = findViewById(R.id.spinner);
        Categoria elemento = (Categoria) comboBox.getSelectedItem();
        Log.i("Elemento Spinner=", elemento.toString());

    }

    public void callAgregar(View view) {
        Intent intent = new Intent(getApplicationContext(),
                AgregarCategoriaActivity.class);
        startActivity(intent);
    }

/*
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","OK MenuActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop","OK MenuActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","OK MenuActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("onRestart","OK MenuActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","OK MenuActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","OK MenuActivity");
    }*/
}