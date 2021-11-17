package co.edu.unab.mercapp.view.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import co.edu.unab.mercapp.R;
import co.edu.unab.mercapp.entity.Producto;

public class CrearProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_produto);
    }

    public void guardar(View view) {
        EditText cod = findViewById(R.id.txt_cod_pro);
        EditText nom = findViewById(R.id.txt_nom_pro);
        EditText precio = findViewById(R.id.txt_pre_pro);
        CheckBox disponible = findViewById(R.id.txt_dis_pro);

        /*Map<String, Object> values = new HashMap<>();
        values.put("codigo", cod.getText().toString());
        values.put("nombre", nom.getText().toString());
        values.put("precio", Integer.parseInt(precio.getText().toString()));
        values.put("disponible", disponible.isChecked());*/
        Producto producto = new Producto(cod.getText().toString(), nom.getText().toString(),
                Integer.parseInt(precio.getText().toString()), disponible.isChecked());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("productos").add(producto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        setResult(RESULT_OK);
                        finish();
                    }
                })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al crear el producto", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void volver(View view) {
        finish();
    }
}