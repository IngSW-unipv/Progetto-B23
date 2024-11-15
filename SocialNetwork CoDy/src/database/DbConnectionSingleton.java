package database;

import View.AlertView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che rappresenta la connessione al database.
 * La classe utilizza il pattern Singleton per garantire che
 * venga creata una sola istanza della connessione al database.
 * La connessione viene aperta solo alla prima richiesta e
 * viene chiusa solo quando l'oggetto {@link DbConnectionSingleton}
 * viene deallocato.
 *
 * @author CoDy
 */
public class DbConnectionSingleton {
    private static DbConnectionSingleton instance;
    private Connection connection;


    /**
     * Costruttore di default della classe. Il costruttore viene
     * chiamato solo una volta, quando l'oggetto viene creato per
     * la prima volta.
     */
    private DbConnectionSingleton() {
        startConnection();
    }

    /**
     * Restituisce l'istanza singleton di DbConnectionSingleton.
     * Se l'istanza non esiste, viene creata una nuova istanza.
     *
     * @return l'istanza singleton di DbConnectionSingleton
     */
    public static synchronized DbConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new DbConnectionSingleton();
        }
        return instance;
    }

    /**
     * Apertura della connessione al database.
     * Il metodo viene chiamato solo una volta, quando l'oggetto
     * viene creato per la prima volta.
     * Il metodo imposta la connessione come attributo
     * {@link #connection} dell'oggetto.
     */
    private void startConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/social";
            String user = "root";
            String password = "D520881R";
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
            throw new RuntimeException("Errore durante l'apertura della connessione al database", e);
        }
    }

    /**
     * Restituisce la connessione al database.
     *
     * @return la connessione al database
     */
    public Connection getConnection() {
        if (this.connection == null) {
            startConnection();
        }
        return this.connection;
    }

    /**
     * Chiusura della connessione al database.
     * Il metodo viene chiamato quando l'oggetto viene deallocato.
     */
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                new AlertView(e.getMessage(), null);
            } finally {
                this.connection = null;
            }
        }
    }
}

