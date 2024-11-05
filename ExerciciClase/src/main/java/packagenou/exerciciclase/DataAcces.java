/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package packagenou.exerciciclase;

import Recursos.Exercicis;
import Recursos.Intents;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAcces { 

    private Connection getConnection() {
        Connection connection = null;


        String coString = "jdbc:sqlserver://localhost;database=simulapdb202315101357;encrypt=true;trustServerCertificate=true;" +
                "user=albert;password=12345678;";
        try {
            connection = DriverManager.getConnection(coString);
        } catch (SQLException ex) {
            Logger.getLogger(DataAcces.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    public List<Exercicis> getExercicis() {
        List<Exercicis> exercicisList = new ArrayList<>();
        String sql = "SELECT * FROM Exercicis";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Exercicis exercici = new Exercicis();
                exercici.setId(rs.getInt("Id"));
                exercici.setNomExercici(rs.getString("NomExercici"));
                exercici.setDescripcio(rs.getString("Descripcio"));
                exercicisList.add(exercici);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercicisList;
    }

    public List<Intents> getIntentsByExercici(int exerciciId) {
        List<Intents> intentsList = new ArrayList<>();
        String sql = "SELECT * FROM Intents WHERE IdExercici = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, exerciciId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Intents intent = new Intents();
                intent.setId(rs.getInt("Id"));
                intent.setIdUsuari(rs.getInt("IdUsuari"));
                intent.setIdExercici(rs.getInt("IdExercici"));
                intent.setInici(rs.getTimestamp("Timestamp_Inici"));
                intent.setFi(rs.getTimestamp("Timestamp_Fi"));
                intent.setVideo(rs.getString("Videofile"));
                intentsList.add(intent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return intentsList;
    }
}
