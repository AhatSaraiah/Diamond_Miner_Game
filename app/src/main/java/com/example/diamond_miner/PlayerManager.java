package com.example.diamond_miner;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class PlayerManager {

    // private  Context context;
    private Activity activity;
    private ArrayList<player> players;
    private String jsn;
    private SharedPreferences mPrefs;
    private Gson gson;


    public PlayerManager(Activity activity) {
        this.mPrefs = activity.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.activity = activity;
        gson = new Gson();
        loadData();
    }


    public ArrayList getRecords() {
        return players;
    }


    public void addUser(player user) {
        players.add(user);
        Collections.sort(players);
        while (players.size() > Constants.ARRAY_MAX_SIZE) {
            players.remove(players.size() - 1);
        }
        saveData();
    }

    private void saveData() {
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        jsn = new Gson().toJson(players);
        prefsEditor.putString(Constants.USER_DETAILS, jsn);
        prefsEditor.commit();
    }

    private void loadData() {
        jsn = mPrefs.getString(Constants.USER_DETAILS, null);
        Type type = new TypeToken<ArrayList<player>>() {
        }.getType();
        players = gson.fromJson(jsn, type);

        if (players == null) {
            players = new ArrayList<>();
        }
    }

    public int getLastPlace() {
        if (players.size() > 0)
            return players.get(players.size() - 1).getScore();
        else
            return -1;
    }

}
