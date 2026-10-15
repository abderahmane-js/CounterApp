package com.example.counterapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_COUNT = "counter_value";

    private Counter counter;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int initialValue = savedInstanceState == null
                ? 0
                : savedInstanceState.getInt(STATE_COUNT, 0);
        counter = new Counter();
        if (initialValue != 0) {
            // Restore the value using the model's public API.
            counter.reset();
            for (int i = 0; i < Math.abs(initialValue); i++) {
                if (initialValue > 0) {
                    counter.increment();
                } else {
                    counter.decrement();
                }
            }
        }

        int padding = dp(24);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(padding, padding, padding, padding);
        root.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        TextView title = new TextView(this);
        title.setText("Counter");
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);
        root.addView(title, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        display = new TextView(this);
        display.setTextSize(64);
        display.setGravity(Gravity.CENTER);
        display.setPadding(0, dp(24), 0, dp(24));
        display.setContentDescription("Counter value");
        root.addView(display, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);

        Button minus = new Button(this);
        minus.setText("−");
        minus.setTextSize(24);
        minus.setContentDescription("Decrease count");

        Button plus = new Button(this);
        plus.setText("+");
        plus.setTextSize(24);
        plus.setContentDescription("Increase count");

        Button reset = new Button(this);
        reset.setText("Reset");
        reset.setContentDescription("Reset count to zero");

        row.addView(minus, new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        ));
        row.addView(plus, new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        ));
        row.addView(reset, new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
        ));

        root.addView(row, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView hint = new TextView(this);
        hint.setText("Use + and − to change the count");
        hint.setTextSize(14);
        hint.setGravity(Gravity.CENTER);
        hint.setPadding(0, dp(16), 0, 0);
        root.addView(hint, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        minus.setOnClickListener(v -> {
            counter.decrement();
            refresh();
        });

        plus.setOnClickListener(v -> {
            counter.increment();
            refresh();
        });

        reset.setOnClickListener(v -> {
            counter.reset();
            refresh();
        });

        setContentView(root);
        refresh();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_COUNT, counter.getValue());
        super.onSaveInstanceState(outState);
    }

    private void refresh() {
        display.setText(String.valueOf(counter.getValue()));
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
