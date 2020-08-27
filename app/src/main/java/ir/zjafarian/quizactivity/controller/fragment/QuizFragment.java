package ir.zjafarian.quizactivity.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.UUID;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.controller.activity.CheatActivity;
import ir.zjafarian.quizactivity.controller.activity.SettingActivity;
import ir.zjafarian.quizactivity.model.Questions;
import ir.zjafarian.quizactivity.model.Setting;
import ir.zjafarian.quizactivity.repository.QuestionsRepository;

import static ir.zjafarian.quizactivity.controller.fragment.CheatFragment.EXTERA_IS_CHEAT;


public class QuizFragment extends Fragment {

    public static final String TAG = "QuizActivityFragment";
    public static final String CURRENT_INDEX = "currentIndex";
    public static final String COUNTER_SCORE = "counterScore";
    public static final String COUNTER_ANSWER = "AnswerCounter";
    public static final String EXTRA_QUESTION_ANSWER = "ir.zjafarian.quizactivity.questionAnswer";
    public static final int REQUEST_CODE_CHEAT = 0;
    public static final String SERIALIZABLE_BANK_QUESTION = "serializableBankQuestion";
    public static final int REQUEST_CODE_SETTING = 1;
    public static final String SERIALIZABLE_SETTING = "SerializableSetting";
    public static final String EXTRA_PUT_SETTING = "put_setting";
    public static final String CHECK_SETTING = "check_setting";
    public static final String SAVE_UI_ID = "save_UIId";
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
    private int mCurrentIndex;
    private int counterAnswer;
    private int counterScore;
    private TextView mTextScore;
    private ViewGroup mLayout_main;
    private ViewGroup mLinearLayout_game;
    private ViewGroup mLinearLayout_over;
    private ImageButton mButton_reset;
    private TextView mTextView_result;
    private Setting setting;
    private boolean checkSetting;
    private UUID mUUIDQuestion;
    private List<Questions> mQuestionsBunkList;
    private QuestionsRepository mQuestionsRepositoryActivity;


    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionsRepositoryActivity = QuestionsRepository.getInstance();
        mQuestionsBunkList = mQuestionsRepositoryActivity.getQuestions();
        setting = new Setting();
        checkSetting = false;
        mUUIDQuestion = (UUID) getActivity().getIntent().getSerializableExtra(QuestionListFragment.EXTRA_GET_QUESTION_ID);
        mCurrentIndex = 0;
        counterAnswer = 0;
        counterScore = 0;
        setQuestion();
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState: " + mCurrentIndex);
            if (savedInstanceState.containsKey(SERIALIZABLE_BANK_QUESTION)) {
                mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
                counterAnswer = savedInstanceState.getInt(COUNTER_ANSWER, 0);
                counterScore = savedInstanceState.getInt(COUNTER_SCORE, 0);
                mUUIDQuestion = (UUID) savedInstanceState.getSerializable(SAVE_UI_ID);
                setQuestion();
            }
            if (savedInstanceState.containsKey(SERIALIZABLE_SETTING)) {
                setting = (Setting) savedInstanceState.getSerializable(SERIALIZABLE_SETTING);
                checkSetting = savedInstanceState.getBoolean(CHECK_SETTING);
            }
        } else
            Log.d(TAG, "savedInstanceState is null");

    }

    private void setQuestion() {
        for (int i = 0; i < mQuestionsBunkList.size(); i++) {
            if (mQuestionsBunkList.get(i).getUUID().equals(mUUIDQuestion)) {
                mCurrentIndex = i;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        setById(view);
        if (savedInstanceState != null) {
            Log.d(TAG, "savedInstanceState: " + mCurrentIndex);
            if (savedInstanceState.containsKey(SERIALIZABLE_BANK_QUESTION)) {
                availableLayout();
                setSituationTrueAndFalseButton();
            }
            if (savedInstanceState.containsKey(SERIALIZABLE_SETTING)) {
                if (checkSetting) {
                    changeSizeTexts();
                    changeColorBackground();
                    changeHideAndShowButtons();
                }
            }
        }
        listener();
        updateQuestions();
        setQuestion();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + mCurrentIndex);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + mCurrentIndex);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + mCurrentIndex);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + mCurrentIndex);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: " + mCurrentIndex);
        outState.putInt(CURRENT_INDEX, mCurrentIndex);
        outState.putInt(COUNTER_ANSWER, counterAnswer);
        outState.putInt(COUNTER_SCORE, counterScore);
        outState.putBoolean(CHECK_SETTING, checkSetting);
        outState.putSerializable(SERIALIZABLE_SETTING, setting);
        outState.putSerializable(SAVE_UI_ID,mUUIDQuestion);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_CHEAT) {
            boolean check = data.getBooleanExtra(EXTERA_IS_CHEAT, false);
            mQuestionsBunkList.get(mCurrentIndex).setUseCheat(check);
        } else if (requestCode == REQUEST_CODE_SETTING) {
            checkSetting = true;
            setting = (Setting) data.getSerializableExtra(SettingActivity.EXTRA_GET_SETTING);
            changeSizeTexts();
            changeColorBackground();
            changeHideAndShowButtons();
        }

    }

    private void setById(View view) {
        mButton_true = view.findViewById(R.id.btn_correct);
        mButton_false = view.findViewById(R.id.btn_in_correct);
        mTextQuestion = view.findViewById(R.id.text_question);
        mButton_next = (ImageButton) view.findViewById(R.id.btn_next);
        mButton_previous = (ImageButton) view.findViewById(R.id.btn_previous);
        mButton_first = (ImageButton) view.findViewById(R.id.btn_first);
        mButton_last = (ImageButton) view.findViewById(R.id.btn_last);
        mButton_score = (ImageButton) view.findViewById(R.id.btn_score);
        mTextScore = view.findViewById(R.id.textView_score);
        mLinearLayout_game = view.findViewById(R.id.layout_game);
        mLinearLayout_game.setVisibility(View.VISIBLE);
        mLinearLayout_over = view.findViewById(R.id.layout_over);
        mLinearLayout_over.setVisibility(View.GONE);
        mButton_reset = view.findViewById(R.id.btn_reset_game);
        mTextView_result = view.findViewById(R.id.textView_result_score);
        mButton_cheat = view.findViewById(R.id.btn_cheat);
        mButton_setting = view.findViewById(R.id.btn_setting);
        mLayout_main = view.findViewById(R.id.layout_main);
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

    private void updateQuestions() {
        int questionTextId = mQuestionsBunkList.get(mCurrentIndex).getQuestionResId();
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

                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBunkList.size();
                setSituationTrueAndFalseButton();
                updateQuestions();
                availableLayout();

            }
        });

        mButton_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionsBunkList.size()) % mQuestionsBunkList.size();
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
                mCurrentIndex = mQuestionsBunkList.size() - 1;
                setSituationTrueAndFalseButton();
                updateQuestions();
                availableLayout();
            }
        });
        mButton_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                mCurrentIndex = 0;
                counterScore = 0;
                counterAnswer = 0;
            }
        });

        mButton_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheatActivity.class);
                intent.putExtra(EXTRA_QUESTION_ANSWER, mQuestionsBunkList.get(mCurrentIndex).isAnswerTrueOrFalse());
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        mButton_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.putExtra(EXTRA_PUT_SETTING, setting);
                startActivityForResult(intent, REQUEST_CODE_SETTING);
            }
        });


    }

    private void setSituationTrueAndFalseButton() {
        //serializeDataPerQuestion();
        if (mQuestionsBunkList.get(mCurrentIndex).isTrueAnswer())
            mButton_true.setEnabled(false);
        else mButton_true.setEnabled(true);
        if (mQuestionsBunkList.get(mCurrentIndex).isFalseAnswer())
            mButton_false.setEnabled(false);
        else mButton_false.setEnabled(true);
    }

    private void checkAnswer(boolean userAnswer) {
        if (mQuestionsBunkList.get(mCurrentIndex).isAnswerTrueOrFalse() == userAnswer) {

            if (mQuestionsBunkList.get(mCurrentIndex).isUseCheat()) {
                Toast toast = Toast.makeText(getActivity(), R.string.message_cheat,
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
                toast.show();

            } else {
                Toast toast = Toast.makeText(getActivity(), R.string.message_correct,
                        Toast.LENGTH_LONG);
                toast.getView().setBackgroundColor(Color.GREEN);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
                toast.show();
                mTextQuestion.setTextColor(Color.GREEN);
                if (userAnswer == true) {
                    mQuestionsBunkList.get(mCurrentIndex).setTrueAnswer(true);
                } else {
                    mQuestionsBunkList.get(mCurrentIndex).setFalseAnswer(true);
                }
                counterScore++;
            }

        } else {
            if (mQuestionsBunkList.get(mCurrentIndex).isUseCheat()) {
                Toast toast = Toast.makeText(getActivity(), R.string.message_cheat,
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getActivity(), R.string.message_in_correct,
                        Toast.LENGTH_LONG);
                toast.getView().setBackgroundColor(Color.RED);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        0);
                toast.show();
                mTextQuestion.setTextColor(Color.RED);
                if (userAnswer == false) {
                    mQuestionsBunkList.get(mCurrentIndex).setTrueAnswer(true);
                } else {
                    mQuestionsBunkList.get(mCurrentIndex).setFalseAnswer(true);
                }
            }
        }
        counterAnswer++;
    }

    private void availableLayout() {
        if (counterAnswer == mQuestionsBunkList.size()) {
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


}