package com.example.counterapp;

// The "model": holds the data, knows nothing about the screen.
public class Counter {

    private int value = 0;

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public void reset() {
        value = 0;
    }

    public int getValue() {
        return value;
    }
}
