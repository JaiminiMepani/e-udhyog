package com.example.e_udhyog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class Session {
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared pref file name
    private static final String PREF_NAME = "SPF_PREF";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User id
    public static final String KEY_ID = "s_id";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "storename";

    // User detail
    public static final String KEY_PHONE = "phn_num";


    public Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }


    public void createLoginSession(String s_id, String storename, String phn_num){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_ID, s_id);

        // Storing name in pref
        editor.putString(KEY_NAME, storename);

        // Storing work
        editor.putString(KEY_PHONE, phn_num);


        // commit changes
        editor.commit();
    }


    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     *
     * @return boolean
     */
    public boolean checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, AlreadySelling.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
            return false;
        }
        return true;

    }


    /**
     * Get stored session data
     *
     * @return HashMap
     */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_ID, pref.getString(KEY_ID, null));

        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // work
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.remove(KEY_ID);
        editor.remove(KEY_NAME);
        editor.remove(KEY_PHONE);

        editor.remove(IS_LOGIN);

        //editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, AlreadySelling.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
