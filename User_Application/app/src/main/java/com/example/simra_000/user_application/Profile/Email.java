package com.example.simra_000.user_application.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.simra_000.user_application.R;

public class Email extends AppCompatActivity {
    RadioButton user,vendor;
    Button submit;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        user=(RadioButton)findViewById(R.id.user);
        vendor=(RadioButton)findViewById(R.id.vendor);
        email=(EditText)findViewById(R.id.email);
        submit=(Button)findViewById(R.id.submit);

    }
}
