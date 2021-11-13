package co.edu.unab.mercapp.view.firebase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import co.edu.unab.mercapp.R;
import co.edu.unab.mercapp.entity.Producto;

public class GestionFirebaseActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference productos=db.collection("productos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_firebase);
        getAllData();
        ListView lista = findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto producto = (Producto) lista.getItemAtPosition(i);
                //Toast.makeText(getApplicationContext(), "ID="+producto.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GestionFirebaseActivity.this, EditarProductoActvity.class);
                intent.putExtra("idDocumento", producto.getId());
                //startActivity(intent);
                editar.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> editar = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK) {
                        getAllData();
                    }
                }
            });

    private void getAllData() {
        ArrayList<Producto> datos = new ArrayList<>();
        productos.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document: task.getResult()) {
                                Producto producto = document.toObject(Producto.class);
                                producto.setId(document.getId());
                                datos.add(producto);
                            }
                            cargarLista(datos);
                        }
                    }
                });
    }

    public void volver(View view) {
        finish();
    }

    private void cargarLista(ArrayList<Producto> datos) {
        ListView lista = findViewById(R.id.lista);
        ArrayAdapter<Producto> adapter = new ArrayAdapter<>(
          getApplicationContext(),
          android.R.layout.simple_list_item_1,
          datos
        );
        lista.setAdapter(adapter);
        Log.i("ListView","Finish");
    }
}