package com.example.task31;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AnswerInterface1 extends AppCompatActivity  {

    private Button option1,option2,option3,submitButton, nextButton;
    public static final int TEXT_REQUEST = 1;
    private Button selectedOption,correctOption;
    private TextView questionText,progressNum,userName;
    private ProgressBar quizProgress;
    public  static final String EXTRA_MESSAGE1 = "com.example.android.task31.extra.MESSAGE1";
    public  static final String EXTRA_MESSAGE2 = "com.example.android.task31.extra.MESSAGE2";
    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    Boolean option = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_interface1);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        userName = findViewById(R.id.userName);
        userName.setText(message);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submitButton = findViewById(R.id.submit_btn);
        nextButton = findViewById(R.id.next_btn);
        questionText = findViewById(R.id.questionTextView);
        quizProgress = findViewById(R.id.progressBar);
        progressNum = findViewById(R.id.progressNumber);

        nextButton.setVisibility(View.INVISIBLE);
        loadNewQuestion();


    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionText.setText(QuestionAnswer.question[currentQuestionIndex]);
        option1.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        option2.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        option3.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
    }



    public void selected(View view) {
        selectedOption = (Button) view;
        selectedAnswer  = selectedOption.getText().toString();
        if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]))
        {
            option = true;
        }else
            option = false;
    }

    public void submit(View view) {
        if(option == true)
        {
            score++;
            selectedOption.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_correct));

        }else
        {
            selectedOption.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_wrong));
            for(int i = 0;i != (currentQuestionIndex+1);i++)
            {
                if(i == currentQuestionIndex){
                    switch (i){
                        case 0:
                            correctOption = option3;
                            break;
                        case 1:
                            correctOption = option2;
                            break;
                        case 2:
                            correctOption = option3;
                            break;
                        case 3:
                            correctOption = option1;
                            break;
                        case 4:
                            correctOption = option2;
                            break;
                    }

                    correctOption.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_correct));

                }
            }
        }
        currentQuestionIndex++;
        nextButton.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.INVISIBLE);

    }

    public void next(View view) {
        option1.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_selector));
        option2.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_selector));
        option3.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_bg_selector));
        loadNewQuestion();
        nextButton.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        quizProgress.setProgress((int)(quizProgress.getProgress()+20));
        progressNum.setText((currentQuestionIndex+1) + "/5");
    }

    void finishQuiz(){
        Intent intent = new Intent(this, EndQuiz.class);
        String message1 = String.valueOf(score);
        String message2 = userName.getText().toString();
        intent.putExtra(EXTRA_MESSAGE1, message1);
        intent.putExtra(EXTRA_MESSAGE2, message2);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(EndQuiz.EXTRA_RESTART);
                userName.setText(reply);
                currentQuestionIndex = 0;
                option = false;
                quizProgress.setProgress(20);
                int temp = currentQuestionIndex;
                progressNum.setText((temp+1) + "/5");
                score = 0;
                loadNewQuestion();
            }
        }
    }
}