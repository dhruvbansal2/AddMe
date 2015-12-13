package com.example.dhruvbansal.myapplication;



        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;

        import com.parse.ParseUser;

/**
 * Created by Varun on 12/5/2015.
 */
public class DispatchActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(ParseUser.getCurrentUser()!=null){
            startActivity(new Intent(this, MainActivity.class));
        }else{
            startActivity(new Intent(this, SignUpOrLoginActivity.class));
        }
    }
}
