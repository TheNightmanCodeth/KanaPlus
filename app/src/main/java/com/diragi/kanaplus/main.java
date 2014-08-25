package com.diragi.kanaplus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import de.cketti.library.changelog.ChangeLog;


public class main extends Activity {
    Boolean all;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    final CharSequence[] gameModePopup = {"Free Play", "Time Trial", "High Score"};
    String mValue;
    AlertDialog modeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_main);

        ChangeLog cl = new ChangeLog(this);
        if (cl.isFirstRun()){
            cl.getLogDialog().show();
        }

        ImageView startLogo = (ImageView)findViewById(R.id.imageView);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = mPrefs.edit();

        all = mPrefs.getBoolean("allCheckBox", false);


            startLogo.setBackgroundResource(R.drawable.kanamain);

        Button startGame = (Button)findViewById(R.id.button);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseModePopup(view);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_resources) {
            Intent gotoResources = new Intent(main.this, Resources.class);
            startActivity(gotoResources);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickChangeToPrefs(View v) {
        Intent goToPrefs = new Intent(main.this, Settings.class);
        startActivity(goToPrefs);
    }



    public void chooseModePopup(View v){

        Log.d("Yes", "Correct method woop");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose game mode");
        builder.setNeutralButton("Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (mValue == null) {

                    Toast.makeText(getApplicationContext(), "You need to pick a game mode!", Toast.LENGTH_SHORT).show();

                } else

                if (mValue.equals("free")) {

                    Intent freePlay = new Intent(main.this, game_free.class);
                    startActivity(freePlay);
                    Log.d("Game mode: ", "Free Play");

                } else if (mValue.equals("time")) {

                    Intent timeTrial = new Intent(main.this, game_timeTrial.class);
                    startActivity(timeTrial);
                    Log.d("Game mode: ", "Time Trial");

                } else if (mValue.equals("high")) {

                    Intent highScore = new Intent(main.this, game_highScore.class);
                    startActivity(highScore);
                    Log.d("Game mode: ", "High Score");

                }

            }
        });
        builder.setSingleChoiceItems(gameModePopup, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                switch (i) {
                    case 0:
                        Log.d("Selected:", "Free");
                        mValue = "free";
                        break;

                    case 1:
                        Log.d("Selected:", "Time");
                        mValue = "time";
                        break;

                    case 2:
                        Log.d("Selected:", "High");
                        mValue = "high";
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "You need to pick a game mode!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        builder.show();

    }

}
