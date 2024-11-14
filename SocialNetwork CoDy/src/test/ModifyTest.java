package test;

import database.DbConnectionSingleton;
import database.UserDAO;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifyTest {
    private Connection connection;
    @BeforeEach
    public void setUp() throws SQLException {
        connection = DbConnectionSingleton.getInstance().getConnection();

        // Creazione di un utente di test nel database
        String createUser = "INSERT INTO users (ID, Nome, Cognome, Email, Password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(createUser)) {
            ps.setInt(1, 111111);
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
        String deleteUser = "DELETE FROM users WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteUser)) {
            ps.setInt(1, 111111);
            ps.executeUpdate();
        }
        DbConnectionSingleton.getInstance().closeConnection();
    }

    @Test
    public void testModifySuccess() {
        UserDAO modifyUser = new UserDAO();

        User user = modifyUser.readUser("test@email.com", "password123");

        user.setNome("Nome Modificato");
        user.setCognome("Cognome Modificato");
        user.setEmail("ciao@email.com");

        UserDAO update = new UserDAO(user);
        update.update();

        assert(user.getNome().equals("Nome Modificato"));
        assert(user.getCognome().equals("Cognome Modificato"));
        assert(user.getEmail().equals("ciao@email.com"));
        System.out.println("la modifica è avvenuta con successo");
    }
}
