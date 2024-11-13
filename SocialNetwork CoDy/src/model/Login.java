package model;

/**
 * La classe Login rappresenta le credenziali di un utente che vuole loggare sul social network.
 *
 * @author Corrado
 * @version 1.0
 */
public class Login {
    private String email;
    private String password;

    /**
     * Costruttore della classe Login.
     *
     * @param email l'email dell'utente
     * @param password la password dell'utente
     */
    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    /**
     * Restituisce l'email dell'utente.
     *
     * @return l'email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'email dell'utente.
     *
     * @param email l'email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return la password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password la password dell'utente
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Verifica le credenziali dell'utente.
     *
     * @return true se le credenziali sono valide, false altrimenti
     */
    public boolean verificaCredenziali() {
        return email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }
}

