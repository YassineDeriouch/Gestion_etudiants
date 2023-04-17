package com.example.tparendre.Service;
import com.example.tparendre.model.Role;
import com.example.tparendre.model.User;
import com.example.tparendre.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserService {

    //Create User with Role an existing Role
    public static boolean addUserToRole(User user, int roleId) {
        boolean success = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Role role = session.get(Role.class, roleId);
            if (role == null) {
                throw new IllegalArgumentException("Invalid role ID specified");
            }
            List<User> users = role.getUsers();
            if (users.contains(user)) {
                throw new IllegalArgumentException("User already belongs to the specified role");
            }
            users.add(user);

            role.setUsers(users);
            //save or update the role (if not already present in the DB it will be saved, otherwise updated)
            session.saveOrUpdate(role);
            transaction.commit();

            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return success;
    }

    //get user by id
    public static User getUserByID(int id){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        User u=session.get(User.class,id);
        t.commit();
        return u;
    }

    //njbd users dyal wahed l role
    public static void getUsersByRole(int id){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        Role r=session.get(Role.class,id);
        List<User> users=r.getUsers();
        for (User u : users) {
            u.setRoles(u.getRoles());
            System.out.println(u);
        }
        t.commit();
    }

    //get all users
    public static List<User> getAllUsers(){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        List<User> users=session.createQuery("from User").list();
        for (User u : users) {
            u.setRoles(u.getRoles());
            System.out.println(u);
        }
        t.commit();
        return users;
    }

    //update user with his role
    public static User updateUser(User u,int id){
        boolean res=false;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        User u1=session.get(User.class,id);
        try {
            u1.setEmail(u.getEmail());
            u1.setPassword(u.getPassword());
            u1.setRoles(u.getRoles());
            session.update(u1);
            res=true;
            t.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            t.rollback();
        }
        finally {
            session.close();
        }

        return u1;
    }

    //delete user By id :

    public static boolean deleteUser(int id){
        boolean res=false;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        User u=session.get(User.class,id);
        try {
            session.delete(u);
            res=true;
            t.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            t.rollback();
        }
        finally {
            session.close();
        }

        return res;
    }

    //delete user by role:
public static boolean deleteUsersByRole(int id){
        boolean res=false;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        Role r=session.get(Role.class,id);
        try {
            List<User> users=r.getUsers();
            for (User u:users){
                session.delete(u);
            }
            res=true;
            t.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            t.rollback();
        }
        finally {
            session.close();
        }

        return res;
    }

    //delete All Users:
    public static boolean deleteAllUsers(){
        boolean res=false;
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        try {
            List<User> users=session.createQuery("from User").list();
            for (User u:users){
                session.delete(u);
            }
            res=true;
            t.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            t.rollback();
        }
        finally {
            session.close();
        }

        return res;
    }

}
