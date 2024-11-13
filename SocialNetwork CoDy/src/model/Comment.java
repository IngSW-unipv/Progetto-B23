package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe Comment rappresenta un commento.
 * Questa classe fornisce metodi per gestire i commenti, come ad esempio il testo del commento e l'autore.
 *
 * @author Corrado
 * @version 1.0
 */
public class Comment {
    private int ID;
    private String testo;
    private User autore;
    private LocalDateTime time;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");

    public Comment() {
    }
    /**
     * Costruttore della classe Comment.
     *
     * @param testo il testo del commento
     * @param autore l'autore del commento
     */
    public Comment(String testo, User autore){
        this.testo = testo;
        this.autore = autore;
        time= LocalDateTime.now();
    }

    /**
     * Ritorna l'ID del commento.
     *
     * @return l'ID del commento
     */
    public int getID() {

        return ID;
    }

    /**
     * Imposta l'ID del commento.
     *
     * @param ID il nuovo ID del commento
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * Ritorna il testo del commento.
     *
     * @return il testo del commento
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Imposta il testo del commento.
     *
     * @param testo il nuovo testo del commento
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * Ritorna l'autore del commento.
     *
     * @return l'autore del commento
     */
    public User getAutore() {
        return autore;
    }

    /**
     * Imposta l'autore del commento.
     *
     * @param autore il nuovo autore del commento
     */
    public void setAutore(User autore) {
        this.autore = autore;
    }

    /**
     * Imposta la data e l'ora del commento a partire da una stringa che rappresenta la data e l'ora
     * nel formato "yyyy-MM-dd HH:mm:ss".
     *
     * @param time la stringa che rappresenta la data e l'ora del commento
     */
    public void setDateTimeFromString(String time){
        this.time= LocalDateTime.parse(time,dateTimeFormatter);
    }


    /**
     * Ritorna la data e l'ora del commento come stringa nel formato "yyyy-MM-dd HH:mm:ss".
     *
     * @return la data e l'ora del commento come stringa
     */
    public String getDateTimeToString(){
        return dateTimeFormatter.format(time);
    }

    /**
     * Ritorna la data del commento come stringa nel formato "EEE, dd MMM yyyy".
     *
     * @return la data del commento come stringa
     */
    public String getDateToString(){
        return dateFormatter.format(time);
    }
}
