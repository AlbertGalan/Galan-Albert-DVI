/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package recursos;

import models.Usuari;
import models.Intents;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Exercicis;
import models.Review;

/**
 *
 * @author Alumne
 */
public class DataAcces {
    private Connection getConnection(){
        Connection connection = null;
        
        String coString="jdbc:sqlserver://localhost;database=simulapdb202315101357;encrypt=true;trustServerCertificate=true;"+"user=albert;password=12345678;";
        try {
            connection = DriverManager.getConnection(coString);
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
    
    public ArrayList<Usuari> getUsuaris(){
        ArrayList<Usuari> usuaris = new ArrayList<>();
        
        String sql = "select * from Usuaris";
        
        Connection connection = getConnection();
        try {
            PreparedStatement seleStatement = connection.prepareStatement(sql);
            ResultSet result=seleStatement.executeQuery();
            while (result.next()) {
                Usuari user = new Usuari();
                user.setId(result.getInt("Id"));
                user.setNom(result.getString("Nom"));
                user.setEmail(result.getString("Email"));
                user.setPasswordHash(result.getString("PasswordHash"));
                //user.setFoto(result.getBytes("Foto"));
                user.setInstructor(result.getBoolean("IsInstructor"));
                usuaris.add(user);
            }
            seleStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuaris;
    }
    
    public Review getAttemptReview(int idIntent) {
        Review review = null;
        String sql = "SELECT * FROM Review WHERE IdIntent = ?";
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setInt(1, idIntent);
            ResultSet resultSet = selectStatement.executeQuery();
            review = new Review();
            while (resultSet.next()) {
                review.setId(resultSet.getInt("Id"));
                review.setIdAttempt(resultSet.getInt("IdIntent"));
                review.setIdReviewer(resultSet.getInt("IdReviewer"));
                review.setReview(resultSet.getInt("Valoracio"));
                review.setComent(resultSet.getString("Comentari"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            review = new Review();
            review.setId(0);
        }
        return review;
    }
public int insertReview(Review r) {
        int result = 0;
        String sql = "INSERT INTO dbo.Review (IdIntent, IdReviewer, Valoracio, Comentari)"
                + " VALUES (?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setInt(1, r.getId());
            insertStatement.setInt(2, r.getIdReviewer());
            insertStatement.setInt(3, r.getReview());
            insertStatement.setString(4, r.getComent());

            result = insertStatement.executeUpdate();
            if (result == 0) {
                throw new SQLException("Creating review failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long longResult = generatedKeys.getLong(1);
                    result = longResult.intValue();
                } else {
                    throw new SQLException("Creating review failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int updateReview(Review r) {
        int result = 0;
        String sql = "UPDATE Review SET Valoracio=?, Comentari=? WHERE Id=?";
        try (Connection conn = getConnection(); PreparedStatement updateStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            updateStatement.setInt(3, r.getId());
            updateStatement.setInt(1, r.getReview());
            updateStatement.setString(2, r.getComent());

            result = updateStatement.executeUpdate();
            if (result == 0) {
                throw new SQLException("Updating review failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
public void deleteIntent(int id) {
    String deleteReviewSql = "DELETE FROM Review WHERE IdIntent = ?";
    String deleteIntentSql = "DELETE FROM Intents WHERE Id = ?";

    try (Connection conn = getConnection()) {
        conn.setAutoCommit(false); // Iniciar transacció

        try (PreparedStatement deleteReviewStatement = conn.prepareStatement(deleteReviewSql);
             PreparedStatement deleteIntentStatement = conn.prepareStatement(deleteIntentSql)) {
            
            // Primer, eliminar les ressenyes associades amb l'intent
            deleteReviewStatement.setInt(1, id);
            deleteReviewStatement.executeUpdate();

            // Després, eliminar l'intent
            deleteIntentStatement.setInt(1, id);
            deleteIntentStatement.executeUpdate();

            conn.commit(); // Confirmar la transacció
        } catch (SQLException e) {
            conn.rollback(); // Revertir canvis en cas d'error
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public int getUserId(String email) {
        String sql = "SELECT Id FROM Usuaris WHERE Email = ?";
        int id = 0;
        try (Connection connection = getConnection(); PreparedStatement selectStatement = connection.prepareStatement(sql);) {
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
public Usuari getUsuari(String email) {
        Usuari user = null;
        String sql = "SELECT * FROM Usuaris WHERE Email=?";

        Connection connection = getConnection();
        try {
            PreparedStatement selectStatement = connection.prepareStatement(sql);
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                user = new Usuari();
                user.setId(resultSet.getInt("Id"));
                user.setNom(resultSet.getString("Nom"));
                user.setEmail(resultSet.getString("Email"));
                user.setPasswordHash(resultSet.getString("PasswordHash"));
                //user.setFoto(resultSet.getBytes("Foto"));
                user.setInstructor(resultSet.getBoolean("IsInstructor"));

            }

            selectStatement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
public ArrayList<Intents> getPendingIntentsValoracio() {
    ArrayList<Intents> intentsList = new ArrayList<>();
    String sql = "SELECT i.Id, i.IdUsuari, i.IdExercici, i.Timestamp_Inici, i.Timestamp_Fi, i.Videofile, "
               + "u.Nom AS UsuariNom, e.NomExercici, r.Valoracio "
               + "FROM Intents i "
               + "LEFT JOIN Review r ON i.Id = r.IdIntent "
               + "JOIN Usuaris u ON i.IdUsuari = u.Id "
               + "JOIN Exercicis e ON i.IdExercici = e.Id "
               + "ORDER BY i.Timestamp_Inici ASC";

    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            Intents intent = new Intents();
            intent.setId(resultSet.getInt("Id"));
            intent.setIdUsuari(resultSet.getInt("IdUsuari"));
            intent.setIdExercici(resultSet.getInt("IdExercici"));
            intent.setInici(resultSet.getTimestamp("Timestamp_Inici"));
            intent.setFi(resultSet.getTimestamp("Timestamp_Fi"));
            intent.setVideo(resultSet.getString("Videofile"));

            Integer valoracio = resultSet.getInt("Valoracio");
            if (resultSet.wasNull()) {
                intent.setStatus("Created");
            } else if (valoracio < 3) {
                intent.setStatus("Fail");
            } else {
                intent.setStatus("Pass");
            }

            Usuari usuari = new Usuari();
            usuari.setId(resultSet.getInt("IdUsuari"));
            usuari.setNom(resultSet.getString("UsuariNom"));

            Exercicis exercici = new Exercicis();
            exercici.setId(resultSet.getInt("IdExercici"));
            exercici.setNomExercici(resultSet.getString("NomExercici"));

            intent.setUsuari(usuari);
            intent.setExercici(exercici);

            intentsList.add(intent);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, "Error fetching pending intents", ex);
    }

    return intentsList;
}


    public ArrayList<Intents> getPendingIntents() {
    ArrayList<Intents> intentsList = new ArrayList<>();
    // Modifiquem la consulta per incloure les dades de l'usuari i l'exercici
    String sql = "SELECT i.Id, i.IdUsuari, i.IdExercici, i.Timestamp_Inici, i.Timestamp_Fi, i.Videofile, "
               + "u.Nom AS UsuariNom, e.NomExercici "
               + "FROM Intents i "
               + "JOIN Usuaris u ON i.IdUsuari = u.Id "
               + "JOIN Exercicis e ON i.IdExercici = e.Id "
               + "WHERE i.Id NOT IN (SELECT IdIntent FROM Review) "
               + "ORDER BY i.Timestamp_Inici ASC";

    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            // Creem un objecte Intents
            Intents intent = new Intents();
            intent.setId(resultSet.getInt("Id"));
            intent.setIdUsuari(resultSet.getInt("IdUsuari"));
            intent.setIdExercici(resultSet.getInt("IdExercici"));
            intent.setInici(resultSet.getTimestamp("Timestamp_Inici"));
            intent.setFi(resultSet.getTimestamp("Timestamp_Fi"));
            intent.setVideo(resultSet.getString("Videofile"));

            // Creem l'usuari associat a aquest intent
            Usuari usuari = new Usuari();
            usuari.setId(resultSet.getInt("IdUsuari"));
            usuari.setNom(resultSet.getString("UsuariNom"));

            // Creem l'exercici associat a aquest intent
            Exercicis exercici = new Exercicis();
            exercici.setId(resultSet.getInt("IdExercici"));
            exercici.setNomExercici(resultSet.getString("NomExercici"));

            // Assignem els objectes Usuari i Exercici a l'intent
            intent.setUsuari(usuari);
            intent.setExercici(exercici);

            // Afegim l'intent a la llista
            intentsList.add(intent);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, "Error fetching pending intents", ex);
    }

    return intentsList;
}


    public ArrayList<Intents> getIntentsByUserId(int userId) {
    ArrayList<Intents> intentsList = new ArrayList<>();
    String sql = "SELECT i.Id, i.IdExercici, i.Timestamp_Inici, i.Timestamp_Fi, i.Videofile, e.NomExercici "
               + "FROM Intents i "
               + "JOIN Exercicis e ON i.IdExercici = e.Id "
               + "WHERE i.IdUsuari = ?";

    try (Connection connection = getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Intents intent = new Intents();
            intent.setId(resultSet.getInt("Id"));
            intent.setIdExercici(resultSet.getInt("IdExercici"));
            intent.setInici(resultSet.getTimestamp("Timestamp_Inici"));
            intent.setFi(resultSet.getTimestamp("Timestamp_Fi"));
            intent.setVideo(resultSet.getString("Videofile"));

            Exercicis exercici = new Exercicis();
            exercici.setId(resultSet.getInt("IdExercici"));
            exercici.setNomExercici(resultSet.getString("NomExercici"));

            intent.setExercici(exercici);
            intentsList.add(intent);
        }
    } catch (SQLException ex) {
        Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, "Error fetching intents by user ID", ex);
    }

    return intentsList;
}
}