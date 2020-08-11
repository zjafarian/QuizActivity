package ir.zjafarian.quizactivity.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.model.Questions;


public class QuizActivity extends AppCompatActivity {
    public static final String TAG = "QuizActivity";
    public static final String CURRENT_INDEX = "currentIndex";
    public static final String COUNTER_SCORE = "counterScore";
    public static final String COUNTER_ANSWER = "AnswerCounter";
    private Button mButton_true;
    private Button mButton_false;
    private TextView mTextQuestion;
    private ImageButton mButton_next;
    private ImageButton mButton_previous;
    private ImageButton mButton_first;
    private ImageButton mButton_last;
    private ImageButton mButton_score;
    private int mCurrentIndex = 0;
    private int counterAnswer;
    private int counterScore;
    private TextView mTextScore;
    private ViewGroup mLinearLayout_game;
    private ViewGroup mLinearLayout_over;
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
        Log.d(TAG, "onCreate: " + mCurrentIndex);
        setContentView(R.layout.activity_quiz);
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState: " + mCurrentIndex);
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
            counterAnswer = savedInstanceState.getInt(COUNTER_ANSWER, 0);
            counterScore = savedInstanceState.getInt(COUNTER_SCORE, 0);
            availableLayout();
        } else
            Log.d(TAG, "savedInstanceState is null");

        deserializeDataPerQuestion();
        setById();
        listener();
        updateQuestions();
        updateScore();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + mCurrentIndex);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + mCurrentIndex);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + mCurrentIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + mCurrentIndex);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: " + mCurrentIndex);
        outState.putInt(CURRENT_INDEX, mCurrentIndex);
        outState.putInt(COUNTER_ANSWER, counterAnswer);
        outState.putInt(COUNTER_SCORE, counterScore);
    }

    private void setById() {
        mButton_true = findViewById(R.id.btn_correct);
        mButton_false = findViewById(R.id.btn_in_correct);
        mTextQuestion = findViewById(R.id.text_question);
        mButton_next = (ImageButton) findViewById(R.id.btn_next);
        mButton_previous = (ImageButton) findViewById(R.id.btn_previous);
        mButton_first = (ImageButton) findViewById(R.id.btn_first);
        mButton_last = (ImageButton) findViewById(R.id.btn_last);
        mButton_score = (ImageButton) findViewById(R.id.btn_score);
        mTextScore = findViewById(R.id.textView_score);
        mLinearLayout_game = findViewById(R.id.layout_game);
        mLinearLayout_game.setVisibility(View.VISIBLE);
        mLinearLayout_over = findViewById(R.id.layout_over);
        mLinearLayout_over.setVisibility(View.GONE);
        mButton_reset = findViewById(R.id.btn_reset_game);
        mTextView_result = findViewById(R.id.textView_result_score);
        mCurrentIndex = 0;
        counterAnswer = 0;
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
                setSituationTrueAndFalseButton();
                updateQuestions();
                availableLayout();

            }
        });

        mButton_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionsBank.length) % mQuestionsBank.length;
                setSituationTrueAndFalseButton();
                updateQuestions();
                availableLayout();

            }
        });

        mButton_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = 0;
                setSituationTrueAndFalseButton();
                updateQuestions();
                availableLayout();
            }
        });

        mButton_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = mQuestionsBank.length - 1;
                setSituationTrueAndFalseButton();
                updateQuestions();
                availableLayout();
            }
        });
        mButton_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setById();
                updateQuestions();
                mTextScore.setVisibility(View.VISIBLE);
                mTextQuestion.setVisibility(View.VISIBLE);
                mButton_score.setVisibility(View.VISIBLE);
                mButton_false.setEnabled(true);
                mButton_true.setEnabled(true);
                mTextScore.setText("");
                mTextView_result.setText("");
            }
        });


    }

    private void setSituationTrueAndFalseButton() {
        deserializeDataPerQuestion();
        if (mQuestionsBank[mCurrentIndex].isTrueAnswer())
            mButton_true.setEnabled(false);
        else mButton_true.setEnabled(true);
        if (mQuestionsBank[mCurrentIndex].isFalseAnswer())
            mButton_false.setEnabled(false);
        else mButton_false.setEnabled(true);
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
                mQuestionsBank[mCurrentIndex].setTrueAnswer(true);
            } else {
                mQuestionsBank[mCurrentIndex].setFalseAnswer(true);
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
                mQuestionsBank[mCurrentIndex].setTrueAnswer(true);
            } else {
                mQuestionsBank[mCurrentIndex].setFalseAnswer(true);
            }
        }
        counterAnswer++;
        serializeDataPerQuestion();
    }

    private void availableLayout() {
        if (counterAnswer == mQuestionsBank.length) {
            if (mLinearLayout_game.getVisibility() == View.VISIBLE &&
                    mLinearLayout_over.getVisibility() == View.GONE) {
                mLinearLayout_game.setVisibility(View.GONE);
                mLinearLayout_over.setVisibility(View.VISIBLE);
                mButton_score.setVisibility(View.GONE);
                mTextScore.setVisibility(View.GONE);
                mTextQuestion.setVisibility(View.GONE);
                mTextView_result.setText(String.valueOf(counterScore));
            }
        }
    }

    private void serializeDataPerQuestion() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("QuestionSerialize");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(mQuestionsBank[mCurrentIndex]);
            outputStream.close();
            fileOutputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    private void deserializeDataPerQuestion() {
        try {
            FileInputStream fileInputStream = new FileInputStream("QuestionSerialize");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            mQuestionsBank[mCurrentIndex] = (Questions) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;

        }
    }


}