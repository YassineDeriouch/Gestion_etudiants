package com.example.tparendre.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.example.tparendre.Service.EtudiantService;
import com.example.tparendre.Service.SpecialiteService;
import com.example.tparendre.model.Etudiant;
import com.example.tparendre.model.Specialite;
import org.apache.struts2.convention.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
//utilise struts

@Namespace("/user") //nom du package
@ResultPath(value = "/WEB-INF/views/") //chemin des pages jsp
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "error") } ) //gestion des erreurs
public class etudiantAction {

    //Déclaration des variables
    private Etudiant etdRes;
    private List<Etudiant> etdList =new ArrayList<>();
    private int id;
    private String nom;
    private String prenom;
    private double Moyenne;
    private String designation;
    private Specialite specialite;

    //Getters & setters
    public Etudiant getEtdRes() { //getters et setters
        return etdRes;
    }
    public void setEtdRes(Etudiant etdRes) { //getters et setters
        this.etdRes = etdRes;
    }
    public List<Etudiant> getEtdList() {
        return etdList;
    }
    public Specialite getSpecialite() {
        return specialite;
    }
    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    public void setEtdList(List<Etudiant> etdList) {
        this.etdList = etdList;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getMoyenne() {
        return Moyenne;
    }
    public void setMoyenne(double moyenne) {
        Moyenne = moyenne;
    }
    public String getDesignation() {return designation;}
    public void setDesignation(String designation) {this.designation = designation;}

    //Méthodes :

    //Action pour redirieger vers la page d'ajout
    @Action(value = "ajouterEtudiant", results = { //action de redirection vers la page d'ajout
            @Result(name = "success", location = "ajouterEtudiant.jsp") }) //page de redirection
    public String ajouterEtd() { //methode ajouterEtd
        return "success"; //redirection vers la page de succes
    }

    //Action d'ajout d'un etudiant
    @Action(value = "saveETD", results = {
            @Result(name = "success", location = "EtdAdded.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String saveETD() {
        String nomETD = this.getNom();
        String prenomETD = this.getPrenom();
        double moyenneETD = this.getMoyenne();
        String designation=this.getDesignation();
        System.out.println("designation = " + designation);
        Specialite s =SpecialiteService.GetSpecialiteByDesignation(designation);
        if(s==null){
            System.out.println("s = NULL");}

        System.out.println(s);
        System.out.println("nomETD = " + nomETD);
        System.out.println("prenomETD = " + prenomETD);
        System.out.println("moyenneETD = " + moyenneETD);
        System.out.println("designation = " + designation);
        System.out.println("specialite obj= " + s);
        this.setSpecialite(s);
        Etudiant etudiant = new Etudiant(nomETD, prenomETD, moyenneETD, s);
        // call service
        boolean res = EtudiantService.SaveEtudiant(etudiant);
        // submit result
        this.setEtdRes(etudiant);
        if (res == true) {
            System.out.println("res = TRUE");
            return "success";
        } else {
            System.out.println("res = FALSE");
            return "error";
        }
    }

    //Action lister etudiants:
    @Action(value = "ListerEtudiants", results = { //action de listage
            @Result(name = "success", location = "listetds.jsp"), //page de redirection
            @Result(name = "error", location = "ksk") }) //page d'erreur
    public String listerEtds() { //methode listerEtd
        // call service
        etdList = EtudiantService.getAllStudents(); //appel de la methode listerEtudiant
        System.out.println("ETD Leeeeeeeeeeeeeeeeeeeee " + etdList);
        // submit result
        this.setEtdList(etdList); //envoi de la liste
        return "success"; //redirection vers la page de succes
    }

    //Action suppression d'un etudiant
    @Action(value = "supprimerEtudiant", results = {
            @Result(name = "success", location = "listetds.jsp"),
            @Result(name = "error", location = "erreurEtd.jsp") })
    public String supprimerEtudiant() {
        String resultat=null;
    // récupérer l'ID de l'étudiant à supprimer
        int idEtd = this.getId();
    // Appel du service pour supprimer l'étudiant
        boolean res = EtudiantService.DeleteStudentById(idEtd);
        if (res == true) {
            resultat="success";
        } else {
            resultat="error";
        }
        return resultat;
    }

    //Action pour redirieger vers la page de modification
    @Action(value = "FormModifierEtd", results = {
            @Result(name = "success", location = "ModifierEtdForm.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String FormModifierEtudiant() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int idEtd = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = EtudiantService.getEtudiantByID(idEtd);
        System.out.println("--------------------FormModifierEtudiant---------------------");
        System.out.println("idEtd = " + idEtd);
        System.out.println("etudiant = " + etudiant);
        System.out.println("--------------------FormModifierEtudiant---------------------");
        ActionContext.getContext().getSession().put("idEtd", idEtd);
        return "success";
    }

    //Action pour modifier un étudiant
    @Action(value = "modifierEtudiant", results = {
            @Result(name = "success", location = "EtdModified.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String modifierEtudiant() {
        String resultat=null;
        int idEtd = (int) ActionContext.getContext().getSession().get("idEtd");
        //int idEtd = this.getId();
        System.out.println("idEtd = " + idEtd);
        String nomEtd = this.getNom();
        String prenomEtd = this.getPrenom();
        double moyenneEtd = this.getMoyenne();
        String designation=this.getDesignation();
        Specialite s =SpecialiteService.GetSpecialiteByDesignation(designation);
        System.out.println("--------------------modifierEtudiant---------------------");
        Etudiant etudiant = new Etudiant(nomEtd, prenomEtd, moyenneEtd, s);
        // call service
        Etudiant e = EtudiantService.updateEtudiant(etudiant, idEtd);
        System.out.println("Etd modified "+e);
        // submit result
        this.setEtdRes(e);
        System.out.println("--------------------modifierEtudiant---------------------");

        return "success";
    }

    //Delete All Action
    @Action(value = "deleteAllStudents", results = {
            @Result(name = "success", location = "listetds.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String deleteAll() {
        String resultat=null;
        // call service
        boolean res = EtudiantService.DeleteAllStudents();
        System.out.println("res = " + res);
        if (res == true) {
            resultat="success";
        } else {
            resultat="error";
        }
        return resultat;
    }
/*



    @Action(value = "modifierEtudiantform", results = { //action de modification
            @Result(name = "success", location = "editEtd.jsp"), //page de redirection
            @Result(name = "error", location = "erreurEtd.jsp") }) //page d'erreur
    public String modifierEtd() { //methode modifierEtd
        // call service
        Etudiant etd = EtudiantService.getEtudiantByID(this.getId()); //appel de la methode modifierEtudiant
        // submit result
        this.setEtdRes(etd); //envoi de l'objet
        if (etd != null) //si l'objet n'est pas vide
            return "success"; //redirection vers la page de succes
        return "error"; //sinon redirection vers la page d'erreur
    }
  @Action(value = "updateEtudiant", results = { //action de mise a jour
            @Result(name = "success", location = "listetds.jsp"), //page de redirection
            @Result(name = "error", location = "erreurEtd.jsp") }) //page d'erreur
    public String updateEtd() { //methode updateEtd
        // call service
        int idEtd = this.getId(); //recuperation de l'id
        String nomEtd = this.getNom(); //recuperation du nom
        String prenomEtd = this.getPrenom(); //recuperation du prenom
        double Moyenne = this.getMoyenne();
        Specialite s=this.getSpecialite();
        Etudiant etd = new Etudiant(nomEtd,prenomEtd,Moyenne,s); //creation de l'objet
        boolean res = EtudiantService.(etd); //appel de la methode modifierEtudiant
        // submit result
        if (res == true) //si la mise a jour est un succes
            return "success"; //redirection vers la page de succes
        return "error"; //sinon redirection vers la page d'erreur
    }

    @Action(value = "chercherEtudiantParNom", results = { //action de recherche
            @Result(name = "success", location = "listetds.jsp"), //page de redirection
            @Result(name = "error", location = "erreurEtd.jsp") }) //page d'erreur
    public String chercherEtd() { //methode chercherEtd
        // call service
        List<Etudiant> etdList = etudiantService.getEtudiantsByNom(this.getNom()); //appel de la methode chercherEtudiant
        // submit result
        this.setEtdList(etdList); //envoi de la liste
        if (etdList.size() > 0) //si la liste n'est pas vide
            return "success"; //redirection vers la page de succes
        return "error"; //sinon redirection vers la page d'erreur
    }*/
}
