package com.example.tparendre.action;
import com.example.tparendre.Service.SpecialiteService;
import com.example.tparendre.model.Etudiant;
import com.example.tparendre.model.Specialite;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Namespace("/user") //nom du package
@ResultPath(value = "/WEB-INF/views/") //chemin des pages jsp
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "error") } ) //gestion des erreurs
public class specialiteAction {
    private Specialite speRes;
    private int id;
    private String nomSpec;

    private List<Etudiant> listEtd = new ArrayList<>();

    private List<Specialite> listSpe = new ArrayList<>();

    public Specialite getSpeRes() {
        return speRes;
    }

    public void setSpeRes(Specialite speRes) {
        this.speRes = speRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomSpec() {
        return nomSpec;
    }

    public void setNomSpec(String nomSpec) {
        this.nomSpec = nomSpec;
    }

    public List<Etudiant> getListEtd() {
        return listEtd;
    }

    public void setListEtd(List<Etudiant> listEtd) {
        this.listEtd = listEtd;
    }

    public List<Specialite> getListSpe() {
        return listSpe;
    }

    public void setListSpe(List<Specialite> listSpe) {
        this.listSpe = listSpe;
    }

    @Action(value = "formAjouterSpecialite", results = {
            @Result(name = "success", location = "ajtspecialite.jsp"),
            @Result(name = "error", location = "error.jsp")
    })
    public String formAjouterSpecialite() {
        return "success";
    }

    @Action(value = "ajouter", results = {
            @Result(name = "success", location = "SpecialiteAdded.jsp"), //redirection vers la page listespecialite.jsp
            @Result(name = "error", location = "error.jsp")}) //redirection vers la page error.jsp
    public String ajouter() { //methode ajouter
        String res = "success";

        String nomSpec = this.getNomSpec();

        Specialite s = new Specialite(nomSpec);

        this.setSpeRes(s);

        boolean r = SpecialiteService.SaveSpecialite(s);

        if (r == true) {

            res = "success";
        } else {
            res = "error";
        }
        return res;
    }

    @Action(value = "listAllSpecialites", results = { //action de listage
            @Result(name = "success", location = "listSpe.jsp"), //page de redirection
            @Result(name = "error", location = "erreurSpe.jsp") //page d'erreur
    }, interceptorRefs = {@InterceptorRef("defaultStack")}) // apply defaultStack interceptor stack
    public String listerSpecs() {
        // call service
        listSpe = SpecialiteService.getAllSpecialite(); //appel de la methode listerEtudiant
        if (listSpe.size() == 0) {
            System.out.println("nuuuuuuuuuuuuulllllllll");
        } else {
            System.out.println("ListSpe Lssssssss" + listSpe);
        }
        // submit result
        this.setListSpe(listSpe); //envoi de la liste

        return "success"; //redirection vers la page de succes
    }

    //supprimer specialite par id
    @Action(value = "supprimerSpecialite", results = { //action supprimer
            @Result(name = "success", location = "listSpe.jsp"), //redirection vers la page listespecialite.jsp
            @Result(name = "error", location = "error.jsp")})  //action supprimer
    public String supprimerSpecialite() { //methode supprimer
        String resultat = null;
        // récupérer l'ID de l'étudiant à supprimer
        int id = this.getId();
        // Appel du service pour supprimer l'étudiant
        boolean res = SpecialiteService.DeleteSpecialiteByID(id); //appel de la methode supprimerSpecialite
        if (res == true) {
            resultat = "success";
        } else {
            resultat = "error";
        }
        return resultat;
    }

    //modifier specialite par id

    @Action(value = "FormModifierSpec", results = { //action supprimer
            @Result(name = "success", location = "ModifierSpeForm.jsp"), //redirection vers la page listespecialite.jsp
            @Result(name = "error", location = "error.jsp")})  //action supprimer
    public String FormModifierSpec() { //methode supprimer
        HttpServletRequest request = ServletActionContext.getRequest();
        int idSpe = Integer.parseInt(request.getParameter("id"));
        Specialite specialite = SpecialiteService.getSpecialiteByID(idSpe);
        System.out.println("--------------------FormModifierSpec---------------------");
        System.out.println("id = " + idSpe);
        System.out.println("Specialite = " + specialite);
        System.out.println("--------------------FormModifierSpec---------------------");
        ActionContext.getContext().getSession().put("idSpe", idSpe);
        return "success";
    }

        //Modifier specialité
        @Action(value = "modifierSpecialite", results = {
                @Result(name = "success", location = "SpeModified.jsp"),
                @Result(name = "error", location = "error.jsp")})
        public String modifierSpecialite () {
            String resultat = null;
            int idSp = (int) ActionContext.getContext().getSession().get("idSpe");
            System.out.println("idSpe = " + idSp);
            String nomSpec = this.getNomSpec();
            System.out.println("nomSpe = " + nomSpec);
            System.out.println("--------------------modifierEtudiant---------------------");
            Specialite s = new Specialite(nomSpec);
            // call service
            Specialite specialiteUpdated = SpecialiteService.UpdateSpecialite(s, idSp);
            System.out.println("Etd modified " + specialiteUpdated);
            // submit result
            this.setSpeRes(specialiteUpdated);
            System.out.println("--------------------modifierEtudiant---------------------");

            return "success";
        }

        //Delete ALl Specialities
        @Action(value = "deleteAllSpecialities", results = {
                @Result(name = "success", location = "listSpe.jsp"),
                @Result(name = "error", location = "error.jsp")})
        public String deleteAllSpecialities () {
            String resultat = null;
            // call service
            boolean res = SpecialiteService.DeleteAllSpecialite();
            System.out.println("res = " + res);
            // submit result
            if (res == true) {
                resultat = "success";
            } else {
                resultat = "error";
            }
            return resultat;
        }

/*
    @Action(value = "listeretudiant", results = { //action listeretudiant
            @Result(name = "success", location = "listeetudiant.jsp"), //redirection vers la page listeetudiant.jsp
            @Result(name = "error", location = "error.jsp") }) //action listeretudiant
    public String listeretudiant() { //methode listeretudiant
        List<Etudiant> listEtd = specialiteService.getEtudiantsBySpecialite(this.getId()); //recuperation de la liste des etudiants
        this.setListEtd(listEtd); //recuperation de la liste des etudiants
        if (listEtd != null)
            return "success"; //redirection vers la page listeetudiant.jsp
        return "error"; //redirection vers la page error.jsp
    }

    @Action(value = "supprimer", results = { //action supprimer
            @Result(name = "success", location = "listespecialite.jsp"), //redirection vers la page listespecialite.jsp
            @Result(name = "error", location = "error.jsp") })  //action supprimer
    public String supprimer() { //methode supprimer
        boolean res = specialiteService.supprimerSpecialite(this.getId()); //appel de la methode supprimerSpecialite
        if (res == true)
            return "success"; //redirection vers la page listespecialite.jsp
        return "error"; //redirection vers la page error.jsp
    }

    @Action(value = "modifier", results = { //action modifier
            @Result(name = "success", location = "listespecialite.jsp"), //redirection vers la page listespecialite.jsp
            @Result(name = "error", location = "error.jsp") }) //redirection vers la page error.jsp
    public String modifier() { //methode modifier
        Specialite spe = new Specialite(this.getId(),this.getLibelle()); //creation d'un objet specialite
        boolean res = specialiteService.modifierSpecialite(spe); //appel de la methode modifierSpecialite
        if (res == true) //
            return "success"; //redirection vers la page listespecialite.jsp
        return "error"; //redirection vers la page error.jsp
    }

    @Action(value="modifierSpecialiteForm", results = { //action modifierform
            @Result(name = "success", location = "modifier.jsp"), //redirection vers la page modifier.jsp
            @Result(name = "error", location = "error.jsp") }) //redirection vers la page error.jsp
    public String modifierform() { //methode modifierform
        Specialite spe = specialiteService.getSpecialiteById(this.getId()); //recuperation de la specialite
        this.setSpeRes(spe); //recuperation de la specialite
        if (spe != null)
            return "success"; //redirection vers la page modifier.jsp
        return "error"; //redirection vers la page error.jsp
    }

 */

}
