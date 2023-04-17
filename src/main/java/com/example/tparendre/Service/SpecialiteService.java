package com.example.tparendre.Service;

import com.example.tparendre.model.Specialite;
import com.example.tparendre.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

public class SpecialiteService {

    //Save Specialite:
    public static boolean SaveSpecialite(Specialite spec){

        boolean res=false;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction t = session.beginTransaction();

        try {
            int SavedIdSpec = (int) session.save(spec);
            t.commit();
            //save et persist font la meme chose :
            //save:retourne l'id
            //persist:de type void ne retourne rien
            System.out.println("ID of the Specialité ADDED !"+SavedIdSpec);
            res=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    //Get specialite by ID:
    public static Specialite getSpecialiteByID(int idSpec){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t=session.beginTransaction();
        Specialite spec=session.get(Specialite.class,idSpec);
        t.commit();
        return spec;
    }

    //Get specialite by Designation:
    public static Specialite GetSpecialiteByDesignation(String designation){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t=session.beginTransaction();
        //Query<Specialite> sert a faire des requetes HQL de type Specialite (class)
        Query<Specialite> query=session.createQuery("from Specialite s where s.designation=:designation",Specialite.class);
        //la requete HQL sert a recuperer un objet de type Specialite
        //setParameter:remplace le parametre de la requete HQL par la valeur de designation
        query.setParameter("designation",designation);
        //uniqueResult:retourne un seul resultat
        Specialite SRes=query.uniqueResult();
        t.commit();
        return SRes;
    }

    //Get All Specialite:
    public static List<Specialite> getAllSpecialite() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Query<Specialite> query = session.createQuery("from Specialite", Specialite.class);
        List<Specialite> l = query.list();
        System.out.println("List of Specialite: " + l);
        t.commit();
        session.close();
        return l;
    }
    //Update Specialite:
    public static Specialite UpdateSpecialite(Specialite s, int id){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t=session.beginTransaction();
        Specialite Oldspecialite=session.get(Specialite.class,id);
        Oldspecialite.setDesignation(s.getDesignation());
        session.update(Oldspecialite);
        t.commit();
        return Oldspecialite;
    }

    //Delete Specialite by ID:
    public static boolean DeleteSpecialiteByID(int id){
        boolean res=false;
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t=session.beginTransaction();
        try {
            Specialite s = session.get(Specialite.class, id);

            session.delete(s);
            res=true;
            t.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    //Delete AllSpecialite
    public static boolean DeleteAllSpecialite(){
        boolean res=false;

        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();

        try {
            Query<Specialite> query = session.createQuery("from Specialite", Specialite.class);
            for(Specialite s:query.list()) {
                session.delete(s);
            }
            res=true;
            t.commit();
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
        finally {
            session.close();
        }
        return res;
    }
}
