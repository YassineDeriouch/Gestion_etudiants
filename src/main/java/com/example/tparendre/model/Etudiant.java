package com.example.tparendre.model;

import javax.persistence.*;
@Entity
@Table(name="tbl_etudiant")
public class Etudiant {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idEtd")
    private int idEtd;
    @Column(name="nomEtd")
    private String nomEtd;
    @Column(name="prenomEtd")
    private String prenomEtd;

    @Column(name="moyenne")
    private double moyenne;

    //Relation ManyToOne with Specialite Entity ( Many Etudiants has One Specialite)
    //Join Column is the foreign key in the table Etudiant
    //idspec is the foreign key in the table Etudiant
    //FetchType.EAGER is used to load the data of the related entity when the data of the main entity is loaded
    //CascadeType.ALL is used to save the data of the related entity when the data of the main entity is saved
    //Fetch Type :
    // lazy loads the data when it is needed and
    // eager loads the data when the data of the main entity is loaded
    @ManyToOne
    @JoinColumn(name="idSpec")
    private Specialite specialite;

    //Getters and Setters
    public int getIdEtd() {
        return idEtd;
    }
    public void setIdEtd(int idEtd) {
        this.idEtd = idEtd;
    }
    public String getNomEtd() {
        return nomEtd;
    }
    public void setNomEtd(String nomEtd) {
        this.nomEtd = nomEtd;
    }
    public String getPrenomEtd() {
        return prenomEtd;
    }
    public void setPrenomEtd(String prenomEtd) {
        this.prenomEtd = prenomEtd;
    }
    public double getMoyenne() {
        return moyenne;
    }
    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    //Constructors
    public Etudiant() {
    }
    public Etudiant(String nomEtd, String prenomEtd, double moyenne, Specialite specialite) {
        this.idEtd = idEtd;
        this.nomEtd = nomEtd;
        this.prenomEtd = prenomEtd;
        this.moyenne = moyenne;
        this.specialite = specialite;
    }
    public Specialite getSpecialite() {
        return specialite;
    }
    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    //toString

    @Override
    public String toString() {
        return "Etudiant{" +
                "idEtd=" + idEtd +
                ", nomEtd='" + nomEtd + '\'' +
                ", prenomEtd='" + prenomEtd + '\'' +
                ", moyenne=" + moyenne +
                ", specialite=" + specialite +
                '}';
    }

}
