package com.diragi.kanaplus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diragi.kanaplus.randomOrg.org.random.RandomOrgClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class game_timeTrial extends Activity {
    int[] hiragana;
    ImageView kanaView;
    int currentKana;
    Boolean all = false;
    int score = 0;
    TextView scoreTextView;
    int[] custom = new int[93];
    int nextIndex = 0;
    int highScore = 0;
    EditText answerBox;
    TextView highScoreBox;
    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    Boolean gameOver = false;
    int time = 21;
    CountDownTimer timerData;
    int noRepeats;
    int noRepeatsAgain;

    int length;
    int disp = 0;
    int[] options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
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
        Log.d("Theme: ", "Set as " +theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        highScoreBox = (TextView)findViewById(R.id.highScore);
        highScoreBox.setText(String.valueOf(highScore));

        getActionBar().setTitle("Kana+");

        kanaView = (ImageView)findViewById(R.id.kanaimageView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);

        editor = mPrefs.edit();

        all = mPrefs.getBoolean("allCheckBox", false);

        editor.putString("mode", "time");

        int highScorePref = mPrefs.getInt("highScore", 0);

        Log.d("HighScore: ", String.valueOf(highScorePref));

        highScore = highScorePref;

        highScoreBox = (TextView)findViewById(R.id.highScore);

        highScoreBox.setText(String.valueOf(highScore));


        scoreTextView.setText(String.valueOf(score));


        changeImage(0);

        options = makeGame();



        makeTimer(21000);



    }

    public void onClickCheckIfCorrect(View v){

        //Drawable currentView = kanaView.getDrawable();
        Log.d("Current kana", "current kana is: " +currentKana);
        noRepeatsAgain = currentKana;
        checkAnswer(0, "a");
        checkAnswer(1, "chi");
        checkAnswer(2, "e");
        checkAnswerSpecial(3, "fu", "hu");
        checkAnswer(4, "ha");
        checkAnswer(5, "he");
        checkAnswer(6, "hi");
        checkAnswer(7, "ho");
        checkAnswer(8, "i");
        checkAnswer(9, "ka");
        checkAnswer(10, "ke");
        checkAnswer(11, "ki");
        checkAnswer(12, "ko");
        checkAnswer(13, "ku");
        checkAnswer(14, "ma");
        checkAnswer(15, "me");
        checkAnswer(16, "mi");
        checkAnswer(17, "mo");
        checkAnswer(18, "mu");
        checkAnswer(19, "n");
        checkAnswer(20, "na");
        checkAnswer(21, "ne");
        checkAnswer(22, "ni");
        checkAnswer(23, "no");
        checkAnswer(24, "nu");
        checkAnswer(25, "o");
        checkAnswer(26, "ra");
        checkAnswer(27, "re");
        checkAnswer(28, "ri");
        checkAnswer(29, "ro");
        checkAnswer(30, "ru");
        checkAnswer(31, "sa");
        checkAnswer(32, "se");
        checkAnswerSpecial(33, "si", "shi");
        checkAnswer(34, "so");
        checkAnswer(35, "su");
        checkAnswer(36, "ta");
        checkAnswer(37, "te");
        checkAnswer(38, "to");
        checkAnswerSpecial(39, "tsu", "tu");
        checkAnswer(40, "u");
        checkAnswer(41, "wa");
        checkAnswer(42, "wo");
        checkAnswer(43, "ya");
        checkAnswer(44, "yo");
        checkAnswer(45, "yu");
        checkAnswer(46, "a");
        checkAnswer(47, "e");
        checkAnswer(48, "ha");
        checkAnswer(49, "he");
        checkAnswer(50, "hi");
        checkAnswer(51, "ho");
        checkAnswerSpecial(52, "hu", "fu");
        checkAnswer(53, "i");
        checkAnswer(54, "ka");
        checkAnswer(55, "ke");
        checkAnswer(56, "ki");
        checkAnswer(57, "ko");
        checkAnswer(58, "ku");
        checkAnswer(59, "ma");
        checkAnswer(60, "me");
        checkAnswer(61, "mi");
        checkAnswer(62, "mo");
        checkAnswer(63, "mu");
        checkAnswer(64, "n");
        checkAnswer(65, "na");
        checkAnswer(66, "ne");
        checkAnswer(67, "ni");
        checkAnswer(68, "no");
        checkAnswer(69, "nu");
        checkAnswer(70, "o");
        checkAnswer(71, "ra");
        checkAnswer(72, "re");
        checkAnswer(73, "ri");
        checkAnswer(74, "ro");
        checkAnswer(75, "ru");
        checkAnswer(76, "sa");
        checkAnswer(77, "se");
        checkAnswerSpecial(78, "si", "shi");
        checkAnswer(79, "so");
        checkAnswer(80, "su");
        checkAnswer(81, "ta");
        checkAnswer(82, "te");
        checkAnswer(83, "ti");
        checkAnswer(84, "to");
        checkAnswer(85, "tu");
        checkAnswer(86, "u");
        checkAnswer(87, "wa");
        checkAnswer(88, "we");
        checkAnswer(89, "wi");
        checkAnswer(90, "wo");
        checkAnswer(91, "ya");
        checkAnswer(92, "yo");
        checkAnswer(93, "yu");
        checkAnswer(94, "ba");
        checkAnswer(95, "be");
        checkAnswer(96, "bi");
        checkAnswer(97, "bo");
        checkAnswer(98, "bu");
        checkAnswer(99, "da");
        checkAnswer(100, "de");
        checkAnswer(101, "di");
        checkAnswer(102, "do");
        checkAnswerSpecial(103, "du", "zu");
        checkAnswer(104, "ga");
        checkAnswer(105, "ge");
        checkAnswer(106, "gi");
        checkAnswer(107, "go");
        checkAnswer(108, "gu");
        checkAnswer(109, "pa");
        checkAnswer(110, "pe");
        checkAnswer(111, "pi");
        checkAnswer(112, "po");
        checkAnswer(113, "pu");
        checkAnswer(114, "za");
        checkAnswer(115, "ze");
        checkAnswer(116, "zi");
        checkAnswer(117, "zo");
        checkAnswer(118, "zu");
        checkAnswer(119, "ba");
        checkAnswer(120, "be");
        checkAnswer(121, "bi");
        checkAnswer(122, "bo");
        checkAnswer(123, "bu");
        checkAnswer(124, "da");
        checkAnswer(125, "de");
        checkAnswer(126, "di");
        checkAnswer(127, "do");
        checkAnswerSpecial(128, "du", "zu");
        checkAnswer(129, "ga");
        checkAnswer(130, "ge");
        checkAnswer(131, "gi");
        checkAnswer(132, "go");
        checkAnswer(133, "gu");
        checkAnswer(134, "pa");
        checkAnswer(135, "pe");
        checkAnswer(136, "pi");
        checkAnswer(137, "po");
        checkAnswer(138, "pu");
        checkAnswer(139, "za");
        checkAnswer(140, "ze");
        checkAnswerSpecial(141, "zi", "ji");
        checkAnswer(142, "zo");
        checkAnswer(143, "zu");

    }

    public void changeImage(int lastDisplayed){

        Log.d("This", "1");

            if (score > highScore) {

                highScore = score;
                highScoreBox = (TextView)findViewById(R.id.highScore);
                highScoreBox.setText(String.valueOf(highScore));
                editor.putInt("highScoreTime", highScore);
                editor.commit();

            }

        Log.d("This", "12");


            int[] hiragana = {R.drawable.a, R.drawable.chi, R.drawable.e, R.drawable.fu, R.drawable.ha, R.drawable.he, R.drawable.hi, R.drawable.ho, R.drawable.i, R.drawable.ka, R.drawable.ke, R.drawable.ki, R.drawable.ko, R.drawable.ku, R.drawable.ma, R.drawable.me, R.drawable.mi, R.drawable.mo, R.drawable.mu, R.drawable.n, R.drawable.na, R.drawable.ne, R.drawable.ni, R.drawable.no, R.drawable.nu, R.drawable.o, R.drawable.ra, R.drawable.re, R.drawable.ri, R.drawable.ro, R.drawable.ru, R.drawable.sa, R.drawable.se, R.drawable.shi, R.drawable.so, R.drawable.su, R.drawable.ta, R.drawable.te, R.drawable.to, R.drawable.tsu, R.drawable.u, R.drawable.wa, R.drawable.wo, R.drawable.ya, R.drawable.yo, R.drawable.yu, R.drawable.a_k, R.drawable.e_k, R.drawable.ha_k, R.drawable.he_k, R.drawable.hi_k, R.drawable.ho_k, R.drawable.hu_k, R.drawable.i_k, R.drawable.ka_k, R.drawable.ke_k, R.drawable.ki_k, R.drawable.ko_k, R.drawable.ku_k, R.drawable.ma_k, R.drawable.me_k, R.drawable.mi_k, R.drawable.mo_k, R.drawable.mu_k, R.drawable.n_k, R.drawable.na_k, R.drawable.ne_k, R.drawable.ni_k, R.drawable.no_k, R.drawable.nu_k, R.drawable.o_k, R.drawable.ra_k, R.drawable.re_k, R.drawable.ri_k, R.drawable.ro_k, R.drawable.ru_k, R.drawable.sa_k, R.drawable.se_k, R.drawable.si_k, R.drawable.so_k, R.drawable.su_k, R.drawable.ta_k, R.drawable.te_k, R.drawable.ti_k, R.drawable.to_k, R.drawable.tu_k, R.drawable.u_k, R.drawable.wa_k, R.drawable.we_k, R.drawable.wi_k, R.drawable.wo_k, R.drawable.ya_k, R.drawable.yo_k, R.drawable.yu_k, R.drawable.ba, R.drawable.be, R.drawable.bi, R.drawable.bo, R.drawable.bu, R.drawable.da, R.drawable.de, R.drawable.diji, R.drawable.dohiragana, R.drawable.dzu, R.drawable.ga, R.drawable.ge, R.drawable.gi, R.drawable.go, R.drawable.gu, R.drawable.pa, R.drawable.pe, R.drawable.pi, R.drawable.po, R.drawable.pu, R.drawable.za, R.drawable.ze, R.drawable.jizi, R.drawable.zo, R.drawable.zu, R.drawable.ba_k, R.drawable.be_k, R.drawable.bi_k, R.drawable.bo_k, R.drawable.bu_k, R.drawable.da_k, R.drawable.de_k, R.drawable.dji_k, R.drawable.do_k, R.drawable.dzu_k, R.drawable.ga_k, R.drawable.ge_k, R.drawable.gi_k, R.drawable.go_k, R.drawable.gu_k, R.drawable.pa_k, R.drawable.pe_k, R.drawable.pi_k, R.drawable.po_k, R.drawable.pu_k, R.drawable.za_k, R.drawable.ze_k, R.drawable.zi_k, R.drawable.zo_k, R.drawable.zu_k };
        Log.d("This", "001");
        if (options == null){
            options = makeGame();
        }
        if (options.length == 0) {
                Log.d("This", "100");
                Log.d("Options length is", "zero");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Chose Kana!");
                builder.setMessage("It looks like you haven't chosen any Kana to display! You can't play until you do, so press 'Continue' to go into settings and chose some!");
                builder.setNeutralButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent goChoseKana = new Intent(game_timeTrial.this, Custom.class);
                        startActivity(goChoseKana);

                    }
                });
                builder.show();

            } else {

                Log.d("This", "13");

                Boolean offline = mPrefs.getBoolean("offline", false);

                if (!offline) {

                    String RandorgAPIKEY = "313af1c8-aa8b-4293-af0e-094240fbbc5e";

                    int max = options.length;
                    int maxVal = max - 1;
                    int superRandVal;

                    Log.d("Max:", String.valueOf(maxVal));

                    RandomOrgClient rand = RandomOrgClient.getRandomOrgClient(RandorgAPIKEY, 0, 143, false);
                    try {
                        int[] superRand = rand.generateIntegers(1, 0, maxVal);
                        superRandVal = superRand[0];
                        if (superRandVal > maxVal) {
                            superRandVal = maxVal;
                        }
                        Log.d("Randval: ", String.valueOf(superRandVal));
                    } catch (Exception e) {
                        Log.e("ROC Exception", "" + e);
                        Toast.makeText(getApplicationContext(), "There was a problem communicating with the random number generator, if the problem persists, please restart Kana+", Toast.LENGTH_SHORT).show();
                        superRandVal = 0;
                    }
                    Log.d("Rand: ", String.valueOf(superRandVal));
                    int kanaId = options[superRandVal];
                    kanaView.setBackgroundResource(hiragana[kanaId]);
                    currentKana = kanaId;
                } else if (offline) {

                    Log.d("This", "41");

                    int i = options.length - 1;
                    Log.d("", "" +i);
                    Log.d("This", "15");
                    Log.d("uh", "uh");

                    Log.d("uh" , "" +disp);
                    if (disp <= i){
                        //It;s the last image so reshuffle and start over
                        Log.d("This", "16");

                        int kanaId = options[disp];
                        Log.d("This", "17");
                        kanaView.setImageResource(hiragana[kanaId]);
                        Log.d("This", "18");
                        Log.d("uhh:", "uh");
                        currentKana = kanaId;

                           /* disp = 0;
                            kanaView.setBackgroundResource(options[disp]);
                            currentKana = disp;
                            reShuffle();
                            disp = 0;*/
                    } else {
                        disp = 0;
                        int kanaId = options[disp];
                        kanaView.setImageResource(hiragana[kanaId]);
                        Log.d("uhh:", "uh");
                        currentKana = kanaId;
                        shuffle(options);
                        disp = 0;
                    }

                }

            }




    }

    public int[] makeGame(){

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPrefs.edit();

        List<Integer> choices = new ArrayList<Integer>();

        Boolean vowels = mPrefs.getBoolean("aiueo", true);
        Boolean kHiragana = mPrefs.getBoolean("kakikukeko", true);
        Boolean sHiragana = mPrefs.getBoolean("Sakikukeko", true);
        Boolean tHiragana = mPrefs.getBoolean("Takikukeko", true);
        Boolean nHiragana = mPrefs.getBoolean("Nakikukeko", true);
        Boolean hHiragana = mPrefs.getBoolean("Hakikukeko", true);
        Boolean mHiragana = mPrefs.getBoolean("Makikukeko", true);
        Boolean yHiragana = mPrefs.getBoolean("Yakikukeko", true);
        Boolean rHiragana = mPrefs.getBoolean("Rakikukeko", true);
        Boolean wHiragana = mPrefs.getBoolean("Wakikukeko", true);
        Boolean nHiraganaPref = mPrefs.getBoolean("Nkikukeko", true);

        Boolean vowelsK = mPrefs.getBoolean("a", false);
        Boolean kKatakana = mPrefs.getBoolean("ka", false);
        Boolean sKatakana = mPrefs.getBoolean("Sa", false);
        Boolean tKatakana = mPrefs.getBoolean("Ta", false);
        Boolean nKatakana = mPrefs.getBoolean("Na", false);
        Boolean hKatakana = mPrefs.getBoolean("Ha", false);
        Boolean mKatakana = mPrefs.getBoolean("Ma", false);
        Boolean yKatakana = mPrefs.getBoolean("Ya", false);
        Boolean rKatakana = mPrefs.getBoolean("Ra", false);
        Boolean wKatakana = mPrefs.getBoolean("Wa", false);
        Boolean nKatakanaPref = mPrefs.getBoolean("N", false);

        Boolean baHiragana = mPrefs.getBoolean("baHiragana", false);
        Boolean daHiragana = mPrefs.getBoolean("daHiragana", false);
        Boolean gaHiragana = mPrefs.getBoolean("gaHiragana", false);
        Boolean paHiragana = mPrefs.getBoolean("paHiragana", false);
        Boolean zaHiragana = mPrefs.getBoolean("zaHiragana", false);

        Boolean baKatakana = mPrefs.getBoolean("baKatakana", false);
        Boolean daKatakana = mPrefs.getBoolean("daKatakana", false);
        Boolean gaKatakana = mPrefs.getBoolean("gaKatakana", false);
        Boolean paKatakana = mPrefs.getBoolean("paKatakana", false);
        Boolean zaKatakana = mPrefs.getBoolean("zaKatakana", false);


        if (vowels){

            choices.add(0);
            choices.add(2);
            choices.add(8);
            choices.add(25);
            choices.add(40);

        }

        if (hHiragana) {

            choices.add(3);
            choices.add(4);
            choices.add(5);
            choices.add(6);
            choices.add(7);

        }

        if (kHiragana) {

            choices.add(9);
            choices.add(10);
            choices.add(11);
            choices.add(12);
            choices.add(13);

        }

        //14-18
        if (mHiragana){

            choices.add(14);
            choices.add(15);
            choices.add(16);
            choices.add(17);
            choices.add(18);

        }

        if (nHiraganaPref){

            choices.add(19);

        }

        if (nHiragana) {

            choices.add(20);
            choices.add(21);
            choices.add(22);
            choices.add(23);
            choices.add(24);

        }

        if (rHiragana) {

            choices.add(26);
            choices.add(27);
            choices.add(28);
            choices.add(29);
            choices.add(30);

        }

        if (sHiragana) {

            choices.add(31);
            choices.add(32);
            choices.add(33);
            choices.add(34);
            choices.add(35);

        }

        if (tHiragana) {

            choices.add(36);
            choices.add(37);
            choices.add(38);
            choices.add(39);
            choices.add(1);

        }

        if (wHiragana) {

            choices.add(41);
            choices.add(42);

        }

        if (yHiragana) {

            choices.add(43);
            choices.add(44);
            choices.add(45);

        }

        if (vowelsK) {

            choices.add(46);
            choices.add(47);
            choices.add(53);
            choices.add(70);
            choices.add(86);

        }

        if (hKatakana) {

            choices.add(48);
            choices.add(49);
            choices.add(50);
            choices.add(51);
            choices.add(52);

        }

        if (kKatakana) {

            choices.add(54);
            choices.add(55);
            choices.add(56);
            choices.add(57);
            choices.add(58);

        }

        if (mKatakana) {

            choices.add(59);
            choices.add(60);
            choices.add(61);
            choices.add(62);
            choices.add(63);

        }

        if (nKatakanaPref) {

            choices.add(64);

        }

        if (nKatakana) {

            choices.add(65);
            choices.add(66);
            choices.add(67);
            choices.add(68);
            choices.add(69);

        }

        if (rKatakana) {

            choices.add(71);
            choices.add(72);
            choices.add(73);
            choices.add(74);
            choices.add(75);

        }

        if (sKatakana) {

            choices.add(76);
            choices.add(77);
            choices.add(78);
            choices.add(79);
            choices.add(80);

        }

        if (tKatakana) {

            choices.add(81);
            choices.add(82);
            choices.add(83);
            choices.add(84);
            choices.add(85);

        }

        if (wKatakana) {

            choices.add(87);
            choices.add(88);
            choices.add(89);
            choices.add(90);

        }

        if (yKatakana) {

            choices.add(91);
            choices.add(92);
            choices.add(93);

        }

        if (baHiragana) {

            choices.add(94);
            choices.add(95);
            choices.add(96);
            choices.add(97);
            choices.add(98);

        }

        if (daHiragana) {

            choices.add(99);
            choices.add(100);
            choices.add(101);
            choices.add(102);
            choices.add(103);

        }

        if (gaHiragana) {

            choices.add(104);
            choices.add(105);
            choices.add(106);
            choices.add(107);
            choices.add(108);

        }

        if (paHiragana) {

            choices.add(109);
            choices.add(110);
            choices.add(111);
            choices.add(112);
            choices.add(113);

        }

        if (zaHiragana) {

            choices.add(114);
            choices.add(115);
            choices.add(116);
            choices.add(117);
            choices.add(118);

        }

        if (baKatakana) {

            choices.add(119);
            choices.add(120);
            choices.add(121);
            choices.add(122);
            choices.add(123);

        }

        if (daKatakana) {

            choices.add(124);
            choices.add(125);
            choices.add(126);
            choices.add(127);
            choices.add(128);

        }

        if (gaKatakana) {

            choices.add(129);
            choices.add(130);
            choices.add(131);
            choices.add(132);
            choices.add(133);

        }

        if (paKatakana) {

            choices.add(134);
            choices.add(135);
            choices.add(136);
            choices.add(137);
            choices.add(138);

        }

        if (zaKatakana) {

            choices.add(139);
            choices.add(140);
            choices.add(141);
            choices.add(142);
            choices.add(143);

        }

        int[] choicesInt = new int[choices.size()];

        int[] conv = convertIntegers(choices);

        length = conv.length;

        shuffle(conv);

        return conv;




    }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    //W00t some l33t timer swag
    public void makeTimer(int time){
        final TextView timer = (TextView)findViewById(R.id.countDown);
        timerData = new CountDownTimer(time, 1000){
            public void onTick(long millisUntilFinished) {

                long timeLeft = millisUntilFinished/1000;
                timer.setText(String.valueOf(timeLeft));

            }
            public void onFinish(){

                timer.setText("0");
                Log.d("Timer: ", "finished");
                editor.putInt("score", score);
                editor.putInt("highScoreTime", highScore);
                editor.commit();
                Intent gameOver = new Intent(game_timeTrial.this, gameOverActivity.class);
                gameOver.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gameOver);
                finish();

            }
        }.start();

    }


    public int timerTime(int time){

        int returnTime = time*1000;

        return returnTime;

    }

    public void resetTimer() {

        if (score >= 20){
            time = 11;
        }
        if (score >= 30){
            time = 10;
        }
        if (score >= 40){
            time = 9;
        }
        if (score >= 50){
            time = 6;
        }
        if (score >= 60){
            time = 5;
        }
        if (score >= 70){
            time = 4;
        }
        if (score >= 80){
            time = 3;
        }

        timerData.cancel();
        int reset = timerTime(time);
        Log.d("Time: ", ""+reset +"," +time);
        makeTimer(reset);
    }

    public void checkAnswer(int position, String ans) {

        if (currentKana == position) {

            noRepeats = position;
            answerBox = (EditText)findViewById(R.id.editTextTime);
            String answer = answerBox.getText().toString();
            if (answer.equalsIgnoreCase(ans)){
                score++;
                scoreTextView.setText(String.valueOf(score));
                answerBox.setText("");
                answerBox.setHint("Romaji");
                disp++;
                changeImage(position);
                resetTimer();

                answerBox.setTextColor(Color.BLACK);
            } else if (answer.equals("")){

                answerBox.setText("");
                answerBox.setHint("Romaji");
                answerBox.setTextColor(Color.BLACK);

            } else {
                if (score > 0) {
                    answerBox.setTextColor(Color.parseColor("#ffc60a1c"));
                    score--;
                    scoreTextView.setText(String.valueOf(score));
                }
            }

        }

    }

    public void checkAnswerSpecial(int pos, String ans, String ansAlt) {

        if (currentKana == pos) {

            noRepeats = currentKana;
            answerBox = (EditText)findViewById(R.id.editTextTime);
            String answer = answerBox.getText().toString();
            if (answer.equalsIgnoreCase(ans)||answer.equalsIgnoreCase(ansAlt)){
                score++;
                scoreTextView.setText(String.valueOf(score));
                answerBox.setText("");
                answerBox.setHint("Romaji");
                disp++;
                changeImage(noRepeats);
                resetTimer();
                answerBox.setTextColor(Color.BLACK);
            } else if (answer.equals("")){

                answerBox.setText("");
                answerBox.setHint("Romaji");
                answerBox.setTextColor(Color.BLACK);

            } else {
                if (score > 0) {
                    answerBox.setTextColor(Color.parseColor("#ffc60a1c"));
                    score--;
                    scoreTextView.setText(String.valueOf(score));
                }
            }

        }

    }

    public void shuffle(int[] ar){

        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        timerData.cancel();

    }


}
