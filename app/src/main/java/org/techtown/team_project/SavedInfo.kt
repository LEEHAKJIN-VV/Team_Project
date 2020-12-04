package org.techtown.team_project

import android.app.Application

class SavedInfo:Application(){
    companion object{
        lateinit var prefs:PreferenceUtil
    }
    override fun onCreate(){
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}