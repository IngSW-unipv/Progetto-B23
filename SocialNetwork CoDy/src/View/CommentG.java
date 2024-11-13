package View;

import javax.swing.*;
import java.awt.*;

/**
 * La classe Comment rappresenta un commento in un post.
 * Questa classe fornisce metodi per gestire la view di un commento
 *
 * @author Dylan
 * @version 1.0
 */
public class CommentG extends JPanel {

    /**
     * Costruttore della classe Comment.
     * Questo costruttore inizializza un nuovo commento con i dettagli forniti.
     *
     * @param c il commento del model che contiene le informazioni da visualizzare
     */
    public CommentG(model.Comment c){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(ColorGUIView.side);
        setBorder(BorderFactory.createEmptyBorder(15,15,15,25));

        JPanel header =new JPanel(new BorderLayout());
        header.setBackground(null);

        JPanel aut1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        aut1.setBackground(null);

        javax.swing.JLabel imgAut = new javax.swing.JLabel(new ImageIcon("immagini/autore.png"));
        aut1.add(imgAut);

        JLabel author= new JLabel(c.getAutore().stampaNome(), 20, Color.lightGray,Font.BOLD);
        author.setBackground(null);
        aut1.add(author);

        header.add(aut1,BorderLayout.WEST);

        add(header);

        JPanel center =new JPanel(new FlowLayout(FlowLayout.LEADING));
        center.setBackground(ColorGUIView.side);
        String testoCommento=c.getTesto();
        StringBuilder sb = new StringBuilder(testoCommento);
        int i = 0;
        while ((i = sb.indexOf(" ", i + 40)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        JTextArea content = new JTextArea(sb.toString(),18, ColorGUIView.white,Font.ITALIC);
        center.add(content);

        add(center);

        JPanel bottom =new JPanel(new BorderLayout());
        bottom.setBackground(ColorGUIView.side);
        bottom.add(new JLabel(c.getDateToString(), 15, Color.lightGray,Font.PLAIN), BorderLayout.EAST);
        add(bottom);

        int height= (int) (90+content.getPreferredSize().getHeight());

        Dimension dimension = new Dimension(500,height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }
}
