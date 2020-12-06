package org.techtown.team_project

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

object EmergencyNotification {
    private val CHANNEL_ID = "ForegroundServiceChannel"

    fun createNotification(context: Context): Notification {
        val notificationIntent: Intent = Intent(context, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context,
                0, notificationIntent, 0)

        val emergencyIntent = Intent(context, EmergencyService::class.java)
        emergencyIntent.action = "EMERGENCY"
        val emergencyPendingIntent = PendingIntent.getService(
                context, 0, emergencyIntent, 0)

        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Foreground Service")  // 휴대폰 상당(제일 최상단)에 뜨는 메시지
                .setContentText("응급대기")
                .setSmallIcon(R.mipmap.ic_launcher_app_icon)
                .setOngoing(true) // true 일 경우 알림 리스트에서 클릭하거나 좌우로 드래그해도 안 사라짐
                .addAction(NotificationCompat.Action(android.R.drawable.alert_dark_frame,
                        "EMERGENCY!!", emergencyPendingIntent))
                .setContentIntent(pendingIntent)
                .build()

        // Channel 만들기 (Oreo 이상부터)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel: NotificationChannel = NotificationChannel(
                    CHANNEL_ID,
                    "Emergency Service Channel",   // 채널 표시명
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
        return notification
    }
}