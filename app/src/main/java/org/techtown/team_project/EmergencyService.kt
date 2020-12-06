package org.techtown.team_project

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.telephony.SmsManager
import android.util.Log
import java.lang.Exception

class EmergencyService: Service() {
    private var protectorNumberList: ArrayList<String> = ArrayList()    // 보호자 전화번호 리스트

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {   // 시작
        when(intent?.action){
            "START_FOREGROUND" -> {
                Log.d(TAG, "포그라운드 서비스 시작")
                startForegroundService()    // 서비스 시작
            }
            "EMERGENCY" -> {
                Log.d(TAG, "응급버튼 누름")
                getProtectorNumberList()
                sendSMS(protectorNumberList, "저의 위치는 ${MainActivity.myLocation} ${MainActivity.smsMessage}")
            }
        }
        return START_STICKY
    }

    private fun getProtectorNumberList(){    // 보낼 메시지를 가져오는 메소드 (보호자 이름과 현재 주소를 가져와야함)
        for(i in 0..4){
            if(SavedInfo.prefs.getString("protector number$i","")!="")
                protectorNumberList.add(SavedInfo.prefs.getString("protector number$i",""))
        }
    }

    private fun sendSMS(phoneNumberList: ArrayList<String>, message: String){      // 메시지를 보내느 코드
        try {
            for (number in phoneNumberList){
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(number, null, message,null, null)
            }
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