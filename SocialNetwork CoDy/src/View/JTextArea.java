package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * La classe JTextArea rappresenta un campo di testo multiriga.
 * Questa classe fornisce metodi per gestire i campi di testo, come ad esempio il testo e il colore del testo.
 * Inoltre, la classe fornisce metodi per gestire gli eventi del mouse, come ad esempio il click e l'hover.
 *
 * @author Dylan
 * @version 1.0
 */
public class JTextArea extends javax.swing.JTextArea {

    private String hint;

    /**
     * Costruttore della classe JTextArea.
     * Imposta il font, il colore di sfondo, il testo iniziale, il colore del testo e il padding.
     * Aggiunge un listener per gestire gli eventi di focus.
     *
     * @param hint il testo suggerimento da visualizzare quando il campo di testo è vuoto
     * @param textSize la dimensione del testo
     * @param padding il padding interno del campo di testo
     */
    public JTextArea(String hint,int textSize, int padding){
        super();
        setFont(new Font("Segoe UI", Font.BOLD,textSize));
        setBackground(ColorGUIView.side);
        setText(hint);
        setForeground((Color.lightGray));
        setBorder(BorderFactory.createEmptyBorder(padding,padding,padding,padding));


        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(hint)){
                    setText("");
                    setForeground(Color.lightGray);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().equals("")){
                    setText(hint);
                    setForeground(Color.lightGray);
                }
            }
        });
    }


    /**
     * Costruttore della classe JTextArea.
     * Imposta il font, il testo, il colore del testo e il padding.
     * Il campo di testo non è editabile.
     *
     * @param text il testo da visualizzare
     * @param textSize la dimensione del testo
     * @param color il colore del testo
     * @param style lo stile del font
     */
    public JTextArea(String text, int textSize, Color color, int style){
        super();
        setFont(new Font("Segoe UI",style,textSize));
        setText(text);
        setBackground(null);
        setForeground(color);
        setEditable(false);
        setPreferredSize(new Dimension(1000,(int) getPreferredSize().getHeight()));
    }

    /**
     * Verifica se il testo del campo di testo è vuoto.
     * Un campo di testo è vuoto se il testo è uguale all'hint
     * oppure se il testo è un carattere vuoto.
     *
     * @return true se il campo di testo è vuoto, false altrimenti
     */
    public boolean isEmpty(){
        return getText().equals(hint) || getText().equals("");
    }
}
