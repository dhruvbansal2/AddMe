package com.example.dhruvbansal.myapplication;


import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

import org.w3c.dom.UserDataHandler;

import java.util.List;

/**
 * Created by VD on 10/22/2014.
 */
public class menu1_Fragment extends Fragment {
    View rootview;
    @Nullable
    private EditText username;
    public boolean test;
    public ParseUser searchUser;
    public TextView searchedU;
    public TextView searchedN;
    public ImageButton Twitter;
    public ImageButton Facebook;
    public ImageButton Instagram;
    public TextView email;
    public TextView age;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout, container, false);
        final Context context = this.getActivity();
        test=false;
        username = (EditText) rootview.findViewById(R.id.SearchUserName);
        searchedU = (TextView) rootview.findViewById(R.id.SearchedUser);
        searchedN = (TextView) rootview.findViewById(R.id.searchedName);
        email = (TextView) rootview.findViewById(R.id.email);
        age = (TextView) rootview.findViewById(R.id.age);
        Button SButton = (Button) rootview.findViewById(R.id.SButton);
        Twitter =(ImageButton) rootview.findViewById(R.id.twitter);
        Facebook = (ImageButton) rootview.findViewById(R.id.facebook);
        Instagram = (ImageButton) rootview.findViewById(R.id.instagram);
        SButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseUser> query = ParseUser.getQuery();
                //query.whereEqualTo(username.getText().toString(), ParseUser.getQuery());
                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> list, ParseException e) {
                        test=false;
                        if (e == null) {
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).getUsername().compareTo(username.getText().toString()) == 0) {
                                    searchUser = list.get(i);
                                    searchedU.setText(searchUser.getUsername());
                                    searchedN.setText(searchUser.getString("Name"));
                                    email.setText(searchUser.getEmail());
                                    age.setText(searchUser.getString("Age"));
                                    Twitter.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Toast toast = Toast.makeText(context, "You are now following " + searchUser.getUsername() + " on Twitter!", Toast.LENGTH_LONG);
                                            TextView a = (TextView) toast.getView().findViewById(android.R.id.message);
                                            if (a != null) a.setGravity(Gravity.CENTER);
                                            toast.show();
                                        }
                                    });
                                    Facebook.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast toast = Toast.makeText(context, "You are now following " + searchUser.getUsername() + " on Facebook!", Toast.LENGTH_LONG);
                                            TextView a = (TextView) toast.getView().findViewById(android.R.id.message);
                                            if (a != null) a.setGravity(Gravity.CENTER);
                                            toast.show();
                                        }
                                    });
                                    Instagram.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast toast = Toast.makeText(context, "You are now following " + searchUser.getUsername() + " on Instagram!!", Toast.LENGTH_LONG);
                                            TextView a = (TextView) toast.getView().findViewById(android.R.id.message);
                                            if (a != null) a.setGravity(Gravity.CENTER);
                                            toast.show();
                                        }
                                    });

                                    test = true;
                                    break;
                                }
                            }
                            if (!test) {
                                Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show();
                                searchUser = null;
                            }
                        }
                        else {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
            }
        });
        return rootview;
    }
    //public ParseUser getSearchUser(){
       // return searchUser;
    //}
}