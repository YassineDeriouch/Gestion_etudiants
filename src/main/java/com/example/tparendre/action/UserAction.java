package com.example.tparendre.action;

import com.example.tparendre.Service.RoleService;
import com.example.tparendre.Service.UserService;
import com.example.tparendre.model.Role;
import com.example.tparendre.model.User;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Namespace("/user") //nom du package
@ResultPath(value = "/WEB-INF/views/") //chemin des pages jsp
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "error") } ) //gestion des erreurs
public class UserAction {
    private int id;
    private String email, password,roleName;
    private List<Role> roles = new ArrayList<Role>();
    private User userRes;
    private Role roleRes;
    private List<User> listUser = new ArrayList<User>();

    //Getters And Setters :

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getRoleName() {return roleName;}
    public void setRoleName(String roleName) {this.roleName = roleName;}
    public List<Role> getRoles() {return roles;}
    public void setRoles(List<Role> roles) {this.roles = roles;}
    public User getUserRes() {return userRes;}
    public void setUserRes(User userRes) {this.userRes = userRes;}
    public Role getRoleRes() {return roleRes;}
    public void setRoleRes(Role roleRes) {this.roleRes = roleRes;}
    public List<User> getListUser() {return listUser;}
    public void setListUser(List<User> listUser) {this.listUser = listUser;}

    @Action(value="formAjouterUserToRole", results = {
            @Result(name = "success", location = "AddUserToRole.jsp"),
            @Result(name = "error", location = "error.jsp")
    })
    public String formAjouterUserToRole() {
        return "success";
    }

    //Add User to Role:
    @Action(value = "AddUserToRole", results = {
            @Result(name = "success", location = "UserAdded.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String AddUserToRole() {
            String email = this.getEmail();
            String password = this.getPassword();
            User user = new User(email, password);
            String roleName = this.getRoleName();
            int idRole = RoleService.getRoleIdByName(roleName);
            this.setUserRes(user);
            boolean res= UserService.addUserToRole(user, idRole);
            if(res) return "success";
            else return "error";
    }

    //Get All Users:

    @Action(value = "GetUsers", results = {
            @Result(name = "success", location = "listUser.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String GetAllUsers() {
        listUser = UserService.getAllUsers();
        this.setListUser(listUser);
        return "success";
    }

    //Del User By Id:

    @Action(value = "supprimerUser", results = {
            @Result(name = "success", location = "listUser.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String supprimerUser() {
        int id = this.getId();
        boolean res = UserService.deleteUser(id);
        System.out.println("res : "+res);
        if(res) return "success";
        else return "error";
    }

    //Action pour redirieger vers la page de modification
    @Action(value = "FormModifierUser", results = {
            @Result(name = "success", location = "ModifierUserForm.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String FormModifierUser() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int id = Integer.parseInt(request.getParameter("id"));
        User u = UserService.getUserByID(id);
        System.out.println("--------------------FormModifierUser---------------------");
        System.out.println("id = " + id);
        System.out.println("User = " + u);
        System.out.println("--------------------FormModifierUser---------------------");
        ActionContext.getContext().getSession().put("idUser", id);
        return "success";
    }

    //Action pour modifier User
    @Action(value = "modifierUser", results = {
            @Result(name = "success", location = "listUser.jsp"),
            @Result(name = "error", location = "error.jsp") })
    public String modifierUser() {
        String resultat=null;
        int id = (int) ActionContext.getContext().getSession().get("idUser");
        //int idEtd = this.getId();
        String email = this.getEmail();
        String password = this.getPassword();
        User user = new User(email, password);
        String roleName = this.getRoleName();
        System.out.println("roleName = " + roleName);
        Role role = new Role(roleName);
        System.out.println("role = " + role);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        System.out.println("roles = " + roles);
        user.setRoles(roles);
        System.out.println("user = " + user);
        System.out.println("--------------------modifierUser---------------------");
        System.out.println("id  = " + id);
        // call service
        User u= UserService.updateUser(user, id);
        System.out.println("User modified "+u);
        // submit result
        this.setUserRes(u);
        System.out.println("--------------------modifierUser---------------------");

        return "success";
    }


    @Action(value = "deleteAllUsers", results = {
            @Result(name = "success", location = "listUser.jsp"),
            @Result(name = "error", location = "erreurRole.jsp") })
    public String deleteAllUsers() {
        String resultat = null;
        //Appel du service pour supprimer le role
        boolean res = UserService.deleteAllUsers();
        System.out.println("res = " + res);
        if (res == true) {
            resultat = "success";
        } else {
            resultat = "error";
        }
        return resultat;
    }


}
