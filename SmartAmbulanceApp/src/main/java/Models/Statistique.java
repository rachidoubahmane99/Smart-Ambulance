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
public class Statistique {
    
    int AdmCount;
    int AmbCount;
    int AmbTCount;
    int PatientCount;

    public Statistique(int AdmCount, int AmbCount, int AmbTCount, int PatientCount) {
        this.AdmCount = AdmCount;
        this.AmbCount = AmbCount;
        this.AmbTCount = AmbTCount;
        this.PatientCount = PatientCount;
    }

    public int getAdmCount() {
        return AdmCount;
    }

    public int getAmbCount() {
        return AmbCount;
    }

    public int getAmbTCount() {
        return AmbTCount;
    }

    public int getPatientCount() {
        return PatientCount;
    }

    public void setAdmCount(int AdmCount) {
        this.AdmCount = AdmCount;
    }

    public void setAmbCount(int AmbCount) {
        this.AmbCount = AmbCount;
    }

    public void setAmbTCount(int AmbTCount) {
        this.AmbTCount = AmbTCount;
    }

    public void setPatientCount(int PatientCount) {
        this.PatientCount = PatientCount;
    }
    
    
    
    
}
