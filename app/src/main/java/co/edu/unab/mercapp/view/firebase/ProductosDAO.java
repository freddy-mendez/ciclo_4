package co.edu.unab.mercapp.view.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import co.edu.unab.mercapp.entity.Producto;

public class ProductosDAO {
    private FirebaseFirestore db;
    private CollectionReference productos;

    public ProductosDAO(FirebaseFirestore db) {
        this.db = db;
        this.productos=db.collection("productos");
    }

    public ArrayList<Producto> getAll() {
        ArrayList<Producto> datos = new ArrayList<>();
        productos.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document: task.getResult()) {
                                Producto producto = document.toObject(Producto.class);
                                datos.add(producto);
                            }
                            Log.i("ArrayList","Finish");
                        }
                    }
                });
        return datos;
    }
}
