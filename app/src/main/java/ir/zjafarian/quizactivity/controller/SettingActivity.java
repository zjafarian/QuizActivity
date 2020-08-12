package ir.zjafarian.quizactivity.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.model.ColorBackground;
import ir.zjafarian.quizactivity.model.Setting;
import ir.zjafarian.quizactivity.model.SizeText;

public class SettingActivity extends AppCompatActivity {
    public static final String SERIALIZABLE_SETTING = "serializableSetting";
    public static final String EXTERA_ANSWER = "ir.zjafarian.quizactivity.answer";
    public static final String EXTRA_ARROW = "ir.zjafarian.quizactivity.arrow";
    public static final String EXTRA_CHEAT = "ir.zjafarian.quizactivity.cheat";
    public static final String EXTRA_SIZE = "ir.zjafarian.quizactivity.size";
    public static final String EXTRA_COLOR = "ir.zjafarian.quizactivity.color";
    public static final String SITUATION_BUTTON_SIZE_SMALL = "situationButtonSizeSmall";
    public static final String SITUATION_BUTTON_SIZE_MEDIUM = "situationButtonSizeMedium";
    public static final String SITUATION_BUTTON_SIZE_LARGE = "situationButtonSizeLarge";
    public static final String SITUATION_BUTTON_COLOR_RED = "situationButtonColorRed";
    public static final String SITUATION_BUTTON_COLOR_BLUE = "situationButtonColorBlue";
    public static final String SITUATION_BUTTON_COLOR_GREEN = "situationButtonColorGreen";
    public static final String SITUATION_BUTTON_COLOR_WHITE = "situationButtonColorWhite";
    public static final String SITUATION_SWITCH_NEXT = "situationSwitchNext";
    public static final String SITUATION_SWITCH_PREVIOUS = "situationSwitchPrevious";
    public static final String SITUATION_SWITCH_FIRST = "situationSwitchFirst";
    public static final String SITUATION_SWITCH_LAST = "situationSwitchLast";
    public static final String SITUATION_SWITCH_CHEAT = "situationSwitchCheat";
    public static final String EXTERA_IS_SAVE = "isSave";
    private Setting setting;
    private RadioButton mRadioButtonSmall;
    private RadioButton mRadioButtonMedium;
    private RadioButton mRadioButtonLarge;
    private RadioButton mRadioButtonLightRed;
    private RadioButton mRadioButtonLightBlue;
    private RadioButton mRadioButtonLightGreen;
    private RadioButton mRadioButtonWhite;
    private Switch aSwitchTrueAnswer;
    private Switch aSwitchFalseAnswer;
    private Switch aSwitchNext;
    private Switch aSwitchPrevious;
    private Switch aSwitchFirst;
    private Switch aSwitchLast;
    private Switch aSwitchCheat;
    private Button mButtonSave;
    private Button mButtonDiscard;
    private boolean[] answer = new boolean[2];
    private boolean[] arrows = new boolean[4];
    private boolean cheat = true;
    private String size = " ";
    private String color = " ";
    private Bundle saveInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setId();
        if (savedInstanceState != null) {
            setting = (Setting) savedInstanceState.getSerializable(QuizActivity.SERIALIZABLE_SETTING);
            saveInstance = savedInstanceState;
            setSavingResult(true);
            setCheckes(savedInstanceState);
        }
        listener();
    }

    private void setCheckes(Bundle savedInstanceState) {
        mRadioButtonSmall.setChecked(savedInstanceState.getBoolean(SITUATION_BUTTON_SIZE_SMALL,
                false));
        mRadioButtonMedium.setChecked(savedInstanceState.getBoolean(SITUATION_BUTTON_SIZE_MEDIUM,
                false));
        mRadioButtonLarge.setChecked(savedInstanceState.getBoolean(SITUATION_BUTTON_SIZE_LARGE,
                false));
        mRadioButtonLightRed.setChecked(savedInstanceState.getBoolean(SITUATION_BUTTON_COLOR_RED,
                false));
        mRadioButtonLightBlue.setChecked(savedInstanceState.getBoolean(SITUATION_BUTTON_COLOR_BLUE,
                false));
        mRadioButtonLightGreen.setChecked(savedInstanceState.getBoolean(SITUATION_BUTTON_COLOR_GREEN,
                false));
        mRadioButtonWhite.setChecked(savedInstanceState.getBoolean(SITUATION_BUTTON_COLOR_WHITE,
                false));
        aSwitchNext.setChecked(savedInstanceState.getBoolean(SITUATION_SWITCH_NEXT,
                false));
        aSwitchPrevious.setChecked(savedInstanceState.getBoolean(SITUATION_SWITCH_PREVIOUS,
                false));
        aSwitchFirst.setChecked(savedInstanceState.getBoolean(SITUATION_SWITCH_FIRST,
                false));
        aSwitchLast.setChecked(savedInstanceState.getBoolean(SITUATION_SWITCH_LAST,
                false));
        aSwitchCheat.setChecked(savedInstanceState.getBoolean(SITUATION_SWITCH_CHEAT,
                false));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SITUATION_BUTTON_SIZE_SMALL, mRadioButtonSmall.isChecked());
        outState.putBoolean(SITUATION_BUTTON_SIZE_MEDIUM, mRadioButtonMedium.isChecked());
        outState.putBoolean(SITUATION_BUTTON_SIZE_LARGE, mRadioButtonLarge.isChecked());
        outState.putBoolean(SITUATION_BUTTON_COLOR_RED, mRadioButtonLightRed.isChecked());
        outState.putBoolean(SITUATION_BUTTON_COLOR_BLUE, mRadioButtonLightBlue.isChecked());
        outState.putBoolean(SITUATION_BUTTON_COLOR_GREEN, mRadioButtonLightGreen.isChecked());
        outState.putBoolean(SITUATION_BUTTON_COLOR_WHITE, mRadioButtonWhite.isChecked());
        outState.putBoolean(SITUATION_SWITCH_NEXT, aSwitchNext.isChecked());
        outState.putBoolean(SITUATION_SWITCH_PREVIOUS, aSwitchPrevious.isChecked());
        outState.putBoolean(SITUATION_SWITCH_FIRST, aSwitchFirst.isChecked());
        outState.putBoolean(SITUATION_SWITCH_LAST, aSwitchLast.isChecked());
        outState.putBoolean(SITUATION_SWITCH_CHEAT, aSwitchCheat.isChecked());
    }

    private void listener() {
        mRadioButtonSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonSmall.setChecked(true);
                size = "SMALL";
                setting.setSizeText(SizeText.SMALL);
            }
        });

        mRadioButtonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonMedium.setChecked(true);
                size = "MEDIUM";
                setting.setSizeText(SizeText.MEDIUM);
            }
        });

        mRadioButtonLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonLarge.setChecked(true);
                size = "LARGE";
                setting.setSizeText(SizeText.LARGE);

            }
        });

        mRadioButtonLightRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonLightRed.setChecked(true);
                color = "LightRed";
                setting.setColorBackground(ColorBackground.LightRed);

            }
        });

        mRadioButtonLightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonLightBlue.setChecked(true);
                color = "LightBlue";
                setting.setColorBackground(ColorBackground.LightBlue);
            }
        });

        mRadioButtonLightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonLightGreen.setChecked(true);
                color = "LightGreen";
                setting.setColorBackground(ColorBackground.LightGreen);
            }
        });

        mRadioButtonWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioButtonWhite.setChecked(true);
                color = "White";
                setting.setColorBackground(ColorBackground.White);
            }
        });

        aSwitchTrueAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitchTrueAnswer.setChecked(true);
                    answer[0] = true;
                    setting.setSettingButtonAnswer(answer);
                } else {
                    aSwitchTrueAnswer.setChecked(false);
                    answer[0] = false;
                    setting.setSettingButtonAnswer(answer);
                }
            }
        });

        aSwitchFalseAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitchFalseAnswer.setChecked(true);
                    answer[1] = true;
                    setting.setSettingButtonAnswer(answer);
                } else {
                    aSwitchFalseAnswer.setChecked(false);
                    answer[1] = false;
                    setting.setSettingButtonAnswer(answer);
                }

            }
        });

        aSwitchNext.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitchNext.setChecked(true);
                    arrows[0] = true;
                    setting.setSettingButtonArrows(arrows);
                } else {
                    aSwitchNext.setChecked(false);
                    arrows[0] = false;
                    setting.setSettingButtonArrows(arrows);
                }

            }
        });

        aSwitchPrevious.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitchPrevious.setChecked(true);
                    arrows[1] = true;
                    setting.setSettingButtonArrows(arrows);
                } else {
                    aSwitchPrevious.setChecked(false);
                    arrows[1] = false;
                    setting.setSettingButtonArrows(arrows);
                }

            }
        });

        aSwitchFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitchFirst.setChecked(true);
                    arrows[2] = true;
                    setting.setSettingButtonArrows(arrows);
                } else {
                    aSwitchFirst.setChecked(false);
                    arrows[2] = false;
                    setting.setSettingButtonArrows(arrows);
                }

            }
        });

        aSwitchLast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitchLast.setChecked(true);
                    arrows[3] = true;
                    setting.setSettingButtonArrows(arrows);
                } else {
                    aSwitchLast.setChecked(false);
                    arrows[3] = false;
                    setting.setSettingButtonArrows(arrows);
                }

            }
        });

        aSwitchCheat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aSwitchCheat.setChecked(true);
                    cheat = true;
                    setting.setSettingCheatButton(cheat);
                } else {
                    aSwitchPrevious.setChecked(false);
                    cheat = false;
                    setting.setSettingCheatButton(cheat);
                }

            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSavingResult(true);
            }
        });

        mButtonDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckes(saveInstance);
                Intent intent = new Intent();

            }
        });
    }

    private void setSavingResult(boolean isSave) {
        Intent intent = new Intent();
        intent.putExtra(EXTERA_IS_SAVE, isSave);
        setResult(RESULT_OK, intent);
    }


    private void setId() {
        mRadioButtonSmall = findViewById(R.id.rd_btn_small);
        mRadioButtonMedium = findViewById(R.id.rd_btn_medium);
        mRadioButtonLarge = findViewById(R.id.rd_btn_large);
        mRadioButtonLightRed = findViewById(R.id.rd_btn_light_red);
        mRadioButtonLightBlue = findViewById(R.id.rd_btn_light_blue);
        mRadioButtonLightGreen = findViewById(R.id.rd_btn_light_green);
        mRadioButtonWhite = findViewById(R.id.rd_btn_white);
        aSwitchTrueAnswer = findViewById(R.id.switch_true_btn);
        aSwitchFalseAnswer = findViewById(R.id.switch_false_btn);
        aSwitchNext = findViewById(R.id.switch_btn_next);
        aSwitchPrevious = findViewById(R.id.switch_btn_previous);
        aSwitchFirst = findViewById(R.id.switch_btn_first);
        aSwitchLast = findViewById(R.id.switch_btn_last);
        aSwitchCheat = findViewById(R.id.switch_btn_cheat);
        mButtonSave = findViewById(R.id.btn_save);
        mButtonDiscard = findViewById(R.id.btn_discard);
    }
}