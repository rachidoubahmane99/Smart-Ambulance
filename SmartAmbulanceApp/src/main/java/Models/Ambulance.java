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
public class Ambulance {
       
    String matricule;
    String NomChafeur;
    String GpsSensor;
    String Temperateur;
    String HearBeat;
    boolean etat;
    
    public Ambulance()
    {
        
    }
    public Ambulance(String matricule,String NomChafeur, String GpsSensor ,String Temperateur ,String HearBeat ,boolean etat){
        this.matricule=matricule;
        this.NomChafeur=NomChafeur;
        this.GpsSensor=GpsSensor;
        this.Temperateur=Temperateur;
        this.HearBeat=HearBeat;
        this.etat =  etat;
        
    }
    
    public String getMatricule(){
        return this.matricule;
    }
     public void setMatricule(String matricule){
         this.matricule=matricule;
    }
      public String getNomChafeur(){
        return this.NomChafeur;
    }
     public void setNomChafeur(String NomChafeur){
         this.NomChafeur=NomChafeur;
    }
      public String getGpsSensor(){
        return this.GpsSensor;
    }
     public void setGpsSensor(String GpsSensor){
         this.GpsSensor=GpsSensor;
    }
      public String getTemperateur(){
        return this.Temperateur;
    }
     public void setTemperateur(String Temperateur){
         this.Temperateur=Temperateur;
    }
    public String getHearBeat(){
        return this.HearBeat;
    }
     public void setHearBeat(String HearBeat){
         this.HearBeat=HearBeat;
    }
     public boolean getEtat(){
        return this.etat;
    }
     public void setEtat(boolean etat){
         this.etat=etat;
    }
}
