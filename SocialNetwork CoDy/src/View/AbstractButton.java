package View;


import model.User;
import javax.swing.JLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe AbstractButton rappresenta un pulsante astratto.
 *
 * Questa classe fornisce metodi per gestire i pulsanti, come ad esempio il testo e l'immagine del pulsante.
 * Inoltre, fornisce metodi per gestire gli eventi del mouse, come ad esempio il click e l'hover.
 *
 * @author Corrado
 * @version 1.1
 */
public abstract class AbstractButton extends JPanel {
    protected User user;
    protected JFrame frame;
    protected JLabel img;
    protected String text;
    protected String pic;

    /**
     * Costruttore della classe AbstractButton.
     *
     * Inizializza il pulsante con il testo e l'immagine specificati.
     *
     * @param text il testo del pulsante
     * @param pic l'immagine del pulsante
     * @param user l'utente associato al pulsante
     * @param frame la finestra di dialogo associata al pulsante
     */
    public AbstractButton(String text, String pic, User user, JFrame frame) {
        this.user = user;
        this.frame = frame;
        this.text = text;
        this.pic = pic;

        setPreferredSize(new Dimension(60, 60));
        setBackground(ColorGUIView.side);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel img = new JLabel(new ImageIcon("immagini/" + pic + ".png"));
        add(img);

        addMouseListener(new MouseAdapter() {
            /**
             * Esegue l'azione del pulsante quando viene premuto con il mouse.
             *
             * @param e l'evento del mouse
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick();
            }

            /**
             * Esegue l'azione del pulsante quando il mouse entra nel suo riquadro.
             *
             * @param e l'evento del mouse
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                onHover();
            }

            /**
             * Esegue l'azione del pulsante quando il mouse esce dal suo riquadro.
             *
             * @param e l'evento del mouse
             */
            @Override
            public void mouseExited(MouseEvent e) {
                onLeave();
            }
        });
    }

/**
 * Esegue l'azione del pulsante quando viene cliccato.
 */
    public abstract void onClick();

    /**
     * Esegue l'azione del pulsante quando il mouse entra nel suo riquadro.
     * cambia il colore di sfondo del pulsante.
     */
    public abstract void onHover();

    /**
     * Esegue l'azione del pulsante quando il mouse esce dal suo riquadro.
     *
     */
    public abstract void onLeave();


    /**
     * Restituisce l'immagine del pulsante.
     *
     * @return l'immagine del pulsante
     */
    public JLabel getImg() {
        return img;
    }
}

