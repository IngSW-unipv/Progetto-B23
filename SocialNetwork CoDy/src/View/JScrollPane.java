package View;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * La classe JScrollPane rappresenta la vista di un pannello che può scorrere.
 * Questa classe è una versione personalizzata della classe JScrollPane di default.
 * La classe fornisce metodi per personalizzare la vista del pannello e del suo scorrimento.
 *
 * @author Dylan
 * @version 1.0
 */
public class JScrollPane extends javax.swing.JScrollPane {

    /**
     * Costruttore che crea un pannello con scorrimento con il componente passato come parametro.
     * @param component il componente che deve essere contenuto nel pannello scorrimento.
     */
    public JScrollPane(JComponent component){
        super(component);
        setBackground(null);
        getViewport().setBackground(null);
        setBorder(null);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setBackground(null);
        scrollBar.setBorder(null);
        scrollBar.setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors(){
                this.thumbColor= ColorGUIView.testoAreaSuggerimento;
            }

            @Override
            protected JButton createDecreaseButton(int orientation){
                return createZeroButton();
            }

            protected JButton createIncreaseButton(int orientation){
                return createZeroButton();
            }

            private JButton createZeroButton(){
                JButton btn= new JButton();
                btn.setPreferredSize(new Dimension(0,0));
                btn.setMaximumSize(new Dimension(0,0));
                btn.setMinimumSize(new Dimension(0,0));
                return btn;
            }
        });

        setVerticalScrollBar(scrollBar);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
        getHorizontalScrollBar().setPreferredSize(new Dimension(0,5));
    }
}
