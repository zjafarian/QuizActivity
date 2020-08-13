package ir.zjafarian.quizactivity.controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.model.Questions;
import ir.zjafarian.quizactivity.model.Setting;


public class QuizActivity extends AppCompatActivity {
    public static final String TAG = "QuizActivity";
    public static final String CURRENT_INDEX = "currentIndex";
    public static final String COUNTER_SCORE = "counterScore";
    public static final String COUNTER_ANSWER = "AnswerCounter";
    public static final String EXTRA_QUESTION_ANSWER = "ir.zjafarian.quizactivity.questionAnswer";
    public static final int REQUEST_CODE_CHEAT = 0;
    public static final String SERIALIZABLE_BANK_QUESTION = "serializableBankQuestion";
    public static final int REQUEST_CODE_SETTING = 1;
    public static final String SERIALIZABLE_SETTING = "SerializableSetting";
    public static final String EXTRA_PUT_SETTING = "put_setting";
    private Button mButton_true;
    private Button mButton_false;
    private TextView mTextQuestion;
    private ImageButton mButton_next;
    private ImageButton mButton_previous;
    private ImageButton mButton_first;
    private ImageButton mButton_last;
    private ImageButton mButton_score;
    private Button mButton_cheat;
    private Button mButton_setting;
    private int mCurrentIndex = 0;
    private int counterAnswer;
    private int counterScore;
    private TextView mTextScore;
    private ViewGroup mLayout_main;
    private ViewGroup mLinearLayout_game;
    private ViewGroup mLinearLayout_over;
    private ImageButton mButton_reset;
    private TextView mTextView_result;
    private Setting setting = new Setting();
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
        setById();
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState: " + mCurrentIndex);
            if (savedInstanceState.containsKey(SERIALIZABLE_BANK_QUESTION)) {
                mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
                counterAnswer = savedInstanceState.getInt(COUNTER_ANSWER, 0);
                counterScore = savedInstanceState.getInt(COUNTER_SCORE, 0);
                mQuestionsBank = (Questions[]) savedInstanceState.getSerializable(SERIALIZABLE_BANK_QUESTION);
                availableLayout();
                setSituationTrueAndFalseButton();
            } else if (savedInstanceState.containsKey(SERIALIZABLE_SETTING)) {
                setting = (Setting) savedInstanceState.getSerializable(SERIALIZABLE_SETTING);
            }
        } else
            Log.d(TAG, "savedInstanceState is null");


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
        outState.putSerializable(SERIALIZABLE_BANK_QUESTION, mQuestionsBank);
        outState.putSerializable(SERIALIZABLE_SETTING, setting);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_CHEAT) {
            boolean check = data.getBooleanExtra(CheatActivity.EXTERA_IS_CHEAT, false);
            mQuestionsBank[mCurrentIndex].setUseCheat(check);
        } else if (requestCode == REQUEST_CODE_SETTING) {
            setting = (Setting) data.getSerializableExtra(SettingActivity.EXTRA_GET_SETTING);
            changeSizeTexts();
            changeColorBackground();
            changeHideAndShowButtons();
        }

    }

    private void changeHideAndShowButtons() {
        mButton_true.setVisibility(setting.isSettingButtonTrue() ? View.VISIBLE : View.GONE);
        mButton_false.setVisibility(setting.isSettingButtonFalse() ? View.VISIBLE : View.GONE);
        mButton_next.setVisibility(setting.isSettingButtonNext() ? View.VISIBLE : View.GONE);
        mButton_previous.setVisibility(setting.isSettingButtonPrevious() ? View.VISIBLE : View.GONE);
        mButton_first.setVisibility(setting.isSettingButtonFirst() ? View.VISIBLE : View.GONE);
        mButton_last.setVisibility(setting.isSettingButtonLast() ? View.VISIBLE : View.GONE);
        mButton_cheat.setVisibility(setting.isSettingButtonCheat() ? View.VISIBLE : View.GONE);
    }

    private void changeColorBackground() {
        switch (setting.getColorBackground()) {
            case LightRed:
                mLayout_main.setBackgroundColor(Color.RED);
                break;
            case LightBlue:
                mLayout_main.setBackgroundColor(Color.BLUE);
                break;
            case LightGreen:
                mLayout_main.setBackgroundColor(Color.GREEN);
                break;
            case White:
                mLayout_main.setBackgroundColor(Color.WHITE);
                break;
            default:
                System.out.println("not found");
                break;
        }
    }

    private void changeSizeTexts() {
        switch (setting.getSizeText()) {
            case SMALL:
                mTextQuestion.setTextSize(14);
                mButton_true.setTextSize(14);
                mButton_false.setTextSize(14);
                mButton_cheat.setTextSize(14);
                mTextScore.setTextSize(14);
                mButton_setting.setTextSize(14);
                break;
            case MEDIUM:
                mTextQuestion.setTextSize(18);
                mButton_true.setTextSize(18);
                mButton_false.setTextSize(18);
                mButton_cheat.setTextSize(18);
                mTextScore.setTextSize(18);
                mButton_setting.setTextSize(18);
                break;
            case LARGE:
                mTextQuestion.setTextSize(26);
                mButton_true.setTextSize(26);
                mButton_false.setTextSize(26);
                mButton_cheat.setTextSize(26);
                mTextScore.setTextSize(26);
                mButton_setting.setTextSize(26);
            default:
                System.out.println("not found");
                break;
        }
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
        mButton_cheat = findViewById(R.id.btn_cheat);
        mButton_setting = findViewById(R.id.btn_setting);
        mLayout_main = findViewById(R.id.layout_main);
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
                mButton_cheat.setVisibility(View.VISIBLE);
                mButton_setting.setVisibility(View.VISIBLE);
                mButton_false.setEnabled(true);
                mButton_true.setEnabled(true);
                mTextScore.setText("");
                mTextView_result.setText("");
            }
        });

        mButton_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(EXTRA_QUESTION_ANSWER, mQuestionsBank[mCurrentIndex].
                        isAnswerTrueOrFalse());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        mButton_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, SettingActivity.class);
                intent.putExtra(EXTRA_PUT_SETTING, setting);
                startActivityForResult(intent, REQUEST_CODE_SETTING);
            }
        });


    }

    private void setSituationTrueAndFalseButton() {
        //serializeDataPerQuestion();
        if (mQuestionsBank[mCurrentIndex].isTrueAnswer())
            mButton_true.setEnabled(false);
        else mButton_true.setEnabled(true);
        if (mQuestionsBank[mCurrentIndex].isFalseAnswer())
            mButton_false.setEnabled(false);
        else mButton_false.setEnabled(true);
    }

    private void checkAnswer(boolean userAnswer) {
        if (mQuestionsBank[mCurrentIndex].isAnswerTrueOrFalse() == userAnswer) {
            if (mQuestionsBank[mCurrentIndex].isUseCheat()) {
                Toast toast = Toast.makeText(this, R.string.message_cheat,
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
                toast.show();

            } else {
                Toast toast = Toast.makeText(this, R.string.message_correct,
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
            }

        } else {
            if (mQuestionsBank[mCurrentIndex].isUseCheat()) {
                Toast toast = Toast.makeText(this, R.string.message_cheat,
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(this, R.string.message_in_correct,
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
        }
        counterAnswer++;
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
                mButton_cheat.setVisibility(View.GONE);
                mButton_setting.setVisibility(View.GONE);
                mTextView_result.setText(String.valueOf(counterScore));
            }
        }
    }


/*

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

*/

}