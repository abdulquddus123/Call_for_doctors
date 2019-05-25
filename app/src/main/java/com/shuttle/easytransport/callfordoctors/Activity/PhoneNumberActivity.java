package com.shuttle.easytransport.callfordoctors.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.shuttle.easytransport.callfordoctors.MainActivity;
import com.shuttle.easytransport.callfordoctors.R;

public class PhoneNumberActivity extends AppCompatActivity {
    private EditText editTextMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);



        editTextMobile = findViewById(R.id.editTextMobile);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent = new Intent(PhoneNumberActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phoneNumber", "+880"+mobile);
                startActivity(intent);
            }
        });
    }
    }

