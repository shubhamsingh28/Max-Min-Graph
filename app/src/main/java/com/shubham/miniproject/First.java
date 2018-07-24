package com.shubham.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class First extends Activity implements View.OnClickListener{
    Button b1,b2;
    EditText e;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        b1=(Button)findViewById(R.id.min);
        b2=(Button)findViewById(R.id.max);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        e=(EditText)findViewById(R.id.nodes);
    }

    @Override
    public void onClick(View view) {
        if(view==b1){
            s=e.getText().toString().trim();
            if (TextUtils.isEmpty(e.getText().toString())) {
                e.setError("PLease enter number of nodes");
                return;
            }
            Intent i=new Intent(this,MainActivity.class);
            i.putExtra("abc",s);
            i.putExtra("type","1");
            startActivity(i);
        }
        if(view==b2){
            s=e.getText().toString().trim();
            if (TextUtils.isEmpty(e.getText().toString())) {
                e.setError("PLease enter number of nodes");
                return;
            }
            Intent i=new Intent(this,MainActivity.class);
            i.putExtra("abc",s);
            i.putExtra("type","2");
            startActivity(i);

        }
    }
}
