package com.example.tparendre.action;

import com.example.tparendre.Service.RoleService;
import com.example.tparendre.model.Role;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Namespace("/user") //nom du package
@ResultPath(value = "/WEB-INF/views/") //chemin des pages jsp
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "error") } ) //gestion des erreurs
public class RoleAction {
    private int idRole;
    private String roleName;
    private Role roleres;

    private List<Role> listRole = new ArrayList<>();
    public int getIdRole() {return idRole;}
    public void setIdRole(int idRole) {this.idRole = idRole;}
    public String getRoleName() {return roleName;}
    public void setRoleName(String roleName) {this.roleName = roleName;}
    public Role getRoleres() {return roleres;}
    public void setRoleres(Role roleres) {this.roleres = roleres;}
    public List<Role> getListRole() {return listRole;}
    public void setListRole(List<Role> listRole) {this.listRole = listRole;}

    @Action(value = "formAjouterRole", results = {
            @Result(name = "success", location = "AddRole.jsp"),
            @Result(name = "error", location = "erreurRole.jsp") })
    public String formAjouterRole() {
        System.out.println("rediredirection vers AddRole.jsp");
        return "success";
    }

   //method AddRole
   @Action(value = "AddRole", results = {
           @Result(name = "success", location = "RoleAdded.jsp"),
           @Result(name = "error", location = "erreurRole.jsp") })
    public String AddRole() {
        String resultat = null;
        String roleName=this.getRoleName();
       System.out.println("roleName = " + roleName);
        Role role = new Role(roleName);
        boolean res=RoleService.addRole(roleName);
        if (res==true){
            System.out.println("role ajouté");
            resultat="success";
        }
        else {
            System.out.println("role non ajouté");
            resultat="error";
        }
       System.out.println("res = " + res);
       System.out.println("role = " + role);
        this.setRoleres(role);
        return resultat;
    }

     //method GetAllRoles
     @Action(value = "GetRoles", results = {
             @Result(name = "success", location = "listRole.jsp"),
             @Result(name = "error", location = "erreurRole.jsp") })
    public String GetAllRoles() {

        listRole= RoleService.getAllRoles();

        this.setListRole(listRole);

        return "success";
    }

    //supprimer role par id
    @Action(value = "supprimerRole", results = { //action supprimer
            @Result(name = "success", location = "listRole.jsp"), //redirection vers la page listeRole.jsp
            @Result(name = "error", location = "error.jsp")})  //action supprimer
    public String DelRole() { //methode supprimer
        String resultat = null;
        // récupérer l'ID du Role à supprimer
        HttpServletRequest request = ServletActionContext.getRequest();
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id = " + id);
        // Appel du service pour supprimer l'étudiant
        boolean res = RoleService.deleteRole(id); //appel de la methode supprimerSpecialite
        System.out.println("res = " + res);
        if (res == true) {
            resultat = "success";
        } else {
            resultat = "error";
        }
        return resultat;
    }

    //Action pour rediriger vers la page de modification
    @Action(value = "FormModifierRole", results = {
            @Result(name = "success", location = "ModifierRoleForm.jsp"),
            @Result(name = "error", location = "erreurRole.jsp") })
    public String FormModifierRole() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int id = Integer.parseInt(request.getParameter("id"));
        Role r= RoleService.getRoleByID(id);
        System.out.println("--------------------FormModifierRole---------------------");
        System.out.println("id = " + id);
        System.out.println("Role = " + r);
        System.out.println("--------------------FormModifierRole---------------------");
        ActionContext.getContext().getSession().put("idRole", id);
        return "success";
    }

    //Action pour modifier un étudiant
        @Action(value = "modifierRole", results = {
            @Result(name = "success", location = "RoleModified.jsp"),
            @Result(name = "error", location = "erreurRole.jsp") })
        public String modifierRole() {
        String resultat=null;
        int id = (int) ActionContext.getContext().getSession().get("idRole");
        //int idEtd = this.getId();
        String RoleName = this.getRoleName();
        System.out.println("--------------------modifierRole---------------------");
        System.out.println("id  = " + id);
        Role role= new Role(RoleName);
            System.out.println("role is : ================>>>>>>> "+role);
        // call service
        Role r= RoleService.updateRole(role, id);
        System.out.println("Role modified "+r);
        // submit result
        this.setRoleres(r);
        System.out.println("--------------------modifierRole---------------------");

        return "success";
    }

    //delete all roles
    @Action(value = "deleteAllRoles", results = {
            @Result(name = "success", location = "listRole.jsp"),
            @Result(name = "error", location = "erreurRole.jsp") })
    public String deleteAllRoles() {
        String resultat = null;
        //Appel du service pour supprimer le role
        boolean res = RoleService.deleteAllRoles();
        System.out.println("res = " + res);
        if (res == true) {
            resultat = "success";
        } else {
            resultat = "error";
        }
        return resultat;
    }


}
