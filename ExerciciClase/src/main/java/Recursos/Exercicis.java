/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Recursos;

/**
 *
 * @author Albert
 */
public class Exercicis {
    private int Id;
    private String NomExercici;
    private String Descripcio;
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNomExercici() {
        return NomExercici;
    }

    public void setNomExercici(String NomExercici) {
        this.NomExercici = NomExercici;
    }
    
    public String getDescripcio() {
    return Descripcio;
}
    public void setDescripcio(String Descripcio) {
    this.Descripcio = Descripcio;
}
    
    @Override
    public String toString() {
        return NomExercici;
    }
}
