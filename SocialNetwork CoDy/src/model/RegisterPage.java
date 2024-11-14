package model;

import database.UserDAO;

/**
 * La classe RegisterPage rappresenta la logica per la registrazione di un nuovo utente.
 *
 * @author dylan
 * @version 1.0
 */
public class RegisterPage {

    /**
     * Verifica la validità dei dati di registrazione.
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param email L'email dell'utente.
     * @param password La password dell'utente.
     * @param confermaPassword La conferma della password.
     * @return Un messaggio di errore se qualche campo non è valido, altrimenti null.
     */

    public String validaDati(String nome, String cognome, String email, String password, String confermaPassword) {
        if (nome.isEmpty()) return "Il nome non può essere vuoto!";
        if (cognome.isEmpty()) return "Il cognome non può essere vuoto";
        if (email.isEmpty()) return "L'email non può essere vuota";
        if (password.isEmpty()) return "La password non può essere vuota";
        if (password.length() < 6) return "La password deve essere di almeno 6 caratteri";
        if (!password.equals(confermaPassword)) return "Le password non coincidono";

        return null;  // Dati validi
    }

    /**
     * Registra un nuovo utente nel sistema.
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param email L'email dell'utente.
     * @param password La password dell'utente.
     * @return true se la registrazione ha avuto successo, false se l'email è già usata.
     */
    public User registraUtente(String nome, String cognome, String email, String password) {
        User nuovoUtente = new User(nome, cognome, email, password);
        UserDAO userDAO = new UserDAO(nuovoUtente);

        if (!userDAO.emailUsata()) {
            userDAO.create();
            return userDAO.getUser();
        } else {
            return null;
        }
    }
}
