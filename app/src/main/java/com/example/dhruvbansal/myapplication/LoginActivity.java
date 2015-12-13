package com.example.dhruvbansal.myapplication;

import android.content.Intent;


        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.parse.LogInCallback;
        import com.parse.ParseException;
        import com.parse.ParseUser;
        import com.parse.SignUpCallback;

/**
 * Created by Varun on 12/5/2015.
 */
public class LoginActivity extends Activity{

    private EditText usernameView;
    private EditText passwordView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);

        Button SButton = (Button) findViewById(R.id.action_button);
        SButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean validationError = false;
                StringBuilder validationErrorMessage = new StringBuilder("Please ");
                if (isEmpty(usernameView)) {
                    validationError = true;
                    validationErrorMessage.append("enter a username");
                }
                if (isEmpty(passwordView)) {
                    if (validationError) {
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter a password");
                }
                validationErrorMessage.append(".");

                if (validationError) {
                    Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Logging in. Please wait.");
                dlg.show();

                ParseUser.logInInBackground(usernameView.getText().toString(), passwordView.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, DispatchActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
    private boolean isEmpty(EditText etext) {
        if(etext.getText().toString().trim().length()>0){
            return false;
        }
        else{
            return true;
        }
    }
}

