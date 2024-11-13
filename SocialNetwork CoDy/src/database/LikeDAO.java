package database;

import View.AlertView;
import model.Post;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * La classe LikeDAO rappresenta la classe che gestisce le operazioni di lettura
 * e scrittura dei like nel database.
 *
 * @author CoDy
 * @version 1.0
 */
public class LikeDAO {
    public LikeDAO (){}

    /**
     * Inserisce un like nel database per un utente specifico su un post specifico.
     *
     * @param u l'utente che ha messo like al post
     * @param p il post a cui l'utente ha messo like
     * @return true se l'operazione di inserimento è andata a buon fine, false altrimenti
     */
    public boolean isLiked(User u, Post p){
        boolean liked;
        Connection connection = null;
        PreparedStatement pstmt = null;

        String insert = "INSERT INTO likes (User,Post) VALUES (?,?)";
        try{
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(insert);
            pstmt.setInt(1, u.getID());
            pstmt.setInt(2, p.getID());

            pstmt.executeUpdate();
            liked=true;
        } catch (SQLException e) {
            new AlertView("isliked", null);
            liked=false;
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return liked;
    }

    /**
     * Elimina un like nel database per un utente specifico su un post specifico.
     *
     * @param u l'utente che ha tolto like al post
     * @param p il post a cui l'utente ha tolto like
     * @return true se l'operazione di eliminazione è andata a buon fine, false altrimenti
     */
    public boolean isDisliked(User u, Post p){
        Connection connection = null;
        boolean disliked;
        PreparedStatement pstmt = null;

        String delete = "DELETE FROM  likes WHERE User = ? AND Post = ?;";
        try  {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(delete);
            pstmt.setInt(1, u.getID());
            pstmt.setInt(2, p.getID());

            pstmt.executeUpdate();
            disliked=true;

        } catch (SQLException e) {
            new AlertView("isliked", null);
            disliked=false;
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return disliked;
    }

    /**
     * Restituisce il numero di like presenti nel database per un post specifico
     *
     * @param p il post di cui si vuole sapere il numero di like
     * @return il numero di like presenti nel database per il post specificato
     */
    public int readPostLikes(Post p){
         int likes;
         Connection connection = null;
         PreparedStatement pstmt = null;
         likes =0;
         String select = "SELECT * FROM likes WHERE Post = ?";

        try  {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(select);
            pstmt.setInt(1, p.getID());
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                likes++;
            }

        } catch (SQLException e) {
            new AlertView("ReadPostlikes", null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return likes;
    }

    /**
     * Restituisce la lista dei post che l'utente ha messo like
     *
     * @param u l'utente di cui si vuole sapere i post a cui ha messo like
     * @return la lista dei post a cui l'utente ha messo like
     */
    public ArrayList<Post> ReadUserLikes (User u){
        Connection connection = null;
        PreparedStatement pstmt = null;
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<Integer> postIDs= new ArrayList<>();

        String select = "SELECT * FROM likes WHERE User = ?";

        try {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(select);
            pstmt.setInt(1, u.getID());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                postIDs.add(rs.getInt("Post"));

            }

            for(int i =0;i<postIDs.size();i++){
                posts.add(new PostDAO().readPostByID(postIDs.get(i)));
            }

        } catch (SQLException e) {
            new AlertView("ReadUserLikes", null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return posts;
    }






}
