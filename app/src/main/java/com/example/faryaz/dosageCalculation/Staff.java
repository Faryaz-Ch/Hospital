package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

public class Staff {
    int id;
    String first_name;
    String last_name;
    String registration_number;
    String type;
    String gender;
    String dob;
    String password;
    String email;
    String mobile;

    public Staff(){

    }

    public Staff(String fn, String ln, String rn, String t, String g, String dob, String pwd, String e, String mob){
      this.first_name = fn;
      this.last_name = ln;
      this.registration_number = rn;
      this.type = t;
      this.gender = g;
      this.dob = dob;
      this.password = pwd;
      this.email = e;
      this.mobile = mob;
    }

    public int get_id(){
        return id;
    }

    public void set_id(int i){
        id = i;
    }

    public String get_first_name(){
        return first_name;
    }

    public void set_first_name(String fn){
        first_name = fn;
    }

    public String get_last_name(){
        return last_name;
    }

    public void set_last_name(String ln){
        last_name = ln;
    }

    public String get_registration_number(){
        return registration_number;
    }

    public void set_registration_number(String rn){
        registration_number = rn;
    }

    public String get_type(){
        return type;
    }

    public void get_type( String t){
        type = t;
    }

    public String get_gender(){
        return gender;
    }

    public void set_gender(String g){
        gender = g;
    }

    public String get_dob(){
        return dob;
    }

    public void set_dob(String d){
        dob = d;
    }

    public String get_password(){
        return password;
    }

    public void set_password(String pwd){
        password = pwd;
    }

    public String get_email(){
        return email;
    }

    public void set_email(String e){
        email = e;
    }

    public String get_mobile(){
        return mobile;
    }

    public void set_mobile(String m){
        mobile = m;
    }



}
