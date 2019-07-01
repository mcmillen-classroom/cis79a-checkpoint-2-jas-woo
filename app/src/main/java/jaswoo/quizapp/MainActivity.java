package jaswoo.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView mTextView;

    private Button mTrueButton;
    private Button mFalseButton;
    //private TextView mFeedback;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mHintButton;

    private Question[] mQuestions;
    private int mIndex;
    private int mScore;

    private Feedback[] mFeedbacks;
    private int mFIndex;
    private TextView mFeedback;
    private TextView mScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFeedback = (TextView) findViewById(R.id.feedback_textview);
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mHintButton = (Button) findViewById(R.id.hint_button);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mPreviousButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);

        //INITIALIZE AN ARRAY OF QUESTIONS
        mQuestions = new Question[5];
        mIndex = 0;
        mScore = 0;
        //INITIALIZE EACH SLOT IN THE ARRAY
        mQuestions[0] = new Question(R.string.question_1, R.string.question_1_hint, true);
        mQuestions[1] = new Question(R.string.question_2, R.string.question_2_hint, false);
        mQuestions[2] = new Question(R.string.question_3, R.string.question_3_hint, false);
        mQuestions[3] = new Question(R.string.question_4, R.string.question_4_hint, true);
        mQuestions[4] = new Question(R.string.question_5, R.string.question_5_hint, false);

        mTextView.setText(mQuestions[mIndex].getTextResId());

        mFeedbacks = new Feedback[5];
        mFIndex = 0;

        mFeedbacks[0] = new Feedback(R.string.feedback_1);
        mFeedbacks[1] = new Feedback(R.string.feedback_2);
        mFeedbacks[2] = new Feedback(R.string.feedback_3);
        mFeedbacks[3] = new Feedback(R.string.feedback_4);
        mFeedbacks[4] = new Feedback(R.string.feedback_5);

        mFeedback.setText(mFeedbacks[mFIndex].getFeedbackResId());
        mFeedback.setVisibility(View.GONE);

        mScoreTextView.setText(getString(R.string.score, mScore));
    }

    @Override
    public void onClick(View view)
    {

        if (view.getId() == R.id.true_button)
        {
            if (checkAnswer(true) == true)
            {
                mScore++;
                mScoreTextView.setText(getString(R.string.score, mScore));
            }
            mFeedback.setVisibility(View.VISIBLE);
        }
        else if (view.getId() == R.id.false_button)
        {
            if (checkAnswer(false))
            {
                mScore++;
                mScoreTextView.setText(getString(R.string.score, mScore));

            }
            mFeedback.setVisibility(View.VISIBLE);
        }
        else if (view.getId() == R.id.next_button)
        {
            mFeedback.setVisibility(View.GONE);
            //CHANGE TO NEXT QUESTION
            //INCREMENT THE INDEX BY ONE

            mIndex++;
            mFIndex++;

            if (mIndex < mQuestions.length)
            {
                //DO IF STATEMENT HERE:
                //IF mINDEX IS GREATER THAN OR EQUAL TO mQUESTIONS.LENGTH
                // set mIndex to 0
                // -or- end the quiz
                //CREATE A SCORE COUNTER
                // could hide views at end of Quiz


                //CHANGE TEXT IN VIEW
                mTextView.setText(mQuestions[mIndex].getTextResId());
                mFeedback.setText(mFeedbacks[mFIndex].getFeedbackResId());
            }
            else
            {
                mTextView.setText(R.string.question_end);
                mFeedback.setVisibility(view.VISIBLE);
                mFeedback.setText(R.string.feedback_end);
            }
        }
        else if (view.getId() == R.id.previous_button)
        {
            mFeedback.setVisibility(View.GONE);

            //CHANGE TO PREVIOUS QUESTION
            mIndex--;
            mFIndex--;


            if (mIndex < 0)
            {
                mIndex = 0;
                mFIndex = 0;
            }
            mTextView.setText(mQuestions[mIndex].getTextResId());
            mFeedback.setText(mFeedbacks[mFIndex].getFeedbackResId());

        }
        else if (view.getId() == R.id.hint_button)
        {
            Toast myToast = Toast.makeText(this, mQuestions[mIndex].getHintTextResId(), Toast.LENGTH_LONG);
            myToast.show();
        }
    }

    public boolean checkAnswer(boolean userInput)
    {
        if (mQuestions[mIndex].getAnswer() == userInput)
        {
            Toast myToast = Toast.makeText(this, "YOU ARE CORRECT!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            return true;
        }
        else
        {
            Toast myToast = Toast.makeText(this, "You are incorrect😟", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 0);
            myToast.show();
            return false;
        }
    }
}

