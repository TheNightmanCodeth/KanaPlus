package com.diragi.kanaplus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import de.cketti.library.changelog.ChangeLog;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class Settings extends PreferenceActivity implements Preference.OnPreferenceClickListener{
    String themeName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        final ListPreference themeList = (ListPreference)findPreference("pref_themeColor");
        if (themeList.getValue()==null){
            themeList.setValueIndex(0);
            themeName = "Default";
        }
        if (themeList.getValue().equals("Red theme")){

            themeName = "Red";

        } else if (themeList.getValue().equals("Black theme")){

            themeName = "Black";

        } else if (themeList.getValue().equals("Default theme")){

            themeName = "Default";

        }
        themeList.setSummary("Chose a new theme. The current theme is: " +themeName);
        themeList.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                if (themeList.getValue().equals("Red theme")){

                    themeName = "Red";

                } else if (themeList.getValue().equals("Black theme")){

                    themeName = "Black";

                } else if (themeList.getValue().equals("Default theme")){

                    themeName = "Default";

                }
                themeList.setSummary("Chose a new theme. The current theme is: " +themeName);
                AlertDialog.Builder freedomAlert = new AlertDialog.Builder(Settings.this);
                freedomAlert.setTitle("Restart now?");
                freedomAlert.setMessage("The theme will not change until you restart Pass+. If you click 'Accept' below, Pass+ will restart now, otherwise you will have to restart later.")
                        .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent restart = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName() );
                                restart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(restart);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int I) {
                                dialog.cancel();
                            }

                        });
                AlertDialog alertDialog = freedomAlert.create();

                alertDialog.show();

                return true;
            }
        });

        Preference changelog = (Preference)findPreference("changelog");

        changelog.setOnPreferenceClickListener(this);



    }

    @Override
    public boolean onPreferenceClick(Preference preference){
        ChangeLog cl = new ChangeLog(this);
        cl.getLogDialog().show();
        return true;
    }


}
