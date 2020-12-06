package org.techtown.team_project

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.telephony.SmsManager
import android.util.Log
import java.lang.Exception

class EmergencyService: Service() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {   // 시작
        when(intent?.action){
            "START_FOREGROUND" -> {
                Log.d(TAG, "포그라운드 서비스 시작")
                startForegroundService()    // 서비스 시작
            }
            "EMERGENCY" -> {
                Log.d(TAG, "응급버튼 누름")
                sendSMS("01071207426", "Test")
            }
        }
        return START_STICKY
    }

    private fun sendSMS(phoneNumber: String, message: String){      // 메시지를 보내느 코드
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun startForegroundService() {
        EmergencyNotification
        val notification = EmergencyNotification.createNotification(this)
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun stopForegroundService() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        Log.d(TAG, "EmergencyService 종료")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onLowMemory() {
        stopForegroundService()
        super.onLowMemory()
    }

    companion object{
        const val TAG = "EmergencyService"
        const val NOTIFICATION_ID = 20
    }
}