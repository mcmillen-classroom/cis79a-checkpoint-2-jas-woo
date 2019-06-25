package jaswoo.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;

    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mFeedback;
    private Button mNextButton;
    private Button mPreviousButton;


    private Question[] mQuestions;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFeedback = (TextView) findViewById(R.id.feedback_textview);
        mNextButton = (Button) findViewById(R.id.next_button);
        mPreviousButton = (Button) findViewById(R.id.previous_button);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);

        //INITIALIZE AN ARRAY OF QUESTIONS
        mQuestions = new Question[5];
        mIndex = 0;
        //INITIAIZE EACH SLOT IN THE ARRAY
        mQuestions[0] = new Question(R.string.question_1, true);
        mQuestions[1] = new Question(R.string.question_2, false);
        mQuestions[2] = new Question(R.string.question_3, false);
        mQuestions[3] = new Question(R.string.question_4, true);
        mQuestions[4] = new Question(R.string.question_5, false);


        mTextView.setText(mQuestions[mIndex].getTextResId());


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.true_button && mQuestions[mIndex].getAnswer() == true) {
            Toast myToast = Toast.makeText(this, "YOU ARE CORRECT!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
        }
        else if (view.getId() == R.id.false_button && mQuestions[mIndex].getAnswer() == false) {
            Toast myToast = Toast.makeText(this, "YOU ARE CORRECT!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
        }
        else if (view.getId() == R.id.next_button) {

            //CHANGE TO NEXT QUESTION
        }
        else if (view.getId() == R.id.previous_button) {
            //CHANGE TO PREVIOUS QUESTION

        }
        else {
            Toast myToast = Toast.makeText(this, "You are incorrect😟", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
        }

    }
}
