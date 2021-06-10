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
public class AmbulanceTravel {
    int IdTravel;
    String AmbulanceMatricule;
    String FullNamePatient;
    String AdressPatient;
    Date TravelDate;
    Boolean IsDone;
    int IdPatient;
    
public AmbulanceTravel(int idTravel,String idAmbulance,String FullNamePatient,String adress,Date travelDate,Boolean done)
{
this.IdTravel=idTravel;
this.AmbulanceMatricule=idAmbulance;
this.FullNamePatient=FullNamePatient;
this.AdressPatient=adress;
this.TravelDate=travelDate;
this.IsDone=done;

}

  public AmbulanceTravel(int idTravel,String idAmbulance,int idPatient,String adress,Date travelDate,boolean done){
        this.IdTravel=idTravel;
        this.AmbulanceMatricule=idAmbulance;
        this.IdPatient=idPatient;
        this.AdressPatient=adress;
        this.TravelDate=travelDate;
        this.IsDone=done;

    }
     public AmbulanceTravel(String idAmbulance,int idPatient,String adress,Date travelDate,boolean done){
        this.AmbulanceMatricule=idAmbulance;
        this.IdPatient=idPatient;
        this.AdressPatient=adress;
        this.TravelDate=travelDate;
        this.IsDone=done;
    }
public int  getIdTravel(){
         return this.IdTravel;
}
public void setIdTravel(int IdTravel){
         this.IdTravel=IdTravel;
}
 public int  getIdPatient(){
         return this.IdPatient;
}
public void setIdPatient(int idPatient){
         this.IdPatient=idPatient;
}
public String  getAmbulanceMatricule(){
         return this.AmbulanceMatricule;
}
public void setAmbulanceMatricule(String idAmbulance){
         this.AmbulanceMatricule=idAmbulance;
}
public String  getFullNamePatient(){
         return this.FullNamePatient;
}
public void setFullNamePatient(String FullNamePatient){
         this.FullNamePatient=FullNamePatient;
}
public String  getAdressPatient(){
         return this.AdressPatient;
}
public void setAdressPatient(String AdressPatient){
         this.AdressPatient=AdressPatient;
}
public Date  getTravelDate(){
         return this.TravelDate;
}
public void setTravelDate(Date travelDate){
         this.TravelDate=travelDate;
}

public Boolean  getIsDone(){
         return this.IsDone;
}
public void settravelDate(Boolean done){
         this.IsDone=done;
}

    
    public AmbulanceTravel(){
        
    }
  

     
     
    
    
}
