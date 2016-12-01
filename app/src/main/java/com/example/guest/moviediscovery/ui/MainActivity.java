package com.example.guest.moviediscovery.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.moviediscovery.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.keywordEditText) EditText mKeywordEditText;
    @Bind(R.id.keywordSubmitButton) Button mKeywordSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mKeywordSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mKeywordSubmitButton) {
            String keyword = mKeywordEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
            intent.putExtra("keyword", keyword);
            startActivity(intent);
        }
    }
}
