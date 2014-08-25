package com.diragi.kanaplus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Map;

public class customKatakana extends PreferenceActivity {
    boolean select = false;

    @Override
    public void onCreate(Bundle b) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = mPrefs.getString("pref_themeColor", "0");

        if (theme.equals("Red theme")) {
            Log.d("theme:", "Red");
            setTheme(R.style.AppThemeRed);
            //Toast.makeText(getApplicationContext(), "If the theme is not correct, please reset Pass+", Toast.LENGTH_SHORT).show();
        }

        if (theme.equals("Black theme")) {
            setTheme(R.style.AppThemeBlack);

            //Toast.makeText(getApplicationContext(), "If the theme is not correct, please reset Pass+", Toast.LENGTH_SHORT).show();
        }

        if (theme.equals("Default theme")) {
            setTheme(R.style.AppTheme);

            //Toast.makeText(getApplicationContext(), "If the theme is not correct, please reset Pass+", Toast.LENGTH_SHORT).show();
        }

        if (theme.equals("Material theme")) {
            setTheme(R.style.Material);

            //Toast.makeText(getApplicationContext(), "If the theme is not correct, please reset Pass+", Toast.LENGTH_SHORT).show();
        }

        if (theme.equals("Material Light theme")) {
            setTheme(R.style.MaterialLight);

            //Toast.makeText(getApplicationContext(), "If the theme is not correct, please reset Pass+", Toast.LENGTH_SHORT).show();
        }

        if (theme.equals("Material LD theme")) {
            setTheme(R.style.MaterialLD);

            //Toast.makeText(getApplicationContext(), "If the theme is not correct, please reset Pass+", Toast.LENGTH_SHORT).show();
        }
        super.onCreate(b);
        addPreferencesFromResource(R.xml.customkatakana);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_selectAll:
                //select all here
                if (select == true){
                    deselectAll();
                } else if (select == false){
                    selectAll();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void selectAll() {
        this.setCheckState(true);
        select = true;
    }

    public void deselectAll() {
        this.setCheckState(false);
        select = false;
    }


    private void setCheckState(Boolean state) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        @SuppressWarnings("unchecked")
        Map<String, Boolean> categories = (Map<String, Boolean>) settings.getAll();
        for (String s : categories.keySet()) {
            Preference pref = findPreference(s);
            if (pref instanceof CheckBoxPreference) {
                CheckBoxPreference check = (CheckBoxPreference) pref;
                check.setChecked(state);
            }
        }
    }



}
