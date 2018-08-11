package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/6/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "anglesea.db";

    public static final String TABLE_ROOM = "rooms";
    public static final String COLUMN_ROOM_ID = "id";
    public static final String COLUMN_ROOM_NO = "room_no";
    public static final String COLUMN_ROOM_STATUS = "status";

    public static final String TABLE_STAFF = "staffs";
    public static final String COLUMN_STAFF_ID = "id";
    public static final String COLUMN_STAFF_FIRST_NAME = "first_name";
    public static final String COLUMN_STAFF_LAST_NAME = "last_name";
    public static final String COLUMN_STAFF_REGISTRATION_NUMBER = "registration_number";
    public static final String COLUMN_STAFF_TYPE = "type";
    public static final String COLUMN_STAFF_GENDER = "gender";
    public static final String COLUMN_STAFF_DOB = "dob";
    public static final String COLUMN_STAFF_PASSWORD = "password";
    public static final String COLUMN_STAFF_EMAIL = "email";
    public static final String COLUMN_STAFF_MOBILE = "mobile";

    public static final String TABLE_PATIENT = "patients";
    public static final String COLUMN_PID = "id";
    public static final String COLUMN_PATIENT_FIRST_NAME= "first_name";
    public static final String COLUMN_PATIENT_LAST_NAME= "last_name";
    public static final String COLUMN_PATIENT_NHI_NUMBER= "nhi_number";
    public static final String COLUMN_PATIENT_DOB= "dob";
    public static final String COLUMN_PATIENT_GENDER= "gender";
    public static final String COLUMN_PATIENT_WEIGHT = "weight";
    public static final String COLUMN_PATIENT_ADMITTED_ON = "admitted_on";
    public static final String COLUMN_PATIENT_DISCHARGED_ON = "discharged_on";
    public static final String COLUMN_PATIENT_ROOM = "room";
    public static final String COLUMN_PATIENT_STATUS = "status";

    public static final String TABLE_DRUG = "drugs";
    public static final String COLUMN_DRUG_ID = "id";
    public static final String COLUMN_DRUG_NAME= "name";
    public static final String COLUMN_DRUG_CODE= "code";
    public static final String COLUMN_DRUG_MG= "mg";
    public static final String COLUMN_DRUG_ML= "ml";
    public static final String COLUMN_DRUG_TYPE = "type";
    public static final String COLUMN_DRUG_DANGEROUS = "is_dangerous";


    public static final String TABLE_PATIENT_MEDICATION = "patient_medication";
    public static final String COLUMN_MEDICATION_ID = "medication_id";
    public static final String COLUMN_MEDICATION_PATIENT_ID = "patient_id";
    public static final String COLUMN_MEDICATION_NURSE_ID = "nurse_id";
    public static final String COLUMN_MEDICATION_DRUG_ID= "drug_id";
    public static final String COLUMN_MEDICATON_DOSAGE= "dosage";
    public static final String COLUMN_MEDICATION_TYPE = "medication_type";
    public static final String COLUMN_MEDICATION_INTERVAL = "interval";
    public static final String COLUMN_MEDICATION_NO_TIMES = "no_of_times";
    public static final String COLUMN_MEDICATION_TIME = "time";
    public static final String COLUMN_MEDICATION_VERIFIED_BY = "verified_by";



    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT + " ( "
                + COLUMN_PID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PATIENT_FIRST_NAME + " TEXT, "
                + COLUMN_PATIENT_LAST_NAME + " TEXT, "
                + COLUMN_PATIENT_NHI_NUMBER + " TEXT, "
                + COLUMN_PATIENT_DOB + " TEXT, "
                + COLUMN_PATIENT_WEIGHT + " DOUBLE, "
                + COLUMN_PATIENT_GENDER + " TEXT , "
                + COLUMN_PATIENT_ADMITTED_ON + " TEXT, "
                + COLUMN_PATIENT_DISCHARGED_ON + " TEXT, "
                + COLUMN_PATIENT_ROOM + " TEXT, "
                + COLUMN_PATIENT_STATUS + " INTEGER)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + TABLE_ROOM + " ( "
                + COLUMN_ROOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_ROOM_NO + " TEXT , "
                + COLUMN_ROOM_STATUS + " INTEGER)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + TABLE_STAFF + " ( "
                + COLUMN_STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STAFF_FIRST_NAME + " TEXT , "
                + COLUMN_STAFF_LAST_NAME + " TEXT , "
                + COLUMN_STAFF_REGISTRATION_NUMBER + " TEXT , "
                + COLUMN_STAFF_GENDER + " TEXT , "
                + COLUMN_STAFF_DOB + " TEXT , "
                + COLUMN_STAFF_PASSWORD + " TEXT , "
                + COLUMN_STAFF_TYPE + " TEXT , "
                + COLUMN_STAFF_EMAIL + " TEXT , "
                + COLUMN_STAFF_MOBILE + " TEXT)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + TABLE_DRUG + " ( "
                + COLUMN_DRUG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DRUG_NAME + " TEXT , "
                + COLUMN_DRUG_CODE + " TEXT , "
                + COLUMN_DRUG_MG + " TEXT , "
                + COLUMN_DRUG_ML + " TEXT , "
                + COLUMN_DRUG_TYPE + " TEXT , "
                + COLUMN_DRUG_DANGEROUS + " TEXT)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS " + TABLE_PATIENT_MEDICATION + " ( "
                + COLUMN_MEDICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MEDICATION_PATIENT_ID + " TEXT , "
                + COLUMN_MEDICATION_NURSE_ID + " TEXT , "
                + COLUMN_MEDICATION_DRUG_ID + " TEXT , "
                + COLUMN_MEDICATON_DOSAGE + " TEXT , "
                + COLUMN_MEDICATION_TYPE + " TEXT , "
                + COLUMN_MEDICATION_TIME + " TEXT , "
                + COLUMN_MEDICATION_INTERVAL + " TEXT , "
                + COLUMN_MEDICATION_NO_TIMES + " TEXT , "
                + COLUMN_MEDICATION_VERIFIED_BY + " TEXT)";
        db.execSQL(sql);


        //addSampleData();
    }

    private void addSampleData(){
        Staff s = new Staff("Bikal", "Shrestha", "235678", "ADMIN", "M", "1985-05004", "123456", "bikalshresth@gmail.com", "022142664675");
        this.addStaff(s);

        s = new Staff("Ashraf", "ashraf", "123456", "DOCTOR", "M", "1986-02-06", "123456", "ramshresth@gmail.com", "0585956657");
        this.addStaff(s);

        Room r = new Room("Rm1", 1);
        this.addRoom(r);

        r = new Room("Rm2", 1);
        this.addRoom(r);

        r = new Room("D1", 1);
        this.addRoom(r);

        r = new Room("D2", 1);
        this.addRoom(r);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        onCreate(db);
    }

    public boolean addPatient(Patient patient){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENT_FIRST_NAME, patient.get_patient_first_name());
        values.put(COLUMN_PATIENT_LAST_NAME, patient.get_patient_last_name());
        values.put(COLUMN_PATIENT_NHI_NUMBER,patient.get_patient_nhi_number());
        values.put(COLUMN_PATIENT_DOB,patient.get_patient_dob());
        values.put(COLUMN_PATIENT_WEIGHT,patient.get_patient_weight());
        values.put(COLUMN_PATIENT_GENDER,patient.get_patient_gender());
        values.put(COLUMN_PATIENT_ADMITTED_ON, patient.get_patient_admitted_on());
        values.put(COLUMN_PATIENT_DISCHARGED_ON, patient.get_patient_discharged_on());
        values.put(COLUMN_PATIENT_ROOM,patient.get_patient_room());
        values.put(COLUMN_PATIENT_STATUS, patient.get_patient_status());
        long res =  db.insert(TABLE_PATIENT, null, values);
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public int databaseToString(String nhiNumber){
        SQLiteDatabase db = getReadableDatabase();
        String dbString = "";
        Cursor recordSet = db.rawQuery("SELECT * FROM " + MyDBHandler.TABLE_PATIENT + " WHERE " + MyDBHandler.COLUMN_PATIENT_NHI_NUMBER +  "='"+nhiNumber+"'",null);
        int count = recordSet.getCount();
        return count;
    }


    public boolean addRoom(Room room){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ROOM_NO, room.get_room_no());
        values.put(COLUMN_ROOM_STATUS, room.get_status());
        long res =  db.insert(TABLE_ROOM, null, values);
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean addStaff(Staff staff){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STAFF_FIRST_NAME, staff.get_first_name());
        values.put(COLUMN_STAFF_LAST_NAME, staff.get_last_name());
        values.put(COLUMN_STAFF_REGISTRATION_NUMBER, staff.get_registration_number());
        values.put(COLUMN_STAFF_PASSWORD, staff.get_password());
        values.put(COLUMN_STAFF_DOB, staff.get_dob());
        values.put(COLUMN_STAFF_TYPE, staff.get_type());
        values.put(COLUMN_STAFF_GENDER, staff.get_gender());
        values.put(COLUMN_STAFF_EMAIL, staff.get_email());
        values.put(COLUMN_STAFF_MOBILE,staff.get_mobile());
        long res =    db.insert(TABLE_STAFF, null, values);
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public boolean addDrug(Drug drug){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DRUG_NAME, drug.name);
        values.put(COLUMN_DRUG_CODE, drug.code);
        values.put(COLUMN_DRUG_MG, drug.mg);
        values.put(COLUMN_DRUG_ML, drug.ml);
        values.put(COLUMN_DRUG_TYPE, drug.type);
        values.put(COLUMN_DRUG_DANGEROUS, drug.is_danger);
        long res = db.insert(TABLE_DRUG,null,values);
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public boolean addMedication(Integer pid, String drug, String nurse_reg_no, String nurse2_reg_no, String total_medication, String medication_type, String interval, String time){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEDICATION_PATIENT_ID, pid);
        values.put(COLUMN_MEDICATION_DRUG_ID, drug);
        values.put(COLUMN_MEDICATION_NURSE_ID, nurse_reg_no);
        values.put(COLUMN_MEDICATION_VERIFIED_BY, nurse2_reg_no);
        values.put(COLUMN_MEDICATON_DOSAGE, total_medication);
        values.put(COLUMN_MEDICATION_TYPE, medication_type);
        values.put(COLUMN_MEDICATION_INTERVAL, interval);
        values.put(COLUMN_MEDICATION_TIME, time );
        long res = db.insert(TABLE_PATIENT_MEDICATION,null,values);
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // login verification
    public boolean verifyLogin(String username, String password){
        SQLiteDatabase db = getReadableDatabase();
        boolean isValid;
            Cursor cursor = db.query(TABLE_STAFF,
                    new String[]{COLUMN_STAFF_REGISTRATION_NUMBER},
                    COLUMN_STAFF_REGISTRATION_NUMBER + " = ?" + " AND " + COLUMN_STAFF_PASSWORD + "=?" ,
                    new String[]{username, password},
                    null, null, null);
            if (cursor.getCount() > 0) {
                isValid = true;
            } else {
                isValid = false;
            }
        return isValid;
    }

    public List<Drug> getAllDrugs() {
        List<Drug> drugList = new ArrayList<Drug>();
        String selectQuery = "SELECT * FROM " + TABLE_DRUG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Drug drug = new Drug();
                drug.set_id(cursor.getInt(0));
                drug.set_name(cursor.getString(1));
                drug.set_code(cursor.getString(2));
                drug.set_mg(cursor.getString(3));
                drug.set_ml(cursor.getString(4));
                drug.set_type(cursor.getString(5));
                drugList.add(drug);
            } while (cursor.moveToNext());
        }
        return drugList;
    }

    public Cursor getAllDrugCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DRUG;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public List<String> getAllDrug() {
        List<String> drugs = new ArrayList<String>();
        String selectQuery = "SELECT id, name FROM " + TABLE_DRUG ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            drugs.add("Select Drug");
            do {
                drugs.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        return drugs;
    }

    public List<String> getAllDrugByType(String type) {
        List<String> drugs = new ArrayList<String>();
        String selectQuery = "SELECT id, name FROM " + TABLE_DRUG + " WHERE type = '" + type + "'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            drugs.add("Select Drug");
            do {
                drugs.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        return drugs;
    }

    public List<String> getAllRooms() {
        List<String> rooms = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_ROOM + " WHERE status = 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            rooms.add("Select Room");
            do {
              rooms.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        return rooms;
    }

    public Cursor getAllRoomCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ROOM;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public Cursor getAllPatientCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PATIENT;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public List<Patient> patientList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_PATIENT;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_PATIENT + " ORDER BY "+ filter;
        }

        List<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Patient patient;

        if (cursor.moveToFirst()) {
            do {
                patient = new Patient();
                patient.set_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PID)));
                patient.set_patient_first_name(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_FIRST_NAME)));
                patient.set_patient_last_name(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_LAST_NAME)));
                patient.set_patient_dob(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_DOB)));
                patient.set_patient_nhi_number(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_NHI_NUMBER)));
                patient.set_patient_gender(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_GENDER)));
                patient.set_patient_room(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ROOM)));
                patient.set_patient_weight(cursor.getDouble(cursor.getColumnIndex(COLUMN_PATIENT_WEIGHT)));
                //patient.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
                patientList.add(patient);
            } while (cursor.moveToNext());
        }
        return patientList;
    }

    public Cursor getSinglePatientCursor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PATIENT + " WHERE id = " + id ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }


    public List<String> getAllPatient() {
        List<String> patients = new ArrayList<String>();
        String selectQuery = "SELECT id, first_name, last_name FROM " + TABLE_PATIENT ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            patients.add("Select Patient");
            do {
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String name = fname + ' ' + lname;
                patients.add(name);
            }
            while (cursor.moveToNext());
        }
        return patients;
    }

    public List<String> getSinglePatient(Integer id) {
        List<String> patients = new ArrayList<String>();
        String selectQuery = "SELECT id, first_name, last_name FROM " + TABLE_PATIENT + " WHERE id =" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String name = fname + ' ' + lname;
                patients.add(name);
            }
            while (cursor.moveToNext());
        }
        return patients;
    }

    public List<String> getAllNurse() {
        List<String> nurses = new ArrayList<String>();
        String selectQuery = "SELECT id, first_name, last_name FROM " + TABLE_STAFF ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            nurses.add("Select Nurse");
            do {
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String name = fname + ' ' + lname;
                nurses.add(name);
            }
            while (cursor.moveToNext());
        }
        return nurses;
    }

    public List<String> getSingleNurse(String reg_no) {
        List<String> nurses = new ArrayList<String>();
        String selectQuery = "SELECT id, first_name, last_name FROM " + TABLE_STAFF + " WHERE registration_number= '" + reg_no +"'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String name = fname + ' ' + lname;
                nurses.add(name);
            }
            while (cursor.moveToNext());
        }
        return nurses;
    }

    public Patient getSinglePatient(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PATIENT + " WHERE id = " + id ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Patient patient = new Patient();
        if( cursor != null && cursor.moveToFirst() ) {
        /*(null != cursor) {
            cursor.moveToFirst();*/
            patient.set_id(cursor.getInt(cursor.getColumnIndex(COLUMN_PID)));
            patient.set_patient_first_name(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_FIRST_NAME)));
            patient.set_patient_last_name(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_LAST_NAME)));
            patient.set_patient_dob(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_DOB)));
            patient.set_patient_nhi_number(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_NHI_NUMBER)));
            patient.set_patient_gender(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_GENDER)));
            patient.set_patient_room(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ROOM)));
            patient.set_patient_weight(cursor.getDouble(cursor.getColumnIndex(COLUMN_PATIENT_WEIGHT)));
            //patient.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
            cursor.close();
        }
        return patient;
    }

    public Drug getSingleDrug(String dg, String dt){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DRUG + " WHERE name = '" + dg + "' and type='" + dt + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        Drug drug = new Drug();
        if (null != cursor) {
            cursor.moveToFirst();
            drug.set_id(cursor.getInt(cursor.getColumnIndex("id")));
            drug.set_name(cursor.getString(cursor.getColumnIndex("name")));
            drug.set_code(cursor.getString(cursor.getColumnIndex("code")));
            drug.set_mg(cursor.getString(cursor.getColumnIndex("mg")));
            drug.set_ml(cursor.getString(cursor.getColumnIndex("ml")));
            drug.set_is_danger(cursor.getString(cursor.getColumnIndex("is_dangerous")));

        }
        return drug;
    }

    public boolean updatePatient(String first_name, String last_name, String nhi,String dob,Double weight, String room)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("first_name",first_name);
        values.put("last_name",last_name);
        values.put("nhi_number",nhi);
        values.put("dob",dob);
        values.put("weight",weight);
        values.put("room",room);
        long res =  db.update(TABLE_PATIENT, values, "nhi_number = ?",new String[] { nhi });
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public boolean delete(String table, int val){
        SQLiteDatabase db = getWritableDatabase();
        String id = Integer.toString(val);
        String whereClause = "";
        if(table.equals("patient_medication")) {
            whereClause = "id=?";
        }
        else{
             whereClause = "medication_id=?";
        }
        String whereArgs[] = {id + ""};
        long res = db.delete(table, whereClause,  whereArgs );
        if(res == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllStaffCursor()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STAFF;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public List<String> getAllStaffType() {
        List<String> staffs = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_STAFF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            staffs.add("Select Staff");
            do {
                staffs.add(cursor.getString(cursor.getColumnIndex("type")));
            }
            while (cursor.moveToNext());
        }
        return staffs;
    }


    public List<Staff> staffList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_STAFF;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_STAFF + " ORDER BY "+ filter;
        }

        List<Staff> staffList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Staff staff;

        if (cursor.moveToFirst()) {
            do {
                staff = new Staff();
                staff.set_id(cursor.getInt(cursor.getColumnIndex(COLUMN_STAFF_ID)));
                staff.set_first_name(cursor.getString(cursor.getColumnIndex(COLUMN_STAFF_FIRST_NAME)));
                staff.set_last_name(cursor.getString(cursor.getColumnIndex(COLUMN_STAFF_LAST_NAME)));
                staff.set_registration_number(cursor.getString(cursor.getColumnIndex(COLUMN_STAFF_REGISTRATION_NUMBER)));
                staff.set_email(cursor.getString(cursor.getColumnIndex(COLUMN_STAFF_EMAIL)));
                staff.set_mobile(cursor.getString(cursor.getColumnIndex(COLUMN_STAFF_MOBILE)));
                //patient.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        return staffList;
    }

    public Cursor getSingleStaffCursor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STAFF + " WHERE id = " + id ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public Cursor getSingleStaffCursor(String regno){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STAFF + " WHERE registration_number = '" + regno + "'" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public boolean updateStaff(String first_name, String last_name, String registration_number, String type, String email, String mobile)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("first_name",first_name);
        values.put("last_name",last_name);
        values.put("registration_number",registration_number);
        values.put("type",type);
        values.put("email",email);
        values.put("mobile",mobile);
        long res =  db.update(TABLE_STAFF, values, "registration_number = ?",new String[] { registration_number });
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public List<String> getAllDRUGType() {
        List<String> drugs = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_DRUG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            drugs.add("Select Drug");
            do {
                drugs.add(cursor.getString(cursor.getColumnIndex("type")));
            }
            while (cursor.moveToNext());
        }
        return drugs;
    }


    public List<Drug> drugList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_DRUG;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_DRUG + " ORDER BY "+ filter;
        }

        List<Drug> drugList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Drug drug;

        if (cursor.moveToFirst()) {
            do {
                drug = new Drug();
                drug.set_id(cursor.getInt(cursor.getColumnIndex(COLUMN_DRUG_ID)));
                drug.set_name(cursor.getString(cursor.getColumnIndex(COLUMN_DRUG_NAME)));
                drug.set_code(cursor.getString(cursor.getColumnIndex(COLUMN_DRUG_CODE)));
                drug.set_mg(cursor.getString(cursor.getColumnIndex(COLUMN_DRUG_MG)));
                drug.set_ml(cursor.getString(cursor.getColumnIndex(COLUMN_DRUG_ML)));
                drug.set_type(cursor.getString(cursor.getColumnIndex(COLUMN_DRUG_TYPE)));
                //patient.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
                drugList.add(drug);
            } while (cursor.moveToNext());
        }
        return drugList;
    }

    public Cursor getSingleDrugCursor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DRUG + " WHERE id = " + id ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public boolean updateDrug(String id, String name, String code, String mg, String ml, String type, String danger)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("code", code);
        values.put("mg", mg);
        values.put("ml", ml);
        values.put("type",type);
        values.put("is_dangerous",danger);
        long res =  db.update(TABLE_DRUG, values, "id = ?",new String[] { id });
        if (res == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public List<Medication> medicationList(String filter, String order_by) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_PATIENT_MEDICATION;
        }else{
            //filter results by filter option provided
             query = "SELECT  * FROM " + TABLE_PATIENT_MEDICATION + " ORDER BY "+ filter;
             // query = "SELECT p.first_name, p.last_name, p.nhi_number, pm.drug_id, pm.dosage FROM " + TABLE_PATIENT_MEDICATION + " AS pm , " + TABLE_PATIENT + " AS p ";
             // query += " WHERE p.pid = pm.patient_id";
        }

        List<Medication> medicationList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Medication medication;
        if(cursor!= null ) {
            if (cursor.moveToFirst()) {
                do {
                    int pid = cursor.getInt(cursor.getColumnIndex(COLUMN_MEDICATION_ID));
                    Patient p = this.getSinglePatient(pid);
                    String name = p._patient_first_name + " " + p._patient_last_name;
                    medication = new Medication();
                    medication.set_medication_id(cursor.getInt(cursor.getColumnIndex(COLUMN_MEDICATION_ID)));
                    medication.set_patient_id(cursor.getInt(cursor.getColumnIndex(COLUMN_MEDICATION_PATIENT_ID)));
                    medication.set_patient_name(name);
                    medication.set_drug_id(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_DRUG_ID)));
                    medication.set_dosage(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATON_DOSAGE)));
                    medication.set_medication_type(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_TYPE)));
                    medication.set_interval(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_INTERVAL)));
                    medication.set_nurse_id(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_NURSE_ID)));
                    medication.set_verified_by(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_VERIFIED_BY)));
                    medication.set_time(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_TIME)));
                    //patient.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PERSON_IMAGE)));
                    medicationList.add(medication);
                } while (cursor.moveToNext());
            }
        }
        return medicationList;
    }

    public Medication getSingleMedication(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_PATIENT_MEDICATION + " WHERE medication_id = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Medication medication = new Medication();
        if (null != cursor) {
            cursor.moveToFirst();
            medication = new Medication();
            medication.set_medication_id(cursor.getInt(cursor.getColumnIndex(COLUMN_MEDICATION_ID)));
            medication.set_patient_id(cursor.getInt(cursor.getColumnIndex(COLUMN_MEDICATION_PATIENT_ID)));
            medication.set_drug_id(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_DRUG_ID)));
            medication.set_dosage(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATON_DOSAGE)));
            medication.set_medication_type(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_TYPE)));
            medication.set_interval(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_INTERVAL)));
            medication.set_nurse_id(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_NURSE_ID)));
            medication.set_verified_by(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_VERIFIED_BY)));
            medication.set_time(cursor.getString(cursor.getColumnIndex(COLUMN_MEDICATION_TIME)));
        }
        return medication;
    }

    public Drug getSingleDrugByName(String dg){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_DRUG + " WHERE name = '" + dg + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        Drug drug = new Drug();
        if (null != cursor) {
            cursor.moveToFirst();
            drug.set_is_danger(cursor.getString(cursor.getColumnIndex("is_dangerous")));
        }
        return drug;
    }

}

