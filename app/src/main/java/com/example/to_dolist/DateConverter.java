package com.example.to_dolist;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public String getString(Date date){
        return new Gson().toJson(date);
    }    //для записи в базу данных

    @TypeConverter
    public Date getDate(String str){
        return new Gson().fromJson(str,Date.class);
    }    // для получения данных

}