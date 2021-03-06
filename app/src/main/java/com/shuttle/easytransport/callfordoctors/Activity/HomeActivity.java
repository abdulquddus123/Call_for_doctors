package com.shuttle.easytransport.callfordoctors.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.shuttle.easytransport.callfordoctors.Fragment.MyFragmentPagerAdapter;
import com.shuttle.easytransport.callfordoctors.R;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mauth;
    ViewPager mviewPager;
    MyFragmentPagerAdapter mFragmentPagerAdapter;
    TabLayout mtabLayout;
    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mauth=FirebaseAuth.getInstance();

        mviewPager=(ViewPager)findViewById(R.id.viewPager);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("doctor");
        //---ADDING ADAPTER FOR FRAGMENTS IN VIEW PAGER----
        mFragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager());
        mviewPager.setAdapter(mFragmentPagerAdapter);

        //---SETTING TAB LAYOUT WITH VIEW PAGER
        mtabLayout=(TabLayout)findViewById(R.id.tabLayout);
        mtabLayout.setupWithViewPager(mviewPager);


    }

    //----SHOWING ALERT DIALOG FOR EXITING THE APP----
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Really Exit ??");
        builder.setTitle("Exit");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok",new HomeActivity.MyListener());
        builder.setNegativeButton("Cancel",null);
        builder.show();

    }
    public class MyListener implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    }

    //---IF USER IS NULL , THEN GOTO LOGIN PAGE----
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mauth.getCurrentUser();
        if(user==null){
            startfn();
        }
        else{
            //---IF LOGIN , ADD ONLINE VALUE TO TRUE---
       //     mDatabaseReference.child(user.getUid()).child("online").setValue("true");

        }
    }

    @Override
    protected void onStop() {
        super.onStop();

     /* //-----for disabling online function when appliction runs in background----
        FirebaseUser user=mauth.getCurrentUser();
        if(user!=null){
            mDatabaseReference.child(user.getUid()).child("online").setValue(ServerValue.TIMESTAMP);
        }
        */
    }

    //---CREATING OPTION MENU---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.settings){
            Intent intent=new Intent(HomeActivity.this,SettingActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.allUsers){
            Intent intent=new Intent(HomeActivity.this,UsersActivity.class);
            startActivity(intent);
        }

        //---LOGGING OUT AND ADDING TIME_STAMP----
        if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(HomeActivity.this,LoginViewActivity.class);
            startActivity(intent);
            finish();
//            mDatabaseReference.child(mauth.getCurrentUser().getUid()).child("online").setValue(ServerValue.TIMESTAMP).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//
//                        FirebaseAuth.getInstance().signOut();
//                        startfn();
//                    }
//                    else{
//                        Toast.makeText(HomeActivity.this, "Try again..", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
        return true;
    }

    //--OPENING LOGIN ACTIVITY--
    private void startfn(){
        Intent intent = new Intent(HomeActivity.this,PatientLogin.class);
        startActivity(intent);
        finish();
    }
}
