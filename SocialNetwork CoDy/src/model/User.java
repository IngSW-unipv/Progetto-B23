package model;

import java.util.ArrayList;

/**
 * La classe User rappresenta un utente.
 *
 * Questa classe fornisce metodi per gestire le informazioni dell'utente, come ad esempio il nome, il cognome, l'indirizzo email e la password.
 * Inoltre, fornisce metodi per gestire le relazioni tra utenti, come ad esempio l'aggiunta e la rimozione di amici.
 *
 * @author Corrado
 * @version 1.0
 */
public class User {

    private int ID;
    private String nome;
    private String cognome;
    private String email;
    private String password;

    private ArrayList<Integer> likesID;

    private ArrayList<Integer> amiciID;


    public User() {
        likesID =new ArrayList<>();
        amiciID= new ArrayList<>();
    }
    /**
     * Costruttore della classe User.
     *
     * @param nome il nome dell'utente
     * @param cognome il cognome dell'utente
     * @param email l'indirizzo email dell'utente
     * @param password la password dell'utente
     */
    public User(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        likesID = new ArrayList<>();
        amiciID = new ArrayList<>();
    }

    /**
     * Ritorna l'ID dell'utente.
     *
     * @return l'ID dell'utente
     */

    public int getID() {
        return ID;
    }

    /**
     * Imposta l'ID dell'utente.
     *
     * @param ID il nuovo ID dell'utente
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Ritorna il nome dell'utente.
     *
     * @return il nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome il nuovo nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Ritorna il cognome dell'utente.
     *
     * @return il cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome il nuovo cognome dell'utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Ritorna il nome e cognome dell'utente in una stringa.
     *
     * @return il nome e cognome dell'utente separati da uno spazio
     */
    public String stampaNome(){
        return nome + " " + cognome;
    }

    /**
     * Ritorna l'indirizzo email dell'utente.
     *
     * @return l'indirizzo email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email dell'utente.
     *
     * @param email il nuovo indirizzo email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Ritorna la password dell'utente.
     *
     * @return la password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password la nuova password dell'utente
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Imposta la lista degli amici dell'utente tramite una lista di oggetti User.
     * La lista degli ID degli amici viene aggiornata con gli ID degli utenti
     * passati nel parametro.
     *
     * @param amici la lista di oggetti User da aggiungere come amici
     */
    public void setAmici(ArrayList<User> amici) {
        amiciID= new ArrayList<>();
        for(User u : amici){
            amiciID.add(u.getID());
        }
    }

    /**
     * Imposta la lista degli ID degli amici dell'utente.
     * Aggiorna la lista interna degli ID degli amici con la lista fornita
     * come parametro.
     *
     * @param amiciID la lista degli ID degli amici da impostare
     */
    public void setFriendsID(ArrayList<Integer> amiciID){
        this.amiciID= amiciID;
    }

    /**
     * Ritorna la lista degli ID degli amici dell'utente.
     * La lista contiene gli ID degli utenti che sono stati aggiunti come amici
     * dell'utente.
     *
     * @return la lista degli ID degli amici dell'utente
     */
    public ArrayList<Integer> getAmiciID(){
        return amiciID;
    }

    /**
     * Verifica se l'utente specificato è amico dell'utente corrente.
     *
     * @param u l'utente da verificare
     * @return vero se l'utente è; amico, falso altrimenti
     */
    public boolean isFriend(User u){
        return amiciID.contains(u.getID());
    }

    /**
     * Aggiunge l'utente specificato come amico, memorizzandone l'ID
     * nella lista degli amici.
     * @param f l'utente da aggiungere come amico
     */
    public void addFriend(User f){
        amiciID.add(f.getID());
    }

    /**
     * Rimuove l'utente specificato come amico, eliminando l'ID dell'utente
     * dalla lista degli amici.
     * @param f l'utente da rimuovere come amico
     */
    public void removeFriend(User f){
        amiciID.remove((Integer) f.getID());
    }

    /**
     * Memorizza l'ID del post nella lista dei post che l'utente ha messo
     * like.
     * @param p il post da segnare come "mi piace"
     */
    public void like(Post p){
        likesID.add(p.getID());
    }

    /**
     * Rimuove l'ID del post dalla lista dei post che l'utente ha messo
     * like.
     * @param p il post da rimuovere.
     */
    public void dislike(Post p){
        likesID.remove((Integer) p.getID());
    }

    /**
     * Verifica se l'utente ha messo like al post specificato.
     * @param p il post da verificare
     * @return vero se l'utente ha messo like al post, falso altrimenti
     */
    public boolean liked(Post p){
        return likesID.contains(p.getID());
    }


    /**
     * Imposta la lista degli ID dei post a cui l'utente ha messi like.
     * @param likesID la lista degli ID dei post a cui si ha messo like
     */
    public void setLikesID(ArrayList<Integer>likesID){
        this.likesID=likesID;
    }


}
