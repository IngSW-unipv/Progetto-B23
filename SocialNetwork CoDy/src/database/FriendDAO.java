
package database;

import View.AlertView;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * La classe FriendDAO rappresenta la classe che gestisce le operazioni di database
 * relative alle amicizie tra utenti.
 *
 * @author CoDy
 * @version 1.0
 */
public class FriendDAO {
    private final User user, f;
    private static Connection connection;

    /**
     * Costruttore che crea un oggetto FriendDAO con l'utente e l'amico da aggiungere.
     * @param user l'utente che richiede l'amicizia
     * @param f l'utente a cui viene richiesta l'amicizia
     */
    public FriendDAO(User user, User f) {
        this.user = user;
        this.f = f;
    }


    /**
     * Aggiunge un'amicizia tra l'utente e l'amico nel database.
     *Se l'amicizia esiste, restituisce false.
     *
     * @return true se l'operazione è andata a buon fine, false altrimenti.
     */
    public boolean isAdded() {
        boolean added;
        PreparedStatement pstmt = null;

        String insert = "INSERT INTO friends (User, Friend) VALUES (?, ?)";

        try {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(insert);
            pstmt.setInt(1, user.getID());
            pstmt.setInt(2, f.getID());

            pstmt.executeUpdate();
            added = true;

        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
            added = false;
        }

        DbConnectionSingleton.getInstance().closeConnection();
        return added;
    }

    /**
     * Rimuove l'amicizia tra l'utente e l'amico dal database.
     * Se l'amicizia non esiste, restituisce false.
     *
     * @return true se l'operazioneè andata a buon fine, false altrimenti.
     */
    public boolean isRemoved(){
        boolean removed;
        PreparedStatement pstmt = null;

        String delete = "DELETE FROM friends WHERE User = ? AND Friend = ?";
        try{
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(delete);
            pstmt.setInt(1, user.getID());
            pstmt.setInt(2, f.getID());

            pstmt.executeUpdate();
            removed=true;
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
            removed=false;
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return removed;
    }

}
