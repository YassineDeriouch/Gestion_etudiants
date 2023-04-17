package com.example.tparendre.Service;

import com.example.tparendre.model.*;
import com.example.tparendre.model.Role;
import com.example.tparendre.util.HibernateUtil;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

public class RoleService {

/*    public static boolean addRole(Role r){
        boolean res=false;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        try {
            session.save(r);
            res=true;
            t.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return res;
    }*/

    public static boolean addRole(String roleName) {
        boolean exists = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List<Role> roles = session.createQuery("from Role").list();
        for (Role r : roles) {
            if (r.getRoleName().equals(roleName)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            Role newRole = new Role(roleName);
            session.save(newRole);
            t.commit();
        } else {
            System.out.println("Le rôle " + roleName + " existe déjà.");
        }
        return !exists;
    }

    public static Role getRoleByID(int id){
        HttpServletRequest request = ServletActionContext.getRequest();
        id = Integer.parseInt(request.getParameter("id"));
        System.out.println("idServGetID: "+id);
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t=session.beginTransaction();
        Role r=session.get(Role.class,id);
        System.out.println("idServGetID2: "+id);
        System.out.println("Role: "+r);
        t.commit();
        return r;
    }
    public static void getRolesByUser(int id){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        User u=session.get(User.class,id);
        List<Role> roles=u.getRoles();
        for (Role r:roles){
            System.out.println(r);
        }
        t.commit();
    }

    //method getAllRoles
    public static List<Role> getAllRoles(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        List<Role> roles=session.createQuery("from Role").list();
        t.commit();
        System.out.println("Roles: "+roles);
        return roles;
    }

    //method DeleteRole
    public static boolean deleteRole(int id){
        boolean res=false;

        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        HttpServletRequest request = ServletActionContext.getRequest();
        id = Integer.parseInt(request.getParameter("id"));
        System.out.println("idServDEL: "+id);
        try {
            Role r=RoleService.getRoleByID(id);
            System.out.println("Role to delete: "+r);
            session.delete(r);
            t.commit();
            res=true;
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
        finally {
            session.close();
        }
        return res;
    }

    //method updateRole
    public static Role updateRole(Role r, int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Transaction status: " + session.getTransaction().getStatus());
        Transaction t = session.beginTransaction();
        HttpServletRequest request = ServletActionContext.getRequest();
        id = Integer.parseInt(request.getParameter("id"));
        Role r1 = session.get(Role.class,id);
        try {
            r1.setRoleName(r.getRoleName());

            System.out.println("Role updated !"+r1);

            session.update(r1);
            t.commit();
        }
        catch (Exception e2){
            e2.printStackTrace();
            t.rollback();
        }
        finally {
            session.close();
        }
        return r1;
    }
    //deleteAll Roles
    public static boolean deleteAllRoles(){
        boolean res=false;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        List<Role> roles=session.createQuery("from Role").list();
        for (Role r:roles){
            session.delete(r);
            res=true;
        }
        t.commit();
        return res;
    }

    //Get ID of Role
    public static int getRoleIdByName(String roleName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int roleId = -1;

        try {
            Query query = session.createQuery("SELECT r.idRole FROM Role r WHERE r.roleName = :name");
            query.setParameter("name", roleName);
            roleId = (int) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return roleId;
    }
}
