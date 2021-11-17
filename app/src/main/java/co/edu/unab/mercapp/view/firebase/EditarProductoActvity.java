package co.edu.unab.mercapp.view.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import co.edu.unab.mercapp.R;
import co.edu.unab.mercapp.entity.Producto;

public class EditarProductoActvity extends AppCompatActivity {
    private String idDocumento;
    private Producto producto;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference productos=db.collection("productos");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto_actvity);
        idDocumento = getIntent().getStringExtra("idDocumento");
        loadProducto();
    }

    private void loadProducto() {
        productos.document(idDocumento).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        producto = documentSnapshot.toObject(Producto.class);
                        mostrarProducto();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al cargar el Producto", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void mostrarProducto() {

        EditText txtCod = findViewById(R.id.txt_cod_pro);
        EditText txtNom = findViewById(R.id.txt_nom_pro);
        EditText txtPre = findViewById(R.id.txt_pre_pro);
        CheckBox disponible = findViewById(R.id.txt_dis_pro);

        //db.document("productos/Producto1")

        txtCod.setText(producto.getCodigo());
        txtNom.setText(producto.getNombre());
        txtPre.setText(""+producto.getPrecio());

        /*for(Map.Entry<String, Object> entry: producto.getReferencias().entrySet()) {
            db.document(entry.getValue().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                }
            });

        }

        db.collection("categorias").document("TPQ86YZ6IyPyPaSUvq29").collection("productos").document("pepsi-600").get()*/

        if (producto.isDisponible()) {
            disponible.setChecked(true);
        } else {
            disponible.setChecked(false);
        }
    }

    public void guardar(View view) {
        EditText cod = findViewById(R.id.txt_cod_pro);
        EditText nom = findViewById(R.id.txt_nom_pro);
        EditText precio = findViewById(R.id.txt_pre_pro);
        CheckBox disponible = findViewById(R.id.txt_dis_pro);

        Map<String, Object> values = new HashMap<>();
        values.put("codigo", cod.getText().toString());
        values.put("nombre", nom.getText().toString());
        values.put("precio", Integer.parseInt(precio.getText().toString()));
        values.put("disponible", disponible.isChecked());

        productos.document(idDocumento).update(values).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                EditarProductoActvity.this.setResult(RESULT_OK);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditarProductoActvity.this, "Error al modificar el producto", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void volver(View view) {
        finish();
    }

    public void eliminar(View view) {
        productos.document(idDocumento)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                EditarProductoActvity.this.setResult(RESULT_OK);
                finish();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}