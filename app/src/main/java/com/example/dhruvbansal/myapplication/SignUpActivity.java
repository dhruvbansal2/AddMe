package com.example.dhruvbansal.myapplication;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.parse.ParseUser;
        import com.parse.SignUpCallback;

        import java.text.ParseException;

/**
 * Created by Varun on 12/5/2015.
 */
public class SignUpActivity extends Activity {
    private EditText usernameView;
    private EditText passwordView;
    private EditText passwordAgainView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        passwordAgainView = (EditText) findViewById(R.id.passwordAgain);

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
                if (!isMatching(passwordView, passwordAgainView)) {
                    if (validationError) {
                        validationErrorMessage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMessage.append("enter the same password twice");
                }
                validationErrorMessage.append(".");
                if (validationError) {
                    Toast.makeText(SignUpActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }
                final ProgressDialog dlg = new ProgressDialog(SignUpActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Signing up. Please wait.");
                dlg.show();

                ParseUser user = new ParseUser();
                user.setUsername(usernameView.getText().toString());
                user.setPassword(passwordView.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(SignUpActivity.this, DispatchActivity.class);
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
    private boolean isMatching(EditText editText1, EditText editText2){
        if(editText1.getText().toString().equals((editText2.getText().toString()))){
            return true;
        }
        else{
            return false;
        }
    }
}
