package dato;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class data {
    public static final String url = "jdbc:postgresql://localhost:5432/productos";
    public static final String usuario = "postgres";
    public static final String contraseña = "1234";

    public static void main(String[] args) {
        // Corrida de 100000 aplicaciones
        for (int i = 0; i < 1000; i++) {
            // Creación de un thread para cada conexión
            new thread().start();
        }
    }
}

class thread extends Thread {
    @Override
    public void run() {
        try {
            // Creación de una conexión a la base de datos
            Connection conn = DriverManager.getConnection(data.url, data.usuario, data.contraseña);
            System.out.println("Conexion hecha");

            // Creación de un Statement para ejecutar la sentencia SQL
            Statement stmt = conn.createStatement();

            // Ejecución de la sentencia SQL
            ResultSet rs = stmt.executeQuery("SELECT * FROM prod");
            while (rs.next()) {
                System.out.println("Registro encontrado: " + rs.getString(1) + ", " + rs.getString(2));
            }

            // Cierre del Statement
            stmt.close();

            // Cierre de la conexión
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        }
    }
}