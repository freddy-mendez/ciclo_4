package co.edu.unab.mercapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> llamadoBoton =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode()==RESULT_OK) {
                                String msg = "La edad del usuario es: ";
                                Intent intent = result.getData();
                                if (intent!=null) {
                                    int edad = intent.getIntExtra("edad", -1);
                                    if (edad!=-1) {
                                        msg += edad;
                                    } else {
                                        msg += "N/D";
                                    }
                                }
                                Toast.makeText(getApplicationContext(), "Todo OK,"+msg, Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e("error:", "Algo paso");
                            }
                        }
                    }
            );

    View.OnClickListener eventoClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btnSalir) {
                MainActivity.super.finish();
            } else if (view.getId()==R.id.btnInicio) {
                EditText user = findViewById(R.id.txtUsername);
                EditText pass = findViewById(R.id.txtPassword);

                String username = user.getText().toString();
                String password = pass.getText().toString();
                Toast.makeText(getApplicationContext(), "Bienvenid@ ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("userName", username);
                llamadoBoton.launch(intent);
                //startActivityForResult(intent, 200);
                //startActivity(intent);
                //finish();
            } else {
                Toast.makeText(getApplicationContext(), "Click en la caja ", Toast.LENGTH_SHORT).show();
            }
            //
        }
    };

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200) {
            if (resultCode==RESULT_OK) {
                String msg = "La edad del usuario es: ";
                if (data!=null) {
                    int edad = data.getIntExtra("edad", -1);
                    if (edad!=-1) {
                        msg += edad;
                    } else {
                        msg += "N/D";
                    }
                }
                Toast.makeText(getApplicationContext(), "Todo OK,"+msg, Toast.LENGTH_SHORT).show();
            } else {
                Log.e("error:", "Algo paso");
            }
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("onCreate","OK MainActivity");
        setContentView(R.layout.activity_main);

        EditText pass = findViewById(R.id.txtPassword);
        pass.setOnClickListener(eventoClick);

        Button btnInicio = findViewById(R.id.btnInicio);
        Button btnSalir = findViewById(R.id.btnSalir);
        btnInicio.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "Click Sostenido", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        btnInicio.setOnClickListener(eventoClick);
        btnSalir.setOnClickListener(eventoClick);



    }

    public void iniciarSesion(View resultado) {

        EditText user = findViewById(R.id.txtUsername);
        EditText pass = findViewById(R.id.txtPassword);

        String username = user.getText().toString();
        String password = pass.getText().toString();

        //Log.i("Validar Username:", username);
        //Log.i("valor Password:",password);

        if (username.equalsIgnoreCase("fmendez") && password.equals("123")) {
            Toast.makeText(getApplicationContext(), "Bienvenid@ "+username, Toast.LENGTH_SHORT).show();
        } else {
            user.setText(null);
            pass.setText(null);
        }
    }

    /*@Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Bienvenid@ ", Toast.LENGTH_SHORT).show();
    }*/

    /*@Override
    public void finish() {
        //super.finish();
    }*/

/*    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart","OK MainActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("onRestart","OK MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume","OK MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause","OK MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("onStop","OK MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","OK MainActivity");
    }*/
}