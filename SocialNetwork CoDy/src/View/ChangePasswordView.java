package View;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che rappresenta la view della finestra per cambiare la password dell'utente loggato.
 *
 * @author Dylan
 * @version 1.0
 */
public class ChangePasswordView {
    private final JFrame frame;
    private final JTextField vecchiaPassword, nuovaPassword, confermaPassword;
    private final JButton submit;

    /**
     * Costruttore che crea la view per il cambio della password.
     */
    public ChangePasswordView(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel panel= new JPanel(new BorderLayout());
        panel.setBackground(null);
        panel.setBorder(BorderFactory.createEmptyBorder(83,99,175,99));
        panel.add(new JLabel("               Cambia Password",40, ColorGUIView.white ,Font.BOLD),BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4,1,10,10));
        center.setBackground(null);
        center.setBorder(BorderFactory.createEmptyBorder(58,150,0,150));

        vecchiaPassword = new JTextField("Vecchia password");
        center.add(vecchiaPassword);

        nuovaPassword = new JTextField("Nuova password");
        center.add(nuovaPassword);

        confermaPassword = new JTextField("Conferma nuova Password");
        center.add(confermaPassword);

        submit= new JButton("Conferma",45,20);
        center.add(submit);
        panel.add(center,BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Restituisce il frame della finestra.
     * @return il frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Restituisce il campo di testo per la vecchia password.
     * @return il campo di testo per la vecchia password
     */
    public JTextField getVecchiaPasswordField() {
        return vecchiaPassword;
    }


    /**
     * Restituisce il campo di testo per la nuova password.
     * @return il campo di testo per la nuova password
     */
    public JTextField getNuovaPasswordField() {
        return nuovaPassword;
    }

    /**
     * Restituisce il campo di testo per la conferma della nuova password.
     * @return il campo di testo per la conferma della nuova password
     */
    public JTextField getConfermaPasswordField() {
        return confermaPassword;
    }

    /**
     * Restituisce il bottone per confermare la modifica della password.
     * @return il bottone per confermare la modifica della password
     */
    public JButton getSubmitButton() {
        return submit;
    }

}
