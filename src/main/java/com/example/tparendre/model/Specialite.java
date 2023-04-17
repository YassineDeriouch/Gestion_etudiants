package com.example.tparendre.model;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
@Table(name="tbl_specialite")
public class Specialite {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idSpec")
    private int idSpec;

    @Column(name="nomSpec")
    private String designation;

    //Getters and Setters
    public int getIdSpec() {
        return idSpec;
    }
    public void setIdSpec(int idSpec) {
        this.idSpec = idSpec;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    //Constructors
    public Specialite() {
    }

    public Specialite(String designation) {
        this.designation = designation;
    }

   //toString
    @Override
    public String toString() {
        return "Specialite{" +
                "idSpec=" + idSpec +
                ", designation='" + designation + '\'' +
                '}';
    }
}
