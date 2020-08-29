package ir.zjafarian.quizactivity.model;

import java.io.Serializable;
import java.util.UUID;

public class Questions implements Serializable {
    private UUID mUUID;
    private int mQuestionResId;
    private boolean mIsAnswerTrueOrFalse;
    private boolean trueAnswer;
    private boolean falseAnswer;
    private boolean isUseCheat = false;
    private String mColor;
    public Questions() {
    }


    public Questions(int questionResId, boolean isAnswerTrueOrFalse, String color) {
        mUUID = UUID.randomUUID();
        mQuestionResId = questionResId;
        mIsAnswerTrueOrFalse = isAnswerTrueOrFalse;
        mColor = color;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public int getQuestionResId() {
        return mQuestionResId;
    }

    public void setQuestionResId(int questionResId) {
        mQuestionResId = questionResId;
    }

    public boolean isAnswerTrueOrFalse() {
        return mIsAnswerTrueOrFalse;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public boolean isFalseAnswer() {
        return falseAnswer;
    }

    public void setFalseAnswer(boolean falseAnswer) {
        this.falseAnswer = falseAnswer;
    }

    public boolean isUseCheat() {
        return isUseCheat;
    }

    public void setUseCheat(boolean useCheat) {
        isUseCheat = useCheat;
    }

    public void setAnswerTrueOrFalse(boolean answerTrueOrFalse) {
        mIsAnswerTrueOrFalse = answerTrueOrFalse;
    }
}
