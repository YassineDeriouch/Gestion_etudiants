package com.example.tparendre.Service;

import com.example.tparendre.model.*;
import com.example.tparendre.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.LinkedList;

public class EtudiantService {

    //Save Etudiant:
    public static boolean SaveEtudiant(Etudiant e){

        boolean res=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction t = session.beginTransaction();

        try {
            Specialite s = SpecialiteService.GetSpecialiteByDesignation(e.getSpecialite().getDesignation());
            e.setSpecialite(s);
            int SavedIdStd = (int) session.save(e);
            t.commit();
            //save et persist font la meme chose :
            //save:retourne l'id
            //persist:de type void ne retourne rien
            System.out.println("ID of the Student ADDED !"+SavedIdStd);
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

    //Check Student if exists(Checked by name) :if not we create it else we return false
    public static boolean saveStudentcheck(Etudiant e){
        Etudiant ECheck = getEtudiantByName(e.getNomEtd());
        System.out.println("Student Alredy Exists here is the informations about :" +ECheck);
        if(ECheck == null){
            e.setIdEtd(e.getIdEtd());
            e.setNomEtd(e.getNomEtd());
            e.setPrenomEtd(e.getPrenomEtd());
            e.setMoyenne(e.getMoyenne());
            e.setSpecialite(SpecialiteService.GetSpecialiteByDesignation(e.getSpecialite().getDesignation()));
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            session.save(e);
            t.commit();
            return true;
        }
        return false;
    }

    //Get Student By ID:
    public static Etudiant getEtudiantByID(int idEtd){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t=session.beginTransaction();
        Etudiant e=session.get(Etudiant.class,idEtd);
        t.commit();
        return e;
    }

    //Get Student By Name:
    public static Etudiant getEtudiantByName(String name){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();

        Query<Etudiant> query = session.createQuery("from Etudiant e where e.nomEtd=: name", Etudiant.class);
        query.setParameter("name", name);
        Etudiant ERes = query.uniqueResult();
        t.commit();
        return ERes;
    }

    //List All Students :
    public static LinkedList<Etudiant> getAllStudents(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        LinkedList<Etudiant>le=new LinkedList<Etudiant>();
        //Query<Etudiant> query sert a faire des requetes HQL de type select * from Etudiant
        //le résultat sera stocké dans query et on l'affichera avec query.list()
        Query<Etudiant> query = session.createQuery("from Etudiant", Etudiant.class);
        for(Etudiant e:query.list()) {
            le.add(e);
        }
        for(Etudiant e:query.list()) {
            System.out.println(e);
        }
        t.commit();

        //or we can use this to display all students:
        //query.list().forEach(System.out::println) :affiche tous les etudiants
        /*
        query.list().forEach(System.out::println);
         */
        return le;
    }

    //Update Student:
    public static Etudiant updateEtudiant(Etudiant e, int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Transaction status: " + session.getTransaction().getStatus());
        Transaction t = session.beginTransaction();
        Etudiant e1 = session.get(Etudiant.class,id);
        try {
            e1.setNomEtd(e.getNomEtd());
            e1.setPrenomEtd(e.getPrenomEtd());
            e1.setMoyenne(e.getMoyenne());
            Specialite specialte = SpecialiteService.GetSpecialiteByDesignation(e.getSpecialite().getDesignation());
            System.out.println("Specialite"+specialte);

            e1.setSpecialite(specialte);
            System.out.println("Etudiant updated !"+e1);

            session.update(e1);
            t.commit();
        }
        catch (Exception e2){
            e2.printStackTrace();
            t.rollback();
        }
        finally {
            session.close();
        }
        return e1;
    }

    //Delete Student By ID  :
    public static boolean DeleteStudentById(int id){
        boolean res=false;

        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();
        try {
            Etudiant e=EtudiantService.getEtudiantByID(id);
            session.delete(e);
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

    //Delete All Students:
    public static boolean DeleteAllStudents(){
        boolean res=false;

        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction t=session.beginTransaction();

        try {
            Query<Etudiant> query = session.createQuery("from Etudiant", Etudiant.class);
            for(Etudiant e:query.list()) {
                session.delete(e);
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
