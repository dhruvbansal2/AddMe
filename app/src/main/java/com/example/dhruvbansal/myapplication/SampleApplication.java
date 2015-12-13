package com.example.dhruvbansal.myapplication;



        import android.app.Application;
        import android.content.Context;

        import com.parse.Parse;
        //import com.parse.ParseFacebookUtils;
        import com.parse.ParseTwitterUtils;
        //import io.fabric.sdk.android.Fabric;


public class SampleApplication extends Application{

    public void onCreate(){
        super.onCreate();
        Parse.initialize(this, "oGWE0hthOZLlmtC102yGfg0rixMYedeFoGM5PQxM", "NEkFoZuCMvnCXM6ShNXWmUgAspE4CbqLFAAMWOyb");
        ParseTwitterUtils.initialize("xoSkk80C4IoIXdoVAWWPLNzoU", "nLuP9JcmfIOVwl6NtMW87eXewfSu7YwVNX3Vilcl9UUPLVwaF0");
        //ParseFacebookUtils.initialize(this);

    }
}
