package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

public class Medication {
    int medication_id;
    int patient_id;
    String nurse_id;
    String drug_id;
    String dosage;
    String medication_type;
    String  interval;
    String time;
    String verified_by;
    String patient_name;

    public Medication(){

    };

    public Medication(int pid,String nid, String did, String dos, String mt, String intv, String t, String v ){
        patient_id = pid;
        nurse_id = nid;
        drug_id = did;
        dosage = dos;
        medication_type = mt;
        interval = intv;
        time = t;
        verified_by = v;
        patient_name = did; //added to retrieve patient name only
    }

    public int get_medication_id(){
        return medication_id;
    }
    public void set_medication_id(int mid){
        medication_id = mid;
    }
    public int get_patient_id(){
        return patient_id;
    }
    public void set_patient_id(int pid){
        patient_id = pid;
    }

    public String get_nurse_id(){
        return nurse_id;
    }

    public void set_nurse_id(String nid){
        nurse_id = nid;
    }

    public String get_drug_id(){
        return drug_id;
    }

    public void set_drug_id(String did){
        drug_id = did;
    }

    public String get_dosage(){
        return dosage;
    }

    public void set_dosage(String dos){
        dosage = dos;
    }

    public String get_medication_type(){
        return medication_type;
    }

    public void set_medication_type(String mt){
        medication_type = mt;
    }

    public String get_interval(){
        return interval;
    }

    public void set_interval(String intv){
        interval = intv;
    }

    public String get_time(){
        return time;
    }

    public void set_time(String t){
        time = t;
    }

    public String get_verified_by(){
        return verified_by;
    }
    public void set_verified_by(String v){
        verified_by = v;
    }

    public String get_patient_name(){
        return patient_name;
    }

    public void set_patient_name(String v){
        patient_name = v;
    }





}
