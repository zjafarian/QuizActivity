package ir.zjafarian.quizactivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    private Button mButton_true;
    private Button mButton_false;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setById();
        lisenerClick();


    }


    private void setById() {
        mButton_true = findViewById(R.id.btn_correct);
        mButton_false = findViewById(R.id.btn_in_correct);
        mTextView = findViewById(R.id.text_question);
    }

    private void lisenerClick() {
        mButton_true.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Drawable icon = getResources().getDrawable(R.drawable.check);
                TextView message;
                message = findViewById(R.id.true_message);


                //Toast toast = Toast.makeText(QuizActivity.this, message.getText(),


                //toa.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                        //0);
                //toast.getView().setBackground(icon);
                //toast.show();
                //mTextView.setTextColor(Color.GREEN);
            }


        });

        mButton_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable icon = getResources().getDrawable(R.drawable.close_circle);
                TextView message;
                message = findViewById(R.id.false_message);
                message.isCursorVisible();

                Toast toast = Toast.makeText(QuizActivity.this, message.getText(),
                        Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0,
                        40);
                toast.getView().setBackground(icon);
                toast.show();
                mTextView.setTextColor(Color.RED);

            }
        });
    }


}