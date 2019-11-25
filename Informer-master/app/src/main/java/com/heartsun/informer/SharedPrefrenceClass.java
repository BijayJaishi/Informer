package com.heartsun.informer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bijay on 11/21/2019.
 */

public class SharedPrefrenceClass {

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public SharedPrefrenceClass(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("UserData",Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void closeApp(int close){
        editor.putInt("close",close);
        editor.apply();
    }

    public void saveMobile(String mobile){
        editor.putString("mobile",mobile);
        editor.apply();
    }

    public void saveToken(String token){
        editor.putString("token",token);
        editor.apply();
    }

    public String getMobile(){
        return sp.getString("mobile","");
    }

    public int getCloseApp(){
        return sp.getInt("close",0);
    }

    public String getToken(){
        return sp.getString("token","");
    }

}