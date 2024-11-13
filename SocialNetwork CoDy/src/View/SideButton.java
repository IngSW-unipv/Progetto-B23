package View;

import Controller.TabController;
import model.User;

/**
 * La classe SideButton rappresenta un pulsante che si trova nel lato sinistro della finestra principale.
 * Questa classe fornisce metodi per gestire il comportamento del pulsante al click e all'hover.
 * Il pulsante al click apre una finestra che contiene una tabella dove sono presenti le opzioni dell'utente,
 * come ad esempio l'elenco degli utenti, dei posts, dei like, della sua pagina profilo e il logout.
 * Estende la classe astratta AbstractButton
 *
 * @author Corrado
 * @version 1.0
 */
public class SideButton extends AbstractButton {

    /**
     * Costruttore per la classe SideButton.
     * @param text il testo da visualizzare nel pulsante
     * @param pic il nome del file dell'immagine da visualizzare nel pulsante
     * @param user l'utente che ha effettuato il login
     * @param frame la finestra principale dell'applicazione
     */
    public SideButton(String text, String pic, User user, JFrame frame) {
        super(text, pic, user, frame);
    }

    /**
     * Metodo che viene chiamato quando il pulsante viene premuto con il mouse.
     * @see AbstractButton#onClick()
     */
    @Override
    public void onClick() {
        new TabController(text, user);
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
     * Metodo che viene chiamato quando il mouse lascia il pulsante.
     * @see AbstractButton#onLeave()
     */
    @Override
    public void onLeave() {
        setBackground(ColorGUIView.side);
    }
}

