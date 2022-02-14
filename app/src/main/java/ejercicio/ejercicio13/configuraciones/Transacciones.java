package ejercicio.ejercicio13.configuraciones;

public class Transacciones {
    //Nombre de la base de datos
    public static final String NameDatabase = "EJERCICIO3";

    //Creación de las Tabla Personas de la base de datos
    public static final String tablapersonas = "personas";

    //Campos especificos de la  tabla persona
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    //Transacciones DDL (data definition lenguage)
    public static final String CreateTablePersonas = "CREATE TABLE " + tablapersonas +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS " + tablapersonas;
}
