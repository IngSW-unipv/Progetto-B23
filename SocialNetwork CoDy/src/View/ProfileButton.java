package View;

import Controller.ModifyProfileController;
import model.User;


/**
 * La classe ProfileButton rappresenta il bottone per la finestra di modifica del profilo dell'utente
 * Questa classe estende la classe astratta AbstractButton
 *
 * @author Corrado
 * @version 1.0
 */
public class ProfileButton extends AbstractButton {

    /**
     * Costruttore per la classe ProfileButton
     * @param text Testo del bottone
     * @param pic Immagine del bottone
     * @param user Utente che clicca il bottone
     * @param frame Frame della finestra
     */
    public ProfileButton(String text, String pic, User user, JFrame frame) {
        super(text, pic, user, frame);
    }

    /**
     * Gestisce l'evento di click del bottone
     * @see AbstractButton#onClick()
     */
    @Override
    public void onClick() {
        new ModifyProfileController(user);
        frame.dispose();
    }

    /**
     * Gestisce l'evento di uscita dal bottone
     * @see AbstractButton#onLeave()
     */
    @Override
    public void onHover() {
        setBackground(ColorGUIView.background);
    }

    /**
     * Gestisce l'evento di ingresso nel bottone
     * @see AbstractButton#onHover()
     */
    @Override
    public void onLeave() {
        setBackground(ColorGUIView.side);
    }

}
