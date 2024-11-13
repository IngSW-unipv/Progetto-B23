
package database;

import View.AlertView;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La classe UserDAO rappresenta un'interfaccia per l'accesso
 * e la modifica dei dati degli utenti nel database.
 *
 * I metodi della classe consentono di creare un utente, di verificare
 * se un utente con una data email esiste, di leggere i dati di un utente
 * e di aggiornare i dati di un utente.
 *
 * @author CoDy
 * @version 1.0
 */
public class UserDAO {
    private User u;
    private Connection connection;

    /**
     * Costruttore che inizializza un oggetto UserDAO con un utente specificato.
     *
     * @param u l'utente da associare a questo UserDAO
     */
    public UserDAO(User u) {
        this.u = u;
    }
    public UserDAO(){}

    /**
     * Metodo che crea un utente nel database.
     *
     * Il metodo crea un utente con i dati dell'oggetto UserDAO
     * e li inserisce nel database.
     */
    public void create() {
        String insert = "INSERT INTO users (Nome, Cognome, Email, Password) VALUES (?,?,?,?)";
        try {
            connection=DbConnectionSingleton.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(insert);
            pstmt.setString(1, u.getNome());
            pstmt.setString(2, u.getCognome());
            pstmt.setString(3, u.getEmail());
            pstmt.setString(4, u.getPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
    }

    /**
     * Metodo che verifica se l'email dell'utente
     * associato e' gia' presente nel database.
     * @return true se l'email e' gia' presente nel database, false altrimenti
     */
    public boolean emailUsata() {
        boolean used = false;
        String select = "SELECT * FROM users WHERE Email = ?";

        try {
            connection=DbConnectionSingleton.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(select);
            pstmt.setString(1, u.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                used = true;
            }
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return used;
    }


    /**
     * Metodo che legge un utente dal database.
     *
     * Il metodo legge l'utente con l'email e la password
     * dell'oggetto UserDAO e lo restituisce.
     * @return l'utente se l'email e la password sono corrette, null altrimenti
     */
    public User getUser() {
        u.setAmici(new ArrayList<>());
        String select = "SELECT ID FROM users WHERE Email = ? AND Password = ?";

        try {
            connection=DbConnectionSingleton.getInstance().getConnection();
            PreparedStatement pstmt = connection.prepareStatement(select);
            pstmt.setString(1, u.getEmail());
            pstmt.setString(2, u.getPassword());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                u.setID(rs.getInt("ID"));
            }
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return u;
    }

    /**
     * Aggiorna l'utente nel database.
     *
     * Il metodo aggiorna i campi dell'utente nel database.
     *
     * @return true se l'aggiornamento va a buon fine, false altrimenti
     */
    public boolean update() {
        PreparedStatement pstmt = null;
        boolean updated;
        String update = "UPDATE users SET Nome = ?, Cognome = ?, Email = ? WHERE ID = ?";

        try  {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(update);
            pstmt.setString(1, u.getNome());
            pstmt.setString(2, u.getCognome());
            pstmt.setString(3, u.getEmail());
            pstmt.setInt(4, u.getID());

            pstmt.executeUpdate();
            updated=true;
        } catch (SQLException e) {
            new AlertView("aggiornaUser", null);
            updated=false;
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return updated;
    }

/**
 * Cambia la password dell'utente nel database.
 *
 * @param newPassword la nuova password da impostare per l'utente
 * @return true se la modifica della password va a buon fine, false altrimenti
 */
    public boolean changePassword(String newPassword){
        PreparedStatement pstmt;
        boolean changed;
        String update = "UPDATE users SET Password = ? WHERE ID = ?";

        try {
            connection=DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(update);
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, u.getID());

            pstmt.executeUpdate();
            changed=true;
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
            changed=false;
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return changed;
    }

    /**
     * Metodo che legge un utente dal database.
     *
     * Il metodo restituisce un oggetto User se l'email e la password
     * passate come parametri sono corrette, altrimenti restituisce null.
     *
     * @param email l'email dell'utente da cercare
     * @param password la password dell'utente da cercare
     * @return un oggetto User se l'utente esiste, null altrimenti
     */
    public User readUser(String email, String password){
        PreparedStatement ps;
        PreparedStatement pstmt;
        PreparedStatement pstmt2;
        String select= "SELECT * FROM users WHERE Email = ? AND Password = ?;";
        User user=null;
        try{
            connection = DbConnectionSingleton.getInstance().getConnection();
            ps = connection.prepareStatement(select);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs =ps.executeQuery();
            if (rs.next()){
                user = new User();
                user.setID(rs.getInt("ID"));
                user.setNome(rs.getString("Nome"));
                user.setCognome(rs.getString("Cognome"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));

                if(!user.getEmail().equals(email) || !user.getPassword().equals(password)){
                    //loginSuc=false;
                    return null;
                }


                String findFriends = "SELECT * FROM friends WHERE User = ?;";
                pstmt= connection.prepareStatement(findFriends);
                pstmt.setInt(1, user.getID());

                ArrayList<Integer> amiciID= new ArrayList<>();
                ResultSet rs2=pstmt.executeQuery();
                while (rs2.next()){
                    amiciID.add(rs2.getInt("Friend"));
                }
                user.setFriendsID(amiciID);


                String findLikeDPosts = "SELECT * FROM likes WHERE User = ?;";
                pstmt2 = connection.prepareStatement(findLikeDPosts);
                pstmt2.setInt(1, user.getID());

                ArrayList<Integer> likedPostID= new ArrayList<>();
                ResultSet rs3=pstmt2.executeQuery();
                while (rs3.next()){
                    likedPostID.add(rs3.getInt("Post"));
                }
                user.setLikesID(likedPostID);
            }



        }catch (SQLException e ){
            new AlertView(e.getMessage(),null);
        }

        DbConnectionSingleton.getInstance().closeConnection();
        return user;
    }

    /**
     * Questo metodo legge i dati di un utente a partire dal suo ID.
     * @param ID l'ID dell'utente da cercare
     * @return un oggetto User se l'utente esiste, null altrimenti
     */
    public User readUserByID(int ID) {
        PreparedStatement pstmt = null;
        String select = "SELECT * FROM users WHERE ID = ?";
        User user = null;
        try {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(select);
            pstmt.setInt(1, ID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setID(ID);
                    user.setNome(rs.getString("Nome"));
                    user.setCognome(rs.getString("Cognome"));
                    user.setEmail(rs.getString("Email"));
                } else {
                    new AlertView("user with ID " + ID + " not found", null);
                }
            }
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return user;
    }


    /**
     * Metodo che legge tutti gli utenti dal database.
     *
     * Il metodo restituisce una lista di oggetti User contenente tutti gli utenti
     * presenti nel database. L'utente che effettua la richiesta non viene incluso
     * nella lista.
     *
     * @param user l'utente che effettua la richiesta
     * @return una lista di oggetti User contenente tutti gli utenti presenti nel database
     */
    public ArrayList<User> readAllUsers(User user){
        PreparedStatement pstmt;

        String select = "SELECT * FROM users;";
        ArrayList<User> users = new ArrayList<>();
        try{
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(select);
            ResultSet rs =pstmt.executeQuery(select);
            while (rs.next()){
                User u = new User();
                u.setID(rs.getInt("ID"));
                u.setNome(rs.getString("Nome"));
                u.setCognome(rs.getString("Cognome"));
                u.setEmail(rs.getString("Email"));
                if(u.getID()!=user.getID()) users.add(u);
            }
        }catch (SQLException e ){
            new AlertView(e.getMessage(),null);
        }

        DbConnectionSingleton.getInstance().closeConnection();
        return users;
    }


}

