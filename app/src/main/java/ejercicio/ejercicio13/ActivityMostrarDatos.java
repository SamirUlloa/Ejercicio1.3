package ejercicio.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ejercicio.ejercicio13.configuraciones.SQLiteConexion;
import ejercicio.ejercicio13.configuraciones.Transacciones;
import ejercicio.ejercicio13.tablas.Personas;

public class ActivityMostrarDatos extends AppCompatActivity {

    //Variables Globales
    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Personas> listaPersonas;
    ArrayList<String> ArregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        lista = (ListView) findViewById(R.id.lista);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloPersonas);
        lista.setAdapter(adp);

        //
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Personas pers = listaPersonas.get(i);

                Intent intent = new Intent(ActivityMostrarDatos.this, ActivityActualizar.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("persona",pers);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //
    }

    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas list_perso = null;
        listaPersonas = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablapersonas, null);

        while (cursor.moveToNext()){
            list_perso = new Personas();
            list_perso.setId(cursor.getInt(0));
            list_perso.setNombres(cursor.getString(1));
            list_perso.setApellidos(cursor.getString(2));
            list_perso.setEdad(cursor.getInt(3));
            list_perso.setCorreo(cursor.getString(4));
            list_perso.setDireccion(cursor.getString(5));

            listaPersonas.add(list_perso);
        }
        cursor.close();

        llenalista();
    }

    private void llenalista() {
        ArregloPersonas = new ArrayList<String>();
        for (int i=0; i<listaPersonas.size(); i++){
            ArregloPersonas.add(listaPersonas.get(i).getId() +" | "+
                    listaPersonas.get(i).getNombres() +" | "+
                    listaPersonas.get(i).getApellidos() + " | "+
                    listaPersonas.get(i).getEdad() + " | " +
                    listaPersonas.get(i).getCorreo() + " | " +
                    listaPersonas.get(i).getDireccion());
        }
    }
}