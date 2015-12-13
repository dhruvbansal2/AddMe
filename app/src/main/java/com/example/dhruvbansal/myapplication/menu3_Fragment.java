package com.example.dhruvbansal.myapplication;

import android.accounts.Account;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
//import com.parse.ParseFacebookUtils;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import android.util.Log;

import java.net.CookieHandler;
import java.net.CookieManager;

/**
 * Created by Z0NEN on 10/22/2014.
 */
public class menu3_Fragment extends Fragment {
    View rootview;
    @Nullable
    public EditText name;
    public EditText age;
    public EditText email;
    public ParseUser currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu3_layout, container, false);
        final Context context = this.getActivity();
        Button logout =(Button) rootview.findViewById(R.id.logout);
        Button submit = (Button) rootview.findViewById(R.id.update);
        Button tweet_Login=(Button) rootview.findViewById(R.id.twitter_login_button);
        Button tweet_Logout = (Button) rootview.findViewById(R.id.twitter_logout_button);
        //Button face_Login = (Button) rootview.findViewById(R.id.facebook_login_button);
        //Button face_Logout = (Button) rootview.findViewById(R.id.facebook_logout_button);
        name = (EditText) rootview.findViewById(R.id.fullName);
        age  = (EditText) rootview.findViewById(R.id.Age);
        email = (EditText) rootview.findViewById(R.id.email);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                if(ParseUser.getCurrentUser()==null) {
                    Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(context, DispatchActivity.class));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.getCurrentUser().setEmail(email.getText().toString());
                ParseUser.getCurrentUser().saveInBackground();
                ParseUser.getCurrentUser().put("Name", name.getText().toString());
                ParseUser.getCurrentUser().saveInBackground();
                ParseUser.getCurrentUser().put("Age", age.getText().toString());
                ParseUser.getCurrentUser().saveInBackground();
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                email.setText("");
                name.setText("");
                age.setText("");
                //Toast.makeText(context, email.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        tweet_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser = ParseUser.getCurrentUser();
                    ParseTwitterUtils.link(currentUser, context, new SaveCallback() {
                        @Override
                        public void done(ParseException ex) {
                            if (ParseTwitterUtils.isLinked(currentUser)) {
                                Toast.makeText(context, "You have been logged in to Twitter!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


        });
        tweet_Logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ParseTwitterUtils.unlinkInBackground(ParseUser.getCurrentUser(), new SaveCallback() {
                    @Override
                    public void done(ParseException ex) {
                        if (ex == null) {
                            Toast.makeText(context, "Logged out of Twitter", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        /*face_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ParseFacebookUtils.isLinked(ParseUser.getCurrentUser())) {
                    ParseFacebookUtils.linkWithReadPermissionsInBackground(ParseUser.getCurrentUser(), this, , new SaveCallback() {
                        @Override
                        public void done(ParseException ex) {
                            if (ParseFacebookUtils.isLinked(ParseUser.getCurrentUser())) {
                                Log.d("MyApp", "Woohoo, user logged in with Facebook!");
                            }
                        }
                    });
                }
            }
        });*/


        return rootview;
    }
    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }*/
}