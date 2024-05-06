package com.sample.thepeacifyapp

import android.app.Application
import com.google.firebase.FirebaseApp

class MyLoginFlowApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}