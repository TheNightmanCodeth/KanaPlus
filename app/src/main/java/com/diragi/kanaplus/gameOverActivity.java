package com.diragi.kanaplus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class gameOverActivity extends Activity {
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    int score;
    int high;
    final CharSequence[] gameModePopup = {"Free Play", "Time Trial", "High Score"};
    String mValue;
    AlertDialog modeDialog;
    String highScoreName;

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
        setContentView(R.layout.activity_gameover);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = mPrefs.edit();

        TextView scoreView = (TextView)findViewById(R.id.score);
        TextView highScoreView = (TextView)findViewById(R.id.highscore);

        highScoreName = "highScoreFree";

        String mValue = mPrefs.getString("mode", "free");

        TextView displayMode = (TextView)findViewById(R.id.textView4);



        if (mValue.equals("free")) {

            displayMode.setText("Game Mode: Free Play");

        } else if (mValue.equals("time")) {

            Log.d("mValue  = ", "time");
            highScoreName = "highScoreTime";
            score = mPrefs.getInt("score", 0);
            high = mPrefs.getInt(highScoreName, 0);
            displayMode.setText("Game Mode: Time Trial");

        } else if (mValue.equals("high")) {

            highScoreName = "highScoreHigh";
            score = mPrefs.getInt("highHighScore", 0);
            high = mPrefs.getInt(highScoreName, 0);
            displayMode.setText("Game Mode: High Score");


        }


        scoreView.setText(String.valueOf(score));
        highScoreView.setText(String.valueOf(high));

        if (high >= 0 && high < 50) {

            //Bronze
            highScoreView.setTextColor(Color.parseColor("#FFCD7F32"));

        } else if (high >= 50 && high < 100){

            //Silver
            highScoreView.setTextColor(Color.parseColor("#FFC0C0C0"));

        } else if (high >= 100 && high <150) {

            //Gold
            highScoreView.setTextColor(Color.parseColor("#FFFFD700"));

        } else if (high >= 150) {

            //Platinum
            highScoreView.setTextColor(Color.parseColor("#FFE5E4E2"));

        }

        if (score >= 0 && score < 50) {

            //Bronze
            scoreView.setTextColor(Color.parseColor("#FFCD7F32"));

        } else if (score >= 50 && score < 100){

            //Silver
            scoreView.setTextColor(Color.parseColor("#FFC0C0C0"));

        } else if (score >= 100 && score <150) {

            //Gold
            scoreView.setTextColor(Color.parseColor("#FFFFD700"));

        } else if (score >= 150) {

            //Platinum
            scoreView.setTextColor(Color.parseColor("#FFE5E4E2"));

        }



    }

    public void onReplayClick(View v){



        if (highScoreName.equals("highScoreFree")) {

            Intent freePlay = new Intent(gameOverActivity.this, game_free.class);
            freePlay.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(freePlay);
            finish();
            Log.d("Game mode: ", "Free Play");

        } else if (highScoreName.equals("highScoreTime")) {

            Intent timeTrial = new Intent(gameOverActivity.this, game_timeTrial.class);
            timeTrial.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(timeTrial);
            finish();
            Log.d("Game mode: ", "Time Trial");

        } else if (highScoreName.equals("highScoreHigh")) {

            Intent highScore = new Intent(gameOverActivity.this, game_highScore.class);
            highScore.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(highScore);
            finish();
            Log.d("Game mode: ", "High Score");

        }

    }

    public void onExitClick(View v){

        Intent quitGame = new Intent(gameOverActivity.this, main.class);
        startActivity(quitGame);

    }

    public void onChangeGameModeClick(View v) {

        Intent goToPrefs = new Intent(gameOverActivity.this, Settings.class);
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

                    Intent freePlay = new Intent(gameOverActivity.this, game_free.class);
                    startActivity(freePlay);
                    Log.d("Game mode: ", "Free Play");

                } else if (mValue.equals("time")) {

                    Intent timeTrial = new Intent(gameOverActivity.this, game_timeTrial.class);
                    startActivity(timeTrial);
                    Log.d("Game mode: ", "Time Trial");

                } else if (mValue.equals("high")) {

                    Intent highScore = new Intent(gameOverActivity.this, game_highScore.class);
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
