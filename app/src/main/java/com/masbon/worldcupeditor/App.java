package com.masbon.worldcupeditor;

import android.app.Application;

import com.masbon.worldcupeditor.di.AppComponent;
import com.masbon.worldcupeditor.di.DaggerAppComponent;
import com.masbon.worldcupeditor.di.modules.AppModule;

public class App extends Application {
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}