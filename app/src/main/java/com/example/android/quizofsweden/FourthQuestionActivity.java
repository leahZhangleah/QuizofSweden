package com.example.android.quizofsweden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ceciliaHumlelu on 2018-03-12.
 */

public class FourthQuestionActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private TextView name;
    private String nameGreeting;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button submitAnswer;
    private String fourthQuestionAnswer;
    private int correctAnswerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_question);
        name = (TextView) findViewById(R.id.name_greeting);

        //get intent from last activity and extra info
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

        submitAnswer();

        radioButton1 = (RadioButton) findViewById(R.id.radioButton_12);
        radioButton1.setOnCheckedChangeListener(this);

        radioButton2 = (RadioButton) findViewById(R.id.radioButton_18);
        radioButton2.setOnCheckedChangeListener(this);

        radioButton3 = (RadioButton) findViewById(R.id.radioButton_23);
        radioButton3.setOnCheckedChangeListener(this);
    }

    //check which radiobutton is clicked
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.radioButton_12:
                if (b){
                    fourthQuestionAnswer = radioButton1.getText().toString();
                }
                break;
            case R.id.radioButton_18:
                if (b){
                    fourthQuestionAnswer = radioButton2.getText().toString();
                }
                break;
            case R.id.radioButton_23:
                if (b){
                    fourthQuestionAnswer = radioButton3.getText().toString();
                }
                break;
            default:
                Toast.makeText(FourthQuestionActivity.this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
        }
    }

    //check if the question is answered and go to next activity
    public void submitAnswer(){
        submitAnswer = (Button) findViewById(R.id.submit_button);
        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fourthQuestionAnswer == null){
                    Toast.makeText(FourthQuestionActivity.this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
                }else {
                    checkAnswer();
                    String resultMessage = nameGreeting + ", you have answered "+correctAnswerNumber+"/4 questions correctly.";
                    Toast.makeText(FourthQuestionActivity.this,resultMessage,Toast.LENGTH_LONG).show();
                    submitAnswer.setEnabled(false);
                }
            }
        });
    }

    //check if the answer is correct
    public void checkAnswer(){
        if (fourthQuestionAnswer == getResources().getString(R.string.fourth_question_choice_3)){
            correctAnswerNumber += 1;
        }
    }
}
