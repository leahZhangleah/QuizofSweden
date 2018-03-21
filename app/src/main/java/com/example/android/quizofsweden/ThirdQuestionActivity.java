package com.example.android.quizofsweden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ceciliaHumlelu on 2018-03-12.
 */

public class ThirdQuestionActivity extends AppCompatActivity {
    private TextView name;
    private String nameGreeting;
    private String thirdQuestionAnswer;
    private EditText thirdQuestionA;
    private Button nextQuestion;
    private int correctAnswerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_question);
        name = (TextView) findViewById(R.id.name_greeting);

        //get intent from last activity and extrea info
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                nameGreeting = null;
            } else {
                nameGreeting = extras.getString("UserName");
                name.setText(nameGreeting);
                correctAnswerNumber = (int) extras.getInt("Answer");
            }
        } else {
            nameGreeting= (String) savedInstanceState.getSerializable("UserName");
            name.setText(nameGreeting);
            correctAnswerNumber = (int) savedInstanceState.getSerializable("Answer");
        }

        nextQuestion();
    }

    //check if user has answered the question and go to next activity
    public void nextQuestion(){
        nextQuestion = (Button) findViewById(R.id.nextQuestionBtn3);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thirdQuestionA = (EditText)findViewById(R.id.third_question_answer);
                thirdQuestionAnswer = thirdQuestionA.getText().toString();
                if (thirdQuestionAnswer.matches("")){
                    Toast.makeText(ThirdQuestionActivity.this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isDigitsOnly(thirdQuestionAnswer)){
                    if (thirdQuestionAnswer.equals(String.valueOf(4))){
                        correctAnswerNumber += 1;
                    }
                    Intent intent = new Intent(ThirdQuestionActivity.this, FourthQuestionActivity.class);
                    intent.putExtra("UserName", nameGreeting);
                    intent.putExtra("Answer", correctAnswerNumber);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else{
                    Toast.makeText(ThirdQuestionActivity.this,R.string.third_question_digit_only,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
