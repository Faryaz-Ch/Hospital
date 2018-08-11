package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

public class Room {

    int id;
    String room_no;
    int status;

    public Room(){

    }

    public Room(String rn, int s){
        this.room_no = rn;
        this.status = s;
    }

    public int get_id(){
        return id;
    }

    public void set_id(int id){
        this.id = id;
    }

    public String get_room_no(){
        return room_no;
    }

    public void set_room_no(String rn){
        this.room_no = rn;
    }

    public int get_status(){
        return status;
    }

    public void set_status(int s){
        this.status = s;
    }
}
