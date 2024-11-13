package View;

import java.awt.*;

/**
 * La classe JLabel rappresenta una versione personalizzata di JLabel.
 * Questa classe fornisce metodi per impostare il testo, il font e il colore di un'etichetta.
 *
 * @author Corrado
 * @version 1.0
 */
public class JLabel extends javax.swing.JLabel {

    /**
     * Costruttore per la classe JLabel
     * @param text il testo da visualizzare
     * @param textSize la dimensione del testo
     * @param color il colore del testo
     * @param style lo stile del testo
     */
    public JLabel(String text,int textSize,Color color, int style){
        setFont(new Font("Segoe UI", style,textSize));
        setText(text);
        setForeground(color);
    }
}
