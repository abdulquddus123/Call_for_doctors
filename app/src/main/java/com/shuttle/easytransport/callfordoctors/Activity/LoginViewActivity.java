package com.shuttle.easytransport.callfordoctors.Activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shuttle.easytransport.callfordoctors.R;

import java.security.Permissions;

public class LoginViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);




    }

    public void DoctorLogin(View view) {
        Intent intent=new Intent(LoginViewActivity.this,DortorLogin.class);
        startActivity(intent);
    }

    public void PatientLogin(View view) {
        Intent intent=new Intent(LoginViewActivity.this,PatientLogin.class);
        startActivity(intent);
    }

    public void CreateDoctorAccount(View view) {
        Intent intent=new Intent(LoginViewActivity.this,DortorRegistration.class);
        startActivity(intent);
//        Intent intent=new Intent(LoginViewActivity.this,PhoneNumberActivity.class);
//        startActivity(intent);
    }

    public void CreatePatientAccount(View view) {
//        Intent intent=new Intent(LoginViewActivity.this,PhoneNumberActivity.class);
//        startActivity(intent);
        Intent intent=new Intent(LoginViewActivity.this,PatientRegistration.class);
        startActivity(intent);

    }
    //system permission



}
