package ir.zjafarian.quizactivity.controller.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.controller.fragment.QuizFragment;


public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment== null){
            QuizFragment quizFragment = new QuizFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container,quizFragment)
                    .commit();
        }

    }

}