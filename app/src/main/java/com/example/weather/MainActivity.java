package com.example.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private static final String TAG = "MainActivity.java";
    private TextView tv_NewUser;

    SharedPreferences sharedPreferences;
    public static final String MY_GLOBAL_PREFS ="MyPrefs";
    public static final String MY_USERNAME ="MyUserName";
    public static final String MY_PASSWORD ="MyPassword";

    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_NewUser = (TextView) findViewById(R.id.textView_Newuser);
        tv_NewUser.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event)
    {
        Log.v(TAG,"Touch Start");
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        return true;
    }

    public void onClick(View v)
    {
        final EditText etPassword = (EditText)findViewById(R.id.editText_Password);
        final EditText etUsername = (EditText)findViewById(R.id.editText_Username);

        Log.v(TAG,"Login with:" + etUsername.getText().toString() + "'" + etPassword.getText().toString());
        //if(isValidUsername(etUsername.getText().toString()) && isValidPassword(etPassword.getText().toString()))
        if (isValidUser(etUsername.getText().toString(),etPassword.getText().toString()))
        {
            Intent intent= new Intent(MainActivity.this, Main3Activity.class);
            Toast.makeText(this, "Valid User", Toast.LENGTH_LONG).show();
            startActivity(intent);

        }
        else
        {
            Toast.makeText(MainActivity.this,"Invalid User",Toast.LENGTH_LONG).show();
        }

    }

    public boolean isValidUser(String userName,String password){
        UserData dbData= dbHandler.findUser(userName);
        Log.v(TAG, "Running Checks... ..."+dbData.getMyUserName()+": "+dbData.getMyPassword()
        +"vs"+userName+": "+password);
        if (dbData.getMyUserName().equals(userName)&&dbData.getMyPassword().equals(password)){
            return true;
        }
        return false;
    }

    //Shared Pref
    /*public boolean isValidPassword(String password)
    {
        sharedPreferences=getSharedPreferences(MY_GLOBAL_PREFS,MODE_PRIVATE);
        String SharedPassword= sharedPreferences.getString(MY_PASSWORD,"");
        if (SharedPassword.equals(password))
        {
            return  true;
        }
        return  false;
    }

    public boolean isValidUsername(String userName)
    {
        sharedPreferences=getSharedPreferences(MY_GLOBAL_PREFS,MODE_PRIVATE);
        String SharedUserName=sharedPreferences.getString(MY_USERNAME,"");

        if(SharedUserName.equals(userName))
        {
            return true;
        }
        return  false;

    }*/
}
