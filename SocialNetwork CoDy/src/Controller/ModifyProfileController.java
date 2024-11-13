package Controller;

import View.ModifyProfileView;
import View.AlertView;
import View.HomePageView;
import View.JFrame;
import View.ChangePasswordView;
import database.UserDAO;
import model.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * La classe ModifyProfileController rappresenta il controller per la finestra di modifica del profilo.
 * Questa classe fornisce metodi per gestire la finestra di modifica del profilo.
 *
 * @author Corrado
 * @version 1.0
 */
public class ModifyProfileController {
    private final ModifyProfileView modifyView;
    private final User user;

    /**
     * Costruttore della classe ModifyProfileController.
     *
     * @param user L'utente per cui aprire la finestra di modifica del profilo.
     */
    public ModifyProfileController(User user) {
        this.user = user;
        this.modifyView = new ModifyProfileView();

        modifyView.getNomeField().setText(user.getNome());
        modifyView.getCognomeField().setText(user.getCognome());
        modifyView.getEmailField().setText(user.getEmail());

        modifyView.addSubmitListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSubmit();
            }
        });

        modifyView.addCambiaPswListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChangePassword();
            }
        });

        modifyView.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleFrame();
            }
        });
    }

    /**
     * Metodo per gestire la chiusura della finestra di modifica del profilo.
     * Verrà aperta la finestra di home page.
     */
    private void handleFrame(){
        JFrame frame=modifyView.getFrame();
        frame.dispose();
        new HomePageController(user, new HomePageView(user));
    }

    /**
     * Metodo per gestire la conferma delle modifiche del profilo.
     * Verranno eseguiti i controlli sui campi di input e verrà effettuato l'update del profilo.
     * se il nome, cognome e email non sono vuoti verranno aggiornati i dati dell'utente.
     */
    private void handleSubmit() {
        String nome = modifyView.getNomeField().getText().trim();
        String cognome = modifyView.getCognomeField().getText().trim();
        String email = modifyView.getEmailField().getText().trim();

        if (nome.isEmpty() || nome.equals("Nome")) {
            new AlertView("Il nome non può essere vuoto", modifyView.getFrame());
            return;
        }
        if (cognome.isEmpty() || cognome.equals("Cognome")) {
            new AlertView("Il cognome non può essere vuoto", modifyView.getFrame());
            return;
        }
        if (email.isEmpty() || email.equals("Email")) {
            new AlertView("L'email non può essere vuota", modifyView.getFrame());
            return;
        }

        user.setNome(nome);
        user.setCognome(cognome);
        user.setEmail(email);

        UserDAO update = new UserDAO(user);
        if (!email.equals(user.getEmail()) || update.emailUsata()) {
            new AlertView("L'Email è già in uso", modifyView.getFrame());
            return;
        }
        if (update.update()) {
            new AlertView("Profilo aggiornato con successo", modifyView.getFrame());
            new HomePageController(user, new HomePageView(user));
            modifyView.getFrame().dispose();
        } else {
            new AlertView("Errore nell'aggiornamento del profilo", modifyView.getFrame());
        }
    }

    /**
     * Metodo per gestire la modifica della password.
     * Verra' aperta la finestra di cambio password.
     */
    private void handleChangePassword() {
        new ChangePasswordController(new ChangePasswordView(), user);
        modifyView.getFrame().dispose();
    }
}
