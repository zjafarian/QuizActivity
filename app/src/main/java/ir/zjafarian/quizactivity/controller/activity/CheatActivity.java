package ir.zjafarian.quizactivity.controller.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ir.zjafarian.quizactivity.R;
import ir.zjafarian.quizactivity.controller.fragment.CheatFragment;

public class CheatActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_cheat);
        CheatFragment cheatFragment = new CheatFragment();
        if (fragment == null){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container_cheat,cheatFragment)
                    .commit();
        }

       /* Bundle bundle = new Bundle();
        bundle.putString("fdfd","hi");
        cheatFragment.setArguments(bundle);*/


    }
}