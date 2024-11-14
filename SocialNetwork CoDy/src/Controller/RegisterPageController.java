package Controller;

import View.AlertView;
import View.HomePageView;
import View.LoginView;
import View.RegisterPageView;
import model.RegisterPage;
import model.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * La classe RegisterPageController gestisce la logica per la pagina di registrazione.
 * Coordina le interazioni tra la vista di registrazione e il modello di registrazione.
 *
 * Fornisce funzionalità per registrare un nuovo utente e per passare alla pagina di login.
 *
 * @author Dylan
 * @version 1.0
 */
public class RegisterPageController {
    private final RegisterPageView registerPage;
    private final RegisterPage registerModel;

    /**
     * Costruttore della classe RegisterPageController.
     *
     * @param registerPage La vista di registrazione.
     */
    public RegisterPageController(RegisterPageView registerPage) {
        this.registerPage = registerPage;
        this.registerModel = new RegisterPage();

        registerPage.getCreaAccButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleRegister();
            }
        });

        registerPage.getLoginLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLogin();
            }
        });
    }

    /**
     * metodo per gestire la registrazione di un nuovo utente.
     * gestisce la validazione dei dati, la registrazione dell'utente
     * e il passaggio alla pagina di login.
     */
    private void handleRegister() {
        String nome = registerPage.getNomeField().getText().trim();
        String cognome = registerPage.getCognomeField().getText().trim();
        String email = registerPage.getEmailField().getText().trim();
        String password = registerPage.getPasswordField().getText().trim();
        String confermaPassword = registerPage.getConfermaPasswordField().getText().trim();

        String errore = registerModel.validaDati(nome, cognome, email, password, confermaPassword);
        if (errore != null) {
            new AlertView(errore, registerPage.getFrame());
            return;
        }

        User nuovoUtente = registerModel.registraUtente(nome, cognome, email, password);
        if (nuovoUtente != null) {
            new HomePageController(nuovoUtente, new HomePageView(nuovoUtente));
            registerPage.getFrame().dispose();
        } else {
            new AlertView("Email già usata", registerPage.getFrame());
        }
    }

    /**
     * Metodo per passare alla pagina di login.
     */
    private void handleLogin() {
        new LoginController(new LoginView());
        registerPage.getFrame().dispose();
    }
}

