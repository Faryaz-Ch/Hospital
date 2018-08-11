package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

public class Patient {
     int _id;
     String _patient_first_name;
     String _patient_last_name;
     String _patient_nhi_number;
     String _patient_dob;
     double _patient_weight;
     String _patient_gender;
     String _patient_admitted_on;
     String _patient_discharged_on;
     String _patient_room;
     int _patient_status;

    public Patient()
    {

    }

    public Patient(String fname, String lname, String nhino,String dob, double wt, String g, String addDate, String disDate, String room, int status)
    {
        this._patient_first_name = fname;
        this._patient_last_name = lname;
        this._patient_nhi_number = nhino;
        this._patient_dob = dob;
        this._patient_weight = wt;
        this._patient_gender = g;
        this._patient_admitted_on = addDate;
        this._patient_discharged_on = disDate;
        this._patient_room = room;
        this._patient_status = status;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_patient_first_name() {
        return _patient_first_name;
    }

    public void set_patient_first_name(String fn) {
        this._patient_first_name = fn;
    }

    public String get_patient_last_name() {
        return _patient_last_name;
    }

    public void set_patient_last_name(String ln) {
        this._patient_last_name = ln;
    }

    public String get_patient_nhi_number() {
        return _patient_nhi_number;
    }

    public void set_patient_nhi_number(String nhino) {
        this._patient_nhi_number = nhino;
    }

    public String get_patient_gender() {
        return _patient_gender;
    }

    public void set_patient_gender(String g) {
        this._patient_gender = g;
    }

    public double get_patient_weight(){
        return _patient_weight;
    }

    public void set_patient_weight(double wt){
        this._patient_weight = wt;
    }

    public String get_patient_dob() {
        return _patient_dob;
    }

    public void set_patient_dob(String dob) {
        this._patient_dob = dob;
    }

    public String get_patient_admitted_on(){
        return _patient_admitted_on;
    }

    public void set_patient_admitted_on(String disDate){
        this._patient_admitted_on = disDate;
    }

    public String get_patient_discharged_on(){
        return _patient_discharged_on;
    }

    public void set_patient_discharged_on(String disDate){
        this._patient_admitted_on = disDate;
    }


    public String get_patient_room() {
        return _patient_room;
    }

    public void set_patient_room(String roomno) {
        this._patient_room = roomno;
    }

    public int get_patient_status(){
        return this._patient_status;
    }

    public void set_patient_status(int status){
        this._patient_status = status;
    }

}
