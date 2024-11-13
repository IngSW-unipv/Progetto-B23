package database;

import View.AlertView;
import model.Comment;
import model.Post;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La classe CommentDAO rappresenta la classe che gestisce le operazioni relative
 * ai commenti sul database.
 *
 * @author CoDy
 * @version 1.0
 */
public class CommentDAO {
    private  Comment c;
    private Post p;
    private User u;
    private Connection connection;
    private int commentsCounter;

    /**
     * Costruttore della classe CommentDAO.
     *
     * Questo costruttore inizializza un'istanza di CommentDAO con un commento, un post e un utente specificati.
     *
     * @param c il commento da gestire nel database
     * @param p il post associato al commento
     * @param u l'utente autore del commento
     */
    public CommentDAO(Comment c, Post p, User u){
        this.c=c;
        this.p=p;
        this.u=u;
    }
    public CommentDAO(){}


    /**
     * Metodo che inserisce nel database un nuovo commento.
     *
     * @return vero se l'operazione è andata a buon fine, falso altrimenti
     */
    public boolean commented() {
        boolean commented;

        String insert = "INSERT INTO comments (Testo,Post,User,Tempo) VALUES (?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            connection=DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(insert);
            pstmt.setString(1, c.getTesto());
            pstmt.setInt(2, p.getID());
            pstmt.setInt(3, u.getID());
            pstmt.setString(4, c.getDateTimeToString());

            pstmt.executeUpdate();
            commented=true;
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
            commented=false;
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return commented;
    }


    /**
     * Metodo che legge dal database il numero di commenti di un post
     *
     * @param p il post di cui si vuole leggere il numero di commenti
     * @return il numero di commenti del post
     */
    public int readPostCommentsCounter(Post p){
        PreparedStatement pstmt = null;
        commentsCounter=0;

        String select = "SELECT * FROM comments WHERE Post = ?";
        try {
            connection = DbConnectionSingleton.getInstance().getConnection();;
            pstmt = connection.prepareStatement(select);
            pstmt.setInt(1, p.getID());
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                commentsCounter++;
            }
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return commentsCounter;
    }

    /**
     * Metodo che legge dal database tutti i commenti di un post
     *
     * @param p il post di cui si vuole leggere i commenti
     * @return una lista di commenti del post
     */
    public ArrayList<Comment> readPostComments(Post p){
        PreparedStatement pstmt = null;

        String select = "SELECT * FROM comments WHERE Post = ?";

        ArrayList<Integer> usersID = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();

        try {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(select);
            pstmt.setInt(1, p.getID());
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                commentsCounter++;
                Comment c = new Comment();
                c.setID(rs.getInt("ID"));
                c.setTesto(rs.getString("Testo"));
                usersID.add(rs.getInt("User"));
                c.setDateTimeFromString(rs.getString("Tempo"));
                comments.add(c);
            }
            UserDAO user=new UserDAO();
            for(int i = 0; i< comments.size(); i++){
                comments.get(i).setAutore(user.readUserByID(usersID.get(i)));
            }

        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return comments;
    }


}