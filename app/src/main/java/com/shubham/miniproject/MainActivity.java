package com.shubham.miniproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        String s=myIntent.getStringExtra("abc");
        String f=myIntent.getStringExtra("type");
        int n=Integer.parseInt(s);
        int fl=Integer.parseInt(f);
        setContentView(new CustomView(this,n,fl));
    }
}
