package com.example.demoapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFireBaseService : FirebaseMessagingService() {
    val TAG = "FirebaseMessagingService"
    val NOTIFICATION_CHANNEL_ID = "10001"


    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification!!.title
        val message = remoteMessage.notification!!.body
        Log.e(TAG, "notification_title: $title")
        Log.e(TAG, "notification_message: $message")


        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)


        val resultIntent = Intent(this,MainActivity::class.java)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val resultPendingIntent = PendingIntent.getActivity(
            this,
            0,
            resultIntent,
            PendingIntent.FLAG_MUTABLE
        )

        mBuilder.setContentIntent(resultPendingIntent)
        val mNotificationId = System.currentTimeMillis().toInt()
        val mNotifyMgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mNotifyMgr.createNotificationChannel(notificationChannel)
        }
        mNotifyMgr.notify(mNotificationId, mBuilder.build())



    }

    override fun onNewToken(token: String) {
        Log.e(javaClass.simpleName, "onNewToken: $token")
        super.onNewToken(token)
    }
}





