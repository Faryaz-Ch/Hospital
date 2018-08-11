package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

public class Drug {

    int id;
    String name;
    String code;
    String mg;
    String ml;
    String type;
    String is_danger;

    public Drug(){}

    public Drug(String n, String c, String m, String l, String t, String d){
        name = n;
        code = c;
        mg = m;
        ml = l;
        type = t;
        is_danger = d;
    }

    public void set_id(int i){
        id = i;
    }

    public int get_id(){
        return id;
    }

    public void set_name(String n){
      name = n;
    }

    public String get_name(){
        return name;
    }


    public void set_code(String c){
        code = c;
    }

    public String get_code(){
        return code;
    }


    public void set_mg(String m){
        mg = m;
    }

    public String get_mg(){
        return mg;
    }


    public void set_ml(String l){
        ml = l;
    }

    public String get_ml(){
        return ml;
    }


    public void set_type(String t){
        type = t;
    }

    public String get_type(){
        return type;
    }

    public void set_is_danger(String d){
        is_danger = d;
    }

    public String get_is_danger(){
        return is_danger;
    }



}
