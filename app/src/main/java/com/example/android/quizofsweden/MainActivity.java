package com.example.android.quizofsweden;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userName;
    Button startQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startQuiz();
    }

    public void startQuiz(){
        startQuiz = (Button) findViewById(R.id.start_quiz);
        userName = (EditText) findViewById(R.id.user_name);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this,R.string.name_required,Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this,FirstQuestionActivity.class);
                    intent.putExtra("UserName",userName.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }
        });
    }
}
