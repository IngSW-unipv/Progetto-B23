package test;

import static org.junit.jupiter.api.Assertions.*;

import database.DbConnectionSingleton;
import database.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class ReadUserTest {
    private Connection connection;
    @BeforeEach
    public void setUp() throws SQLException {
        connection = DbConnectionSingleton.getInstance().getConnection();

        // Creazione di un utente di test nel database
        String createUser = "INSERT INTO users (ID, Nome, Cognome, Email, Password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(createUser)) {
            ps.setInt(1, 11111);
            ps.setString(2, "Nome");
            ps.setString(3, "Cognome");
            ps.setString(4, "test@email.com");
            ps.setString(5, "password123");
            ps.executeUpdate();
        }
        DbConnectionSingleton.getInstance().closeConnection();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection = DbConnectionSingleton.getInstance().getConnection();
        // Rimozione dell'utente di test dal database
        String deleteUser = "DELETE FROM users WHERE Email = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteUser)) {
            ps.setString(1, "test@email.com");
            ps.executeUpdate();
        }
        DbConnectionSingleton.getInstance().closeConnection();
    }

    @Test
    public void testLoginSuccess() {
        UserDAO readUser = new UserDAO();
        assertNotNull(readUser.readUser("test@email.com", "password123"), "Il Login dovrebbe dare esito positivo");

        User user = new UserDAO().readUser("test@email.com", "password123");

        assertNotNull(user, "l'utente non dovrebbe essere nullo dopo il login");
        assertEquals("Nome", user.getNome(), "i nomi dovrebbero essere uguali");
        assertEquals("Cognome", user.getCognome(), "i cognomi dovrebbero essere uguali");
        assertEquals("test@email.com", user.getEmail(), "le mail dovrebbero combaciare");
    }

    @Test
    public void testLoginFailure() {
        UserDAO readUser = new UserDAO();
        assertNull(readUser.readUser("test@emailsbagliata.com", "password123"), "Il login dovrebbe fallire per mail sbagliata");
        UserDAO readUserP = new UserDAO();
        assertNull(readUserP.readUser("test@email.com", "password321"), "Il login dovrebbe fallire per password sbagliata");
    }

    @Test
    public void testCaseInsensitive() {
        UserDAO readUser = new UserDAO();
        assertNull(readUser.readUser("TEST@EMAIL.COM", "password123"), "il Login dovrebbe dare esito negativo perchè email case sensitive");

        UserDAO readUserP = new UserDAO();
        assertNull(readUserP.readUser("test@email.com", "PASSWORD123"), "il Login dovrebbe dare esito negativo perchè password case sensitive");
    }
}
