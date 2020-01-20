package com.example.android.quizofsweden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ceciliaHumlelu on 2018-03-12.
 */

public class SecondQuestionActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView name;
    private String nameGreeting;
    private int correctAnswerNumber;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private Button nextQuestion;
    private boolean choice1;
    private boolean choice2;
    private boolean choice3;
    private boolean choice4;
    private boolean ifAnswerIsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_question);
        name = (TextView) findViewById(R.id.name_greeting);

        //get intent from last activity and extrea info
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                nameGreeting = null;
            } else {
                nameGreeting = extras.getString("UserName");
                correctAnswerNumber = (int) extras.getInt("Answer");
                name.setText(nameGreeting);
            }
        } else {
            nameGreeting= (String) savedInstanceState.getSerializable("UserName");
            correctAnswerNumber = (int) savedInstanceState.getSerializable("Answer");
            name.setText(nameGreeting);
        }

        //check if any checkbox has been checked
        checkBox1 = (CheckBox) findViewById(R.id.check_box_skype);
        checkBox1.setOnCheckedChangeListener(this);

        checkBox2 = (CheckBox) findViewById(R.id.check_box_bosch);
        checkBox2.setOnCheckedChangeListener(this);

        checkBox3 = (CheckBox) findViewById(R.id.check_box_ericsson);
        checkBox3.setOnCheckedChangeListener(this);

        checkBox4 = (CheckBox) findViewById(R.id.check_box_hm);
        checkBox4.setOnCheckedChangeListener(this);

        nextQuestion();
    }


    //check if user has answered the question and go to next activity
    public void nextQuestion(){
        nextQuestion = (Button) findViewById(R.id.nextQuestionBtn2);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifAnswerIsSelected = choice1 == false && choice2 == false && choice3 == false && choice4 == false;
                if (ifAnswerIsSelected){
                    Toast.makeText(SecondQuestionActivity.this,R.string.question_answer_required,Toast.LENGTH_SHORT).show();
                }else {
                    checkAnswer();
                    Intent intent = new Intent(SecondQuestionActivity.this, ThirdQuestionActivity.class);
                    intent.putExtra("UserName", nameGreeting);
                    intent.putExtra("Answer",correctAnswerNumber);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });
    }

    public void checkAnswer(){
        if (choice1 == true && choice2 == false && choice3 == true && choice4 == true ){
            correctAnswerNumber += 1;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.check_box_skype:
                choice1 = b;
                break;
            case R.id.check_box_bosch:
                choice2 = b;
                break;
            case R.id.check_box_ericsson:
                choice3 = b;
                break;
            case R.id.check_box_hm:
                choice4 = b;
                break;
        }
    }
}
