package com.example.counterapp;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// The "view": builds the screen in Java, like a JFrame + JPanel.
public class MainActivity extends AppCompatActivity {

    private final Counter counter = new Counter();
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main panel (vertical)
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);

        // The number
        display = new TextView(this);
        display.setTextSize(64);
        display.setGravity(Gravity.CENTER);

        // Button row (horizontal)
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER);

        Button minus = new Button(this);
        minus.setText("-");
        Button plus = new Button(this);
        plus.setText("+");
        Button reset = new Button(this);
        reset.setText("Reset");

        row.addView(minus);
        row.addView(plus);
        row.addView(reset);

        root.addView(display);
        root.addView(row);

        // Listeners (like ActionListener in Swing)
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

    private void refresh() {
        display.setText(String.valueOf(counter.getValue()));
    }
}
