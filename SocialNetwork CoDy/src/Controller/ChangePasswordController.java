package Controller;

import View.AlertView;
import View.ChangePasswordView;
import View.HomePageView;
import View.JFrame;
import database.UserDAO;
import model.User;

import java.awt.event.*;

/**
 * Classe che rappresenta il controller della finestra per cambiare la password dell'utente loggato.
 *
 * @author Dylan
 * @version 1.0
 */
public class ChangePasswordController {
    private final ChangePasswordView changePasswordView;

    /**
     * Costruttore del controller per la modifica della password.
     *
     * @param changePasswordView la view per la modifica della password
     * @param user l'utente loggato
     */
    public ChangePasswordController(ChangePasswordView changePasswordView, User user) {
        JFrame frame = changePasswordView.getFrame();
        this.changePasswordView = changePasswordView;
        changePasswordView.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new HomePageController(user, new HomePageView(user));
            }
        });

        changePasswordView.getSubmitButton().addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                String vecchiaPassword = changePasswordView.getVecchiaPasswordField().getText().trim();
                String nuovaPassword = changePasswordView.getNuovaPasswordField().getText().trim();
                String confermaPassword = changePasswordView.getConfermaPasswordField().getText().trim();

                if (!vecchiaPassword.equals(user.getPassword())) {
                    new AlertView("la vecchia password non corrisponde", frame);
                    return;
                }
                if (vecchiaPassword.isEmpty()) {
                    new AlertView("inserisci la vecchia password", frame);
                    return;
                }
                if (nuovaPassword.isEmpty() || nuovaPassword.startsWith(" ")) {
                    new AlertView("inserisci la nuova password", frame);
                    return;
                }
                if (confermaPassword.isEmpty()) {
                    new AlertView("conferma la nuova password", frame);
                    return;
                }

                if (nuovaPassword.length() < 6) {
                    new AlertView("la password deve contenere almeno 6 caratteri", frame);
                    return;
                }

                if (!nuovaPassword.equals(confermaPassword)) {
                    new AlertView("le password non corrispondono", frame);
                    return;
                }


                UserDAO change = new UserDAO(user);

                if (change.changePassword(nuovaPassword)) {
                    user.setPassword(nuovaPassword);
                    new HomePageController(user, new HomePageView(user));
                    new AlertView("Password cambiata con successo", changePasswordView.getFrame());
                    frame.dispose();
                }
            }
        });


    }

}

