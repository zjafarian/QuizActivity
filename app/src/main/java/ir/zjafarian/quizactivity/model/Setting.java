package ir.zjafarian.quizactivity.model;

import java.io.Serializable;

import ir.zjafarian.quizactivity.controller.QuizActivity;

public class Setting implements Serializable {
    private SizeText sizeText = SizeText.MEDIUM;
    private ColorBackground colorBackground = ColorBackground.White;
    private boolean settingButtonTrue = true;
    private boolean settingButtonFalse = true;
    private boolean settingButtonNext = true;
    private boolean settingButtonPrevious = true;
    private boolean settingButtonFirst = true;
    private boolean settingButtonLast = true;
    private boolean settingButtonCheat = true;

    public SizeText getSizeText() {
        return sizeText;
    }

    public void setSizeText(SizeText sizeText) {
        this.sizeText = sizeText;
    }

    public ColorBackground getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(ColorBackground colorBackground) {
        this.colorBackground = colorBackground;
    }

    public boolean isSettingButtonTrue() {
        return settingButtonTrue;
    }

    public void setSettingButtonTrue(boolean settingButtonTrue) {
        this.settingButtonTrue = settingButtonTrue;
    }

    public boolean isSettingButtonFalse() {
        return settingButtonFalse;
    }

    public void setSettingButtonFalse(boolean settingButtonFalse) {
        this.settingButtonFalse = settingButtonFalse;
    }

    public boolean isSettingButtonNext() {
        return settingButtonNext;
    }

    public void setSettingButtonNext(boolean settingButtonNext) {
        this.settingButtonNext = settingButtonNext;
    }

    public boolean isSettingButtonPrevious() {
        return settingButtonPrevious;
    }

    public void setSettingButtonPrevious(boolean settingButtonPrevious) {
        this.settingButtonPrevious = settingButtonPrevious;
    }

    public boolean isSettingButtonFirst() {
        return settingButtonFirst;
    }

    public void setSettingButtonFirst(boolean settingButtonFirst) {
        this.settingButtonFirst = settingButtonFirst;
    }

    public boolean isSettingButtonLast() {
        return settingButtonLast;
    }

    public void setSettingButtonLast(boolean settingButtonLast) {
        this.settingButtonLast = settingButtonLast;
    }

    public boolean isSettingButtonCheat() {
        return settingButtonCheat;
    }

    public void setSettingButtonCheat(boolean settingButtonCheat) {
        this.settingButtonCheat = settingButtonCheat;
    }
}
