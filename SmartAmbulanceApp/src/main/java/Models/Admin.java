/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;



/**
 *
 * @author rachid dev
 */
public class Admin {
    
    int id;
    String nom;
    String prenom;
    String email;
    String phone;
    int active;
    String mdp;
    String ProfileImg;
    public Admin(){
        
    }
    public Admin(int id, String nom, String prenom, String email, String phone, int active) {

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.active = active;

    }
    public Admin(int id, String nom, String prenom, String email, String phone, String img) {

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.ProfileImg=img;

    }
    
    
    //consturctor To use For Update 
    
     public Admin( String nom, String prenom, String email, String phone,String ProfileImg) {

        
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.ProfileImg=ProfileImg;

    }
    
    // End of Update Constructor
     public Admin( String nom, String prenom, String email, String phone, int active ,String mdp,String ProfileImg) {

        
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
        this.active = active;
        this.mdp=mdp;
        this.ProfileImg=ProfileImg;

    }

       public Admin( String nom, String prenom, String email, String phone) {

        
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;
    
    }
     
    public int getId() {
        return this.id;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String Phone) {
        this.phone = Phone;
    }

    public int getActive() {
        return this.active;
    }
     public void setActive(int etat) {
         this.active=etat;
    }
     public String getMdp() {
        return this.mdp;
    }
     public void setMdp(String mdp) {
         this.mdp=mdp;
    }
    public String getProfileImg() {
        return this.ProfileImg;
    }
     public void setProfileImg(String ProfileImg) {
         this.ProfileImg=ProfileImg;
    }
}
