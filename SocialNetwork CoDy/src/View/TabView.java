package View;

import javax.swing.*;
import java.awt.*;

/**
 * La classe TabView rappresenta la vista della nuova tab quando si preme un pulsante.
 * Questa classe fornisce metodi per gestire la vista della tab.
 *
 * @author Dylan
 * @version 1.0
 */
public class TabView {
    private JFrame frame;
    private javax.swing.JLabel home; //JLabel
    private JPanel panel;
    private JLabel title;



    /**
     * Costruttore della classe TabView
     * Costruisce la vista per la tab con i seguenti componenti:
     *         header:pannello che contiene il pulsante "Home"
     *         panel: pannello che contiene tutti i componenti della tab
     */
    public TabView(){
        frame=new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ColorGUIView.side);

        Dimension dimension = new Dimension(500,50);
        header.setPreferredSize(dimension);
        header.setMaximumSize(dimension);
        header.setMinimumSize(dimension);
        header.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

        JPanel bottom =new JPanel(new BorderLayout());
        bottom.setBackground(null);

        JPanel north = new JPanel(new BorderLayout());
        north.setBackground(null);

        title = new JLabel("placeHolder",20, ColorGUIView.white,Font.BOLD);
        north.add(title,BorderLayout.WEST);

        home = new javax.swing.JLabel(new ImageIcon("immagini/home.png"));
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));

        north.add(home,BorderLayout.EAST);
        header.add(north,BorderLayout.NORTH);

        panel.add(header);

        frame.getContentPane().add(new JScrollPane(panel));
        frame.setVisible(true);
        frame.requestFocus();
    }

    /**
     * Restituisce il frame della tab.
     * @return il frame della tab
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Restituisce il pannello della home.
     * @return il pannello della home
     */
    public javax.swing.JLabel getHome() {
        return home;
    }

    /**
     * Restituisce il pannello della tab.
     * @return il pannello della tab
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Restituisce il titolo della tab.
     * @return il titolo della tab
     */
    public JLabel getTitle() {
        return title;
    }
}
