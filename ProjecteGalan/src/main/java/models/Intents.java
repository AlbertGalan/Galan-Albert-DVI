/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author Albert
 */
public class Intents {
    private int Id;
    private int IdUsuari;
    private int IdExercici;
    private Timestamp Inici;
    private Timestamp Fi;
    private String Video;
    
    private Usuari usuari;
    private Exercicis exercici;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdUsuari() {
        return IdUsuari;
    }

    public void setIdUsuari(int IdUsuari) {
        this.IdUsuari = IdUsuari;
    }

    public int getIdExercici() {
        return IdExercici;
    }

    public void setIdExercici(int IdExercici) {
        this.IdExercici = IdExercici;
    }

    public Timestamp getInici() {
        return Inici;
    }

    public void setInici(Timestamp Inici) {
        this.Inici = Inici;
    }

    public Timestamp getFi() {
        return Fi;
    }

    public void setFi(Timestamp Fi) {
        this.Fi = Fi;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String Video) {
        this.Video = Video;
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public Exercicis getExercici() {
        return exercici;
    }

    public void setExercici(Exercicis exercici) {
        this.exercici = exercici;
    }
    
    public String getExerciciNom() {
        return exercici != null ? exercici.getNomExercici() : "Desconegut";
    }
    
    public String getUsuariNom() {
        return usuari != null ? usuari.getNom() : "Desconegut";
    }
    
}
