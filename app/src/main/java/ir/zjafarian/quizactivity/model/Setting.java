package ir.zjafarian.quizactivity.model;

import java.io.Serializable;

import ir.zjafarian.quizactivity.controller.QuizActivity;

public class Setting implements Serializable {
    private SizeText sizeText;
    private ColorBackground colorBackground;
    private boolean[] settingButtonAnswer = new boolean[2];
    private boolean[] settingButtonArrows = new boolean[4];
    private boolean settingCheatButton = true;

    {
        for (int i = 0; i < 4; i++) {
            settingButtonArrows[i] = true;
        }
        for (int i = 0; i < 2; i++) {
            settingButtonAnswer[i] = true;
        }
    }


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

    public boolean[] getSettingButtonAnswer() {
        return settingButtonAnswer;
    }

    public void setSettingButtonAnswer(boolean[] settingButtonAnswer) {
        this.settingButtonAnswer = settingButtonAnswer;
    }

    public boolean[] getSettingButtonArrows() {
        return settingButtonArrows;
    }

    public void setSettingButtonArrows(boolean[] settingButtonArrows) {
        this.settingButtonArrows = settingButtonArrows;
    }

    public boolean isSettingCheatButton() {
        return settingCheatButton;
    }

    public void setSettingCheatButton(boolean settingCheatButton) {
        this.settingCheatButton = settingCheatButton;
    }

    public void setFieldSetting(boolean cheatButton, boolean[] buttonAnswer, boolean[]
            buttonArrows, String size, String color) {
        settingCheatButton = cheatButton;
        settingButtonAnswer = buttonAnswer;
        settingButtonArrows = buttonArrows;
        switch (color) {
            case "LightRed":
                colorBackground = ColorBackground.LightRed;
                break;
            case "LightBlue":
                colorBackground = ColorBackground.LightBlue;
                break;
            case "LightGreen":
                colorBackground = ColorBackground.LightGreen;
                break;
            case "White":
                colorBackground = ColorBackground.White;
                break;
            default:
                System.out.println("not found");
                break;
        }
        switch (size) {
            case "SMALL":
                sizeText = SizeText.SMALL;
                break;
            case "MEDIUM":
                sizeText = SizeText.MEDIUM;
                break;
            case "LARGE":
                sizeText = SizeText.LARGE;
                break;

            default:
                System.out.println("not found");
                break;
        }

    }
}
