package edu.temple.webapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PrefConfig {

    private static final String URL_KEY = "list_key100";
    private static final String TITLE_KEY = "list_key200";


    public static void writeListInPref(Context context, ArrayList<String> urls, ArrayList<String> titles) {
        Gson gson = new Gson();
        String titlesString = gson.toJson(titles);
        String urlString = gson.toJson(urls);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TITLE_KEY, titlesString);
        editor.putString(URL_KEY, urlString);
        editor.apply();
    }

    public static ArrayList<String> readUrlFromPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String urlsString = pref.getString(URL_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = gson.fromJson(urlsString, type);
        return list;
    }

    public static ArrayList<String> readTitleFromPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String titlesString = pref.getString(TITLE_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = gson.fromJson(titlesString, type);
        return list;
    }

}