package ir.zjafarian.quizactivity.controller.activity;

import androidx.fragment.app.Fragment;

import ir.zjafarian.quizactivity.controller.fragment.QuizFragment;


public class QuizActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new QuizFragment();
    }

}