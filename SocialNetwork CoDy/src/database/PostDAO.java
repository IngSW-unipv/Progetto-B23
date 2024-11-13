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
 * La classe PostDAO rappresenta la classe che gestisce le operazioni di database
 * relative ai post.
 *
 * @author CoDy
 * @version 1.0
 */
public class PostDAO {

    private Post post;
    private Connection connection;

    private ArrayList<Post> posts;

    /**
     * Costruttore della classe PostDAO
     *
     * @param post il post da inserire nel db
     */
    public PostDAO(Post post){
        this.post=post;
    }
    public PostDAO(){}

    /**
     * Inserisce un nuovo post nel database.
     *
     * Questo metodo utilizza il post fornito per creare una nuova entry nel database.
     * Se l'inserimento ha successo, ritorna true, altrimenti false.
     *
     * @return true se il post è stato inserito correttamente, false in caso di errore
     */
    public boolean posted(){
        boolean posted;
        PreparedStatement pstmt = null;

        String insert = "INSERT INTO posts (Testo,User,Tempo) VALUES (?,?,?)";


        try {
            connection=DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(insert);
            pstmt.setString(1, post.getTesto());
            pstmt.setInt(2, post.getAutore().getID());
            pstmt.setString(3, post.getDateTimeToString());

            pstmt.executeUpdate();
            posted=true;
        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
            posted=false;
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return posted;
    }

    /**
     * Legge i post scritti da un determinato utente dal database.
     *
     * Questo metodo esegue una query al database per ottenere tutti i post associati all'utente
     * specificato. I post ottenuti vengono aggiunti a una lista di oggetti Post.
     *
     * @param u l'utente di cui leggere i post
     * @return un'ArrayList contenente i post dell'utente specificato
     */
    public ArrayList<Post> readUserPosts(User u) {
        PreparedStatement pstmt = null;
        posts= new ArrayList<>();

        String select = "SELECT * FROM posts WHERE User = ?";

        try  {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(select);
            pstmt.setInt(1, u.getID());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setID(rs.getInt("ID"));
                p.setTesto(rs.getString("Testo"));
                p.setDateTimeFromString(rs.getString("Tempo"));
                p.setAutore(u);
                posts.add(p);
            }

        } catch (SQLException e) {
            new AlertView("ReadUserPosts", null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return posts;
    }

    /**
     * Genera la timeline dell'utente corrente.
     *
     * Questo metodo costruisce la timeline dell'utente corrente prelevando i post
     * scritti dall'utente e dai suoi amici. La timeline viene generata eseguendo
     * una query al database che preleva i post scritti dall'utente e dai suoi amici
     * e li aggiunge a una lista di oggetti Post.
     *
     * @param user l'utente di cui generare la timeline
     * @return un'ArrayList contenente i post dell'utente e dei suoi amici
     */
    public ArrayList<Post> generateTimeline(User user) {
        posts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        PreparedStatement pstmt = null;
        if (!user.getAmiciID().isEmpty()) {
            for (int i = 0; i < user.getAmiciID().size(); i++) {
                sb.append("User =" + user.getAmiciID().get(i));
                if (i != user.getAmiciID().size() - 1) {
                    sb.append(" OR ");
                }else {
                    sb.append(";");
                }
            }
            String select = "SELECT * FROM posts WHERE " + sb.toString();
            try{
                connection = DbConnectionSingleton.getInstance().getConnection();
                pstmt = connection.prepareStatement(select);
                ResultSet rs= pstmt.executeQuery(select);
                ArrayList<Integer> usersID= new ArrayList<>();
                while(rs.next()){
                    Post p = new Post();
                    p.setID(rs.getInt("ID"));
                    p.setTesto(rs.getString("Testo"));
                    usersID.add(rs.getInt("User"));
                    p.setDateTimeFromString(rs.getString("Tempo"));
                    posts.add(p);
                }

                for(int i=0;i<usersID.size();i++){
                    posts.get(i).setAutore(new UserDAO().readUserByID(usersID.get(i)));
                }
            } catch (SQLException e){
                new AlertView(e.getMessage(),null);
            }
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return posts;
    }

    /**
     * Legge un post dal database dato l'ID.
     *
     * Questo metodo esegue una query al database per ottenere il post
     * specificato dall'ID del post. Il post ottenuto viene restituito come oggetto Post.
     *
     * @param ID l'ID del post da leggere
     * @return il post ottenuto dal database
     */
    public Post readPostByID(int ID){

        PreparedStatement pstmt = null;
        String select = "SELECT * FROM posts WHERE ID = ?";

        try {
            connection = DbConnectionSingleton.getInstance().getConnection();
            pstmt = connection.prepareStatement(select);
            pstmt.setInt(1, ID);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                post = new Post();
                post.setID(ID);
                post.setTesto(rs.getString("Testo"));
                post.setDateTimeFromString(rs.getString("Tempo"));
                post.setAutore(new UserDAO().readUserByID(rs.getInt("User")));
            }else{
                new AlertView("post", null);
            }

        } catch (SQLException e) {
            new AlertView(e.getMessage(), null);
        }
        DbConnectionSingleton.getInstance().closeConnection();
        return post;
    }

}