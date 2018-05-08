package com.fansy.smz.fansygame.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fansy.smz.fansygame.R;
import com.fansy.smz.fansygame.mathgame.utils.AnswerSignal;
import com.fansy.smz.fansygame.mathgame.utils.Book;
import com.fansy.smz.fansygame.mathgame.utils.PullBookParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongxujie on 1/18/18.
 */

public class MathPkActivity extends Activity {

    private static final int buttonCount = 10;
    private TextView mathFormula;
    private TextView mathAnswer;
    private TextView mathFormulaHistory;
    private TextView mathScore;
    private Button startButton;
    private int answer = 0;
    private String mMathFormula = "";

    private int isMaster = 0;

    private int score = 0;

    private boolean answerState = true;

    private MessageHandler messageHandler;

    private int slaveScore = 0;

    private MathProblems mp = new MathProblems();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        isMaster = bundle.getInt("isMaster");

        setContentView(R.layout.math_pk);

        startButton = findViewById(R.id.start);
        if(isMaster == 1) {
            startButton.setOnClickListener(startListener);
        }else {
            startButton.setEnabled(false);
        }

        mathFormula = findViewById(R.id.formula);
        mathAnswer = findViewById(R.id.answer);
        mathFormulaHistory = findViewById(R.id.history);
        mathScore = findViewById(R.id.score);

        int startID = R.id.num0;
        for (int i = 0; i < buttonCount; i++){
            findViewById(startID + i).setOnClickListener(numButtonListener);
        }
        findViewById(R.id.clear).setOnClickListener(numButtonListener);

        messageHandler = new MessageHandler(Looper.getMainLooper());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    View.OnClickListener startListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            mMathFormula = mp.createMathFormula();
            mathFormula.setText(mMathFormula);
            mathAnswer.setText("");
            sendBook();
        }
    };

    View.OnClickListener numButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(answerState){
                String strAnswer = "";

                switch(view.getId()) {
                    case R.id.clear:
                        answer = 0;
                        break;
                    default:
                        if(answer < 10){
                            answer = answer * 10 + view.getId() - R.id.num0;
                        }
                        break;
                }

                if(view.getId() == R.id.clear) {
                    strAnswer = "";
                }else {
                    strAnswer = "" + answer;
                }

                mathAnswer.setText(strAnswer);
                if (answer == mp.getAnswer()){
                    if(isMaster == 1){
                        //MainActivity.instance.sendRightAnswerSignal();
                        score++;
                    }
                    getRightAnswer();
                }
            }
        }
    };

    private void sendBook(){
        Book book = new Book();
        book.setX(mp.getX());
        book.setY(mp.getY());
        book.setZ(mp.getZ());
        book.setFormula(mp.getFormula());

        PullBookParser parser = new PullBookParser();
        List<Book> books = new ArrayList<Book>();

        books.add(book);

        try {
            String strBook = parser.serialize(books);
            // KenHong todo send

        } catch (Exception e) {
            Log.e("KenHong", e.getMessage());
        }

        slaveScore = 0;
    }

    public void onBookReceived(Book book){
        mMathFormula = book.getFormula();
        mp.setMathFormula(book.getX(), book.getY(), book.getZ(), mMathFormula);

        // Slave score
        if(book.getScore() == 1){
            score++;
            mathScore.setText("" + score);
        }

        answer = 0;

        mathFormula.setText(mMathFormula);
        mathAnswer.setText("");
        answerState = true;
    }

    public void onRightAnswerSignalReceived(AnswerSignal answerSignal){
        if(answerState){
            slaveScore = 1;
            getRightAnswer();
        }
    }

    private void getRightAnswer(){
        answerState = false;

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(200);

                    answerState = true;

                    Message msg = messageHandler.obtainMessage();

                    Bundle b = new Bundle();
                    b.putInt("signal", 777);
                    msg.setData(b);

                    messageHandler.sendMessage(msg);

                }catch (Exception e){
                    Log.e("KenHong", e.getMessage());
                }
            }
        }.start();
    }

    class MessageHandler extends Handler {
        public MessageHandler(Looper L) {
            super(L);
        }

        @Override
        public void handleMessage(Message msg) {
        // 这里用于更新UI
            Bundle b = msg.getData();
            int data = b.getInt("signal");


            if((data == 777) && (isMaster == 1)) {
                String history = mMathFormula + answer;
                mathFormulaHistory.setText(history);

                mMathFormula = mp.createMathFormula();
                mathFormula.setText(mMathFormula);


                answer = 0;
                mathAnswer.setText("");
                mathScore.setText("" + score);
                sendBook();
            }
        }
    }
}
