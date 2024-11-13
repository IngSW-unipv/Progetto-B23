package View;

import Controller.RegisterPageController;
import model.User;


/**
 * La classe SideButtonLogout rappresenta un bottone laterale per la funzione di logout.
 * estende la classe astratta AbstractButton
 *
 * @author Corrado
 * @version 1.0
 */
public class SideButtonLogout extends AbstractButton {

    /**
     * Costruttore per la classe SideButtonLogout
     *
     * @param text il testo del pulsante
     * @param pic  il percorso dell'immagine del pulsante
     * @param user l'utente loggato
     * @param frame la finestra principale
     */
    public SideButtonLogout(String text, String pic, User user, JFrame frame) {
        super(text, pic, user, frame);
    }

    /**
     * Esegue l'azione del pulsante quando viene premuto con il mouse.<br>
     * Nel caso specifico, il pulsante logout, apre la schermata di registrazione e chiude la finestra
     * principale.
     */
    @Override
    public void onClick() {
        new RegisterPageController(new RegisterPageView());
        frame.dispose();
    }


    /**
     * Metodo che viene chiamato quando il mouse passa sopra il pulsante.
     * @see AbstractButton#onHover()
     */
    @Override
    public void onHover() {
        setBackground(ColorGUIView.background);
    }

    /**
     * Il metodo onLeave esegue l'azione del pulsante quando il mouse esce dal suo riquadro.
     * @see AbstractButton#onLeave()
     */
    @Override
    public void onLeave() {
        setBackground(ColorGUIView.side);
    }


}
