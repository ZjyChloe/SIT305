package com.example.task31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndQuiz extends AppCompatActivity {

    private TextView name, score;

    public static final String EXTRA_RESTART =
            "com.example.android.task31.extra.RESTART";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_quiz);
        Intent intent = getIntent();
        String message1 = intent.getStringExtra(AnswerInterface1.EXTRA_MESSAGE1);
        String message2 = intent.getStringExtra(AnswerInterface1.EXTRA_MESSAGE2);

        score = findViewById(R.id.ScoreNumber);
        name = findViewById(R.id.yourName);
        score.setText(message1);
        name.setText(message2);
    }

    public void reStart(View view) {
        String restart = name.getText().toString();
        Intent restartIntent = new Intent();
        restartIntent.putExtra(EXTRA_RESTART, restart);
        setResult(RESULT_OK,restartIntent);
        finish();
    }

    public void finish(View view) {
        finishAffinity();
    }
}