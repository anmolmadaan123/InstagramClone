package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("IZ2D20STUO8xzezHRZcwZnDUPZTBgVyF11KUXhyy")
                // if defined
                .clientKey("zWuo8SE4Fm9tKcZ5Rq0nNm1EKrleGWgeRd72kymw")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
