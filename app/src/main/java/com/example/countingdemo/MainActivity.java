package com.example.countingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextView = findViewById(R.id.text_view);
}

@Override
protected void onResume() {
    super.onResume();
    setValue(1500);
}
private void setValue(final int value) {
    mTextView.post(new Runnable() {
        @Override
        public void run() {
            mTextView.postOnAnimation(
                    new CountingHelper(0, // initial value
                            value, // final value
                            5000, // duration in milliseconds
                            mTextView));
        }
    });
}
private TextView mTextView;
}
