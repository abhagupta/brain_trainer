package sample.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    TextView answerTextView;
    TextView pointsTextView;
    TextView sumTextView;
     TextView timerTextView;
    RelativeLayout gameLayoutView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){

        score = 0;
        numberOfQuestions = 0;

        answerTextView.setText("");
        pointsTextView.setText("0/0");
        timerTextView.setText("30s");
        playAgain.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long currentSecond) {
                timerTextView.setText( String.valueOf(currentSecond / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                answerTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                timerTextView.setText("0s");

            }
        }.start();
    }

    public void generateQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);

            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score ++ ;
            answerTextView.setText("Correct!");



        } else {
            answerTextView.setText("Wrong!");

        }

        numberOfQuestions ++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayoutView.setVisibility(RelativeLayout.VISIBLE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
         button0 = (Button) findViewById(R.id.button0);
         button1 = (Button) findViewById(R.id.button1);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.playAgainButton);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gameLayoutView = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        generateQuestion();
        playAgain(findViewById(R.id.playAgainButton));

    }
}
