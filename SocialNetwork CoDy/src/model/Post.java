package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * La classe Post rappresenta un post.
 *
 * Questa classe fornisce metodi per gestire i post, come ad esempio il testo del post, l'autore del post e la data e l'ora di pubblicazione.
 *
 * @author Dylan
 * @version 1.0
 */
public class Post {

    private int ID;
    private String testo;
    private User autore;
    private LocalDateTime tempo;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");

    public Post() {
    }
    /**
     * Costruttore della classe Post.
     *
     * @param testo il testo del commento
     * @param autore l'autore del commento
     */
    public Post(String testo, User autore) {
        this.testo = testo;
        this.autore = autore;
        tempo= LocalDateTime.now();
    }

    /**
     * Ritorna l'ID del post.
     *
     * @return l'ID del post
     */
    public int getID() {
        return ID;
    }

    /**
     * Imposta l'ID del post.
     *
     * @param ID l'ID del post
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * Ritorna il testo del post.
     *
     * @return il testo del post
     */
    public String getTesto() {
        return testo;
    }


    /**
     * Imposta il testo del post.
     *
     * @param testo il nuovo testo del post
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }


    /**
     * Ritorna l'autore del post.
     *
     * @return l'autore del post
     */
    public User getAutore() {
        return autore;
    }

    /**
     * Imposta l'autore del post.
     *
     * @param autore il nuovo autore del post
     */
    public void setAutore(User autore) {
        this.autore = autore;
    }

    /**
     * Ritorna la data e l'ora del post come stringa.
     *
     * La stringa viene formattata secondo il pattern "yyyy-MM-dd HH:mm:ss".
     *
     * @return la data e l'ora del post come stringa
     */
    public String getDateTimeToString(){
        return dateTimeFormatter.format(tempo);
    }

    /**
     * Imposta la data e l'ora del commento a partire da una stringa che rappresenta la data e l'ora
     * nel formato "yyyy-MM-dd HH:mm:ss".
     *
     * @param tempo la stringa che rappresenta la data e l'ora del commento
     */public void setDateTimeFromString(String tempo){
        this.tempo= LocalDateTime.parse(tempo,dateTimeFormatter);
    }

    /**
     * Ritorna la data del commento come stringa nel formato "EEE, dd MMM yyyy".
     *
     * @return la data del commento come stringa
     */
    public String getDateToString(){
        return dateFormatter.format(tempo);
    }
}
