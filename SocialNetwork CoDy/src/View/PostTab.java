package View;

import Controller.CommentsTabController;
import database.CommentDAO;
import database.LikeDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * La classe Post rappresenta la vista di un post.
 *
 * Questa classe fornisce metodi per gestire la vista di un post, come ad esempio
 * il testo del post, l'autore del post, il numero di like e la lista dei commenti.
 *
 * @author Dylan
 * @version 1.0
 */
public class PostTab extends JPanel {
    private JLabel likesCounter;
    private model.Post post;

/**
 * Costruttore della classe Post.
 *
 * @param u        l'utente che ha creato il post
 * @param post     il modello di post associato a questa vista
 * @param f        il JFrame associato alla vista del post
 * @param redirect booleano che indica se il post deve gestire un reindirizzamento
 */
    public PostTab(User u, model.Post post, JFrame f, boolean redirect){
        this.post=post;


        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(ColorGUIView.side);
        setBorder(BorderFactory.createEmptyBorder(15,15,15,25));

        JPanel header =new JPanel(new BorderLayout());
        header.setBackground(ColorGUIView.side);

        JPanel aut1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        aut1.setBackground(null);

        javax.swing.JLabel imgAut = new javax.swing.JLabel(new ImageIcon("immagini/autore.png"));
        aut1.add(imgAut);

        JLabel author= new JLabel(post.getAutore().stampaNome(), 20, Color.lightGray,Font.BOLD);
        author.setBackground(null);
        aut1.add(author);

        header.add(aut1,BorderLayout.WEST);

        JLabel date= new JLabel(post.getDateToString(), 15, Color.lightGray,Font.PLAIN);
        header.add(date,BorderLayout.EAST);

        add(header);
        add(Box.createVerticalStrut(7));

        JPanel center =new JPanel(new FlowLayout(FlowLayout.LEADING));
        center.setBackground(ColorGUIView.side);

        String testoPost=post.getTesto();
        StringBuilder sb = new StringBuilder(testoPost);
        int i = 0;
        while ((i = sb.indexOf(" ", i + 40)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        JTextArea content = new JTextArea(sb.toString(),18, ColorGUIView.white,Font.ITALIC);

        center.add(content);
        add(center);
        add(Box.createVerticalStrut(7));

        JPanel bottom =new JPanel(new BorderLayout());
        bottom.setBackground(null);

        JPanel likes = new JPanel(new FlowLayout(FlowLayout.LEFT,13,13));
        likes.setBackground(null);

        javax.swing.JLabel like= new javax.swing.JLabel(new ImageIcon("immagini/like.png"));

        like.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if(u.liked(post)){
            like.setIcon(new ImageIcon("immagini/liked.png"));
        }else{
            like.setIcon(new ImageIcon("immagini/like.png"));
        }

        //
        like.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!u.liked(post)){
                    if(new LikeDAO().isLiked(u,post)){
                        like.setIcon(new ImageIcon("immagini/liked.png"));
                        u.like(post);
                    }
                }else{
                    if(new LikeDAO().isDisliked(u,post)){
                        like.setIcon(new ImageIcon("immagini/like.png"));
                        u.dislike(post);
                    }
                }
                refreshLikesCounter();

            }
        });

        likes.add(like);

        likesCounter = new JLabel("",15, Color.lightGray,Font.BOLD);
        refreshLikesCounter();
        likes.add(likesCounter);
        bottom.add(likes,BorderLayout.WEST);


        int commentsCount = new CommentDAO().readPostCommentsCounter(post);

        if(redirect) {
            JLabel comments = new JLabel("", 15, Color.lightGray, Font.BOLD);
            comments.setCursor(new Cursor(Cursor.HAND_CURSOR));
            // per cambiare il testo quando i commenti diventano più di uno
            if (commentsCount < 2 && commentsCount != 0) {
                comments.setText(commentsCount + " Commento");
            } else {
                comments.setText(commentsCount + " Commenti");
            }

            //listener sul tasto dei commenti per accedere alla sezione commenti

            comments.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new CommentsTabController(new CommentsTabView(u, post), u, post);
                    f.dispose();
                }
            });

            bottom.add(comments, BorderLayout.EAST);
        }
        add(bottom);

        int height= (int) (125+content.getPreferredSize().getHeight());

        Dimension dimension = new Dimension(500,height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
    }

    /**
     * refreshLikesCounter, metodo che aggiorna il numero di likes nella label
     * likesCounter, utilizzato per aggiornare il numero di likes quando si
     * clicca sul bottone del like
     */
    private void refreshLikesCounter(){
        int likesCount = new LikeDAO().readPostLikes(post);
        likesCounter.setText(likesCount+ " Mi Piace");
    }
}
