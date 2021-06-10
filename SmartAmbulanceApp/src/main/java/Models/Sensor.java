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
public class Sensor {
    public int id;
    public String SensorSerie;
    public Sensor(){
        
    }
     public Sensor(int id , String serie){
         this.id=id;
         this.SensorSerie=serie;
        
    }
      public Sensor( String serie){
         this.SensorSerie=serie;
        
    }
      public int getId(){
          return this.id;
      }
      public void SetId(int id){
          this.id=id;
      }
       public String getSensorSerie(){
          return this.SensorSerie;
      }
      public void SetId(String serie){
          this.SensorSerie=serie;
      }
}
