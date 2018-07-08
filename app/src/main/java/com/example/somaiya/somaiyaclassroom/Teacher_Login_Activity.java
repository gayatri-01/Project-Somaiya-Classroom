package com.example.somaiya.somaiyaclassroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Teacher_Login_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static int backpress = 1;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private CardView mCourse;
    private CardView mSyllabus;
    private CardView mPrevYears;
    private CardView mEasySol;
    private CardView Calendar;
    private CardView notice;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__login);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        mAuth = FirebaseAuth.getInstance();

        mCourse = (CardView) findViewById(R.id.courseMaterial);
        mSyllabus = (CardView) findViewById(R.id.syllabus_tch);
        mPrevYears = (CardView) findViewById(R.id.prevYears_tch);
        mEasySol = (CardView) findViewById(R.id.easySol_tch);
        notice = (CardView) findViewById(R.id.notice);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        Calendar=(CardView) findViewById(R.id.Calendar);
        setSupportActionBar(mToolbar);



        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mtoggle = new ActionBarDrawerToggle(this, mdrawerlayout, R.string.Open, R.string.Close);
        mdrawerlayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(true);



        mCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitycourseMaterial();
            }
        });
        mSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySyllabus();
            }
        });
        mPrevYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityprevYear();
            }
        });
        mEasySol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityeasySol();
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice(v);
            }
        });
        Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalendar(v);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void notice(View v) {
        Uri uri = Uri.parse("https://kjsce.somaiya.edu/kjsce/academics/");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }


    public void openCalendar(View v) {
        Uri uri = Uri.parse("https://www.google.com/calendar");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    public void openActivitycourseMaterial() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, courseMaterial.class);
        this.startActivity(main_intent);
    }

    public void openActivitySyllabus() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, Syllabus.class);
        startActivity(main_intent);
    }

    public void openActivityprevYear() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, PreviousPapers.class);
        startActivity(main_intent);
    }

    public void openActivityeasySol() {
        Intent main_intent = new Intent(Teacher_Login_Activity.this, Easysolution.class);
        startActivity(main_intent);
    }


    /*public void LogOut() {
        //Firebase SignOut
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        openMainSignIn(null);
                    }
                });
    }

    public void openMainSignIn(FirebaseUser user) {
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }*/


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    public void openMainSignInStudent(FirebaseUser user){
        finish();
        startActivity(new Intent(this, HomeActivity.class));
    }


    public void onNavigationItemSelected(MenuItem item) {
        logout();
    }

    public void notif(MenuItem item) {
        Uri uri = Uri.parse("https://www.google.com/calendar");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    public void settings(MenuItem item) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "With concern to FCP");

        startActivity(emailIntent);
    }

    private void logout() {
        finish();
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        openMainSignInStudent(null);
                    }
                });
    }

};




















