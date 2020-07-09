package br.com.fullface.ffdroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Wender on 05/08/2016.
 * Classe feita para salvar as preferencias necessarias
 * que serão usadas ao decorrer do uso do app
 * infos que deverão ser mantidas mesmo após o app ser fechado.
 */
public class Prefs {
    private static final String PREF_ID = "FULLFACE";

    public static void setBoolean(Context context, String flag, boolean on) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(flag, on);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        boolean b = pref.getBoolean(flag, true);
        return b;
    }

    public static boolean isCheck(Context context, String flag) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(flag, false);
    }

    public static void setInteger(Context context, String flag, int valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(flag, valor);
        editor.commit();
    }

    public static int getInteger(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        int i = pref.getInt(flag, 0);
        return i;
    }

    public static void setString(Context context, String flag, String valor) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(flag, valor);
        editor.commit();
    }

    public static String getString(Context context, String flag) {
        SharedPreferences pref = context.getSharedPreferences(PREF_ID, 0);
        String s = pref.getString(flag, "");
        return s;
    }

    public static void clearAllPrefs(Context context, String string){
        SharedPreferences pref = context.getSharedPreferences(string, Context.MODE_PRIVATE);
        pref.edit().clear().commit();
    }

    public static void simpleClearPrefs(Context context){
        SharedPreferences prefs = null; // here you get your prefrences by either of two methods
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
