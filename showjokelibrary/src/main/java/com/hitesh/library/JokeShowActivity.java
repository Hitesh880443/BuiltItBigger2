package com.hitesh.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class JokeShowActivity extends AppCompatActivity {

    private static final String INTENT_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_show);
        if (!getIntent().getStringExtra(INTENT_JOKE).isEmpty()) {
            String joke = getIntent().getStringExtra(INTENT_JOKE);
            TextView textViewJoke = (TextView) findViewById(R.id.jokeText);
            textViewJoke.setText(joke);
        }
    }
}
