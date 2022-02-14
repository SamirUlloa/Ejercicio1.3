package ejercicio.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ejercicio.ejercicio13.configuraciones.SQLiteConexion;
import ejercicio.ejercicio13.configuraciones.Transacciones;
import ejercicio.ejercicio13.tablas.Personas;

public class ActivityActualizar extends AppCompatActivity {

    TextView codigo;
    EditText nombres, apellidos, edad, correo, direccion;
    Button btnActualiza;
    Button btnElimi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        codigo = (TextView) findViewById(R.id.txtCod);
        nombres = (EditText) findViewById(R.id.txtNom);
        apellidos = (EditText) findViewById(R.id.txtApe);
        edad = (EditText) findViewById(R.id.txtEda);
        correo = (EditText) findViewById(R.id.txtCorre);
        direccion = (EditText) findViewById(R.id.txtDire);

        btnActualiza = (Button) findViewById(R.id.btnActualiza);
        btnActualiza = (Button) findViewById(R.id.btnActualiza);

        Bundle obj = getIntent().getExtras();

        Personas perso = null;

        if (obj != null){
            perso = (Personas) obj.getSerializable("persona");

            codigo.setText(perso.getId().toString());
            nombres.setText(perso.getNombres().toString());
            apellidos.setText(perso.getApellidos().toString());
            edad.setText(perso.getEdad().toString());
            correo.setText(perso.getCorreo().toString());
            direccion.setText(perso.getDireccion().toString());
        }

        btnActualiza = (Button) findViewById(R.id.btnActualiza);
        btnActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModificarPersonas();
            }
        });

        btnElimi = (Button) findViewById(R.id.btnElimi);
        btnElimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarPersonas();
            }
        });
    }

    private void EliminarPersonas() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String cod = codigo.getText().toString();

        db.delete("personas", "id=" + cod, null);
        Toast.makeText(this, "Regristo " + cod + " Eliminado Correctamente", Toast.LENGTH_LONG).show();

        db.close();
        LimpiarPantalla();
    }

    private void LimpiarPantalla() {
        codigo.setText("");
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }

    private void ModificarPersonas() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String cod = codigo.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        if (!codigo.getText().toString().isEmpty()){
            db.update("personas", valores, "id=" + cod, null);
            Toast.makeText(this, "Se Actualizo el Registro: " +cod, Toast.LENGTH_LONG).show();
        }

    }
}