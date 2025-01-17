package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Key for saving the state of the TextView
    private static final String TEXT_ON_PROGRESS = "currentSleep";
    private static final String TEXT_STATE = "currentText";

    // The TextView where we wil show results
    private TextView mTextView = null;
    private TextView mTextViewProgress = null;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        mTextView= findViewById (R.id.textView1);
        mTextViewProgress= findViewById (R.id.textView2);
        mProgressBar= findViewById (R.id.progressBar);
        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mTextViewProgress.setText (savedInstanceState.getString (TEXT_ON_PROGRESS));
        }
    }
    /**`
     * Handles the onCLick for the "Start Task" button. Launches the AsyncTask
     * which performs work off of the UI thread.
     *
     * @param view The view (Button) that was clicked.
     */
    public void startTask(View view) {
        // Put a message in the text view
        mTextView.setText(R.string.start_task);

        // Start the AsyncTask.
        new SimpleAsyncTask(mTextView,mTextViewProgress,mProgressBar).execute();
    }
    /**
     * Saves the contents of the TextView to restore on configuration change.
     * @param outState The bundle in which the state of the activity is saved
     * when it is spontaneously destroyed.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}
