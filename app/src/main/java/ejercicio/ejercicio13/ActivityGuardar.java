package ejercicio.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ejercicio.ejercicio13.configuraciones.SQLiteConexion;
import ejercicio.ejercicio13.configuraciones.Transacciones;

public class ActivityGuardar extends AppCompatActivity {

    EditText codigo, nombres, apellidos, edad, correo, dirrecion;
    Button btnadd;
    Button btnEliminar;
    Button btnModificar;
    Button btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        codigo = (EditText) findViewById(R.id.txtcodigo);
        nombres = (EditText) findViewById(R.id.txtnombres);
        apellidos = (EditText) findViewById(R.id.txtapellido);
        edad = (EditText) findViewById(R.id.txtedad);
        correo = (EditText) findViewById(R.id.txtcorreo);
        dirrecion = (EditText) findViewById(R.id.txtdireccion);

        btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersonas();
            }
        });

        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarPersonas();
            }
        });

        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModificarPersonas();
            }
        });

        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityGuardar.this, ActivityMostrarDatos.class);
                startActivity(intent);
            }
        });
    }

    private void AgregarPersonas() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, dirrecion.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(),
                "Registro ingreso con exito!! Codigo " + resultado.toString(),
                Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();
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

    private void ModificarPersonas(){
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String cod = codigo.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, dirrecion.getText().toString());

        if (!codigo.getText().toString().isEmpty()){
            db.update("personas", valores, "id=" + cod, null);
            Toast.makeText(this, "Se Actualizo el Registro: " +cod, Toast.LENGTH_LONG).show();
        }

        LimpiarPantalla();
    }

    private void LimpiarPantalla() {
        codigo.setText("");
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        dirrecion.setText("");
    }
}