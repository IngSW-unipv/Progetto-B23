package View;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;

/**
 * La classe JButton rappresenta un pulsante personalizzato.
 * Questa classe fornisce metodi per gestire la view di un pulsante, come ad esempio il testo e la forma del pulsante.
 *
 * @author Dylan
 * @version 1.1
 */
public class JButton extends JLabel {
    private Shape shape;
    private final int  radius;

    /**
     * Costruisce un nuovo pulsante personalizzato con il testo specificato,
     * il raggio degli angoli arrotondati e la dimensione del testo.
     *
     * @param text     il testo del pulsante
     * @param radius   il raggio degli angoli arrotondati
     * @param textSize la dimensione del testo
     */
    public JButton(String text,int radius,int textSize){
        super(text);
        this.radius=radius;
        setFont(new Font("Segoe UI", Font.BOLD,textSize));
        setOpaque(false);
        setForeground(ColorGUIView.black);
        setHorizontalAlignment(CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Imposta la forma arrotondata del pulsante.
     * <p>
     * @param g il contesto grafico dove disegnare la forma arrotondata
     */
    protected void paintComponent(Graphics g){
        g.setColor(ColorGUIView.pink);
        g.fillRoundRect(0,0,getWidth()-1,getHeight()-1,radius,radius);
        super.paintComponent(g);
    }

    /**
     * Disegna la forma arrotondata del pulsante.
     * <p>
     * @param g il contesto grafico dove disegnare la forma arrotondata
     */
    protected void paintBorder(Graphics g){
        g.setColor(ColorGUIView.black);
    }

    /**
     * Ritorna true se il punto specificato cade all'interno del pulsante, altrimenti false.
     * <p>
     * @param x la coordinata x del punto
     * @param y la coordinata y del punto
     * @return true se il punto cade all'interno del pulsante, altrimenti false
     */
    public boolean contains(int x, int y){
        if(shape == null || !shape.getBounds().equals(getBounds())){
            shape = new RoundRectangle2D.Float(0,0,getWidth()-1,getHeight()-1,45,45);
        }
        return shape.contains(x,y);
    }
}
