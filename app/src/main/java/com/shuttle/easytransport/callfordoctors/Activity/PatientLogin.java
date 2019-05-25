package com.shuttle.easytransport.callfordoctors.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.shuttle.easytransport.callfordoctors.MainActivity;
import com.shuttle.easytransport.callfordoctors.R;

import java.util.HashMap;
import java.util.Map;

public class PatientLogin extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextInputLayout emailTextInputLayout,passTextInputLayout;
    DatabaseReference mDatabaseReference;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);


        emailTextInputLayout=(TextInputLayout)findViewById(R.id.editText1);
        passTextInputLayout=(TextInputLayout)findViewById(R.id.editText2);
        progressDialog=new ProgressDialog(PatientLogin.this);

        mauth=FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");

    }

    //----SHOWING ALERT DIALOG FOR EXITING THE APP----
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(PatientLogin.this);
        builder.setMessage("Really Exit ??");
        builder.setTitle("Exit");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok",new MyListener());
        builder.setNegativeButton("Cancel",null);
        builder.show();

    }
    public class MyListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    }

    public void buttonIsClicked(View view){

        switch(view.getId()){

            case R.id.buttonSign:

                String email=emailTextInputLayout.getEditText().getText().toString().trim();
                String password=passTextInputLayout.getEditText().getText().toString().trim();

                //---CHECKING IF EMAIL AND PASSWORD IS NOT EMPTY----
                if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(PatientLogin.this, "Please Fill all blocks", Toast.LENGTH_SHORT).show();
                    return ;
                }
                progressDialog.setTitle("Logging in");
                progressDialog.setMessage("Please wait while we are checking the credentials..");
                progressDialog.setCancelable(false);
                progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                login_user(email,password);
                break;

            case R.id.buttonRegister:

                Intent intent=new Intent(PatientLogin.this,PatientRegistration.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private void login_user(String email, String password) {

        //---SIGN IN FOR THE AUTHENTICATE EMAIL-----
        mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            //---ADDING DEVICE TOKEN ID AND SET ONLINE TO BE TRUE---
                            //---DEVICE TOKEN IS USED FOR SENDING NOTIFICATION----
                            String user_id=mauth.getCurrentUser().getUid();
                            String token_id= FirebaseInstanceId.getInstance().getToken();
                            Map addValue = new HashMap();
                            addValue.put("device_token",token_id);
                            addValue.put("online","true");

                            //---IF UPDATE IS SUCCESSFULL , THEN OPEN MAIN ACTIVITY---
                            mDatabaseReference.child(user_id).updateChildren(addValue, new DatabaseReference.CompletionListener(){

                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                                    if(databaseError==null){

                                        //---OPENING MAIN ACTIVITY---
                                        Log.e("Login : ","Logged in Successfully" );
                                        Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(PatientLogin.this,HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(PatientLogin.this, databaseError.toString()  , Toast.LENGTH_SHORT).show();
                                        Log.e("Error is : ",databaseError.toString());

                                    }
                                }
                            });



                        }
                        else{
                            //---IF AUTHENTICATION IS WRONG----
                            Toast.makeText(PatientLogin.this, "Wrong Credentials" +
                                    "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
