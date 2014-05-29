/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vacaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mx
 */
public class Conexion {

  //private String url;
    /**
     *
     */
    
// DServer <- odbcDriverConnect(connection = "DRIVER=SQL Server;
// Trusted_Connection=Yes;DATABASE=ViCo;SERVER=FSX-GT3")
    
    public Connection conn;
    public Statement s;
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    private static String url = "jdbc:sqlserver://FSX-GT3;databaseName=Colaboradores;integratedSecurity=true";
    //private static String url = "jdbc:sqlserver://IEI-000-846zrm1\\SQLEXPRESS;databaseName=Colaboradores;integratedSecurity=true";
    private static String user = "";
    private static String password = "";
    private static Connection Conectar = null;
    
    //-Djava.library.path="C:\sqljdbc_4.0\enu\auth\x86"
    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    //
    public static Connection getConnection(String driver, String url, String user, String password) throws SQLException {
    //public static Connection getConnection(String driver, String url) throws SQLException {
        try {
            Class.forName(driver);
            Conectar = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
        }
        return Conectar;
    }
    
    public static Connection getConnection() throws SQLException {
        return getConnection(driver, url, user, password);
    }    
    
                
        //64078
        /*
        
        * Primero:
                Habilitar el protocolo TCP/IP, desde el SQL Server Configuration Manager hacemos clic en el nodo "Protocols for SQLEXPRESS " y con el botón derecho seleccionamos Enable.
        
        * Segundo:
                Predeterminamos en que puerto va escuchar el sql server, se puede dejar dinámico pero vamos a tener problemas con el firewall.
                Desde el SQL Server Configuration Manager hacemos clic en el nodo "Protocols for SQLEXPRESS " y con el botón derecho seleccionamos Propiedades.
                Vamos IPAdress a IPAll, en el valor de TCP Dynamic Port lo limpiamos y escribimos un valor de puerto en TCP Port, por ejemplo 1433 que es el puerto por default de SQL Server.
                * Iniciar SQL Browser
        
        * Tercero:
                Luego vamos al firewall de windows y agregamos una excepción para el puerto 1433 y listo....
        
        * Cuarto:
                Ubicamos el archivo para autenticación: 
                -Djava.library.path="C:\Program Files (x86)\Java\sqljdbc_4.0\enu\auth\x86"
                * para ubicar el archivo "sqljdbc_auth.dll" para la autoautenticacion.
                Right click on the Project==>Properties==>Click on RUN
                ==>VM Options : -Djava.library.path="C:\Your Directory where Dll is present"
        * Quinto:
                Right click on the Project==>Properties==>Click on Libraries
                * agregamos la libreria sqljdbc_4.jar

         */
    //}


    public static void close() throws SQLException {
        Conectar.close();
    }
    
}
