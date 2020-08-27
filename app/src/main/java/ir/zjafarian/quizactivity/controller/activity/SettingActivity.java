package ir.zjafarian.quizactivity.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.controller.fragment.QuizFragment;
import ir.zjafarian.quizactivity.model.ColorBackground;
import ir.zjafarian.quizactivity.model.Setting;
import ir.zjafarian.quizactivity.model.SizeText;

public class SettingActivity extends AppCompatActivity {

    public static final String EXTRA_GET_SETTING = "ir.zjafarian.quizactivity.put_setting";
    public static final String SAVE_SETTING = "save_setting";
    private Setting setting = new Setting();
    private Setting defaultSetting;
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
    private RadioGroup mGroupFont;
    private RadioGroup mGroupColor;

    private Bundle saveInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setId();
        if (savedInstanceState != null) {
            setting = (Setting) savedInstanceState.getSerializable(SAVE_SETTING);
        }
        setting = (Setting) getIntent().getSerializableExtra(QuizFragment.EXTRA_PUT_SETTING);
        setSettingFields();
        defaultSetting = setting;
        listener();
    }

    private void setSettingFields() {
        switch (setting.getSizeText()) {
            case SMALL:
                mRadioButtonSmall.setChecked(true);
                break;
            case MEDIUM:
                mRadioButtonMedium.setChecked(true);
                break;
            case LARGE:
                mRadioButtonLarge.setChecked(true);
                break;
            default:
                mRadioButtonMedium.setChecked(true);
                break;
        }
        switch (setting.getColorBackground()) {
            case LightRed:
                mRadioButtonLightRed.setChecked(true);
                break;
            case LightBlue:
                mRadioButtonLightBlue.setChecked(true);
                break;
            case LightGreen:
                mRadioButtonLightGreen.setChecked(true);
                break;
            case White:
                mRadioButtonWhite.setChecked(true);
                break;
            default:
                mRadioButtonWhite.setChecked(true);
                break;

        }
        aSwitchNext.setChecked(setting.isSettingButtonNext());
        aSwitchPrevious.setChecked(setting.isSettingButtonPrevious());
        aSwitchFirst.setChecked(setting.isSettingButtonFirst());
        aSwitchLast.setChecked(setting.isSettingButtonLast());
        aSwitchCheat.setChecked(setting.isSettingButtonCheat());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVE_SETTING, setting);
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
        aSwitchPrevious = findViewById(R.id.switch_btn_first);
        aSwitchFirst = findViewById(R.id.switch_btn_first);
        aSwitchLast = findViewById(R.id.switch_btn_last);
        aSwitchCheat = findViewById(R.id.switch_btn_cheat);
        mButtonSave = findViewById(R.id.btn_save);
        mButtonDiscard = findViewById(R.id.btn_discard);
        mGroupFont = findViewById(R.id.font_size_group);
        mGroupColor = findViewById(R.id.color_group);
    }

    private void listener() {
        mGroupFont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_btn_small:
                        setting.setSizeText(SizeText.SMALL);
                        break;
                    case R.id.rd_btn_medium:
                        setting.setSizeText(SizeText.MEDIUM);
                        break;
                    case R.id.rd_btn_large:
                        setting.setSizeText(SizeText.LARGE);
                        break;
                }
            }
        });

        mGroupColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_btn_light_red:
                        setting.setColorBackground(ColorBackground.LightRed);
                        break;
                    case R.id.rd_btn_light_blue:
                        setting.setColorBackground(ColorBackground.LightBlue);
                        break;
                    case R.id.rd_btn_light_green:
                        setting.setColorBackground(ColorBackground.LightGreen);
                        break;
                    case R.id.rd_btn_white:
                        setting.setColorBackground(ColorBackground.White);
                        break;
                }
            }
        });

        aSwitchNext.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setting.setSettingButtonNext(true);
                else setting.setSettingButtonNext(false);
            }
        });

        aSwitchTrueAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setting.setSettingButtonTrue(true);
                else setting.setSettingButtonTrue(false);
            }
        });

        aSwitchFalseAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setting.setSettingButtonFalse(true);
                else setting.setSettingButtonFalse(false);
            }
        });

        aSwitchPrevious.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setting.setSettingButtonPrevious(true);
                else setting.setSettingButtonPrevious(false);
            }
        });

        aSwitchFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setting.setSettingButtonFirst(true);
                else setting.setSettingButtonFirst(false);
            }
        });

        aSwitchLast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setting.setSettingButtonLast(true);
                else setting.setSettingButtonLast(false);
            }
        });

        aSwitchCheat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setting.setSettingButtonCheat(true);
                else setting.setSettingButtonCheat(false);
            }
        });

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_GET_SETTING, setting);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mButtonDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting = defaultSetting;
                finish();
            }
        });
    }


}

