package ir.zjafarian.quizactivity.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.controller.QuizActivity;

public class CheatActivity extends AppCompatActivity {
    public static final String EXTERA_IS_CHEAT = "ir.zjafarian.quizactivity.isCheat";
    public static final String M_IS_ANSWER = "mIsAnswer";
    private Button mButtonAnswerCheat;
    private TextView mTextCheatAnswer;
    private boolean mIsAnswerTrue;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setId();
        if (savedInstanceState != null) {
            mIsAnswerTrue = getIntent().getBooleanExtra(QuizActivity.Extera_QUESTION_ANSWER,
                    false);
            setTextAnswerCheat();
            setShownAnswerResult(true);
        }


        listener();

    }

    private void listener() {
        mButtonAnswerCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextAnswerCheat();
                setShownAnswerResult(true);

            }
        });
    }

    private void setTextAnswerCheat() {
        if (mIsAnswerTrue)
            mTextCheatAnswer.setText(R.string.btn_true);
        else mTextCheatAnswer.setText(R.string.btn_false);
    }

    private void setShownAnswerResult(boolean isCheat) {
        Intent intent = new Intent();
        intent.putExtra(EXTERA_IS_CHEAT, isCheat);
        setResult(RESULT_OK, intent);

    }

    private void setId() {
        mButtonAnswerCheat = findViewById(R.id.btn_answer_cheat);
        mTextCheatAnswer = findViewById(R.id.textView_answer_cheat);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(M_IS_ANSWER, mIsAnswerTrue);
    }
}