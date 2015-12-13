package com.example.dhruvbansal.myapplication;

import android.os.Bundle;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

/**
 * Created by Varun on 12/5/2015.
 */
public class SignUpOrLoginActivity extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_or_login);
        Button LButton = (Button) findViewById(R.id.login);
        LButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignUpOrLoginActivity.this, LoginActivity.class));
            }
        });
        Button SButton = (Button) findViewById(R.id.signup);
        SButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SignUpOrLoginActivity.this, SignUpActivity.class));
            }
        });
    }

}
