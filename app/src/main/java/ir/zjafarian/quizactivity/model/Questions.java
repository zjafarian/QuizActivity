package ir.zjafarian.quizactivity.model;

public class Questions {
    private int mQuestionResId;
    private boolean mIsAnswerTrueOrFalse;

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
