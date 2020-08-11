package ir.zjafarian.quizactivity.model;

public class Questions implements java.io.Serializable {
    private int mQuestionResId;
    private boolean mIsAnswerTrueOrFalse;
    private boolean trueAnswer;
    private boolean falseAnswer;

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

    public void setAnswerTrueOrFalse(boolean answerTrueOrFalse) {
        mIsAnswerTrueOrFalse = answerTrueOrFalse;
    }
}
