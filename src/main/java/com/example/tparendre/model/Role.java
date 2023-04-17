package com.example.tparendre.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="T_Roles")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idRole;
    private String roleName;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable( name = "T_Users_Roles_Associations",
            joinColumns = @JoinColumn( name = "idRole" ),
            inverseJoinColumns = @JoinColumn( name = "idUser" ))
    private List<User> users = new ArrayList<User>();

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }
    public int getIdRole() {
        return idRole;
    }
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Role [idRole=").append(idRole).append(", roleName=").append(roleName).append(", users=[");
        for (User user : users) {
            sb.append(user.getEmail()).append(", ");
        }
        sb.append("]]");
        return sb.toString();
    }



}
