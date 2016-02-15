package com.example.eliane.burkinaloi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends  AppCompatActivity {
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    DbHelper mDBHelper;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    TextView timerValue;
    Button butNext;

long mytime;


    private final long startTime = 30 * 1000;

    private final long interval = 1 * 1000;

    private CountDownTimer countDownTimer;

    private boolean timerHasStarted = false;




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new DbHelper(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(DbHelper.DBNAME);
        if (false == database.exists() || true == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        butNext = (Button) findViewById(R.id.button1);

        timerValue = (TextView) findViewById(R.id.timerValue);
//
       countDownTimer = new MyCountDownTimer(startTime, interval);

        timerValue.setText(timerValue.getText() + String.valueOf(startTime / 1000));


        quesList = mDBHelper.getAllQuestions();
        currentQ = quesList.get(qid);

        setQuestionView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if (!timerHasStarted) {

            countDownTimer.start();

            timerHasStarted = true;
                butNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                        RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());

                        Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());

                        if (currentQ.getANSWER().equals(answer.getText())) {
                            score++;
                            Log.d("score", "Your score" + score);
                        }
                        if (qid < 4) {
                            currentQ = quesList.get(qid);
                            setQuestionView();
                        } else {
                            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("score", score); //Your score
                            intent.putExtras(b); //Put your score to your next Intent
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            /*else {
                /*Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle b = new Bundle();
                b.putInt("score", score); //Your score
                intent.putExtras(b); //Put your score to your next Intent
                startActivity(intent);
                finish();
                timerValue.setText(""+mytime);
            }*/
            // butNext.setText("STOP");

        }



    else {

        countDownTimer.cancel();

        timerHasStarted = false;
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); //Your score
            intent.putExtras(b); //Put your score to your next Intent
            startActivity(intent);
            finish();

    }
    }
    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DbHelper.DBNAME);
            String outFileName = DbHelper.DBLOCATION + DbHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }
    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {

            timerValue.setText("Time's up!");
            qid=5;
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); //Your score
            intent.putExtras(b); //Put your score to your next Intent
            startActivity(intent);
            finish();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mytime= millisUntilFinished / 1000;
            timerValue.setText("" + millisUntilFinished / 1000);
        }
        public long  myget(){
            return mytime;
        }
    }

    }
