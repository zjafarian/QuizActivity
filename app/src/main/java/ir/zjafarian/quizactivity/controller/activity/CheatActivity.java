package ir.zjafarian.quizactivity.controller.activity;

import androidx.fragment.app.Fragment;

import ir.zjafarian.quizactivity.controller.fragment.CheatFragment;

public class CheatActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CheatFragment();
    }
}