package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

/**
 * La classe JTextField rappresenta un campo di testo personalizzato.
 * Questa classe fornisce metodi per gestire i campi di testo, come ad esempio il testo e il colore del testo.
 * Inoltre, la classe fornisce metodi per gestire gli eventi del mouse, come ad esempio il click e l'hover.
 *
 * @author Dylan
 * @version 1.0
 */
public class JTextField extends javax.swing.JTextField {

    private Shape shape;
    private String hint;

    /**
     * Costruttore per creare un campo di testo con un suggerimento.
     * Imposta il font, la trasparenza, il testo e il colore di primo piano,
     * e aggiunge un listener di focus per gestire il suggerimento.
     *
     * @param hint il testo suggerito da visualizzare quando il campo è vuoto
     */
    public JTextField(String hint){
        super();
        this.hint=hint;
        setFont(new Font("Segoe UI",Font.BOLD,20));
        setOpaque(false);
        setText(hint);
        setForeground(ColorGUIView.side);
        setBorder(BorderFactory.createEmptyBorder(TOP,20,BOTTOM,20));

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(hint)){
                    setText("");
                    setForeground(ColorGUIView.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().equals("")){
                    setText(hint);
                    setForeground(Color.black);
                }

            }
        });

    }

    /**
     * Disegna il campo di testo con un bordo arrotondato
     * @param g il contesto grafico per disegnare il campo di testo
     */
    protected void paintComponent(Graphics g){
        g.setColor(ColorGUIView.white);
        g.fillRoundRect(0,0,getWidth()-1,getHeight()-1,45,45);
        super.paintComponent(g);
    }

    /**
     * Ritorna true se il testo del campo di testo è vuoto.
     * Un campo di testo è vuoto se il testo è uguale all'hint
     * oppure se il testo è un carattere vuoto.
     *
     * @return true se il campo di testo è vuoto, false altrimenti
     */
    public boolean isEmpty(){
        return getText().equals(hint) || getText().equals("");
    }

    /**
     * Disegna il bordo del campo di testo arrotondato.
     * <p>
     * @param g il contesto grafico per disegnare il bordo del campo di testo
     */
    protected void paintBorder(Graphics g){
        g.setColor(ColorGUIView.white);
        g.drawRoundRect(0,0,getWidth()-1,getHeight()-1,45,45);
    }


    /**
     * Ritorna true se il punto specificato cade all'interno del campo di testo, altrimenti false.
     * <p>
     * @param x la coordinata x del punto
     * @param y la coordinata y del punto
     * @return true se il punto cade all'interno del campo di testo, altrimenti false
     */
    public boolean contains(int x, int y){
        if(shape == null || !shape.getBounds().equals(getBounds())){
            shape = new RoundRectangle2D.Float(0,0,getWidth()-1,getHeight()-1,45,45);
        }
        return shape.contains(x,y);
    }

}
