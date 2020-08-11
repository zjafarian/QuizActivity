package ir.zjafarian.quizactivity.model;

import java.io.Serializable;

public class Questions implements Serializable {
    private int mQuestionResId;
    private boolean mIsAnswerTrueOrFalse;
    private boolean trueAnswer;
    private boolean falseAnswer;
    private boolean isUseCheat = false;

    public Questions() {
    }

    public Questions(int questionResId, boolean isAnswerTrueOrFalse) {
        mQuestionResId = questionResId;
        mIsAnswerTrueOrFalse = isAnswerTrueOrFalse;
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
