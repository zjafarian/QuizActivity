package ir.zjafarian.quizactivity.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.model.Questions;


public class QuizActivity extends AppCompatActivity {
    private Button mButton_true;
    private Button mButton_false;
    private boolean[] mSituationTrueButton;
    private boolean[] mSituationFulseButton;
    private TextView mTextQuestion;
    private ImageButton mButton_next;
    private ImageButton mButton_previous;
    private ImageButton mButton_first;
    private ImageButton mButton_last;
    private int mCurrentIndex;
    private int disableCounter;
    private int counterScore;
    private TextView mTextScore;
    private LinearLayout mLinearLayout_game;
    private LinearLayout mLinearLayout_over;
    private LinearLayout mLinearLayout_score;
    private ImageButton mButton_reset;
    private TextView mTextView_result;
    private Questions[] mQuestionsBank = {
            new Questions(R.string.question_australia, false),
            new Questions(R.string.question_oceans, true),
            new Questions(R.string.question_mideast, false),
            new Questions(R.string.question_africa, true),
            new Questions(R.string.question_americas, false),
            new Questions(R.string.question_asia, false)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setById();
        listener();
        updateQuestions();
        updateScore();
    }


    private void setById() {
        mButton_true = findViewById(R.id.btn_correct);
        mButton_false = findViewById(R.id.btn_in_correct);
        mSituationTrueButton = new boolean[mQuestionsBank.length];
        mSituationFulseButton = new boolean[mQuestionsBank.length];
        mTextQuestion = findViewById(R.id.text_question);
        mButton_next = (ImageButton) findViewById(R.id.btn_next);
        mButton_previous = (ImageButton) findViewById(R.id.btn_previous);
        mButton_first = (ImageButton) findViewById(R.id.btn_first);
        mButton_last = (ImageButton) findViewById(R.id.btn_last);
        mTextScore = findViewById(R.id.textView_score);
        mLinearLayout_game = findViewById(R.id.layout_game);
        mLinearLayout_game.setVisibility(View.VISIBLE);
        mLinearLayout_score = findViewById(R.id.layout_score);
        mLinearLayout_score.setVisibility(View.VISIBLE);
        mLinearLayout_over = findViewById(R.id.layout_over);
        mLinearLayout_over.setVisibility(View.GONE);
        mButton_reset = findViewById(R.id.btn_reset_game);
        mTextView_result = findViewById(R.id.textView_result_score);
        mCurrentIndex = 0;
        disableCounter = 0;
        counterScore = 0;
    }

    private void updateQuestions() {
        int questionTextId = mQuestionsBank[mCurrentIndex].getQuestionResId();
        mTextQuestion.setText(questionTextId);
        mTextQuestion.setTextColor(Color.BLACK);
    }

    private void updateScore() {
        mTextScore.setText(String.valueOf(counterScore));
    }

    private void listener() {
        mButton_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateScore();
            }
        });

        mButton_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateScore();

            }
        });

        mButton_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                if (mSituationTrueButton[mCurrentIndex])
                    mButton_true.setEnabled(false);
                else mButton_true.setEnabled(true);
                if (mSituationFulseButton[mCurrentIndex])
                    mButton_false.setEnabled(false);
                else mButton_false.setEnabled(true);
                updateQuestions();
                if (disableCounter == mQuestionsBank.length) {
                    if (mLinearLayout_game.getVisibility() == View.VISIBLE &&
                            mLinearLayout_over.getVisibility() == View.GONE) {
                        mLinearLayout_game.setVisibility(View.GONE);
                        mLinearLayout_score.setVisibility(View.GONE);
                        mLinearLayout_over.setVisibility(View.VISIBLE);
                        mTextView_result.setText(String.valueOf(counterScore));
                    }

                }
            }
        });

        mButton_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionsBank.length) % mQuestionsBank.length;
                if (mSituationTrueButton[mCurrentIndex])
                    mButton_true.setEnabled(false);
                else mButton_true.setEnabled(true);
                if (mSituationFulseButton[mCurrentIndex])
                    mButton_false.setEnabled(false);
                else mButton_false.setEnabled(true);
                updateQuestions();
                if (disableCounter == mQuestionsBank.length) {
                    if (mLinearLayout_game.getVisibility() == View.VISIBLE &&
                            mLinearLayout_over.getVisibility() == View.GONE) {
                        mLinearLayout_game.setVisibility(View.GONE);
                        mLinearLayout_score.setVisibility(View.GONE);
                        mLinearLayout_over.setVisibility(View.VISIBLE);
                        mTextView_result.setText(counterScore);
                    }

                }
            }
        });

        mButton_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = 0;
                updateQuestions();
                if (disableCounter == mQuestionsBank.length) {
                    if (mLinearLayout_game.getVisibility() == View.VISIBLE &&
                            mLinearLayout_over.getVisibility() == View.INVISIBLE) {
                        mLinearLayout_game.setVisibility(View.GONE);
                        mLinearLayout_score.setVisibility(View.GONE);
                        mLinearLayout_over.setVisibility(View.VISIBLE);
                        mTextView_result.setText(counterScore);

                    }
                }
            }
        });

        mButton_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = mQuestionsBank.length - 1;
                updateQuestions();
                if (disableCounter == mQuestionsBank.length) {
                    if (mLinearLayout_game.getVisibility() == View.VISIBLE &&
                            mLinearLayout_over.getVisibility() == View.INVISIBLE) {
                        mLinearLayout_game.setVisibility(View.GONE);
                        mLinearLayout_score.setVisibility(View.GONE);
                        mLinearLayout_over.setVisibility(View.VISIBLE);
                        mTextView_result.setText(counterScore);
                    }

                }
            }
        });
        mButton_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setById();
                mButton_false.setEnabled(true);
                mButton_true.setEnabled(true);
                mTextScore.setText("");
                mTextView_result.setText("");
            }
        });


    }

    private void checkAnswer(boolean userAnswer) {
        if (mQuestionsBank[mCurrentIndex].isAnswerTrueOrFalse() == userAnswer) {
            Toast toast = Toast.makeText(QuizActivity.this, R.string.message_correct,
                    Toast.LENGTH_LONG);
            toast.getView().setBackgroundColor(Color.GREEN);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                    0);
            toast.show();
            mTextQuestion.setTextColor(Color.GREEN);
            if (userAnswer == true) {
                mSituationTrueButton[mCurrentIndex] = true;
            } else {
                mSituationFulseButton[mCurrentIndex] = true;
            }
            counterScore++;
        } else {
            Toast toast = Toast.makeText(QuizActivity.this, R.string.message_in_correct,
                    Toast.LENGTH_LONG);
            toast.getView().setBackgroundColor(Color.RED);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                    0);
            toast.show();
            mTextQuestion.setTextColor(Color.RED);
            if (userAnswer == false) {
                mSituationTrueButton[mCurrentIndex] = true;
            } else {
                mSituationFulseButton[mCurrentIndex] = true;
            }
        }
        disableCounter++;

    }


}