/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author rachid dev
 */
public class Patient {
     int idPatient;
    String nom;
    String prenom;
    String maladie;
    String MedicalNotes;
    String adress;
    String phone;
    Date JoinDate;
    
    
    public Patient(){
        
    }
    public Patient(int id, String nom, String prenom, String maladie, String MedicalNotes, String adress, String phone,Date JoinDate) {

        this.idPatient = id;
        this.nom = nom;
        this.prenom = prenom;
        this.maladie = maladie;
        this.MedicalNotes = MedicalNotes;
        this.adress = adress;
        this.phone = phone;
        this.JoinDate = JoinDate;
      

    }
    public Patient( String nom, String prenom, String maladie, String MedicalNotes, String adress, String phone,Date JoinDate) {

  
        this.nom = nom;
        this.prenom = prenom;
        this.maladie = maladie;
        this.MedicalNotes = MedicalNotes;
        this.adress = adress;
        this.phone = phone;
        this.JoinDate = JoinDate;
      

    }
    
    
    public int getIdPatient() {
        return this.idPatient;
    }
    public void SetIdPatient(int id) {
        this.idPatient = id;
    }

  

    public String getNom() {
        return this.nom;
    }
    
     public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
     public String getMaladie() {
        return this.maladie;
    }

    public void setMaladie(String maladie) {
        this.maladie = maladie;
    }
    
      public String getMedicalNotes() {
        return this.MedicalNotes;
    }

    public void setMedicalNotes(String MedicalNotes) {
        this.MedicalNotes = MedicalNotes;
    }
    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
     public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
     public Date getJoinDate() {
        return this.JoinDate;
    }

    public void setJoinDate(Date JoinDate) {
        this.JoinDate = JoinDate;
    }
    
}
