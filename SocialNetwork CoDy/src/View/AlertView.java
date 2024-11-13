package View;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

/**
 * La classe AlertView rappresenta un avviso a schermo.
 * Questa classe crea una finestra che visualizza un messaggio di avviso
 * La finestra si chiude  quando l'utente clicca la X.
 *
 * @author Dylan
 * @version 1.0
 */
public class AlertView {

    /**
     * Crea un avviso a schermo con il contenuto e il padre passati come parametri.
     * La finestra si chiude quando l'utente clicca la X.
     *
     * @param testoAvviso il contenuto del messaggio di avviso
     * @param padre la finestra padre
     */
    public AlertView(String testoAvviso, JFrame padre){
        JFrame frame = new JFrame();
        frame.setSize(430,170);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel= new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        panel.setBackground(ColorGUIView.background);

        JLabel title= new JLabel("Avviso", 22, ColorGUIView.red,Font.BOLD);
        title.setHorizontalAlignment(JLabel.CENTER);
        panel.add(title,BorderLayout.NORTH);

        JLabel msg= new JLabel(testoAvviso, 20, ColorGUIView.white,Font.BOLD);
        msg.setHorizontalAlignment(JLabel.CENTER);
        panel.add(msg,BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(padre);
        frame.setVisible(true);

    }
}
